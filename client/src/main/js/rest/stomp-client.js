var SockJS = require( 'sockjs-client' );
require( 'stompjs' );

function register( registrations ) {
    var socket = SockJS( 'http://Localhost:8081/sockjs' );
    var stompClient = Stomp.over( socket );
    stompClient.connect( {}, function ( frame ) {
        registrations.forEach( function ( registration ) {
            stompClient.subscribe( registration.route, registration.callback );
        } );
    } );
}

module.exports = {
    register: register
};