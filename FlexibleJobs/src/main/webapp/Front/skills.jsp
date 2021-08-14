<%@ page import="states.State" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/14/2021
  Time: 8:14 PM
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

        <%if(state != null && state.getLoggedUser() != null){%>
        <%if (state.getLoggedUser().getType().equals("employee")){%>
        window.location.href = "/FlexibleJobs/Front/successfulLoginEmployee.jsp"
        <%} else if(state.getLoggedUser().getType().equals("employer")){%>
        window.location.href = "/FlexibleJobs/Front/successfulLoginEmployer.jsp"
        <%} else if(state.getLoggedUser().getType().equals("administrator")){%>
        window.location.href = "/FlexibleJobs/Front/successfulLoginAdmin.jsp"
        <%}%>
        <%}%>
    </script>
</head>
<body>
    <nav class="skills-header">
        <img class="skills-header-logo" src="/FlexibleJobs/Front/logo.png">
    </nav>

    <div class="skills-wrapper">
        <form class="skills-form" id="skills-form" action="/FlexibleJobs/registerHandler" method="post" >
            <label class="skills-label" for="firstname">Skills for Dasakmebuli</label>


            <input class="skills-submit-button" type="submit" value="Add skills and continue">
            <input class="skills-skip-button" type="submit" value="Skip adding skills">
        </form>
    </div>
</body>
</html>
