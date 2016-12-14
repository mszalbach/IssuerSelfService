import React from "react";
import {connect} from "react-redux";
import SecuritiesHistoryPage from "../../components/securitiesHistoryPage";
import {connectWebsocket} from "../../reducers/securityWebsocket";


export default connect(
        state => ({history: state.securityHistory.history}),
        {connectWebsocket}
)( SecuritiesHistoryPage );