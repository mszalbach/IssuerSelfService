import React from "react";

export default class SecurityTable extends React.Component {

    render() {
        const {securities} = this.props;
        return (
            <table>
                <thead>
                <tr>
                    <th>ISIN</th>
                    <th>Symbol</th>
                </tr>
                </thead>
                <tbody>
                {securities.map(security => <SecurityRow key={security._links.self.href} security={security}/>
                )}
                </tbody>
            </table>
        );
    }
}

class SecurityRow extends React.Component {

    render() {
        return (
            <tr>
                <td>{this.props.security.isin}</td>
                <td>{this.props.security.symbol}</td>
            </tr>
        );
    }
}