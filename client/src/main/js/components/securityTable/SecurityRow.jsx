import React from "react";
import {Button} from "react-bootstrap";

export default class SecurityRow extends React.Component {

    static propTypes = {
        security: React.PropTypes.object.isRequired,
        attributes: React.PropTypes.array.isRequired,
        deleteSecurity: React.PropTypes.func.isRequired,
        postSecurityLink: React.PropTypes.func.isRequired
    };


    state = {
        ignoredLinks: ["security", "self"]
    };


    handleDelete = () => {
        this.props.deleteSecurity(this.props.security);
    };

    handleEvent = (action) => {
        this.props.postSecurityLink(this.props.security, action);
    };

    render() {
        let {security, attributes} = this.props;

        let actions = Object.keys(security._links)
            .filter(link => !this.state.ignoredLinks.includes(link))
            .map(link => <Button key={link + "_" + security.isin}
                                 id={link + "_" + security.isin}
                                 onClick={() => this.handleEvent(link)}>{link}</Button>);

        return (
            <tr>
                {attributes.map(attribute =>
                    <td key={attribute}>{security[attribute]}</td>)
                }
                <td id={"actions_" + security.isin}>
                    {actions}
                    <Button id={"delete_" + security.isin} onClick={this.handleDelete}>X</Button>
                </td>
            </tr>
        );
    }
}