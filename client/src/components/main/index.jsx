import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import Landing from "../landingPage";
import NoMatch from "../noMatch";
import LoginPage from "../../container/loginPage";
import SecuritiesPage from "../../container/securitiesPage";
import SecuritiesHistoryPage from "../../container/securitiesHistoryPage";
import {requireAuthentication} from "../../container/auth/index";

export default class Main extends React.Component {

    render() {
        return (
            <Switch>
                <Route path="/login" component={LoginPage}/>
                <Route path="/landing" component={Landing}/>
                <Route path="/securitieshistory" component={requireAuthentication( SecuritiesHistoryPage )}/>
                <Route path="/securities" component={requireAuthentication( SecuritiesPage )}/>
                <Redirect from='/' to='/landing'/>
                <Route component={NoMatch}/>
            </Switch>
        )
    }
}