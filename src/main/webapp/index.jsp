<%--
  Created by jochen on 6/30/13.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jetty Driven</title>
</head>
<body>
    <c:set var="baseURL" value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}"/>
    <ul>
        <li><a href="<c:out value='${baseURL}'/>">Home</a></li>
        <li><a href="<c:out value='${baseURL}'/>/user">User</a></li>
        <li><a href="/api">/api</a></li>
        <li><a href="<c:out value='${helper.buildLoginUrl(baseURL)}'/>">log in with google</a></li>
    </ul>

    <h1>WELKOM, Choose...</h1>
    <form action="${pageContext.request.contextPath}/rest/user" method="post">
        First name:
        <input type="text" name="firstname">
        <input type="submit" name="button1" value="send" />
    </form>


</body>
</html>