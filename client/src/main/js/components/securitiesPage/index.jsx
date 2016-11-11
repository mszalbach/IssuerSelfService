import React from "react";
import SecurityTable from "../../container/securityTable";
import CreateDialog from "../../container/createDialog";


export default class SecuritiesPage extends React.Component {

    static propTypes = {
        fetchSecurities: React.PropTypes.func.isRequired,
        fetchAttributes: React.PropTypes.func.isRequired
    };

    componentDidMount() {
        this.props.fetchAttributes();
        this.props.fetchSecurities();
    }


    render() {
        return <div>
            <CreateDialog />
            <br/>
            <SecurityTable />
        </div>;
    }
}