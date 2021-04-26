/**
 * Created by ChristianHernon on 2/5/2016.
 */

(function(){

    var score = 0,
        level = 1,
        words = [],
        speed = 10000;

    // create divs
    $('<div>', { class: 'playing-area' }).appendTo('body');
    $('<div>', { class: 'score' }).appendTo('body');
    $('<div>', { class: 'input' }).appendTo('body');

    // create scoreboard
    $('<section>Level<p>' + level + '</p><section>Score<p>' + score + '</p></section></section>').appendTo('.score');

    // create input field and buttons for game
    $('<input>', { type: 'text', id: 'data' }).appendTo('.input').focus();
    $('<input>', { type: 'button', id: 'stop', value: 'Stop' }).appendTo('.input');
    $('<input>', { type: 'button', id: 'continue', value: 'Continue' }).appendTo('.input');

    //create audio element
    $('body').append($('<audio>', { id: 'mySound', preload: 'auto' }));
    $('#mySound').append($('<source>', { src: '../files/match.mp3' }));

    // read in the words.txt file, start game upon success
    $.get('../files/words.txt', runApplication);

    function runApplication(data) {
        fillOutArrayData(data);
        displayWord();
        startFall();
    }// end runApplication

    function fillOutArrayData(data) {
        words = data.split('\n');
    }// end fillOutArray

    function displayWord() {
        $('.playing-area').prepend($('<span>', { class: 'word' }));
        var len = words.length,
            index = Math.floor(len * Math.random());
        // get rid of whitespace characters with 'trim'
        $('.word').text($.trim(words[index]));
        var myWord = $('.word').text();
    }// end displayWord

    function startFall() {
        $('.word').animate( { top: '95%' }, speed, newWord);
    }// end startFall

    function newWord() {
        $('.word').stop();
        $('.word').remove();
        $('#data').val('');
        displayWord();
        startFall();
    }// end newWord

    function inputHandler(e) {
        var $data = $('#data'),
            $word = $('.word'),
            //$sound = $('#mySound'),
            $score = $('.score p');

        if (e.keyCode == 13) {
            if ($data.val() == $word.text()) {
                $('#mySound')[0].play();
                score += 10;
                $score.eq(1).text(score);
                if (score == 100 || speed == 200 || speed == 300 || speed == 400) {
                    speed *= 0.67;
                    $score.eq(0).text(++level);
                }
                newWord();
            }
            $data.val('');
        }
    }// end inputHandler


    $('#data').keyup(function($this) {
        inputHandler($this);
    });

    $('#stop').click(function () {
        $('.word').stop();
    });

    $('#continue').click(function () {
        newWord();
    });

})();
