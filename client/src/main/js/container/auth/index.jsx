import React from "react";
import {connect} from "react-redux";
import {replace} from "react-router-redux";

export function requireAuthentication(Component) {

    class AuthComponent extends React.Component {

        componentWillMount() {
            if (!this.props.isAuthenticated) {
                this.props.replace({
                                       pathname: '/login',
                                       state: {nextPathname: this.props.history.location.pathname}
                                   } )

            }
        }

        render() {
            return this.props.isAuthenticated ? <Component  {...this.props} /> : null;
        }
    }

    return connect(
        (state) => ({
            isAuthenticated: state.authentication.isAuthenticated
        }),
        {replace}
    )(AuthComponent);

}