import {combineReducers} from "redux";
import {routerReducer} from "react-router-redux";
import {reducer as toastrReducer} from 'react-redux-toastr';
import securities from "./securities";
import authentication from "./authentication";

export default combineReducers({
    securities,
    authentication,
    toastr: toastrReducer,
    routing: routerReducer
});