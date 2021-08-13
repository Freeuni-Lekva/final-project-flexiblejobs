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
    Set<Job> jobs=JobDatabase.getJobsByEmployer(acc.getUserName());
%>
<head>

    <title>FlexibleJobs | World's best freelancing Webpage</title>
    Welcome <%=acc.getPersonalData().getFirstName()%><br>
    <a href="/updateData">Settings</a><br>
    <a href="/Front/updatePassword.jsp">Change Password</a><br>
    <a href="/Jobs_Front/Jobcreation.jsp">Post a job</a><br>


</head>
<body>

<ul>
    <%
        if(jobs.size()==0){
    %>
        <h1>You have not posted a job yet</h1>
    <%
        }
        for (Job job:jobs) {
    %>
            <li>
                <hr>
                <h1><%=job.getHeader()%></h1>
                <label><%=job.getJobStatus()+"                       "+job.getDate()%></label><br>
                <label>Applications:<%=job.getNumApplications()%></label><br>
                <label>Hires:<%=0%></label><br>
                <a href="EmployerJob.jsp?jobId=<%=job.getJobId()%>">See More</a>
                <hr>
            </li>
    <%
        }

    %>

</ul>
</body>
</html>
