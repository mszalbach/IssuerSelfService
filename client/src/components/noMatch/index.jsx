import React from "react";
import {Link} from "react-router-dom";

export default class NoMatch extends React.Component {

    render() {
        return <div>
            <h1>404 Not Found</h1>
            <Link to="/">Return To Home</Link>
        </div>
    }
}