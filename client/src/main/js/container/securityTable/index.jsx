import {connect} from "react-redux";
import SecurityTable from "../../components/securityTable";
import {deleteSecurity, postSecurityLink} from "../../reducers/securities";

export default connect(
    state => ({securities: state.securities.securities, attributes: state.securities.attributes}),
    {deleteSecurity, postSecurityLink}
)(SecurityTable);