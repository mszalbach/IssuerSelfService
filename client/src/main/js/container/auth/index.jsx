import React from "react";
import {connect} from "react-redux";
import {hashHistory} from "react-router";

export function requireAuthentication(Component) {

    class AuthComponent extends React.Component {

        componentWillMount() {
            if (!this.props.isAuthenticated) {
                hashHistory.replace({
                    pathname: '/login',
                    state: {nextPathname: hashHistory.getCurrentLocation().pathname}
                });
            }
        }

        render() {
            // render the component that requires auth (passed to this wrapper)
            return (
                <Component  {...this.props} />
            )
        }
    }

    const mapStateToProps =
        (state) => ({
            isAuthenticated: state.authentication.isAuthenticated
        });

    return connect(mapStateToProps)(AuthComponent);

}