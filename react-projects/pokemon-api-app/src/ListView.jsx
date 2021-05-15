import React from "react";
import Loading from "./LoadingAnimation";
import { FetchOne } from "./FetchData";
import ListTile from "./ListTile";

export default class ListView extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			data: null,
		};

		this.getListView = this.getListView.bind(this);
	}

	async componentDidMount() {
		const url =
			this.state.data?.next ||
			"https://pokeapi.co/api/v2/pokemon?offset=0&limit=10";
		this.setState({
			data: await FetchOne(url),
		});
	}

	getListView() {
		return this.state.data.results.map((pokemon) => (
			<ListTile key={pokemon.url} url={pokemon.url} />
		));
	}

	render() {
		const isLoading = !!!this.state.data?.results?.length;
		return (
			<section style={{ margin: "100px 0" }}>
				{isLoading ? <Loading msg="Loading Data..." /> : this.getListView()}
			</section>
		);
	}
}
