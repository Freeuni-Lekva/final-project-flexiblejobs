<%@ page import="accounts.Account" %>
<%@ page import="accounts.MessageDao" %>
<%@ page import="accounts.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gioch
  Date: 08-Aug-21
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Account active=(Account) request.getSession().getAttribute("loggedUser");
    String addressee=(String) request.getSession().getAttribute("addressee");
    MessageDao dao=(MessageDao) request.getServletContext().getAttribute("messageDao");
    List<Message> messages=dao.getConversation(active.getUserName(),addressee);
%>
<head>
    <title>Personal Chat with <%=addressee%></title>
</head>
<body>
<ul>
<%
    for (Message curr : messages) {
        String text = curr.getText();
        if (curr.getSender().equals(active.getUserName())) {
%>
    <li>
        <label><%="es marjvniv unda eweros"+text%> </label>
    </li>
<%
        } else {
%>
    <li>
        <label><%=text%> </label>
    </li>
<%
        }
    }
%>
</ul>
<form action="/Chat" method="post">
    <input name="message" type="text" id="message">
    <input type="submit" name="send" value="Send">
</form>
</body>
</html>
