
<%@ page import="java.beans.IntrospectionException" %><%--
  Created by IntelliJ IDEA.
  User: Toko
  Date: 8/6/2021
  Time: 12:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Application</title>
</head>
<body>

<h1>Enter following information:</h1>
<%
    int jobId = Integer.parseInt(request.getParameter("jobId"));
%>
<form action = "/FlexibleJobs/ApplicationsServlet" method="post">

    <label for = "employee_name">Your Name: </label>
    <input type = "text" id = "employee_name" name = "employee_name">
    <br><br>
    <label for ="bid">Your Bid: </label>
    <input type="number" id = "bid" name="bid" step="0.01">
    <br><br>
    <label for = "letter">Letter: </label>
    <br><br>
    <textarea id = "letter" name="letter" cols="100" rows="10" placeholder="Max 5000 words"></textarea>
    <br><br>
    <input type="hidden" name = "id" value="<%=jobId%>">
    <input type = "hidden" name = "condition" value = "send">
    <input type="submit" name="send_application" value="Send Application">
</form>



</body>
</html>
