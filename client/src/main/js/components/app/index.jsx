import React from "react";
import SecurityTable from "../securityTable";

export default class App extends React.Component {

    render() {
        var securities = [
            {
                "isin": "US02079K1079",
                "symbol": "GOOG",
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/securities/1"
                    },
                    "security": {
                        "href": "http://localhost:8081/api/securities/1"
                    }
                }
            },
            {
                "isin": "US0378331005",
                "symbol": "AAPL",
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/securities/3"
                    },
                    "security": {
                        "href": "http://localhost:8081/api/securities/3"
                    }
                }
            }
        ];

        return <SecurityTable securities={securities}/>;
    }
}