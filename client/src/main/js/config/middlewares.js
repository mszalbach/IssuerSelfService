import {applyMiddleware, compose} from "redux";
import thunk from "redux-thunk";
import {routerMiddleware} from "react-router-redux";
import history from "../config/history";


export default compose(
    applyMiddleware(thunk),
    applyMiddleware(routerMiddleware(history)));