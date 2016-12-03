import React from "react";
import {shallow, mount} from "enzyme";
import CreateDialog from "components/createDialog";


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
        var attributes = ["isin", "symbol"];
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

    xit('should call onCreateFunction with form values', () => {
        let onCreateSpy = jasmine.createSpy('onCreateSpy');
        let wrapper = mount(<CreateDialog schema={schema} addSecurity={onCreateSpy}/>);
        //did not trigger validate method
        document.getElementById("root_isin").value = "CH0123456789";
        document.getElementById("create").click();
        expect(onCreateSpy).toHaveBeenCalledWith({"isin": "CH0123456789"});

    });


});

