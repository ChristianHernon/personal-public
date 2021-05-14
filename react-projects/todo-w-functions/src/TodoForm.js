import React from 'react';
import PropTypes from 'prop-types';

export default function TodoForm(props) {
    const { newItem, handleChange, addItem, handleKeyDown } = props;
    return (
        <div className='todo-form'>
            <input type='text' value={newItem} onChange={handleChange} onKeyDown={handleKeyDown} />
            <button onClick={addItem}>Add</button>
        </div>
    )
}

TodoForm.propTypes = {
    newItem: PropTypes.string.isRequired,
    addItem: PropTypes.func.isRequired,
    handleChange: PropTypes.func.isRequired,
    handleKeyDown: PropTypes.func.isRequired
};