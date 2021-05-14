import React from 'react';
import PropTypes from 'prop-types';

export default class TodoForm extends React.Component {
    render() {
        const { newItem, handleChange, addItem } = this.props;
        return (
            <div className='todo-form'>
                <input type='text' value={newItem} onChange={handleChange} />
                <button onClick={addItem}>Add</button>
            </div>
        )
    }
}

TodoForm.propTypes = {
    newItem: PropTypes.string.isRequired,
    addItem: PropTypes.func.isRequired,
    handleChange: PropTypes.func.isRequired
};