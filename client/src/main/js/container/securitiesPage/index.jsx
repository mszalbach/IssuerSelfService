import React from "react";
import {connect} from "react-redux";
import SecuritiesPage from "../../components/securitiesPage";
import {fetchSecurities} from "../../reducers/securities";


export default connect(
    state => ({}),
    {fetchSecurities}
)(SecuritiesPage);