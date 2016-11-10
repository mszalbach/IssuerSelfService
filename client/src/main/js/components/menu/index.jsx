import React from "react";
import {Link} from "react-router";
import {Navbar, Nav, NavItem} from "react-bootstrap";
import {LinkContainer} from "react-router-bootstrap";
import UserControl from "../../components/userControl";

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
                    <LinkContainer to="/securities">
                        <NavItem>Securities</NavItem>
                    </LinkContainer>
                </Nav>
                <Nav pullRight>
                    <UserControl username={username} logout={logout} isAuthenticated={isAuthenticated}/>
                </Nav>
            </Navbar>
        )
    }
}