<%@ page import="jobs.Job" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="accounts.*" %><%--
  Created by IntelliJ IDEA.
  User: Lizi
  Date: 8/13/2021
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String username=request.getParameter("username");
%>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
</head>
<body>
<%
    PersonalData pd =  PersonalDataDao.selectByUsername(username);
    ReviewDao reviewDao = (ReviewDao) request.getServletContext().getAttribute("reviewDao");
%>
<h1><%=pd.getFirstName() + " " + pd.getLastName()%></h1>
<br>
<h2><%=pd.getProfileHeading()%></h2>
<br>
<h3><%=pd.getLivingPlace()%></h3>
<br>
<h2><%=pd.getProfileDescription()%></h2>
<br>
<%
    AccountDao accDao = (AccountDao) request.getServletContext().getAttribute("accountDao");
    Account acc = accDao.selectByUsername(username);
    Set<Job> workHistory = acc.getWorkHistory();
    for (Job job:workHistory)  {%>
<label><%=job.getHeader()%></label>
<%
    }
%>
</body>
</html>
