import React, { useState, useEffect } from 'react';
import TodoList from './TodoList';
import TodoForm from './TodoForm';
import { GenerateID } from './generateId';

export default function TodoListContainer(props) {

    const [newItem, setNewItem] = useState('');
    const [items, setItems] = useState([{
        name: 'Build Todo List App',
        done: true,
        key: GenerateID.next().value
    }]);

    const handleKeyDown = e => {
        if (e.key === 'Enter') addItem();
    };

    const handleChange = ({ target }) => {
        // capture text from input field
        const text = target.value;

        // update state value for "newItem"
        setNewItem(text);
    };

    const addItem = () => {
        // exit early if there is no item
        if (!!!newItem.trim()) return;

        // build new item to add
        const itemToAdd = {
            name: newItem,
            done: false,
            key: GenerateID.next().value
        };

        // update state with new item
        setItems(prevItems => [itemToAdd, ...prevItems]);

        // clear text for new item
        setNewItem('');
    };

    const completeItem = key => {
        // create new copy of state items
        const updatedItems = [...items];

        // get the index of the item to update
        const index = updatedItems.findIndex(v => v.key === key);

        // toggle the done state of the item
        updatedItems[index].done = !updatedItems[index].done;

        // update the state
        setItems(updatedItems);
    };

    const removeItem = key => {
        // create copy of filtered items
        const filteredItems = items.filter(v => v.key !== key);

        // update the state of items
        setItems(filteredItems);
    }

    // get count of items that are "done"
    const getTodoCount = () => items.filter(v => v.done).length;

    return (
        <section className='todo-section'>
            <TodoForm
                newItem={newItem}
                handleChange={handleChange}
                handleKeyDown={handleKeyDown}
                addItem={addItem}
            />
            <TodoList
                items={items}
                count={getTodoCount()}
                onClick={completeItem}
                onRemove={removeItem}
            />
        </section>
    );
}