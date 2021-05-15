import React from "react";
import "./LoadingAnimation.css";

export default class Loading extends React.Component {
	render() {
		const styles = {
			"--loading-color": this.props.color ?? "#fff",
		};

		return (
			<div className="loading-component" style={styles}>
				<div className="loading-animation">
					<div></div>
					<div></div>
					<div></div>
				</div>
				<h2>{this.props.msg ?? "Default"}</h2>
			</div>
		);
	}
}
