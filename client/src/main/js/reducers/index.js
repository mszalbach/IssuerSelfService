import {combineReducers} from "redux";
import {routerReducer} from "react-router-redux";
import {reducer as toastrReducer} from "react-redux-toastr";
import securities from "./securities";
import authentication from "./authentication";
import securityWebsocket from "./securityWebsocket";

export default combineReducers({
    securities,
    authentication,
    securityWebsocket,
    toastr: toastrReducer,
    routing: routerReducer
});