import React from "react";
import Menu from "../../container/menu";

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