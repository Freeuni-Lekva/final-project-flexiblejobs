<%@ page import="servlets.FlexibleJobsConstants" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="accounts.*" %><%--
  Created by IntelliJ IDEA.
  User: gioch
  Date: 10-Aug-21
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
Account acc=(Account) request.getSession().getAttribute("loggedUser");
    AccountDao accountDao=(AccountDao) request.getServletContext().getAttribute("accountDao");
    double d=accountDao.getRating(acc.getUserName());
    int b=accountDao.getCurrentBalance(acc.getUserName());
    acc.setBalance(b);
    acc.setRating(d);
String webpage="/FlexibleJobs/Front/successfulLogin";
    if(acc.getType().equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE))
        webpage += "Employee.jsp";
    else if(acc.getType().equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER))
        webpage+="Employer.jsp";
    else
        webpage+="Admin.jsp";
%>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
</head>
<body>
<h1> Your personal information has been updated</h1><br>
<a href=<%=webpage%>>Back</a><br>

</body>
</html>
