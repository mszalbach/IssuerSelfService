import React from "react";
import {Table} from "react-bootstrap";

export default class SecurityTable extends React.Component {

    static propTypes = {
        history: React.PropTypes.array.isRequired,
        connectWebsocket: React.PropTypes.func.isRequired
    };

    componentDidMount() {
        this.props.connectWebsocket();
    }

    render() {
        let {history} = this.props;
        return (
                <Table id="securitieshistory" striped bordered condensed hover responsive>
                    <thead>
                    <tr>
                        <th>Version</th>
                        <th>ISIN</th>
                        <th>Change Date</th>
                        <th>Change</th>
                        <th>Author</th>
                        <th>roles</th>
                    </tr>
                    </thead>
                    <tbody>
                    {history.map(entry =>
                        <tr key={entry.id}>
                            <td>{entry.id}</td>
                            <td>{entry.security.isin}</td>
                            <td>{entry.revisionDate}</td>
                            <td>{entry.revisionType}</td>
                            <td>{entry.username}</td>
                            <td>{entry.roles}</td>
                        </tr>
                    )}
                    </tbody>
                </Table>
        );
    }
}