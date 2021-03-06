<%@ page import="jobs.JobDatabase" %>
<%@ page import="jobs.Job" %>
<%@ page import="jobs.ApplicationDAO" %>
<%@ page import="jobs.Application" %>
<%@ page import="java.util.Set" %>
<%@ page import="servlets.FlexibleJobsConstants" %>
<%@ page import="accounts.AccountDao" %>
<%@ page import="accounts.Account" %>
<%@ page import="accounts.PersonalData" %>
<%--
  Created by IntelliJ IDEA.
  User: gioch
  Date: 09-Aug-21
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    int jobId=Integer.parseInt(request.getParameter("jobId"));
    JobDatabase jobDao=(JobDatabase) request.getServletContext().getAttribute("jobDao");
    Job job=jobDao.getJob(jobId);

    ApplicationDAO appdao=(ApplicationDAO) request.getServletContext().getAttribute("appDao");
    Set<Application> apps=appdao.getApplicationsForJob(jobId);

    AccountDao accDao=(AccountDao) request.getServletContext().getAttribute("accountDao");
%>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
    <%=job.getHeader()%><br>
</head>
<body>
<label>Status:<%=job.getJobStatus()%></label><br>
<label>Date posted:<%=job.getDate()%></label><br>
<label><%=job.getDescription()%></label><br>
<label>Applications:<%=apps.size()%></label><br>
<a href=/FlexibleJobs/Front/successfulLoginEmployer.jsp>Back</a><br>
<a href=/FlexibleJobs/approve?jobId=<%=jobId%>>Close Job</a><br>
<br><br><br>
<label>Applications</label><br>
<%
    for (Application app:apps) {
        Account employee=accDao.selectByUsername(app.getEmployee());
        PersonalData data= employee.getPersonalData();
        %>
<a href=/FlexibleJobs/Front/UserProfile.jsp?username=<%=employee.getUserName()%>><%=data.getFirstName()+" "+data.getLastName()%></a><br>
        <%
        if(app.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_WAITING)){
            %>
<form action="/FlexibleJobs/appManager?jobId=<%=jobId%>&?employee=<%=app.getEmployee()%>" method="post">
    <input type="hidden" id="be" name="be" value="hire">
    <input type="submit" value="Hire">
</form>
<form action="/FlexibleJobs/appManager?jobId=<%=jobId%>&?employee=<%=app.getEmployee()%>" method="post">
    <input type="hidden" id="b" name="be" value="rej">
    <input type="submit" value="Reject">
</form><br>
            <%
        }else if(app.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_HIRED)){
            %>
<form action="/FlexibleJobs/Front/approveWork.jsp?jobId=<%=jobId%>&?employee=<%=app.getEmployee()%>">
    <input type="submit" value="Approve">
</form>
<form action="/FlexibleJobs/appManager?jobId=<%=jobId%>&?employee=<%=app.getEmployee()%>" method="post">
    <input type="hidden" id="bee" name="be" value="rej">
    <input type="submit" value="Reject">
</form><br>
            <%
        }
        %> <a href="/FlexibleJobs/Front/Application.jsp?jobid=<%=jobId%>&?employee=<%=app.getEmployee()%>"Application></a><br><%
    }
            %>
</body>
</html>
