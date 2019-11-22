import React from "react";
import {shallow, mount} from "enzyme";
import CreateDialog from "./index";


const schema = {
    "title": "Example Schema",
    "type": "object",
    "properties": {
        "isin": {
            "type": "string"
        },
        "symbol": {
            "type": "string"
        }
    }
};

describe('<CreateDialog/>', () => {


    afterEach(function () {
        //for modal dialogs need to clean the body because they are attached outside of the react component
        document.body.innerHTML = "";
    });

    it('should exists', () => {
        let wrapper = shallow(<CreateDialog attributes={[]}/>);
        expect(wrapper).not.toBe(null);
    });

    it('should have correct properties', () => {
        let wrapper = mount(<CreateDialog schema={schema}/>);
        expect(wrapper.prop('schema')).toBe(schema);
    });

    it('should contain enough <input> Elements', () => {
        let wrapper = mount(<CreateDialog schema={schema}/>);
        wrapper.find("button").simulate('click');
        expect(document.getElementsByTagName("input").length).toBe(2);
    });

    it('should render correct attribute', () => {
        let wrapper = mount(<CreateDialog schema={schema}/>);
        wrapper.find("button").simulate('click');
        expect(document.getElementById("root_isin").id).toMatch(/isin/);
    });

    it('should call onCreateFunction with form values', () => {
        let onCreateSpy = jasmine.createSpy('onCreateSpy');
        let wrapper = mount(<CreateDialog schema={schema} addSecurity={onCreateSpy}/>);
        wrapper.find("button").simulate('click');
        //could not type into field and trigger validation
        wrapper.setState({formData: {"isin": "CH0123456789"}});
        document.getElementById("create").click();
        expect(onCreateSpy).toHaveBeenCalledWith({"isin": "CH0123456789", "symbol": undefined});

    });

    it('should disable button if errors are set', () => {
        let wrapper = mount(<CreateDialog schema={schema}/>);
        wrapper.find("button").simulate('click');
        wrapper.setState({error: true});
        expect(document.getElementById("create").disabled).toBe(true);
    });

    it('should set error state onChangeMethod', () => {
        let wrapper = mount(<CreateDialog schema={schema}/>);
        wrapper.find("button").simulate('click');
        wrapper.instance().onChange({formData: {}, errors: ["error"]});
        expect(wrapper.state("error")).toBe(true);
    });


});

