<%@ page import="states.State" %>
<%@ page import="servlets.FlexibleJobsConstants" %><%--
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
    <link href = "/FlexibleJobs/Front/logo.png" rel="icon" type="image/gif">
    <script>
        <%State state = (State) request.getSession().getAttribute("state");%>

        <%if(state != null && state.getLoggedUser() != null && !state.getError().get(0).equals(FlexibleJobsConstants.INCORRECT_PASSWORD)){%>
            <%if (state.getLoggedUser().getType().equals("employee")){%>
                window.location.href = "/FlexibleJobs/Front/successfulLoginEmployee.jsp"
            <%} else if(state.getLoggedUser().getType().equals("employer")){%>
                window.location.href = "/FlexibleJobs/Front/successfulLoginEmployer.jsp"
            <%} else if(state.getLoggedUser().getType().equals("administrator")){%>
                window.location.href = "/FlexibleJobs/Front/successfulLoginAdmin.jsp"
            <%}%>
        <%}%>

        function goToRegister(){
            this.window.location.href = "/FlexibleJobs/Front/register.jsp"
        }

        window.onload = function () {
            <% if(state!=null && state.getError()!= null){ %>
            <% if(state.getError().get(0).equals(FlexibleJobsConstants.NO_ACCOUNT)) { %>
            document.getElementsByClassName("login-username-input-wrapper")[0].style.border = "1px solid red";
            <%} else if(state.getError().get(0).equals(FlexibleJobsConstants.INCORRECT_PASSWORD)){ %>
            document.getElementsByClassName("login-password-input-wrapper")[0].style.borderColor = "red";
            <%}%>
            <%}%>
        }
    </script>
</head>
<body>
    <nav class="login-header">
        <img class="login-header-logo" src="/FlexibleJobs/Front/logo.png">
    </nav>

    <div class="login-wrapper">
        <form class="login-form" action="/FlexibleJobs/login" method="post">
            <label class="login-label" for="username">Log in to FlexibleJobs</label>
            <div class="login-username-input-wrapper">
                <span class="login-username-input-span">&#128231;</span>
                <input class="login-username-input" id="username" name="username" type="text" placeholder="Enter Username"/>
            </div>
            <div class="login-password-input-wrapper">
                <span class="login-password-input-span">&#128271;</span>
                <input class="login-password-input" id="password" name="password" type="password" placeholder="Enter Password"/>
            </div>
            <input class="login-submit-button" type="submit" value="Continue with Username">
        </form>
        <div>--- Don't have an FlexibleJobs account? --- </div>
        <button class="register-button" onclick="goToRegister()">Sign Up</button>
    </div>
    <div class="login-footer">
        <div class="login-footer-description">© 2021 - 2021 FlexibleJobs® National Inc.</div>
        <div class="login-footer-terms-of-service">Terms of Service</div>
        <div class="login-footer-privacy-policy">Privacy Policy</div>
        <div class="login-footer-contact">Contact</div>
    </div>
</body>
</html>
