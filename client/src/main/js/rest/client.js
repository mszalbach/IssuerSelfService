import rest from "rest";
import errorCode from "rest/interceptor/errorCode";
import mime from "rest/interceptor/mime";
import basicAuth from "rest/interceptor/basicAuth";


module.exports = rest.wrap(mime)
    .wrap(errorCode, {code: 500})
    .wrap(basicAuth, {username: 'Ralf', password: 'ralf'});