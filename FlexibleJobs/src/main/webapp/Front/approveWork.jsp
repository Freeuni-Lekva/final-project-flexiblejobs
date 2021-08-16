<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
</head>
<%
    int jobId=Integer.parseInt(request.getParameter("jobId"));
    String employee=request.getParameter("employee");
%>
<body>
<a href=/FlexibleJobs/Front/EmployerJob.jsp?jobId=<%=jobId%>>Back</a><br><br>
<form action="/FlexibleJobs/approve?jobId=<%=jobId%>&?employee=<%=employee%>" method="post">
<label for="rate"> Rate the work:</label>
<select name="rate" id="rate">
    <option value="1"> 1</option>
    <option value="2"> 2</option>
    <option value="3"> 3</option>
    <option value="4"> 4</option>
    <option value="5"> 5</option>
</select>
<input type="submit" value="Approve">
</form>
</body>
</html>
