import {connect} from "react-redux";
import CreateDialog from "../../components/createDialog";
import {addSecurity} from "../../reducers/securities";

export default connect(
    state => ({schema: state.securities.schema}),
    {addSecurity}
)(CreateDialog);