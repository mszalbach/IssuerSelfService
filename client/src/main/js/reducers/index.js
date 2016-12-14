import {combineReducers} from "redux";
import {routerReducer} from "react-router-redux";
import {reducer as toastrReducer} from "react-redux-toastr";
import securities from "./securities";
import authentication from "./authentication";
import securityWebsocket from "./securityWebsocket";
import securityHistory from "./securityHistory";

export default combineReducers({
    securities,
    authentication,
    securityWebsocket,
    securityHistory,
    toastr: toastrReducer,
    routing: routerReducer
});