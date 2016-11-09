import {logout} from "../../reducers/authentication";
import {connect} from "react-redux";
import UserControl from "../../components/userControl";

export default connect(
    state => ({username: state.authentication.username, isAuthenticated: state.authentication.isAuthenticated}),
    {logout}
)(UserControl);