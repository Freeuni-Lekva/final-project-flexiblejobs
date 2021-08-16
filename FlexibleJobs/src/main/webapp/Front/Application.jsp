<%@ page import="jobs.Application" %>
<%@ page import="accounts.PersonalDataDao" %><%--
  Created by IntelliJ IDEA.
  User: Lizi
  Date: 8/14/2021
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
</head>
<body>
<%
    int jobId = Integer.parseInt(request.getParameter("jobid"));
    String employee = request.getParameter("employee");
    Application app = getApplicationsByJobAndEmployee(jobId, employee);
    String firstName = PersonalDataDao.selectByUsername(employee).getFirstName();
    String lastName = PersonalDataDao.selectByUsername(employee).getLastName();
%>
<h2><%=firstName+" " + lastName%></h2><br>
<h2><%="Bid: " + app.getBid()%>%></h2><br>
<h2><%="Date: " + app.getDate()%>%></h2><br>
<h2><%=app.getLetter()%>%></h2><br>
</body>
</html>
