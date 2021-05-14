import React from 'react';
import PropTypes from 'prop-types';

export default function TodoList(props) {
    const { items, count, onClick, onRemove } = props;
    const shrug = '¯\\_(ツ)_/¯';
    const shrugStyles = { fontSize: '2rem', fontWeight: 400, textAlign: 'center' };

    const buildItemHTML = ({ key, name, done }) => {
        const className = done ? 'todo-item done' : 'todo-item';
        return (
            <li className={className} key={key}>
                <span className='item-name' onClick={() => onClick(key)}>{name}</span>
                <span className='remove-icon' onClick={() => onRemove(key)}>&#10006;</span>
            </li>
        );
    };

    return (
        <div>
            <p style={{ margin: 0, padding: '0.75em' }}>{count} of {items.length} Items Complete!</p>
            <ul className='todo-list'>
                {items.length ? items.map(buildItemHTML) : <h1 style={shrugStyles}>{shrug}<br />No items here...</h1>}
            </ul>
        </div>
    );
};

TodoList.propTypes = {
    count: PropTypes.number.isRequired,
    items: PropTypes.array.isRequired,
    onClick: PropTypes.func.isRequired,
    onRemove: PropTypes.func.isRequired
};