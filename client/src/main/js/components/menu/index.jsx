import React from "react";
import {Link} from "react-router";
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from "react-bootstrap";
import {LinkContainer} from "react-router-bootstrap";

export default class Menu extends React.Component {

    static propTypes = {
        username: React.PropTypes.string,
        logout: React.PropTypes.func,
        isAuthenticated: React.PropTypes.bool,
    };

    render() {
        let {username, logout, isAuthenticated} = this.props;

        return (
            <Navbar fluid>
                <Navbar.Header>
                    <Navbar.Brand>
                        <Link to="/">
                            Issuer Self Service
                        </Link>
                    </Navbar.Brand>
                </Navbar.Header>
                <Nav>
                    <LinkContainer to="landing">
                        <NavItem>Landing</NavItem>
                    </LinkContainer>
                    <LinkContainer to="securities">
                        <NavItem>Securities</NavItem>
                    </LinkContainer>
                </Nav>
                <Nav pullRight>
                    { isAuthenticated ? (
                        <UserDropdown username={username} logout={logout}/>
                    ) : (
                        <SignInButton />
                    )}
                </Nav>
            </Navbar>
        )
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