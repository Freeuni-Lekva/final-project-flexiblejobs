<%@ page import="accounts.Account" %>
<%@ page import="accounts.AccountDao" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.08.2021
  Time: 01:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
</head>
<body>
<nav class = "menu">
    <a href="/webapp/index.jsp">
        <img src="/Front/logo.png" style="width:100px;height:40px; float: left">
    </a>

    <a href="">
        <p style="float: left"> Find Job</p>
    </a>

    <%
        String username = request.getParameter("username");
    %>
    <p style="float: right"> Welcome, <%= username%> </p>

</nav>
<hr style="border-color: darkslategray">
<hr style="border-color: darkslategray">

<div style="border-radius: 15px; position: absolute; margin: 150px; width:50%;height:70%; border-color: darkslategray;
 background-color: antiquewhite" align="center">

    <div style="border-radius: 15px">
        <% AccountDao accountDao = (AccountDao) request.getServletContext().getAttribute("accountDao");
        Account account = accountDao.selectByUsername(username);
        %>
    </div>

    <div style="border-radius: 15px">
    </div>

    <div style="border-radius: 15px">
    </div>

</div>


</body>
</html>
