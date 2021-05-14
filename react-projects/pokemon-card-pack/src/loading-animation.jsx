import React from 'react';
import './loading-animation.css';

export default class Loading extends React.Component {
    render() {
        const styles = {};
        if (this.props.color) styles['--loading-color'] = this.props.color;
        return (
            <div className='loading-component' style={styles}>
                <div className='loading-animation'>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
                <h2>{this.props.msg ?? 'Default'}</h2>
            </div>
        );
    }
}