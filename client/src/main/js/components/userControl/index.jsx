import React from "react";
import {MenuItem, NavDropdown, NavItem} from "react-bootstrap";
import {LinkContainer} from "react-router-bootstrap";

export default class UserControl extends React.Component {


    static propTypes = {
        username: React.PropTypes.string,
        logout: React.PropTypes.func,
        isAuthenticated: React.PropTypes.bool,
    };


    render() {
        let {username, logout, isAuthenticated} = this.props;

        let control;
        if (isAuthenticated) {
            return control = <UserDropdown username={username} logout={logout}/>;
        } else {
            return control = <SignInButton />;
        }

    }
}

export class UserDropdown extends React.Component {


    render() {
        let {username, logout} = this.props;
        return (
            <NavDropdown title={username} id="userdropdown">
                <MenuItem header>Signed in as <strong>{username}</strong></MenuItem>
                <MenuItem divider/>
                <MenuItem id="logout" eventKey="1" onClick={logout}>Logout</MenuItem>
            </NavDropdown>)
    }
}


export class SignInButton extends React.Component {

    render() {
        return (
            <LinkContainer to="/login">
                <NavItem id="signin">Sign In</NavItem>
            </LinkContainer>
        )
    }
}