import React from "react";
import {Alert, Button, ControlLabel, Form, FormControl, FormGroup} from "react-bootstrap";

export default class LoginPage extends React.Component {


    static propTypes = {
        login: React.PropTypes.func.isRequired,
        errorMessage: React.PropTypes.string
    };

    state = {
        user: "",
        password: ""
    };

    handleSubmit = () => {
        let {login} = this.props;
        login(this.state.user, this.state.password);
    };

    handleUserChange = (e) => {
        this.setState({user: e.target.value});
    };

    handlePasswordChange = (e) => {
        this.setState({password: e.target.value});
    };

    render() {
        let {errorMessage} = this.props;
        let errorPanel = errorMessage ? <Alert id="loginError" bsStyle="warning">{errorMessage}</Alert> : null;

        return (
            <div>
                {errorPanel}
                <Form>
                    <FormGroup>
                        <ControlLabel>User</ControlLabel>
                        <FormControl id="user" type="text" placeholder="User" value={this.state.user}
                                     onChange={this.handleUserChange}/>
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Password</ControlLabel>
                        <FormControl id="password" type="password" placeholder="Password" value={this.state.password}
                                     onChange={this.handlePasswordChange}/>
                    </FormGroup>
                    <Button id="login" onClick={this.handleSubmit}>Login</Button>
                </Form>
            </div>
        )
    }
}