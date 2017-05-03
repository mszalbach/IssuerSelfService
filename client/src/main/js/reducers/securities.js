import {toastr} from "react-redux-toastr";
import {createClient} from "../rest/client";

const FETCH_SUCCESS = 'securities/FETCH_SUCCESS';
const FETCH_FAIL = 'securities/FETCH_FAIL';
const DELETE_SUCCESS = 'security/DELETE_SUCCESS';
const ADD_SUCCESS = 'security/ADD_SUCCESS';
const FETCH_ATTRIBUTES_SUCCESS = 'security/FETCH_ATTRIBUTES_SUCCESS';


const initialState = {
    securities: [],
    attributes: [],
    schema: {}
};

export default function securitiesReducer(state = initialState, action) {
    switch (action.type) {
        case FETCH_SUCCESS:
            return {
                ...state,
                securities: action.securities,
            };
        case FETCH_FAIL:
            return {
                ...state,
                securities: action.securities,
            };
        case FETCH_ATTRIBUTES_SUCCESS:
            return {
                ...state,
                attributes: action.attributes,
                schema: action.schema
            };
        default:
            return state;
    }
}

function setSecurities(data) {
    return {type: FETCH_SUCCESS, securities: data.entity._embedded.securities};
}

function setSecuritiesError() {
    toastr.warning("Security Fetch", "Could not fetch Securities");
    return {type: FETCH_FAIL, securities: []};
}


export function fetchSecurities() {
    return function (dispatch) {
        return createClient()({method: 'GET', path: '/api/securities'}).then(
            response => {
                dispatch(setSecurities(response));
            },
            response => {
                dispatch(setSecuritiesError());
            }
        );
    };
}

function securityDeleted(response) {
    toastr.success("Security Delete", "Security " + response.isin + " Deleted");
    return {type: DELETE_SUCCESS};
}

export function deleteSecurity(security) {
    return function (dispatch) {
        return createClient()({
            method: 'DELETE',
            path: security._links.self.href
        }).then(response => {
                dispatch(securityDeleted(security));
            }
        )
    };
}

function securityAdded(response) {
    toastr.success("Security Add", "Security " + response.entity.isin + " Added");
    return {type: ADD_SUCCESS};
}

export function addSecurity(security) {

    return function (dispatch) {
        return createClient()({
            method: 'POST',
            path: '/api/securities',
            entity: security,
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            dispatch(securityAdded(response));
        })
    };
}

export function fetchAttributes() {
    return function (dispatch) {
        return createClient()({
            method: 'GET',
            path: '/api/profile/securities',
            headers: {'Content-Type': 'application/schema+json'}
        }).then(
            response => {
                dispatch({
                    type: FETCH_ATTRIBUTES_SUCCESS,
                    schema: response.entity,
                    attributes: Object.keys(response.entity.properties)
                });
            }
        );
    };
}

export function postSecurityLink(security, link) {
    return function (dispatch) {
        return createClient()({
            method: 'POST',
            path: security._links[link].href
        }).then(response => {
                toastr.success("Security " + link, "Security " + security.isin + " " + link);
            }
        )
    };
}
