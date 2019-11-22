import React from "react";
import PropTypes from "prop-types";
import SecurityTable from "../../container/securityTable";
import CreateDialog from "../../container/createDialog";


export default class SecuritiesPage extends React.Component {

    static propTypes = {
        connectWebsocket: PropTypes.func.isRequired
    };

    componentDidMount() {
        this.props.connectWebsocket();
    }


    render() {
        return <div>
            <CreateDialog />
            <br/>
            <SecurityTable />
        </div>;
    }
}