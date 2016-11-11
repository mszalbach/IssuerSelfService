import React from "react";
import {shallow, mount} from "enzyme";
import SecurityTable from "components/securityTable";

describe('<SecurityTable/>', () => {
    it('should exists', () => {
        let wrapper = shallow(<SecurityTable securities={securities} attributes={attributes}/>);
        expect(wrapper).not.toBe(null);
    });

    it('should have correct properties', () => {
        let wrapper = mount(<SecurityTable securities={securities} attributes={attributes}/>);
        expect(wrapper.prop('securities')).toBe(securities);
    });

    it('should contain enough <SecurityRow>', () => {
        let wrapper = shallow(<SecurityTable securities={securities} attributes={attributes}/>);
        expect(wrapper.find("SecurityRow").length).toBe(2);
    });

    it('should render correct ISIN', () => {
        let wrapper = shallow(<SecurityTable securities={securities} attributes={attributes}/>);
        expect(wrapper.html()).toMatch(/US0378331005/);
    });
    it('should call delete function', () => {
        let onDeleteSpy = jasmine.createSpy('onDeleteSpy');
        let wrapper = mount(<SecurityTable securities={securities} deleteSecurity={onDeleteSpy}
                                           attributes={attributes}/>);
        wrapper.find("#delete_US02079K1079").simulate('click');
        expect(onDeleteSpy).toHaveBeenCalledWith(
            {
                isin: 'US02079K1079',
                symbol: 'GOOG',
                _links: {self: {href: 'http://localhost:8081/api/securities/1'}}
            });
    });

});

var attributes = ["isin", "symbol"];

var securities = [
    {
        "isin": "US02079K1079",
        "symbol": "GOOG",
        "_links": {
            "self": {
                "href": "http://localhost:8081/api/securities/1"
            }
        }
    },
    {
        "isin": "US0378331005",
        "symbol": "AAPL",
        "_links": {
            "self": {
                "href": "http://localhost:8081/api/securities/3"
            }
        }
    }
];
