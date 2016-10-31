import React from "react";
import SecurityTable from "../securityTable";
import CreateDialog from "../createDialog";
import client from "../../rest/client";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap-theme.css";

export default class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            securities: [],
            attributes: ["isin", "symbol"]

        };
    }

    componentDidMount() {
        this.loadFromServer();
    }

    loadFromServer() {
        client({method: 'GET', path: '/securities'}).then(response => {
            this.setState({securities: response.entity._embedded.securities});
        });
    }

    onCreate(newSecurity) {
        client({
            method: 'POST',
            path: '/securities',
            entity: newSecurity,
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            this.loadFromServer();
        })
    }

    onDelete(security) {
        client({
            method: 'DELETE',
            path: security._links.self.href
        }).then(response => {
            this.loadFromServer();
        })
    }

    render() {
        return <div>
            <CreateDialog attributes={this.state.attributes} onCreate={(security) => this.onCreate(security)}/>
            <br/>
            <SecurityTable securities={this.state.securities} onDelete={(security) => this.onDelete(security)}/>
        </div>;
    }
}