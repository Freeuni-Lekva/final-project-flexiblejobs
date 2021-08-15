<%@ page import="jobs.JobDatabase" %>
<%@ page import="jobs.Job" %>
<%@ page import="java.util.Set" %>

<html>
<body>

<%
    JobDatabase database = (JobDatabase) application.getAttribute("jobDao");
    Set<Job> jobs = database.getJobs();
    for(Job job : jobs){
        out.print("<a href=\"Jobs_Front/adding_application.jsp?id=" + job.getJobId() + "\">"
                + job.getHeader() + "</a>");
        out.print("<br/>");
    }
%>

</body>
</html>
