import client from "../rest/client";


const LOGIN = 'authentication/LOGIN';
const LOGIN_SUCCESS = 'authentication/LOGIN_SUCCESS';
const LOGIN_FAIL = 'authentication/LOGIN_FAIL';

const LOGOUT = 'authentication/LOGOUT';
const LOGOUT_SUCCESS = 'authentication/LOGOUT_SUCCESS';
const LOGOUT_FAIL = 'authentication/LOGOUT_FAIL';

const GET_SESSION = 'authentication/GET_SESSION';
const GET_SESSION_SUCCESS = 'authentication/GET_SESSION_SUCCESS';
const GET_SESSION_FAIL = 'authentication/GET_SESSION_FAIL';

const ERROR_MESSAGE = 'authentication/ERROR_MESSAGE';

const initialState = {
    isAuthenticated: false,
    username: null,
    token: null
};

export default function reducer(state = initialState, action) {
    switch (action.type) {
        case LOGIN_SUCCESS:
            return {
                ...state,
                isAuthenticated: true,
                username: action.username,
                token: action.token
            };
        case LOGIN_FAIL:
            return {
                ...state,
                isAuthenticated: false,
                username: null,
                errorMessage: action.message
            };
        case LOGOUT_SUCCESS:
            return {
                ...state,
                isAuthenticated: false,
                username: null,
                token: null
            };
        case GET_SESSION:
            return {
                ...state,
                loading: true
            };
        default:
            return state;
    }
}

function setLogin(data) {
    return {type: LOGIN_SUCCESS, username: "Ralf", token: data.entity};
}


export function login(username, password) {
    return function (dispatch) {
        return client({
            method: 'POST',
            path: '/api/session',
            headers: {'Content-Type': 'text/plain'}
        }).then(response => {
                dispatch(setLogin(response));
            }
        );
    };
}