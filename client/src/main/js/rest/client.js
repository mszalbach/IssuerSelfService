import rest from "rest";
import errorCode from "rest/interceptor/errorCode";
import mime from "rest/interceptor/mime";
import defaultRequest from "rest/interceptor/defaultRequest";

module.exports = {
    createClient: function () {
        let client = rest;

        var token = localStorage.getItem('auth-token');
        if (token) {
            client = client.wrap(defaultRequest, {headers: {'X-Auth-Token': token}})
        }

        return client.wrap(mime)
            .wrap(errorCode, {code: 400})
            .wrap(defaultRequest, {headers: {'X-Requested-With': 'XMLHttpRequest'}});
    }
};