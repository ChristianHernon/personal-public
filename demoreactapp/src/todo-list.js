import React from 'react';
import TodoItem from './todo-item';

export default class TodoList extends React.Component {
    constructor(props) {
        super(props);

        // init state
        this.state = {
            items: [{
                name: 'Build Todo List App',
                done: true,
                key: Date.now(),
            }]
        };

        // bindings
        this.addItem = this.addItem.bind(this);
        this.completeItem = this.completeItem.bind(this);
    }

    addItem(ev) {
        // exit early if there is no item
        if (!!!this._inputElement.value.trim()) return;

        // build new item to add
        const itemToAdd = {
            name: this._inputElement.value,
            done: false,
            key: Date.now(),
        };

        // update state with new item
        this.setState(prevState => {
            return { items: prevState.items.concat(itemToAdd) }
        });

        // clear input so it's ready for the next item
        this._inputElement.value = '';

        // prevent default form submission action
        ev.preventDefault();
    }

    completeItem(key) {
        // create new copy of state items
        const updatedItems = [...this.state.items];
        // get the index of the item to update
        const index = updatedItems.findIndex(v => v.key === key);

        // toggle the done state of the item
        updatedItems[index].done = !updatedItems[index].done;

        // update the state
        this.setState({
            items: updatedItems
        });
    }

    getTodoCount() {
        // return the count of the items that are "done"
        return this.state.items.filter(v => v.done).length;
    }

    render() {
        return (
            <section className='todo-section'>
                <form onSubmit={this.addItem} className='todo-form'>
                    <input ref={el => this._inputElement = el} placeholder='Add Todo Item' />
                    <button type='submit'>Add Item</button>
                </form>
                <p className='todo-counter'>{this.getTodoCount()} of {this.state.items.length} tasks complete!</p>
                <TodoItem items={this.state.items} onClick={this.completeItem} />
            </section>
        );
    }
};