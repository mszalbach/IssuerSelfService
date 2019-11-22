import React from "react";
import {mount, shallow} from "enzyme";
import Menu, {SignInButton, UserDropdown} from "./index";
import {MemoryRouter} from 'react-router-dom';

const mountWithRouter = node => mount( <MemoryRouter>{node}</MemoryRouter> );

describe( '<Menu/>', () => {
    it( 'should exists', () => {
        let wrapper = shallow( <Menu/> );
        expect( wrapper ).not.toBe( null );
    } );

    it( 'should render sign in button if not logged it', () => {
        let wrapper = mountWithRouter( <Menu isAuthenticated={false}/> );
        expect( wrapper.html() ).toMatch( /Sign In/ );
        expect( wrapper.find( SignInButton ).length ).toBe( 1 );

    } );

    it( 'should render info when logged in', () => {
        let wrapper = mountWithRouter( <Menu username="Adam" isAuthenticated={true}/> );
        expect( wrapper.html() ).toMatch( /Adam/ );
        expect( wrapper.find( UserDropdown ).length ).toBe( 1 );

    } );

} );

