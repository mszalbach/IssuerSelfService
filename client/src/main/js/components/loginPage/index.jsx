import React from "react";
import {Button, FormGroup, FormControl, ControlLabel, Form, Alert} from "react-bootstrap";

export default class LoginPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: "",
            password: ""
        };
    }

    handleSubmit() {
        let {login} = this.props;
        login(this.state.user, this.state.password);
    }

    handleUserChange(e) {
        this.setState({user: e.target.value});
    }

    handlePasswordChange(e) {
        this.setState({password: e.target.value});
    }

    render() {
        const {errorMessage} = this.props;
        const errorPanel = errorMessage ? <Alert bsStyle="warning">{errorMessage}</Alert> : null;

        return (
            <div>
                {errorPanel}
                <Form>
                    <FormGroup>
                        <ControlLabel>User</ControlLabel>
                        <FormControl id="user" type="text" placeholder="User" value={this.state.user}
                                     onChange={(e)=>this.handleUserChange(e)}/>
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Password</ControlLabel>
                        <FormControl id="password" type="password" placeholder="Password" value={this.state.password}
                                     onChange={(e) => this.handlePasswordChange(e)}/>
                    </FormGroup>
                    <Button id="login" onClick={() => this.handleSubmit()}>Login</Button>
                </Form>
            </div>
        )
    }
}