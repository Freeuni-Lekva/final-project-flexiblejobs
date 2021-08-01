<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.08.2021
  Time: 00:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
</head>
<body>

<%
    String username = request.getParameter("username");
%>

<form action="/loginHandler" method="post">
    <h3 align="center"> Please enter password </h3>
    <label for="usernameInput">User Name: </label>
    <input name="username" type="text" placeholder="Enter username" id="usernameInput" value="<%= username%>">
    <br>
    <br>
    <label for="passwordInput">Password: </label>
    <input name="password" id="passwordInput" placeholder="Enter password" type="password">
    <input type="submit" value="Login">
    <br>
    <br>
</form>
</body>
</html>
