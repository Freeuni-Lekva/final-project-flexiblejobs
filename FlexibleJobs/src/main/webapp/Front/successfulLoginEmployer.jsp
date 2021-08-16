<%@ page import="accounts.Account" %>
<%@ page import="jobs.JobDatabase" %>

<%@ page import="java.util.Set" %>
<%@ page import="jobs.Job" %>
<%--Created by IntelliJ IDEA.
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
    <a href="/FlexibleJobs/updateData">Settings</a><br>
    <a href="/FlexibleJobs/Front/updatePassword.jsp">Change Password</a><br>
    <a href="/FlexibleJobs/Jobs_Front/Jobcreation.jsp">Post a job</a><br>
    <a href="/FlexibleJobs/Front/UserProfile.jsp?username=<%=acc.getUserName()%>">Profile</a><br>

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
                <label>Status:<%=job.getJobStatus()%></label><br>
                <label>Date posted:<%=job.getDate()%></label><br>
                <a href="/FlexibleJobs/Front/EmployerJob.jsp?jobId=<%=job.getJobId()%>">See More</a>
                <hr>
            </li>
    <%
        }

    %>

</ul>
</body>
</html>
