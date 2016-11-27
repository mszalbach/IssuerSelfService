import {connect} from "react-redux";
import SecurityTable from "../../components/securityTable";

export default connect(
    state => ({securities: state.securities.securities, attributes: state.securities.attributes})
)(SecurityTable);