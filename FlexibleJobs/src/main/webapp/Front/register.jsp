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
    <link rel="stylesheet" href="/FlexibleJobs/Front/css/register.css">
    <link href = "/FlexibleJobs/Front/logo.png" rel="icon" type="image/gif">
    <script>
        function goToLogin(){
            this.window.location.href = "/FlexibleJobs/Front/login.jsp"
        }
    </script>
</head>
<body>
    <nav class="register-header">
        <img class="register-header-logo" src="/FlexibleJobs/Front/logo.png">
    </nav>

    <div class="register-wrapper">
        <form class="register-form" action="/FlexibleJobs/registerHandler" method="post">
            <label class="register-label" for="username">Register to FlexibleJobs</label>
            <input class="register-firstname-input" id="username" name="firstname" type="text" placeholder="First Name"/>
            <input class="register-lastname-input" id="password" name="lastname" type="text" placeholder="Last Name"/>
            <input class="register-submit-button" type="submit" value="Continue with Username">
        </form>
        <div>--- Already have a FlexibleJobs account? --- </div>
        <button class="login-button" onclick="goToLogin()">Log In</button>
    </div>
</body>
</html>
