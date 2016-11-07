import React from "react";
import {Button, FormGroup, FormControl, ControlLabel, Form, Alert} from "react-bootstrap";

export default class LoginPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: "Ralf",
            password: "ralf"
        };
    }

    handleSubmit() {
        this.props.login(this.state.user, this.state.password);

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
                        <FormControl id="user" type="text" placeholder="User" value={this.state.user}/>
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Password</ControlLabel>
                        <FormControl id="password" type="password" placeholder="Password" value={this.state.password}/>
                    </FormGroup>
                    <Button id="login" onClick={() => this.handleSubmit()}>Login</Button>
                </Form>
            </div>
        )
    }
}