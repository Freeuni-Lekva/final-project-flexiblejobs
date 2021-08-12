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
        <form class="register-form" id="register-form" >
            <label class="register-label" for="firstname">Register to FlexibleJobs</label>
            <div class="login-sides-wrapper">
                <div class="register-left-side-wrapper">
                    <input class="register-common-input" id="firstname" name="firstname" type="text" placeholder="Enter First Name"/>
                    <input class="register-common-input" id="lastname" name="lastname" type="text" placeholder="Enter Last Name"/>
                    <input class="register-common-input" id="username" name="username" type="text" placeholder="Enter Username"/>
                    <input class="register-common-input" id="password" name="password" type="password" placeholder="Enter Password"/>
                </div>
                <div class="register-right-side-wrapper">
                    <select class="register-select-input" id="userTypes" name="userType" form="register-form">
                        <option class="register-select-option" value="employee">employee</option>
                        <option class="register-select-option" value="employer">employer</option>
                        <option class="register-select-option" value="administrator">administrator</option>
                    </select>
                    <input class="register-common-input" id="livingPlace" name="livingPlace" type="text" placeholder="Enter Living Place"/>
                    <input class="register-common-input" id="profileHeading" name="profileHeading" type="text" placeholder="Enter Profile Heading"/>
                    <input class="register-common-input" id="profileDescription" name="profileDescription" type="text" placeholder="Enter Profile Heading"/>
                </div>
            </div>
            <input class="register-submit-button" type="submit" value="Create Account">
        </form>
        <div>--- Already have a FlexibleJobs account? --- </div>
        <button class="login-button" onclick="goToLogin()">Log In</button>
    </div>
</body>
</html>
