import React from "react";
import {shallow, mount} from "enzyme";
import LoginPage from "components/loginPage";


describe('<Menu/>', () => {
    it('should exists', () => {
        let wrapper = shallow(<LoginPage />);
        expect(wrapper).not.toBe(null);
    });

    it('should render error', () => {
        let wrapper = mount(<LoginPage errorMessage={"My super error message"}/>);
        expect(wrapper.html()).toMatch(/My super error message/);
    });

    it('should call login function', () => {
        let onLoginSpy = jasmine.createSpy('onLoginSpy');
        let wrapper = mount(<LoginPage login={onLoginSpy}/>);
        wrapper.setState({"user": "username", "password": "secret"});
        wrapper.find("#login").simulate('click');
        expect(onLoginSpy).toHaveBeenCalledWith("username", "secret");
    })

});

