import React from "react";
import {connect} from "react-redux";
import SecuritiesPage from "../../components/securitiesPage";
import {connectWebsocket} from "../../reducers/securityWebsocket";


export default connect(
    state => ({}),
    {connectWebsocket}
)(SecuritiesPage);