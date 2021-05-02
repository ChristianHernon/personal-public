import React from 'react';

export default class TodoItem extends React.Component {
    constructor(props) {
        super(props)

        // bindings
        this.buildRow = this.buildRow.bind(this);
    }

    buildRow(item) {
        return (
            <li className={item.done ? 'todo-item done' : 'todo-item'}
                onClick={() => this.props.onClick(item.key)}
                key={item.key}>
                {item.name}
            </li>
        );
    }

    render() {
        return (
            <ol className='todo-list'>
                {this.props.items.map(this.buildRow)}
            </ol>
        );
    }
};