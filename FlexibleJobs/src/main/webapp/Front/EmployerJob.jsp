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
<a href=/Front/successfulLoginEmployer.jsp>Back</a><br>
<a href=/approve?jobId=<%=jobId%>>Close Job</a><br>
<br><br><br>
<label>Applications</label><br>
<%
    for (Application app:apps) {
        Account employee=accDao.selectByUsername(app.getEmployee());
        PersonalData data= employee.getPersonalData();
        %>
<a href=/Front/UserProfile.jsp?username=<%=employee.getUserName()%>><%=data.getFirstName()+" "+data.getLastName()%></a><br>
        <%
        if(app.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_WAITING)){
            %>
<form>
    <input type="submit" value="Interview">
</form>
<form action="/appManager?jobId=+<%=jobId%>&?employee=<%=app.getEmployee()%>" method="post">
    <input type="submit" value="Hire">
</form>
<form action="/appManager?jobId=<%=jobId%>&?employee=<%=app.getEmployee()%>" method="post">
    <input type="submit" value="Reject">
</form><br>
            <%
        }else if(app.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_HIRED)){
            %>
<form action="/Front/approveWork.jsp?jobId=<%=jobId%>&?employee=<%=app.getEmployee()%>">
    <input type="submit" value="Approve">
</form>
<form action="/appManager?jobId=<%=jobId%>&?employee=<%=app.getEmployee()%>" method="post">
    <input type="submit" value="Reject">
</form><br>
            <%
        }
        %> <a href="/Front/Application.jsp?jobid=<%=jobId%>&?employee=<%=app.getEmployee()%>"Application></a><br><%
    }
            %>
</body>
</html>
