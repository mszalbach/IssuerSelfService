import React from "react";
import ReactDOM from "react-dom";
import {hashHistory, Route, Router, IndexRedirect} from "react-router";
import {syncHistoryWithStore} from "react-router-redux";
import {Provider} from "react-redux";
import initStore from "config/store";
import MainLayout from "./components/MainLayout";
import Landing from "./components/landingPage";
import LoginPage from "./container/loginPage";
import NoMatch from "./components/noMatch";
import SecuritiesPage from "container/securitiesPage";
import {requireAuthentication} from "container/auth";

const store = initStore();
const history = syncHistoryWithStore(hashHistory, store);

export default class App extends React.Component {

    render() {
        return (
            <Provider store={store}>
                <Router history={history}>
                    <Route path="/" component={MainLayout}>
                        <IndexRedirect to="landing"/>
                        <Route path="landing"
                               component={Landing}/>
                        <Route path="securities"
                               component={requireAuthentication(SecuritiesPage)}/>
                        <Route path="login"
                               component={LoginPage}/>
                        <Route path="*" component={NoMatch}/>
                    </Route>
                </Router>
            </Provider>
        )
    };
}


ReactDOM.render(<App />, document.getElementById("application"));