<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Susbcribe to Events</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/javascript/jquery-2.0.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/javascript/EventSource.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/javascript/atmosphere.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/javascript/connection.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/javascript/application.js"></script>
</head>
<body>
    <input id="ctx" type="hidden" value="${pageContext.request.contextPath }" />
    <div id="header"><h3>Atmosphere Chat. Default transport is SSE, fallback is long-polling</h3></div>
    <div>
        <span id="status">Connecting...</span>
    </div>
    <div id="content"></div>
</body>
</html>
