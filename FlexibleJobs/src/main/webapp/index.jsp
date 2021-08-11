<%@ page import="jobs.JobDatabase" %>
<%@ page import="jobs.Job" %>
<%@ page import="java.util.Set" %>
<%@ page import="javax.sql.DataSource" %>
<html>
<body>

<%
    DataSource source = (DataSource) application.getAttribute("datasource");
    JobDatabase database = new JobDatabase(source);
    Set<Job> jobs = database.getJobs();
    for(Job job : jobs){
        out.print("<a href=\"Jobs_Front/adding_application.jsp?id=" + job.getJobId() + "\">"
                + job.getHeader() + "</a>");
        out.print("<br/>");
    }
%>

</body>
</html>
