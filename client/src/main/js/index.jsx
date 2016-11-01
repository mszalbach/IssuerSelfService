import React from "react";
import ReactDOM from "react-dom";
import {Route, Router, IndexRedirect, hashHistory} from "react-router";
import Securities from "container/securities";
import LoginPage from "components/loginPage";
import NoMatch from "components/noMatch";
import initStore from "config/store";
import {syncHistoryWithStore} from "react-router-redux";
import {Provider} from "react-redux";

export default class App extends React.Component {


    render() {
        const store = initStore();
        const history = syncHistoryWithStore(hashHistory, store);


        return (
            <Provider store={store}>
                <Router history={history}>
                    <Route path="/">
                        <IndexRedirect to="/securities"/>
                    </Route>
                    <Route path="securities"
                           component={Securities}/>
                    <Route path="login"
                           component={LoginPage}/>
                    <Route path="*" component={NoMatch}/>
                </Router>
            </Provider>
        )
    }
}


ReactDOM.render(<App />, document.getElementById("application"));