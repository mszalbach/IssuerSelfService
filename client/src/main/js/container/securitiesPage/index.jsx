import React from "react";
import {connect} from "react-redux";
import SecuritiesPage from "../../components/securitiesPage";
import {fetchSecurities, fetchAttributes} from "../../reducers/securities";


export default connect(
    state => ({}),
    {fetchSecurities, fetchAttributes}
)(SecuritiesPage);