const path = require( 'path' );
const webpack = require( 'webpack' );
const merge = require( 'webpack-merge' );
const validate = require( 'webpack-validator' );
const pkg = require( "./package.json" );
const CleanWebpackPlugin = require( 'clean-webpack-plugin' );
const ExtractTextPlugin = require( 'extract-text-webpack-plugin' );
const HtmlWebpackPlugin = require( 'html-webpack-plugin' );


const PATHS = {
    app: path.join( __dirname, 'src/main/js' ),
    build: path.join( __dirname, 'target/build' )
};


const common = {
    entry: {
        app: PATHS.app,
        vendor: Object.keys( pkg.dependencies )
    },
    devtool: 'source-map',
    output: {
        path: PATHS.build,
        filename: '[name].js'
    },

    resolve: {
        modulesDirectories: ['node_modules', './src/main/js'],
        extensions: ['', '.js', '.jsx']
    },

    module: {
        loaders: [
            {test: /\.js[x]?$/, exclude: /node_modules/, loader: 'babel-loader'},
            {test: /\.css$/, loader: ExtractTextPlugin.extract( 'style-loader', 'css-loader' )},
            //needed for bootstrap
            {test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/font-woff'},
            {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file'},
            {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=image/svg+xml'},
            {test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/octet-stream'},
        ]
    },


    plugins: [
        new ExtractTextPlugin( 'bundle.css' ),
        new HtmlWebpackPlugin( {
            template: './src/main/js/index.html'
        } )
    ]
};


var config;

// Detect how npm is run and branch based on that
switch ( process.env.npm_lifecycle_event ) {
    case 'build':
        config = merge( common, {
            output: {
                filename: '[name].[chunkhash].js'
            },
            devtool: 'cheap-source-map',
            plugins: [new webpack.DefinePlugin( {
                'process.env.NODE_ENV': JSON.stringify( 'production' )

            } ),
                new webpack.optimize.UglifyJsPlugin( {
                    compress: {
                        warnings: false
                    }
                } ),
                new webpack.optimize.CommonsChunkPlugin( "vendor", "[name].[chunkhash].js" ),
                new CleanWebpackPlugin( PATHS.build )
            ]

        } );
        break;
    default:
        config = merge( common, {
                devtool: 'eval-source-map'
            }
        );

}

module.exports = validate( config );