<%@ page import="accounts.Account" %>
<%@ page import="accounts.PersonalData" %>
<%@ page import="accounts.PersonalDataDao" %>
<%@ page import="servlets.FlexibleJobsConstants" %><%--
  Created by IntelliJ IDEA.
  User: gioch
  Date: 10-Aug-21
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Account acc=(Account) request.getSession().getAttribute("loggedUser");
    PersonalData data= acc.getPersonalData();
    String webpage="/Front/successfulLogin";
    if(acc.getType().equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE))
        webpage += "Employee.jsp";
    else if(acc.getType().equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER))
        webpage+="Employer.jsp";
    else
        webpage+="Admin.jsp";
%>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
    Update Your Personal Information
</head>
<body>
<br>
<a href=<%=webpage%>>Back</a><br>
<form action="/updateData" method="post">
    <label for="firstname">Firstname: </label>
    <input name="firstname" type="text" id="firstname" value="<%=data.getFirstName()%>"><br>
    <label for="lastname">Lastname: </label>
    <input name="lastname" type="text" id="lastname" value="<%=data.getLastName()%>"><br>
    <label for="livingplace">Living Place: </label>
    <input name="livingplace" type="text" id="livingplace" value="<%=data.getLivingPlace()%>"><br>
    <label for="heading">Profile Heading: </label>
    <input name="heading" type="text" id="heading" value="<%=data.getProfileHeading()%>"><br>
    <label for="description">Profile Description: </label>
    <input name="description" type="text" id="description" value="<%=data.getProfileDescription()%>"><br>
    <label for="password">Password: </label>
    <input name="password" type="password" id="password" value="es cota xans davikidot"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>
