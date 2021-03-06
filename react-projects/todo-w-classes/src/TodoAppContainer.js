import React from 'react';
import TodoList from './TodoList';
import TodoForm from './TodoForm';
import { GenerateID } from './generateId';

export default class TodoListContainer extends React.Component {
    constructor(props) {
        super(props);

        // init state
        this.state = {
            newItem: '',
            items: [{
                name: 'Build Todo List App',
                done: true,
                key: GenerateID.next().value
            }]
        };

        // bindings
        this.addItem = this.addItem.bind(this);
        this.completeItem = this.completeItem.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.removeItem = this.removeItem.bind(this);
        this.handleKeyDown = this.handleKeyDown.bind(this);
    }

    componentDidMount() {
        document.addEventListener('keydown', this.handleKeyDown);
    }

    componentWillUnmount() {
        document.removeEventListener('keydown', this.handleKeyDown);
    }

    handleKeyDown(e) {
        if (e.key === 'Enter') this.addItem();
    }

    handleChange({ target }) {
        // capture text from input field
        const text = target.value;

        // update state value for "newItem"
        this.setState(prevState => ({
            ...prevState,
            newItem: text
        }));
    }

    addItem() {
        // exit early if there is no item
        if (!!!this.state.newItem.trim()) return;

        // build new item to add
        const itemToAdd = {
            name: this.state.newItem,
            done: false,
            key: GenerateID.next().value
        };

        // update state with new item
        this.setState(prevState => ({
            ...prevState,
            newItem: '',
            items: [itemToAdd, ...prevState.items]
        }));
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

    removeItem(key) {
        // create new copy of filtered state items
        const filteredItems = this.state.items.filter(v => v.key !== key);

        // update the state
        this.setState({
            items: filteredItems
        });
    }

    getTodoCount() {
        // return the count of the items that are "done"
        return this.state.items.filter(v => v.done).length;
    }

    render() {
        return (
            <section className='todo-section'>
                <TodoForm
                    newItem={this.state.newItem}
                    handleChange={this.handleChange}
                    addItem={this.addItem}
                />
                <TodoList
                    items={this.state.items}
                    onClick={this.completeItem}
                    count={this.getTodoCount()}
                    remove={this.removeItem}
                />
            </section>
        );
    }
};