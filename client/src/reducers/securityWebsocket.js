import SockJS from "sockjs-client";
import Stomp from "stompjs";
import {toastr} from "react-redux-toastr";
import {fetchAttributes, fetchSecurities} from "./securities";
import {fetchHistory} from "./securityHistory";


const CONNECT_SUCCESS = 'securityWebsocket/CONNECT_SUCCESS';
const ALREADY_CONNECTED = 'securityWebsocket/ALREADY_CONNECTED';

const initialState = {
    connected: false
};


export default function reducer(state = initialState, action) {
    switch (action.type) {
        case CONNECT_SUCCESS:
            return {
                ...state,
                connected: action.connected,
            };
        default:
            return state;
    }
}


export function connectWebsocket() {
    return function (dispatch, getState) {
        if (!getState().securityWebsocket.connected) {
            const socket = SockJS('/sockjs');
            const stompClient = Stomp.over(socket);
            return stompClient.connect({}, function (frame) {
                subscribe('/topic/newSecurity', dispatch, stompClient);
                subscribe('/topic/updateSecurity', dispatch, stompClient);
                subscribe('/topic/deleteSecurity', dispatch, stompClient);
                dispatch(fetchSecurities());
                dispatch(fetchAttributes());
                dispatch(fetchHistory());
                toastr.info("Connected to Server");
                dispatch({type: CONNECT_SUCCESS, connected: true})
            });
        } else {
            dispatch({type: ALREADY_CONNECTED})
        }
    };
}


function subscribe(route, dispatch, stompClient) {
    stompClient.subscribe(route, function (message) {
        dispatch(fetchSecurities());
        dispatch(fetchHistory());
    });
}