var webpack = require('karma-webpack');
var webpackConfig = require('./webpack.config');

webpackConfig.externals = {
    'cheerio': 'window',
    'react/addons': true, // important!!
    'react/lib/ExecutionEnvironment': true,
    'react/lib/ReactContext': true
};

module.exports = function (config) {
    config.set({
        frameworks: ['jasmine'],
        files: [
            './node_modules/phantomjs-polyfill/bind-polyfill.js',
            './node_modules/babel-polyfill/browser.js',
            './node_modules/promise-polyfill/promise.js',
            'src/test/**/*\.test.jsx'

        ],
        browsers: ['PhantomJS'],
        preprocessors: {
            'src/test/**/*\.test.jsx': ['webpack'],
            'src/**/*.jsx': ['webpack']
        },

        webpack: webpackConfig,
        webpackMiddleware: {noInfo: true},
        singleRun: true
    });
};