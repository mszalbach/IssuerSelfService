import {createClient} from "../rest/client";

const FETCH_SUCCESS = 'securityHistory/FETCH_SUCCESS';

const initialState = {
    history: []
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