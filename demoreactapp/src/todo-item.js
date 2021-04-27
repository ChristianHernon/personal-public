import React from 'react';

export class TodoItem extends React.Component {
    render() {
        let itemsHTML = this.props.items.map(item => <li className="todo-item" key={item.key}>{item.name}</li>);
        return (
            <ul className="todo-list">
                {itemsHTML}
            </ul>
        );
    }
};

export default TodoItem;