import React from "react";
import CardPack from "./CardPack";
import ListView from "./ListView";
import "./App.css";

export default class PokemonApp extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			page: "list",
		};

		this.updatePage = this.updatePage.bind(this);
	}

	updatePage({ target }) {
		this.setState({
			page: target.dataset.page,
		});
	}

	render() {
		let pageContent;
		switch (this.state.page) {
			case "list":
				pageContent = <ListView />;
				break;
			case "cards":
				pageContent = <CardPack />;
				break;
			default:
				pageContent = <ListView />;
		}
		return (
			<main className="App">
				<nav>
					<ul>
						<li onClick={this.updatePage} data-page="list">
							List View
						</li>
						<li onClick={this.updatePage} data-page="cards">
							Open Card Pack
						</li>
					</ul>
				</nav>
				{pageContent}
			</main>
		);
	}
}
