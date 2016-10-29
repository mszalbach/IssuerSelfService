import React from "react";
import {shallow, mount} from "enzyme";
import CreateDialog from "components/createDialog";

describe('<CreateDialog/>', () => {
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
        let wrapper = shallow(<CreateDialog attributes={[1, 2, 3]}/>);
        expect(wrapper.find("input").length).toBe(3);
    });

    it('should render correct attribute', () => {
        let wrapper = shallow(<CreateDialog attributes={["correct"]}/>);
        expect(wrapper.html()).toMatch(/correct/);
    });

    it('should call onCreateFunction with form values', () => {
        let onCreateSpy = jasmine.createSpy('onCreateSpy');
        let wrapper = mount(<CreateDialog attributes={["correct"]} onCreate={onCreateSpy}/>);
        wrapper.find('input').node.value = 'True';
        wrapper.find("button").simulate('click');
        expect(onCreateSpy).toHaveBeenCalledWith({"correct": "True"});

    });


});

