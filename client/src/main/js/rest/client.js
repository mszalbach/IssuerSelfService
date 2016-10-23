var rest = require('rest');
var pathPrefix = require('rest/interceptor/pathPrefix');
var errorCode = require('rest/interceptor/errorCode');
var mime = require('rest/interceptor/mime');

module.exports = rest.wrap(mime)
    .wrap(errorCode, {code: 500})
    .wrap(pathPrefix, {prefix: '/api'});