import React from "react";
import ReactDOM from "react-dom";
import {Route, Router, IndexRedirect} from "react-router";
import {syncHistoryWithStore} from "react-router-redux";
import ReduxToastr from "react-redux-toastr";
import {Provider} from "react-redux";
import initStore from "config/store";
import MainLayout from "./components/MainLayout";
import Landing from "./components/landingPage";
import LoginPage from "./container/loginPage";
import NoMatch from "./components/noMatch";
import SecuritiesPage from "container/securitiesPage";
import {requireAuthentication} from "container/auth";
import history from "config/history";
import "react-redux-toastr/lib/css/react-redux-toastr.css";
import stompClient from "./rest/stomp-client";

const store = initStore();
const syncHistory = syncHistoryWithStore( history, store );


export default class App extends React.Component {


    refresh( message ) {
        console.log( "received ws message " + message )
    }


    componentDidMount() {
        stompClient.register( [
                                  {route: '/topic/newSecurity', callback: this.refresh},
                                  {route: '/topic/updateSecurity', callback: this.refresh},
                                  {route: '/topic/deleteSecurity', callback: this.refresh}
                              ] );
    }

    render() {


        return (
                <Provider store={store}>
                    <div>
                        <Router history={syncHistory}>
                            <Route path="/" component={MainLayout}>
                                <IndexRedirect to="landing"/>
                                <Route path="landing"
                                       component={Landing}/>
                                <Route path="securities"
                                       component={requireAuthentication( SecuritiesPage )}/>
                                <Route path="login"
                                       component={LoginPage}/>
                                <Route path="*" component={NoMatch}/>
                            </Route>
                        </Router>
                        <ReduxToastr position="bottom-right"/>
                    </div>
                </Provider>
        )
    };
}


ReactDOM.render( <App />, document.getElementById( "application" ) );