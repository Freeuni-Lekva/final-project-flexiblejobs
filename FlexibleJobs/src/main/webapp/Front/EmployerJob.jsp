<%@ page import="jobs.JobDatabase" %>
<%@ page import="jobs.Job" %><%--
  Created by IntelliJ IDEA.
  User: gioch
  Date: 09-Aug-21
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    int jobId=Integer.parseInt(request.getParameter("id"));
    JobDatabase jobDao=(JobDatabase) request.getServletContext().getAttribute("jobDao");
    Job job=jobDao.getJob(jobId);


%>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
    <%=job.getHeader()%>
</head>
<body>
<label><%=job.getJobStatus()+"                       "+job.getDate()%></label><br>
<label><%=job.getDescription()%></label><br>
<label>Applications:<%=job.getNumApplications()%></label><br>
<label>Hires:<%=0%></label><br>
</body>
</html>
