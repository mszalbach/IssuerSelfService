import React from "react";
import {DropdownButton, MenuItem, Button} from "react-bootstrap";
import {hashHistory} from "react-router";

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
            control = <UserDropdown username={username} logout={logout}/>;
        } else {
            control = <SignInButton />;
        }

        return ( <div id="usercontrol">
            {control}
        </div>)
    }
}

export class UserDropdown extends React.Component {

    render() {
        let {username, logout} = this.props;
        return (<DropdownButton title={username} id="userdropdown">
            <MenuItem header>Signed in as <strong>{username}</strong></MenuItem>
            <MenuItem divider/>
            <MenuItem id="logout" eventKey="1" onClick={() => logout()}>Logout</MenuItem>
        </DropdownButton>)
    }
}


export class SignInButton extends React.Component {

    handleSubmit = () => {
        hashHistory.push("login");
    };

    render() {
        return <Button id="signin" onClick={this.handleSubmit}>Sign In</Button>
    }
}