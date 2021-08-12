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
    <title>Log In - FlexibleJobs</title>
    <link rel="stylesheet" href="/FlexibleJobs/Front/css/login.css">

</head>
    <nav class="login-header">
        <img class="login-header-logo" src="/FlexibleJobs/Front/logo.png">
    </nav>

    <div class="login-wrapper">
        <form class="login-form">
            <label class="login-label" for="username">Log in to FlexibleJobs</label>
            <div class="login-username-input-wrapper">
                <input class="login-username-input" id="username" type="text" placeholder="Enter Username"/>
            </div>
            <input class="login-submit-button" type="submit" value="Continue with Username">
        </form>

    </div>
</body>
</html>
