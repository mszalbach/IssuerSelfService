import React from "react";
import {shallow, mount} from "enzyme";
import UserControl, {SignInButton, UserDropdown} from "components/userControl";


describe('<UserControl/>', () => {
    it('should exists', () => {
        let wrapper = shallow(<UserControl />);
        expect(wrapper).not.toBe(null);
    });

    it('should have correct properties', () => {
        let wrapper = mount(<UserControl username="Marcel"/>);
        expect(wrapper.prop('username')).toBe("Marcel");
    });

    it('should render sign in button if not logged it', () => {
        let wrapper = shallow(<UserControl isAuthenticated={false}/>);
        expect(wrapper.html()).toMatch(/Sign In/);
        expect(wrapper.find(SignInButton).length).toBe(1);

    });

    it('should render info when logged in', () => {
        let wrapper = shallow(<UserControl username="Marcel" isAuthenticated={true}/>);
        expect(wrapper.html()).toMatch(/Marcel/);
        expect(wrapper.find(UserDropdown).length).toBe(1);

    });

});

