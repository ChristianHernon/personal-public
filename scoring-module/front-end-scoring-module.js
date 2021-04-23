// jshint esversion: 6
String.prototype.getScore = function () {
    if (this.toLowerCase().includes('disqualify')) return 'dq';
    let regex = /\((\d+)\s\w+\)/;
    let matches;
    return (!!(matches = regex.exec(this)) && matches.length > 1) ? Number(matches[1]) : 0;
};

Array.prototype.addItem = function (item) {
    this.push(item);
    return this;
};

$.fn.extend({
    createObserver: function (callback, config) {
        if (this.length === 1) {
            config = config || {
                childList: true
            };
            if (!!!window.systemObservers || !Array.isArray(window.systemObservers)) window.systemObservers = [];
            var newObserver = new MutationObserver(callback);
            newObserver.observe(this[0], config);
            window.systemObservers.push(newObserver);
            return this;
        } else if (this.length > 1) {
            console.warn('Observer skipped, too many elements handed in. Bind to a singular element instead');
        } else if (this.length === 0) {
            console.warn('Observer skipped, no element found');
        }
    },
    getItemsWithPageIds: function (selector) {
        this.each(function () {
            this.querySelector(selector).dataset.pageid = this.dataset.pageid;
        });
        return this.find(selector);
    },
    filterByPage: function (pageid) {
        return this.filter(function () {
            return this.dataset.pageid == pageid;
        });
    },
    getConfiguredInputs: function (type) {
        // expecting types of [radio, checkbox, text]
        const inputSelector = 'input[type=####]'.replace('####', type);

        this.each(function (index, fieldSection) {
            if (fieldSection.dataset.processed) return;

            let highScoreIndex = null;
            let highScore = 0;
            let description = fieldSection.querySelector('.details');
            let isBonus = (!!description) ? description.innerText.toLowerCase().contains('bonus') : false;
            let inputCollection = fieldSection.querySelectorAll(inputSelector);

            inputCollection.forEach((input, index) => {
                let score;

                if (type === 'text') score = (!!input.previousElementSibling) ? input.previousElementSibling.innerText.getScore() : 0;
                else score = input.nextElementSibling.innerText.getScore();

                if (!!score) {
                    if (type === 'radio' && score > highScore) {
                        highScoreIndex = index;
                        highScore = score;
                    }
                    input.dataset.score = score;
                }

                if (isBonus) input.dataset.bonus = isBonus;
                input.dataset.pageid = fieldSection.dataset.pageid;
            });

            if (type === 'radio' && highScoreIndex !== null) inputCollection[highScoreIndex].dataset.optionMax = true;

            fieldSection.dataset.processed = true;
        });

        return this.find(inputSelector);
    },
    notIgnoreOnSubmit: function () {
        return this.filter((index, value) => !$(value).parents('.fieldSection').hasClass('ignoreOnSubmit'));
    },
    filterForScoring: function () {
        return this.filter((i, v) => (v.type === 'text' && !!v.value) || (v.type !== 'text' && v.checked));
    },
    notHiddenPage: function () {
        return this.filter((index, value) => !$(value).parents('.fieldSection').hasClass('hiddenPage'))
    },
});

