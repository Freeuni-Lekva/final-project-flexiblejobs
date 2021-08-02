<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.08.2021
  Time: 00:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
    <link href="/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body style="background-color: greenyellow">


<form action="/loginHandler" method="post">
    <h3 align="center"> Please log in </h3>
    <label for="usernameInput">User Name: </label>
    <input name="username" type="text" placeholder="Enter username" id="usernameInput">
    <br>
    <br>
    <input type="submit" value="Login">
    <br>
    <br>
</form>

</body>
</html>
