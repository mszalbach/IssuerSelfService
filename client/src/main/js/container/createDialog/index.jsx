import {connect} from "react-redux";
import CreateDialog from "../../components/createDialog";
import {addSecurity} from "../../reducers/securities";

export default connect(
    state => ({securities: state.securities.securities, attributes: state.securities.attributes}),
    {addSecurity}
)(CreateDialog);