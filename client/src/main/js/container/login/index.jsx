import {login} from "../../reducers/authentication";
import {connect} from "react-redux";
import LoginPage from "../../components/loginPage";

export default connect(
    state => ({}),
    {login}
)(LoginPage);