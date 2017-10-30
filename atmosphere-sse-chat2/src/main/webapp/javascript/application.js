$(function () {
    "use strict";

    var content = $('#content');
    var input = $('#input');
    var status = $('#status');
    var socket = getSocket();
    var request = getConnection();

    request.onOpen = function (response) {
        content.html($('<p>', {text: 'Atmosphere connected using ' + response.transport}));
        input.removeAttr('disabled').focus();
        status.html($('<p>', {text : 'UUID: ' + response.request.uuid}));
    };

    request.onMessage = function (response) {
        var message = response.responseBody;
        addMessage(message);
    };

    request.onReconnect = function (request, response) {
        content.html($('<p>', { text: 'Connection lost, trying to reconnect. Trying to reconnect ' + request.reconnectInterval}));
    };

    request.onReopen = function (response) {
        content.html($('<p>', { text: 'Atmosphere re-connected using ' + response.transport }));
    };

    request.onClose = function (response) {
        console.log("Close");
    };

    request.onError = function (response) {
        content.html($('<p>', {text: 'Sorry, but there\'s some problem with your '
                    + 'socket or the server is down'}));
    };

    var subSocket = socket.subscribe(request);

    input.keydown(function(e) {
        if (e.keyCode === 13) {
            var msg = $(this).val();
            subSocket.push(msg);
        }
    });

    function addMessage(message) {
        content.append('<p>' + message + '</p>');
    }
});

