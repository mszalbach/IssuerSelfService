import React from "react";
import {Table, Button} from "react-bootstrap";

export default class SecurityTable extends React.Component {

    render() {
        const {securities} = this.props;
        return (
            <Table id="securities" striped bordered condensed hover responsive>
                <thead>
                <tr>
                    <th>ISIN</th>
                    <th>Symbol</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                {securities.map(security => <SecurityRow key={security._links.self.href} security={security}
                                                         onDelete={this.props.onDelete}/>)}
                </tbody>
            </Table>
        );
    }
}

class SecurityRow extends React.Component {

    handleDelete() {
        this.props.onDelete(this.props.security);
    }

    render() {
        let security = this.props.security;
        return (
            <tr>
                <td>{security.isin}</td>
                <td>{security.symbol}</td>
                <td>
                    <Button id={"delete_" + security.isin} onClick={() => this.handleDelete()}>X</Button>
                </td>
            </tr>
        );
    }
}