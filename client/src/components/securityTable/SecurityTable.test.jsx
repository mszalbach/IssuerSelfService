import React from "react";
import {mount, shallow} from "enzyme";
import SecurityTable from "./index";

const emptyFunction = () => void 0;

describe('<SecurityTable/>', () => {
    it('should exists', () => {
        let wrapper = shallow(<SecurityTable securities={securities} attributes={attributes}
                                             postSecurityLink={emptyFunction} deleteSecurity={emptyFunction}/>);
        expect(wrapper).not.toBe(null);
    });

    it('should have correct properties', () => {
        let wrapper = mount(<SecurityTable securities={securities} attributes={attributes}
                                           postSecurityLink={emptyFunction} deleteSecurity={emptyFunction}/>);
        expect(wrapper.prop('securities')).toBe(securities);
    });

    it('should contain enough <SecurityRow>', () => {
        let wrapper = shallow(<SecurityTable securities={securities} attributes={attributes}
                                             postSecurityLink={emptyFunction} deleteSecurity={emptyFunction}/>);
        expect(wrapper.find("SecurityRow").length).toBe(2);
    });

    it('should render correct ISIN', () => {
        let wrapper = shallow(<SecurityTable securities={securities} attributes={attributes}
                                             postSecurityLink={emptyFunction} deleteSecurity={emptyFunction}/>);
        expect(wrapper.html()).toMatch(/US0378331005/);
    });

    xit('should call delete function', () => {
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

let attributes = ["isin", "symbol"];

let securities = [
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
