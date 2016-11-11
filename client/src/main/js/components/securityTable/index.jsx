import React from "react";
import {Table, Button} from "react-bootstrap";

export default class SecurityTable extends React.Component {

    static propTypes = {
        securities: React.PropTypes.array.isRequired,
        attributes: React.PropTypes.array.isRequired,
        deleteSecurity: React.PropTypes.func.isRequired
    };

    render() {
        let {securities, attributes, deleteSecurity} = this.props;
        return (
            <Table id="securities" striped bordered condensed hover responsive>
                <thead>
                <tr>
                    {attributes.map(attribute =>
                        <th key={"heading_" + attribute}>{attribute}</th>
                    )}
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                {securities.map(security => <SecurityRow key={security._links.self.href}
                                                         security={security}
                                                         attributes={attributes}
                                                         onDelete={deleteSecurity}/>)}
                </tbody>
            </Table>
        );
    }
}

class SecurityRow extends React.Component {

    handleDelete = () => {
        this.props.onDelete(this.props.security);
    };

    render() {
        let {security, attributes} = this.props;
        return (
            <tr>
                {attributes.map(attribute =>
                    <td key={attribute}>{security[attribute]}</td>)
                }
                <td>
                    <Button id={"delete_" + security.isin} onClick={this.handleDelete}>X</Button>
                </td>
            </tr>
        );
    }
}