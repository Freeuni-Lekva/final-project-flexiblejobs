<%@ page import="accounts.Account" %>
<%@ page import="servlets.FlexibleJobsConstants" %>
<%@ page import="accounts.Employer" %>
<%@ page import="accounts.AccountDao" %><%--
  Created by IntelliJ IDEA.
  User: gioch
  Date: 11-Aug-21
  Time: 12:08
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
    Update Your Password
</head>
<body>
<br>
<a href=<%=webpage%>>Back</a><br>

<form action="/FlexibleJobs/updatePassword" method="post">
    <label for="password">New Password: </label>
    <input name="password" type="password" id="password" placeholder="Enter New Password"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>
