<%--
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
        <img src="/Front/logo.png" style="width:100px;height:40px;">
    </a>

    <a href="">
        <p> Find Job</p>
    </a>

    <%
        String username = request.getParameter("username");
    %>
    <p style="float: right"> Welcome, <%= username%> </p>

</nav>

<div style="border-radius: 15px; position: absolute; left:30%; margin-left: 150px; width:1000px;height:900px;
border:0px solid #000; background-color: mediumseagreen" align="center">



</div>

<hr style="border-color: darkslategray">
<hr style="border-color: darkslategray">
</body>
</html>
