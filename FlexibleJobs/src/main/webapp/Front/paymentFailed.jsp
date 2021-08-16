<%--
  Created by IntelliJ IDEA.
  User: Lizi
  Date: 8/15/2021
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
       int jobId =  Integer.parseInt(request.getParameter("jobId"));
       String employee=request.getParameter("employee");
%>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
</head>
<body>
<h1>Payment not accepted. Please, check your balance.</h1><br>
<form action="/fillBalance?jobId=<%=jobId%>&?employee=<%=employee%>" method="post">
    <input type="submit"value="Fill balance">
</form>
</body>
</html>
