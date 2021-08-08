<%@ page import="servlets.FlexibleJobsConstants" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.08.2021
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
</head>
<body>
<form action="/registerHandler" method="post">
    <label for="usernameInput">username: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="username" type="text" placeholder="Enter username" id="usernameInput">
    <label for="passwordInput">password: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="password" type="password" placeholder="Enter password" id="passwordInput">
    <label for="typeChoice"> choose type:</label>
    <select name="type" id="typeChoice">
        <option value="Employee"> Employee </option>
        <option value="Employer"> Employer </option>
        <option value="Administrator"> Administrator </option>
    </select>

    <input style="border-radius: 15px; background-color: palegreen" type="submit" value="Login">
    <br>
    <br>
</form>
</body>
</html>
