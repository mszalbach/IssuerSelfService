import React from "react";
import ReactDOM from "react-dom";
import {Button, Modal, FormGroup, FormControl, ControlLabel, Form} from "react-bootstrap";

export default class CreateDialog extends React.Component {


    static propTypes = {
        addSecurity: React.PropTypes.func.isRequired,
        attributes: React.PropTypes.array.isRequired
    };

    state = {
        showModal: false,
    };

    close = () => {
        this.setState({showModal: false});
    };


    open = () => {
        this.setState({showModal: true});
    };

    handleSubmit = (e) => {
        e.preventDefault();
        var newSecurity = {};
        this.props.attributes.forEach(attribute => {
            newSecurity[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.addSecurity(newSecurity);
        this.props.attributes.forEach(attribute => {
            ReactDOM.findDOMNode(this.refs[attribute]).value = ''; // clear out the dialog's inputs
        });
        this.close();
    };

    render() {
        var inputs = this.props.attributes.map(attribute =>
            <FormGroup key={attribute}>
                <ControlLabel>{attribute}</ControlLabel>
                <FormControl id={attribute} type="text" placeholder={"Enter " + attribute} ref={attribute}/>
            </FormGroup>
        );

        return (
            <div>
                <Button onClick={this.open} id="openCreate">Create Security</Button>

                <Modal show={this.state.showModal} onHide={this.close}>
                    <Modal.Header closeButton>
                        <Modal.Title>Create Security</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            {inputs}
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button id="create" onClick={this.handleSubmit}>Create</Button>
                        <Button onClick={this.close}>Close</Button>
                    </Modal.Footer>
                </Modal>
            </div>
        );
    }

}