import client from "../rest/client";

const FETCH_SUCCESS = 'securities/FETCH_SUCCESS';
const DELETE_SUCCESS = 'security/DELETE_SUCCESS';
const ADD_SUCCESS = 'security/ADD_SUCCESS';


const initialState = {
    securities: []
};

export default function securitiesReducer(state = initialState, action) {
    switch (action.type) {
        case FETCH_SUCCESS:
            return {
                ...state,
                securities: action.securities,
            };
        default:
            return state;
    }
}

function setSecurities(data) {
    return {type: FETCH_SUCCESS, securities: data.entity._embedded.securities};
}


export function fetchSecurities() {
    return function (dispatch) {
        return client({method: 'GET', path: '/api/securities'}).then(response => {
                dispatch(setSecurities(response));
            }
        );
    };
}

function securityDeleted() {
    return {type: DELETE_SUCCESS};
}

export function deleteSecurity(security) {
    return function (dispatch) {
        return client({
            method: 'DELETE',
            path: security._links.self.href
        }).then(response => {
                dispatch(securityDeleted());
                dispatch(fetchSecurities());
            }
        )
    };
}

function securityAdded() {
    return {type: ADD_SUCCESS};
}

export function addSecurity(security) {

    return function (dispatch) {
        return client({
            method: 'POST',
            path: '/api/securities',
            entity: security,
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            dispatch(securityAdded());
            dispatch(fetchSecurities());
        })
    };
}
