import {combineReducers} from "redux";
import securities from "./securities";
import {routerReducer as routing} from "react-router-redux";

export default combineReducers({
    securities,
    routing
});