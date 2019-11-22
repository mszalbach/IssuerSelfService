import React from "react";
import {Table} from "react-bootstrap";
import PropTypes from "prop-types";
import SecurityRow from "./SecurityRow";

export default class SecurityTable extends React.Component {

    static propTypes = {
        securities: PropTypes.array.isRequired,
        attributes: PropTypes.array.isRequired,
        deleteSecurity: PropTypes.func.isRequired,
        postSecurityLink: PropTypes.func.isRequired,
    };

    render() {
        let {securities, attributes, deleteSecurity, postSecurityLink} = this.props;
        return (
            <Table id="securities" striped bordered condensed hover responsive>
                <thead>
                <tr>
                    {attributes.map(attribute =>
                        <th key={"heading_" + attribute}>{attribute}</th>
                    )}
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {securities.map(security => <SecurityRow key={security._links.self.href}
                                                         security={security} attributes={attributes}
                                                         deleteSecurity={deleteSecurity}
                                                         postSecurityLink={postSecurityLink}/>)}
                </tbody>
            </Table>
        );
    }
}