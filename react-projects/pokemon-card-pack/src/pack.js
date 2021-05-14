import React from 'react';
import './card.css';
import Loading from './loading-animation';

export default class CardPack extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            cards: null
        };

        this.openNewPack = this.openNewPack.bind(this);
    }

    async componentDidMount() {
        const cards = await this.loadCardData();
        this.setState({
            cards: cards
        });
    }

    rollRandomInt = max => Math.ceil(Math.random() * max);

    getArrayOfRandomIds() {
        const maxID = 898;
        const count = 5;
        const ids = new Set();
        while (ids.size < count) {
            ids.add(this.rollRandomInt(maxID));
        }
        return ids;
    }

    async loadCardData() {
        const ids = this.getArrayOfRandomIds();
        const fetchRequests = [];

        for (const id of ids)
            fetchRequests.push(fetch(`https://pokeapi.co/api/v2/pokemon/${id}`));

        const resolved = await Promise.all(fetchRequests);
        const data = await Promise.all(resolved.map(r => r.json()));

        return data;
    }

    buildCardHTML(cardData, index) {
        const style = {
            '--anim-delay': index + 1
        };
        return (
            <article key={cardData.id} className='card' style={style}>
                <div className='card-face card-back'></div>
                <div className='card-face card-front'>
                    <h2 className='pokemon-name'>{cardData.name}</h2>
                    <img className='pokemon-image' src={cardData.sprites.other['official-artwork'].front_default} alt={cardData.name} />
                </div>
            </article>
        )
    }

    async openNewPack() {
        this.setState({
            cards: null
        });
        this.setState({
            cards: await this.loadCardData()
        });
    }

    render() {
        const isLoading = this.state.cards === null;
        const cardSection = isLoading
            ? <Loading msg='Loading Card Pack...' color='white' />
            : this.state.cards.map((card, index) => this.buildCardHTML(card, index));
        const buttonStyles = {
            'background': 'seagreen',
            'color': '#fff',
            'padding': '0.5em 2em',
            'fontSize': '1.25rem',
            'fontWeight': '700',
            'border': 'none',
            'borderRadius': '0.2em',
            'cursor': 'pointer',
        };
        return (
            <div>
                {!isLoading && <button type='button' onClick={this.openNewPack} style={buttonStyles}>Get New Set</button>}
                <section className='card-row'>
                    {cardSection}
                </section>
            </div>
        );
    }
}