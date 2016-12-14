import {createClient} from "../rest/client";

const FETCH_SUCCESS = 'securityHistory/FETCH_SUCCESS';

const initialState = {
    history: [{
        "security": {
            "id": 1,
            "isin": "CH0123456789",
            "symbol": null,
            "nominalValue": null,
            "issuer": null,
            "state": "Open"
        },
        "revisionDate": "2016-12-12T13:05:23.025+0000",
        "revisionType": "ADD",
        "id": 1,
        "username": "Ralf",
        "roles": "[ROLE_ADMIN]"
    }, {
        "security": {
            "id": 1,
            "isin": "CH0123456789",
            "symbol": null,
            "nominalValue": null,
            "issuer": null,
            "state": "Requested"
        },
        "revisionDate": "2016-12-12T13:05:25.528+0000",
        "revisionType": "MOD",
        "id": 2,
        "username": "Ralf",
        "roles": "[ROLE_EMITTENT]"
    }]
};

export default function reducer( state = initialState, action ) {
    switch ( action.type ) {
        case FETCH_SUCCESS:
            return {
                ...state,
                history: action.history
            };
        default:
            return state;
    }
}


export function fetchHistory( security ) {
    return function ( dispatch ) {
        return createClient()( {
                                   method: 'GET',
                                   path: "api/securities/history",

                                   headers: {'Content-Type': 'application/json'}
                               } ).then( response => {
                                             dispatch( setHistory( response ) );
                                         },
        );
    };
}

function setHistory( data ) {
    return {type: FETCH_SUCCESS, history: data.entity};
}