import React from "react";
import {connect} from "react-redux";
import SecurityTable from "../../components/securityTable";
import CreateDialog from "../../components/createDialog";
import {fetchSecurities, deleteSecurity, addSecurity} from "../../reducers/securities";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap-theme.css";


class Securities extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            attributes: ["isin", "symbol"]
        };
    }

    componentDidMount() {
        this.props.fetchSecurities();
    }


    render() {
        return <div>
            <CreateDialog attributes={this.state.attributes} onCreate={(security) => this.props.addSecurity(security)}/>
            <br/>
            <SecurityTable securities={this.props.securities}
                           onDelete={(security) => this.props.deleteSecurity(security)}/>
        </div>;
    }
}

export default connect(
    state => ({securities: state.securities.securities}),
    {fetchSecurities, deleteSecurity, addSecurity}
)(Securities);