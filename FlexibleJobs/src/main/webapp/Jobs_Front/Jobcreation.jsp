<%--
  Created by IntelliJ IDEA.
  User: Toko
  Date: 8/4/2021
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create job</title>
</head>
<body>
<h1>Enter following information</h1>

<form action = "/JobsServlet" method="post">
    <label for = "Jobtitle">Job Title: </label>
    <input type  = "text" id = "Jobtitle" name = "title" size = "50">
    <br> <br>
    <label for = "budget">Job Budget: </label>
    <input type  = "number" step = "0.01" id = "budget" name = "budget">
    <br> <br>
    <label for = "employer">Employer Name: </label>
    <input type = "text" id = "employer" name = "employer">
    <br><br>
    <label for = "jobduration">Estimated Time: </label>
    <input type="text" id = "jobduration" name="jobduration">
    <br> <br>

    <label for = "description">Job Description: </label>
    <br>
    <textarea id="description" name="description" rows="10" cols="100">
    </textarea>
    <br> <br>
    <input type = "submit" value = "Create Job">
</form>


</body>
</html>

