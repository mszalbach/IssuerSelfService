import {hashHistory} from "react-router";
import {createClient} from "../rest/client";

const LOGIN = 'authentication/LOGIN';
const LOGIN_SUCCESS = 'authentication/LOGIN_SUCCESS';
const LOGIN_FAIL = 'authentication/LOGIN_FAIL';

const LOGOUT = 'authentication/LOGOUT';
const LOGOUT_SUCCESS = 'authentication/LOGOUT_SUCCESS';

const initialState = {
    isAuthenticated: false,
    username: null,
    token: null,
    errorMessage: null,
};

export default function reducer(state = initialState, action) {
    switch (action.type) {
        case LOGIN_SUCCESS:
            return {
                ...state,
                isAuthenticated: true,
                username: action.username,
                token: action.token,
                errorMessage: null
            };
        case LOGIN_FAIL:
            return {
                ...state,
                isAuthenticated: false,
                username: null,
                errorMessage: action.errorMessage
            };
        case LOGOUT_SUCCESS:
            return {
                ...state,
                isAuthenticated: false,
                username: null,
                token: null,
                errorMessage: null
            };
        default:
            return state;
    }
}


export function login(username, password) {
    return function (dispatch) {
        return createClient()({
            method: 'POST',
            path: '/api/session',
            entity: {
                'username': username,
                'password': password,
            },
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
                localStorage.setItem('auth-token', response.entity.token);
                dispatch(setLogin(response));
                hashHistory.push('securities');

            },
            response => {
                dispatch(setLoginError(response));
            }
        );
    };
}

function setLogin(data) {
    return {type: LOGIN_SUCCESS, username: data.entity.userName, token: data.entity.token};
}

function setLoginError(data) {
    return {type: LOGIN_FAIL, errorMessage: data.status.code + ":" + data.entity.message};
}

export function logout() {
    return function (dispatch) {
        return createClient()({
            method: 'DELETE',
            path: '/api/session'
        }).then(response => {
            localStorage.removeItem('auth-token');
            dispatch(setLogout(response));
            hashHistory.push('login');
        });
    };
}

function setLogout(data) {
    return {type: LOGOUT_SUCCESS};
}