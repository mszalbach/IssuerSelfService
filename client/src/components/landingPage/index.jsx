import React from "react";

export default class Landing extends React.Component {

    render() {
        return (
            <div>
                <h1>Landing Page</h1>
                <p>Can be used to create new Securities.</p>
                <p>List of some valid ISIN which can be used for testing:</p>
                <ul>
                    <li>CH0123456789</li>
                    <li>US02079K1079</li>
                    <li>US5949181045</li>
                </ul>
            </div>
        )
    }
}