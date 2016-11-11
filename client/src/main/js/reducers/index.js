import {combineReducers} from "redux";
import {routerReducer} from "react-router-redux";
import securities from "./securities";
import authentication from "./authentication";

export default combineReducers({
    securities,
    authentication,
    routing: routerReducer
});