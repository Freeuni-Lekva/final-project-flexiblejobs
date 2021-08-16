<%@ page import="jobs.Job" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="accounts.*" %>
<%@ page import="servlets.FlexibleJobsConstants" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.RoundingMode" %><%--
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
    AccountDao accDao = (AccountDao) request.getServletContext().getAttribute("accountDao");
    Account acc = accDao.selectByUsername(username);
    String type = acc.getType();
    double rev = reviewDao.averageReview(username);
%>
<h1><%=pd.getFirstName() + " " + pd.getLastName()%></h1>
<br>
<h2><%=pd.getProfileHeading()%></h2>
<br>
<h3><%=pd.getLivingPlace()%></h3>
<br>
<h2>balance: <%=accDao.getCurrentBalance(username)%></h2>
<br>
<%
    if (type.equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE)) {%>
<h2>rating: <%=rev%></h2>
<%}
%>
<br>
<h2><%=pd.getProfileDescription()%></h2>
<br>
<br>
<h3>Work History</h3>
<br>
<%
    Set<Job> workHistory = acc.getWorkHistory();
    for (Job job:workHistory)  {%>
<label><%=job.getHeader()%></label>
<br>
<%
    if (type.equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE)) {%>
<h2>rating: <%=reviewDao.selectByJob(username, job.getJobId()).getPoints()%></h2><br> <%}
}
%>
</body>
</html>
