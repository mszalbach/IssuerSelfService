import React from "react";
import {shallow, mount} from "enzyme";
import Menu, {SignInButton, UserDropdown} from "components/menu";


describe('<Menu/>', () => {
    it('should exists', () => {
        let wrapper = shallow(<Menu />);
        expect(wrapper).not.toBe(null);
    });

    xit('should have correct properties', () => {
        let wrapper = mount( <Menu username="Adam"/> );
        expect( wrapper.prop( 'username' ) ).toBe( "Adam" );
    });

    xit('should render sign in button if not logged it', () => {
        let wrapper = shallow(<Menu isAuthenticated={false}/>);
        expect(wrapper.html()).toMatch(/Sign In/);
        expect(wrapper.find(SignInButton).length).toBe(1);

    });

    xit('should render info when logged in', () => {
        let wrapper = shallow( <Menu username="Adam" isAuthenticated={true}/> );
        expect( wrapper.html() ).toMatch( /Adam/ );
        expect(wrapper.find(UserDropdown).length).toBe(1);

    });

});

