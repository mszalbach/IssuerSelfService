import {combineReducers} from "redux";
import {routerReducer as routing} from "react-router-redux";
import securities from "./securities";

export default combineReducers({
    securities,
    routing
});