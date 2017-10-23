**POC de broadcaster usando Hazelcast en Atmosphere**

Para <u>agregar</u> clientes:

<p>[http://127.0.0.1:7011/atmosphere-chat-sse-bc/](http://127.0.0.1:7011/atmosphere-chat-sse-bc/)

<p>[http://152.139.124.183:7011/atmosphere-chat-sse-bc/](http://152.139.124.183:7011/atmosphere-chat-sse-bc/)

Para probar <u>(input)</u> que se envian datos a los clientes por diferentes servidores:

Primer servidor:

<p>[http://152.139.124.183:7011/atmosphere-chat-sse-bc/PruebaServlet?ip=152.139.124.183&mensaje=helloworld1](http://152.139.124.183:7011/atmosphere-chat-sse-bc/PruebaServlet?ip=152.139.124.183&mensaje=helloworld1)
<p>[http://127.0.0.1:7011/atmosphere-chat-sse-bc/PruebaServlet?ip=127.0.0.1&mensaje=helloworld1](http://127.0.0.1:7011/atmosphere-chat-sse-bc/PruebaServlet?ip=127.0.0.1&mensaje=helloworld1)

Segundo servidor:
<p>[http://152.139.124.183:7021/atmosphere-chat-sse-bc/PruebaServlet?ip=152.139.124.183&mensaje=helloworld2](http://152.139.124.183:7021/atmosphere-chat-sse-bc/PruebaServlet?ip=152.139.124.183&mensaje=helloworld2)
<p>[http://127.0.0.1:7021/atmosphere-chat-sse-bc/PruebaServlet?ip=127.0.0.1&mensaje=helloworld2](http://127.0.0.1:7021/atmosphere-chat-sse-bc/PruebaServlet?ip=127.0.0.1&mensaje=helloworld2)

