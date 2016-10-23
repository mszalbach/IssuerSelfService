import React from "react";
import {shallow, mount} from "enzyme";
import SecurityTable from "components/securityTable";

describe('<SecurityTable/>', () => {
    it('should exists', () => {
        const wrapper = shallow(<SecurityTable securities={securities}/>);
        expect(wrapper).not.toBe(null);
    });

    it('should have correct properties', () => {
        const wrapper = mount(<SecurityTable securities={securities}/>);
        expect(wrapper.prop('securities')).toBe(securities);
    });

    it('should contain enough <SecurityRow>', () => {
        const wrapper = shallow(<SecurityTable securities={securities}/>);
        expect(wrapper.find("SecurityRow").length).toBe(2);
    });

    it('should render correct ISIN', () => {
        const wrapper = shallow(<SecurityTable securities={securities}/>);
        expect(wrapper.html()).toMatch(/US0378331005/);
    });

});

var securities = [
    {
        "isin": "US02079K1079",
        "symbol": "GOOG",
        "_links": {
            "self": {
                "href": "http://localhost:8081/api/securities/1"
            },
            "security": {
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
            },
            "security": {
                "href": "http://localhost:8081/api/securities/3"
            }
        }
    }
];
