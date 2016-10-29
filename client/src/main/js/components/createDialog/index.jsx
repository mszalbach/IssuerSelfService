import React from "react";
import ReactDOM from "react-dom";
export default class CreateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        var newSecurity = {};
        this.props.attributes.forEach(attribute => {
            newSecurity[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onCreate(newSecurity);
        this.props.attributes.forEach(attribute => {
            ReactDOM.findDOMNode(this.refs[attribute]).value = ''; // clear out the dialog's inputs
        });
        window.location = "#";
    }

    render() {
        var inputs = this.props.attributes.map(attribute =>
            <p key={attribute}>
                <input id={attribute} type="text" placeholder={attribute} ref={attribute}/>
            </p>
        );
        return <div>
            <h2>Create new Security</h2>
            <form>
                {inputs}
                <button id="create" onClick={this.handleSubmit}>Create</button>
            </form>
        </div>;
    }
}