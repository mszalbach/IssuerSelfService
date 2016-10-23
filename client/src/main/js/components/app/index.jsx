import React from "react";
import SecurityTable from "../securityTable";
import client from "../../rest/client";

export default class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {securities: []};
    }

    componentDidMount() {
        this.loadFromServer();
        console.log(this.state.securities)
    }

    loadFromServer() {
        client({method: 'GET', path: '/securities'}).then(response => {
            this.setState({securities: response.entity._embedded.securities});
        });
    }

    render() {
        return <SecurityTable securities={this.state.securities}/>;
    }
}