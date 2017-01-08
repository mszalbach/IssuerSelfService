import React from "react";
import Form from "react-jsonschema-form";
import validator from "validator";
import {Button, Modal} from "react-bootstrap";

export default class CreateDialog extends React.Component {


    static propTypes = {
        addSecurity: React.PropTypes.func.isRequired,
        schema: React.PropTypes.object.isRequired
    };

    state = {
        showModal: false,
        formData: {},
        error: false
    };

    uiSchema = {
        "ui:order": ["isin", "symbol", "*"],
        "isin": {"ui:autofocus": true},
        //currently not used from the schema
        "state": {"ui:readonly": true}
    };

    close = () => {
        this.setState({showModal: false});
    };


    open = () => {
        this.setState({showModal: true});
    };

    onChange = (form) => {
        //needed or else the form resets
        this.setState({
            formData: form.formData,
            error: (form.errors.length > 0 )
        });
    };

    handleSubmit = (form) => {

        this.props.addSecurity(form.formData);

        this.close();
    };

    validate = (formData, errors) => {
        if (!validator.isISIN(formData.isin + "")) {
            errors.isin.addError("ISIN not valid");
        }
        return errors;
    };

    render() {

        return (
            <div>
                <Button onClick={this.open} id="openCreate">Create Security</Button>

                <Modal show={this.state.showModal} onHide={this.close}>
                    <Modal.Header closeButton>
                        <Modal.Title>Create Security</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form
                            formData={this.state.formData}
                            schema={this.props.schema}
                            uiSchema={this.uiSchema}
                            liveValidate={true}
                            validate={this.validate}
                            showErrorList={false}
                            autocomplete="off"
                            onChange={this.onChange}
                            onSubmit={this.handleSubmit}>
                            <Modal.Footer>
                                <Button id="create" type="submit" disabled={this.state.error}>Create</Button>
                                <Button onClick={this.close}>Close</Button>
                            </Modal.Footer>
                        </Form>
                    </Modal.Body>
                </Modal>
            </div>
        );
    }

}