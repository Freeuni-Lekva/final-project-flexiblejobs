<%@ page import="servlets.FlexibleJobsConstants" %>
<%@ page import="states.State" %><%--
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
        <%State state = (State) request.getSession().getAttribute("state");%>


        <%if(state != null && state.getLoggedUser() != null && state.getError() == null){%>
            <%if (state.getLoggedUser().getType().equals("employee")){%>
            window.location.href = "/FlexibleJobs/Front/successfulLoginEmployee.jsp"
            <%} else if(state.getLoggedUser().getType().equals("employer")){%>
            window.location.href = "/FlexibleJobs/Front/successfulLoginEmployer.jsp"
            <%} else if(state.getLoggedUser().getType().equals("administrator")){%>
            window.location.href = "/FlexibleJobs/Front/successfulLoginAdmin.jsp"
            <%}%>
        <%}%>

        function goToLogin(){
            this.window.location.href = "/FlexibleJobs/Front/login.jsp"
        }

        window.onload = function () {
            <% if(state!=null && state.getError()!= null){ %>
                <% if(state.getError().contains(FlexibleJobsConstants.NULL_USERNAME) || state.getError().contains(FlexibleJobsConstants.EXISTED_ACCOUNT)) { %>
                document.getElementById("username").style.border = "1px solid red";
                <%} %>
                <% if(state.getError().contains(FlexibleJobsConstants.NULL_PASSWORD)) { %>
                document.getElementById("password").style.border = "1px solid red";
                <%}%>
                <% if(state.getError().contains(FlexibleJobsConstants.NULL_FIRSTNAME)) { %>
                document.getElementById("firstname").style.border = "1px solid red";
                <%} %>
                <% if(state.getError().contains(FlexibleJobsConstants.NULL_LASTNAME)) { %>
                document.getElementById("lastname").style.border = "1px solid red";
                <% }%>
                <% if(state.getError().contains(FlexibleJobsConstants.NULL_LIVING_PLACE)) { %>
                document.getElementById("livingPlace").style.border = "1px solid red";
                <%} %>
                <% if(state.getError().contains(FlexibleJobsConstants.NULL_HEADING)) { %>
                document.getElementById("profileHeading").style.border = "1px solid red";
                <%} %>
                <% if(state.getError().contains(FlexibleJobsConstants.NULL_DESCRIPTION)) { %>
                document.getElementById("profileDescription").style.border = "1px solid red";
                <%} %>
            <%}%>
        }
    </script>
</head>
<body>
    <nav class="register-header">
        <img class="register-header-logo" src="/FlexibleJobs/Front/logo.png">
    </nav>

    <div class="register-wrapper">
        <form class="register-form" id="register-form" action="/FlexibleJobs/registerHandler" method="post" >
            <label class="register-label" for="firstname">Register to FlexibleJobs</label>
            <div class="login-sides-wrapper">
                <div class="register-left-side-wrapper">
                    <input class="register-common-input" id="firstname" name="firstname" type="text" placeholder="Enter First Name"/>
                    <input class="register-common-input" id="lastname" name="lastname" type="text" placeholder="Enter Last Name"/>
                    <input class="register-common-input" id="username" name="username" type="text" placeholder="Enter Username"/>
                    <input class="register-common-input" id="password" name="password" type="password" placeholder="Enter Password"/>
                </div>
                <div class="register-right-side-wrapper">
                    <select class="register-select-input" id="userTypes" name="type" form="register-form">
                        <option class="register-select-option" value="employee">employee</option>
                        <option class="register-select-option" value="employer">employer</option></select>
                    <input class="register-common-input" id="livingPlace" name="livingPlace" type="text" placeholder="Enter Living Place"/>
                    <input class="register-common-input" id="profileHeading" name="profileHeading" type="text" placeholder="Enter Profile Heading"/>
                    <input class="register-common-input" id="profileDescription" name="profileDescription" type="text" placeholder="Enter Profile Description"/>
                </div>
            </div>
            <input class="register-submit-button" type="submit" value="Create Account">
        </form>
        <div>--- Already have a FlexibleJobs account? --- </div>
        <button class="login-button" onclick="goToLogin()">Log In</button>
    </div>

    <div class="register-footer">
        <div class="register-footer-description">?? 2021 - 2021 FlexibleJobs?? National Inc.</div>
        <div class="register-footer-terms-of-service">Terms of Service</div>
        <div class="register-footer-privacy-policy">Privacy Policy</div>
        <div class="register-footer-contact">Contact</div>
    </div>
</body>
</html>
