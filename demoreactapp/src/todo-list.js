import React from 'react';
import TodoItem from './todo-item';

export class TodoList extends React.Component {
    constructor(props) {
        super(props);
        this.addItem = this.addItem.bind(this);
        this.state = {
            items: []
        };
    }

    addItem(ev) {
        if (!!this._inputElement.value) {
            var itemToAdd = {
                name: this._inputElement.value,
                key: Date.now()
            };

            this.setState(prevState => {
                return { items: prevState.items.concat(itemToAdd) }
            });

            this._inputElement.value = "";
        }

        console.log(this.state.items);

        ev.preventDefault();
    }

    render() {
        return (
            <section className="todo-section">
                <form onSubmit={this.addItem}>
                    <input ref={el => this._inputElement = el} placeholder="Add Todo Item" />
                    <button type="submit">Add Item</button>
                </form>
                <TodoItem items={this.state.items} />
            </section>
        );
    }
};

export default TodoList;