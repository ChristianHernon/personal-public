import React from "react";
import { FetchOne } from "./FetchData";
import "./ListTile.css";

export default class ListTile extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			data: null,
		};
	}

	async componentDidMount() {
		const url = this.props.url;
		this.setState({
			data: await FetchOne(url),
		});
	}

	getTypes(typeArray) {
		return typeArray.map((typeObj) => typeObj.type.name).join(", ");
	}

	render() {
		const pokemon = this.state.data;
		return (
			<>
				{pokemon === null ? (
					<div style={{ background: "grey" }}></div>
				) : (
					<article className="flex-row">
						<div className="left">
							<p className="name">{pokemon.name}</p>
							<p className="types">{this.getTypes(pokemon.types)}</p>
						</div>
						<div className="right">
							<img src={pokemon.sprites.front_default} alt={pokemon.name} />
						</div>
					</article>
				)}
			</>
		);
	}
}
