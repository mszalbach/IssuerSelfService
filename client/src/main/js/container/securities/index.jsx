import React from "react";
import {connect} from "react-redux";
import SecurityTable from "../../components/securityTable";
import CreateDialog from "../../components/createDialog";
import {fetchSecurities, deleteSecurity, addSecurity} from "../../reducers/securities";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap-theme.css";


class Securities extends React.Component {

    static propTypes = {
        fetchSecurities: React.PropTypes.func.isRequired,
        deleteSecurity: React.PropTypes.func.isRequired,
        addSecurity: React.PropTypes.func.isRequired,
    };

    state = {
        attributes: ["isin", "symbol"],
    };


    componentDidMount() {
        this.props.fetchSecurities();
    }


    render() {
        return <div>
            <CreateDialog attributes={this.state.attributes} onCreate={this.props.addSecurity}/>
            <br/>
            <SecurityTable attributes={this.state.attributes}
                           securities={this.props.securities}
                           onDelete={this.props.deleteSecurity}/>
        </div>;
    }
}

export default connect(
    state => ({securities: state.securities.securities}),
    {fetchSecurities, deleteSecurity, addSecurity}
)(Securities);