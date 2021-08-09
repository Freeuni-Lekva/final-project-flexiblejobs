<%@ page import="accounts.Account" %>
<%@ page import="jobs.JobDatabase" %>

<%@ page import="java.util.Set" %>
<%@ page import="jobs.Job" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.08.2021
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Account acc=(Account) request.getSession().getAttribute("loggedUser");
    JobDatabase dao=(JobDatabase) request.getServletContext().getAttribute("JobDao");
    Set<Job> jobs=dao.getJobsByEmployer(acc.getUserName());
%>
<head>

    <title>FlexibleJobs | World's best freelancing Webpage</title>

</head>
<body>
<ul>
    <%
        for (Job job:jobs) {
    %>
            <li>
                <hr>
                <h1>jobis dasaxeleba</h1>
                <label>statusi<%="                       "%> tarigi</label><br>
                <label>Applications:<%=12%></label><br>
                <label>Hires:<%=12%></label><br>
                <a href="EmployerJob?id=<%=job.getJobId()%>">See More</a>
                <hr>
            </li>
    <%
        }

    %>

</ul>
</body>
</html>
