import React from 'react';
import PropTypes from 'prop-types';

export default class TodoList extends React.Component {
    constructor(props) {
        super(props);

        this.buildItemHTML = this.buildItemHTML.bind(this);
    }

    buildItemHTML({ key, name, done }) {
        const { onClick, remove } = this.props;
        const className = done ? 'todo-item done' : 'todo-item';
        return (
            <li className={className} key={key}>
                <span className='item-name' onClick={() => onClick(key)}>{name}</span>
                <span className='remove-icon' onClick={() => remove(key)}>&#10006;</span>
            </li>
        );
    }

    render() {
        const shrug = '¯\\_(ツ)_/¯';
        const shrugStyles = { fontSize: '2rem', fontWeight: 400, textAlign: 'center' };
        const { items, count } = this.props
        return (
            <div>
                <p style={{ margin: 0, padding: '0.75em' }}>{count} of {items.length} Items Complete!</p>
                <ul className='todo-list'>
                    {items.length ? items.map(this.buildItemHTML) : <h1 style={shrugStyles}>{shrug}<br />No items here...</h1>}
                </ul>
            </div>
        );
    }
};

TodoList.propTypes = {
    count: PropTypes.number.isRequired,
    items: PropTypes.array.isRequired,
    onClick: PropTypes.func.isRequired
};