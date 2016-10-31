import React from "react";
import {shallow, mount} from "enzyme";
import CreateDialog from "components/createDialog";

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
        let wrapper = mount(<CreateDialog attributes={attributes}/>);
        expect(wrapper.prop('attributes')).toBe(attributes);
    });

    it('should contain enough <input> Elements', () => {
        let wrapper = mount(<CreateDialog attributes={["1", "2", "3"]}/>);
        wrapper.find("button").simulate('click');
        expect(document.getElementsByTagName("input").length).toBe(3);
    });

    it('should render correct attribute', () => {
        let wrapper = mount(<CreateDialog attributes={["correct"]}/>);
        wrapper.find("button").simulate('click');
        expect(document.getElementById("correct").placeholder).toMatch(/correct/);
    });

    it('should call onCreateFunction with form values', () => {
        let onCreateSpy = jasmine.createSpy('onCreateSpy');
        let wrapper = mount(<CreateDialog attributes={["correct"]} onCreate={onCreateSpy}/>);
        wrapper.find("button").simulate('click');
        document.getElementById("correct").value = "True";
        document.getElementById("create").click();
        expect(onCreateSpy).toHaveBeenCalledWith({"correct": "True"});

    });


});

