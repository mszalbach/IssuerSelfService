import {logout} from "../../reducers/authentication";
import {connect} from "react-redux";
import Menu from "../../components/menu";
import {withRouter} from "react-router-dom";

export default connect(
    state => ({username: state.authentication.username, isAuthenticated: state.authentication.isAuthenticated}),
    {logout}
    //needed for LinkContainer to work correctly with router
)(withRouter(Menu));