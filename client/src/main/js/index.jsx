import React from "react";
import ReactDOM from "react-dom";
import {Route, Router, IndexRedirect, hashHistory} from "react-router";
import {syncHistoryWithStore} from "react-router-redux";
import {Provider} from "react-redux";
import Securities from "container/securities";
import LoginPage from "container/login";
import NoMatch from "components/noMatch";
import initStore from "config/store";

const store = initStore();
const history = syncHistoryWithStore(hashHistory, store);

export default class App extends React.Component {

    render() {
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