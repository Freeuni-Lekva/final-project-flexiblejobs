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
    <title>Register - FlexibleJobs</title>
    <link rel="stylesheet" href="/FlexibleJobs/Front/css/login.css">
    <link href = "/FlexibleJobs/Front/logo.png" rel="icon" type="image/gif">
</head>
<body>
<form action="/registerHandler" method="post">
    <label for="usernameInput">username: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="username" type="text"
           placeholder="Enter username" id="usernameInput">
    <br>
    <br>

    <label for="firstnameInput">firstname: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="firstname" type="text"
           placeholder="Enter firstname" id="firstnameInput">

    <br>
    <br>

    <label for="lastnameInput">lastname: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="lastname" type="text"
           placeholder="Enter lastname" id="lastnameInput">

    <br>
    <br>

    <label for="livingPlaceInput">living place: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="livingPlace" type="text"
           placeholder="Enter living place" id="livingPlaceInput">

    <br>
    <br>

    <label for="profileHeadingInput">profile heading: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="profileHeading" type="text"
           placeholder="Enter profile heading" id="profileHeadingInput">

    <br>
    <br>

    <label for="profileDescriptionInput">profile description: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="profileDescription" type="text"
           placeholder="Enter profile description" id="profileDescriptionInput">

    <br>
    <br>

    <label for="passwordInput">password: </label>
    <input style="background-color: palegreen; border-radius: 15px" name="password" type="password"
           placeholder="Enter password" id="passwordInput">

    <br>
    <br>

    <label for="typeChoice"> choose type:</label>
    <select name="type" id="typeChoice">
        <option value="employee"> Employee</option>
        <option value="employer"> Employer</option>
        <option value="administrator"> Administrator</option>
    </select>

    <br>
    <br>

    <input style="border-radius: 15px; background-color: palegreen" type="submit" value="Login">
    <br>
    <br>
</form>
</body>
</html>
