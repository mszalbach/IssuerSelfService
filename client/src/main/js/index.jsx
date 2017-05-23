import React from "react";
import ReactDOM from "react-dom";
import ReduxToastr from "react-redux-toastr";
import {Provider} from "react-redux";
import initStore from "config/store";
import Menu from "./container/menu";
import {HashRouter} from "react-router-dom";
import Main from "./components/main";
import "react-redux-toastr/lib/css/react-redux-toastr.min.css";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap-theme.css";

const store = initStore();

export default class App extends React.Component {

    render() {
        return (
            <Provider store={store}>
                <div>
                    <HashRouter>
                        <div>
                            <Menu />
                            <Main />
                        </div>
                    </HashRouter>
                    <ReduxToastr position="bottom-right"/>
                </div>
            </Provider>
        )
    };
}


ReactDOM.render( <App />, document.getElementById( "application" ) );