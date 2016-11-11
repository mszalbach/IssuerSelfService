import {push} from "react-router-redux";
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
    return function (dispatch, getState) {
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

                let nextPath = '';
                if (getState().routing.locationBeforeTransitions.state) {
                    nextPath = getState().routing.locationBeforeTransitions.state || '';
                }
                dispatch(push(nextPath));
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
    return {
        type: LOGIN_FAIL,
        errorMessage: data.status.code + ":" + (data.entity.message ? data.entity.message : data.status.text)
    };
}

export function logout() {
    return function (dispatch) {
        return createClient()({
            method: 'DELETE',
            path: '/api/session'
        }).then(response => {
            localStorage.removeItem('auth-token');
            dispatch(setLogout(response));
            dispatch(push('login'));
        });
    };
}

function setLogout(data) {
    return {type: LOGOUT_SUCCESS};
}