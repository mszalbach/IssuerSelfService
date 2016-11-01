import React from "react";
import reducer from "../reducers";
import {createStore, applyMiddleware} from "redux";
import thunk from "redux-thunk";


var initialize = (initialState = {}) => {
    const store = createStore(reducer, initialState, applyMiddleware(thunk));

    if (module.hot) {
        // Enable Webpack hot module replacement for reducers
        module.hot.accept('../reducers', () => {
            const nextReducer = require('../reducers');
            store.replaceReducer(nextReducer);
        });
    }
    return store;
};

export default initialize;
