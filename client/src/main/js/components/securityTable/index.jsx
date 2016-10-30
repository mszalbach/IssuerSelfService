import React from "react";

export default class SecurityTable extends React.Component {

    render() {
        const {securities} = this.props;
        return (
            <table id="securities">
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
            </table>
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
                    <button id={"delete_" + security.isin} onClick={() => this.handleDelete()}>X</button>
                </td>
            </tr>
        );
    }
}