(function () {
    'use strict';
    const setupScoring = () => {
        let $inputs = {
            radio: $('#submissionForm .radioList').getConfiguredInputs('radio'),
            checkbox: $('#submissionForm .checkboxList').not('.disqualify').getConfiguredInputs('checkbox'),
            text: $('#submissionForm .text.scored').getConfiguredInputs('text'),
            all: function () {
                return this.radio.add(this.checkbox).add(this.text);
            },
        };

        const $pageScores = $('#submissionForm .page-score').getItemsWithPageIds('input[type=text]');
        const $pageMaxes = $('#submissionForm .page-max').getItemsWithPageIds('input[type=text]');
        const $pagePercents = $('#submissionForm .page-percent').getItemsWithPageIds('input[type=text]');
        const $applicationScore = $('#submissionForm .application-score input[type=text]');
        const $dqMarkers = $('#submissionForm .disqualify input[type=checkbox]');

        const calcPercent = (points, total) => {
            let percent = Math.round((Number(points) / Number(total)) * 100);
            return (isNaN(percent)) ? 0 : percent;
        };

        const totalScoreFromInputs = inputCollection => {
            return inputCollection.map(function () {
                return (isFinite(this.dataset.score)) ? Number(this.dataset.score) : 0;
            }).toArray().reduce((total, item) => total + item, 0);
        };

        const checkForDisqualify = () => {
            let dq = ($inputs.all().filter('[data-score=dq]:visible').filterForScoring().length > 0);
            $dqMarkers.notHiddenPage().prop('checked', dq).change();
        };

        const handleInputChange = () => {
            $pageScores.add($pagePercents).add($pageMaxes).add($applicationScore).each(function () {
                this.readOnly = true;
                this.tabIndex = -1;
            });
            $pageScores.filter(':visible').val(
                totalScoreFromInputs($inputs.all().filter(':visible').filterForScoring())
            ).change();
            updatePageMaxScore();
            $pagePercents.filter(':visible').val(
                calcPercent($pageScores.filter(':visible').val(), $pageMaxes.filter(':visible').val())
            ).change();
            checkForDisqualify();
            updateApplicationScore();
        };

        const updateApplicationScore = () => {
            $applicationScore.val(
                $pageScores.map((index, pageScore) => {
                    return Number(pageScore.value);
                }).toArray().reduce((total, item) => total + item, 0)
            ).change();
        };

        const updatePageMaxScore = () => {
            let activeInputs = $inputs.radio.filter('[data-option-max=true]:visible')
                .add($inputs.checkbox.filter(':visible'))
                .add($inputs.text.filter(':visible'))
                .not('[data-bonus=true]');
            $pageMaxes.filter(':visible').val(
                totalScoreFromInputs(activeInputs)
            );
        };

        const handleInitialPageLoad = () => {
            $pageScores.each((pageIndex, pageScore) => {
                pageIndex = pageScore.dataset.pageid;
                let $pageRadios = $inputs.radio.filterByPage(pageIndex).notIgnoreOnSubmit();
                let $pageOtherInputs = $inputs.checkbox.add($inputs.text).filterByPage(pageIndex).notIgnoreOnSubmit();
                let points = $(pageScore).val(
                    totalScoreFromInputs($pageRadios.add($pageOtherInputs).filterForScoring())
                ).change().val();
                let maxPoints = $pageMaxes.filterByPage(pageIndex).val(
                    totalScoreFromInputs($pageRadios.filter((index, radio) => !!radio.dataset.optionMax).add($pageOtherInputs).not('[data-bonus=true]'))
                ).change().val();
                $pagePercents.filterByPage(pageIndex).val(
                    calcPercent(points, maxPoints)
                );
            });
            updateApplicationScore();
        };

        const handleNewDomNodes = newElements => {
            console.log('New elements added to the DOM');
            let element, newInputs, type;
            newElements = newElements.reduce((nodeArray, mutation) => {
                for (element of mutation.addedNodes) nodeArray.push(element);
                return nodeArray;
            }, []);
            for (element of newElements) {
                if (element.classList.contains('radioList')) type = 'radio';
                else if (element.classList.contains('checkboxList')) type = 'checkbox';
                else if (element.classList.contains('text') && element.classList.contains('scored')) type = 'text';
                else continue;
                newInputs = $(element).getConfiguredInputs(type);
                newInputs.change(handleInputChange);
                $inputs[type] = $inputs[type].add(newInputs);
            }
            updatePageMaxScore();
        };

        const handleDependentFieldToggle = () => {
            console.log('dependent fields displayed or hidden');
            handleInputChange();
            updatePageMaxScore();
        };

        const handlePageChange = () => console.log('page changed');

        // filter functions
        const newDomNodes = v => v.type === 'childList' && v.addedNodes.length > 0 && v.addedNodes[0].tagName === 'SECTION';

        $inputs.all().change(handleInputChange);
        handleInitialPageLoad();

        $('.fields fieldset').createObserver(mutations => {
            // simplify to an array of identifiers [childList, style, class]
            let summary = mutations.map(v => v.type == 'attributes' ? v.attributeName : v.type).reduce((a, b) => a.includes(b) ? a : a.addItem(b), []);
            if (summary.includes('childList')) handleNewDomNodes(mutations.filter(newDomNodes));
            else if (summary.includes('style')) handleDependentFieldToggle();
            else if (summary.length === 1 && summary[0] === 'class') handlePageChange();
        }, {
            childList: true,
            attributes: true,
            attributeOldValue: true,
            attributeFilter: ['style', 'class'],
            subtree: true,
        });
    };

    // call the setup function once the form load is complete
    FrontendApiManager.onReady("submissionForm", setupScoring);
})();