import React from "react";
import SecurityTable from "../securityTable";
import CreateDialog from "../createDialog";
import client from "../../rest/client";

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
        console.log(this.state.securities)
    }

    loadFromServer() {
        client({method: 'GET', path: '/securities'}).then(response => {
            this.setState({securities: response.entity._embedded.securities});
        });
    }

    onCreate(newSecurity) {
        console.log(newSecurity);
        client({
            method: 'POST',
            path: '/securities',
            entity: newSecurity,
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            this.loadFromServer();
        })
    }

    render() {
        return <div>
            <CreateDialog attributes={this.state.attributes} onCreate={(security) => this.onCreate(security)}/>
            <SecurityTable securities={this.state.securities}/>
        </div>;
    }
}