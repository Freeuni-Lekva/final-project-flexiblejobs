<%@ page import="jobs.Job" %>
<%@ page import="jobs.JobDatabase" %><%--
  Created by IntelliJ IDEA.
  User: Toko
  Date: 8/7/2021
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    int jobId=Integer.parseInt(request.getParameter("jobId"));
    JobDatabase jobDao=(JobDatabase) request.getServletContext().getAttribute("jobDao");
    Job job=jobDao.getJob(jobId);
%>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
    <%=job.getHeader()%><br>
</head>
<body>
<a href=/FlexibleJobs/Front/successfulLoginEmployee.jsp>Back</a><br>
<label>Status:<%=job.getJobStatus()%></label><br>
<label>Date posted:<%=job.getDate()%></label><br>
<label><%=job.getDescription()%></label><br>
<form action="/FlexibleJobs/Jobs_Front/adding_application.jsp">
    <input type="hidden" id="jobId" name="jobId" value="<%=jobId%>">
    <input type="submit" value="send application">
</form>
</body>
</html>
