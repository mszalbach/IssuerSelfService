import React from "react";
import ReactDOM from "react-dom";
import {Route, Router, IndexRedirect, hashHistory} from "react-router";
import Securities from "components/securities";
import LoginPage from "components/loginPage";
import NoMatch from "components/noMatch";

export default class App extends React.Component {

    render() {
        return (
            <Router history={hashHistory}>
                <Route path="/">
                    <IndexRedirect to="/securities"/>
                </Route>
                <Route path="securities"
                       component={Securities}/>
                <Route path="login"
                       component={LoginPage}/>
                <Route path="*" component={NoMatch}/>
            </Router>
        )
    }
}


ReactDOM.render(<App />, document.getElementById("application"));