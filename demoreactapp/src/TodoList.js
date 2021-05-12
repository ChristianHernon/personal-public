import React from 'react';
import PropTypes from 'prop-types';

export default class TodoList extends React.Component {
    constructor(props) {
        super(props);

        this.buildItemHTML = this.buildItemHTML.bind(this);
    }

    buildItemHTML({ key, name, done }) {
        const className = done ? 'todo-item done' : 'todo-item';
        return <li className={className} key={key} onClick={() => this.props.onClick(key)}>{name}</li>;
    }

    render() {
        const { items, count } = this.props
        return (
            <div>
                <p>{count} of {items.length} Items Complete!</p>
                <ul className='todo-list'>
                    {items.map(this.buildItemHTML)}
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