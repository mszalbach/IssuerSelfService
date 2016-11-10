import {logout} from "../../reducers/authentication";
import {connect} from "react-redux";
import Menu from "../../components/menu";

export default connect(
    state => ({username: state.authentication.username, isAuthenticated: state.authentication.isAuthenticated}),
    {logout}
)(Menu);