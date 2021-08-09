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
</head>
<body style="background-color: darkseagreen">
<nav class = "menu">
    <a href="/webapp/index.jsp">
    <img src="/Front/logo.png" style="width:100px;height:40px;">
     </a>
</nav>
<hr style="border-color: darkslategray">
<hr style="border-color: darkslategray">
<div style="border-radius: 15px; position: absolute; left:30%; margin-left: 150px; width:500px;height:300px;
border:0px solid #000; background-color: mediumseagreen" align="center">
<form action="/loginHandler" method="post">
    <h3 align="center"> Please log in </h3>
    <label for="usernameInput">username: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="username" type="text" placeholder="Enter username" id="usernameInput">
    <br>
    <br>
    <input style="border-radius: 15px; background-color: palegreen" type="submit" value="Login">
    <br>
    <br>
</form>
    <a href="/Front/register.jsp">
        <input style="border-radius: 15px; background-color: palegreen" type="submit" value="Sign up">
    </a>
</div>
</body>
</html>
