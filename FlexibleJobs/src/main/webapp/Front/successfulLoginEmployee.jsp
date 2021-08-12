<%@ page import="states.EmployeeState" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.08.2021
  Time: 01:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile - FlexibleJobs</title>
    <link rel="stylesheet" href="/FlexibleJobs/Front/css/login.css">
    <link href = "/FlexibleJobs/Front/logo.png" rel="icon" type="image/gif">
</head>
<body>
    <%EmployeeState state = (EmployeeState) request.getSession().getAttribute("state");%>
    <div class="employee-name-wrapper">
        <%=state.getLoggedUser().getUserName()%>
    </div>
</body>
</html>
