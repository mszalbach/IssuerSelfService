import {combineReducers} from "redux";
import {routerReducer as routing} from "react-router-redux";
import securities from "./securities";
import authentication from "./authentication";

export default combineReducers({
    securities,
    authentication,
    routing
});