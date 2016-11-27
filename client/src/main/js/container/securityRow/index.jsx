import {connect} from "react-redux";
import SecurityRow from "../../components/securityRow/index";
import {deleteSecurity, postSecurityLink} from "../../reducers/securities";

export default connect(
    state => ({attributes: state.securities.attributes}),
    {deleteSecurity, postSecurityLink}
)(SecurityRow);