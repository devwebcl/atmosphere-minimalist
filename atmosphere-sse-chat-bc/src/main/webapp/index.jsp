<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba</title>
        <script type="text/javascript" src="javascript/jquery-2.0.3.js"></script>
        <script type="text/javascript" src="javascript/atmosphere-min.js"></script>

        <script type="text/javascript">
            var socket = atmosphere;
            var request = {url: document.location.toString() + 'atmosphere/prueba',
                contentType: "application/json",
                transport: 'sse',
                fallbackTransport: 'long-polling',
                reconnectInterval: 10000
                };

            request.onMessage = function (response) {
                var message = response.responseBody;
                alert(message);
            };

            var subSocket = socket.subscribe(request);

        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
