import React from "react";
import {Button, FormGroup, FormControl, ControlLabel, Form, Alert} from "react-bootstrap";

export default class LoginPage extends React.Component {

    render() {
        const {errorMessage} = this.props;
        const errorPanel = errorMessage ? <Alert bsStyle="warning">{errorMessage}</Alert> : null;

        return (
            <div>
                {errorPanel}
                <Form>
                    <FormGroup>
                        <ControlLabel>User</ControlLabel>
                        <FormControl id="user" type="text" placeholder="User"/>
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Password</ControlLabel>
                        <FormControl id="password" type="password" placeholder="Password"/>
                    </FormGroup>
                    <Button id="login">Login</Button>
                </Form>
            </div>
        )
    }
}