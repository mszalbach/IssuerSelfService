import React from "react";
import Menu from "../../container/menu";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap-theme.css";

export default class MainLayout extends React.Component {

    render() {
        return (
            <div>
                <Menu/>
                {this.props.children}
            </div>
        )
    }
}