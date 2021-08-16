<%@ page import="accounts.AccountDao" %>
<%@ page import="accounts.Account" %>
<%@ page import="accounts.MessageDao" %>
<%@ page import="accounts.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.08.2021
  Time: 00:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FlexibleJobs | World's best freelancing Webpage</title>
    <script>
        <%AccountDao accountDao = (AccountDao) request.getServletContext().getAttribute("accountDao");%>
        <%Account to = accountDao.selectByUsername((String) request.getSession().getAttribute("conversationWith"));%>
        <%Account from = (Account) request.getSession().getAttribute("loggedUser");%>
        <%List<Message> messages = ((MessageDao) request.getServletContext().getAttribute("messageDao")).getConversation(from.getUserName(), to.getUserName());%>
        const chatSocket = new WebSocket("ws://localhost:8080/FlexibleJobs/chat-endpoint");

        <%if(session.getAttribute("sender")!=null){%>
        chatSocket.onopen = function (){
            chatSocket.send("reload")
        }
        <%session.removeAttribute("sender");%>
        <%}%>

        chatSocket.onmessage = function (ev) {
            if (ev.data === "reload") {
                window.location.href = "http://localhost:8080/FlexibleJobs/chat.jsp"
            }
        };
    </script>
</head>
<body>
<div style="border: 1px solid black; width: 40vw; margin: 0 auto; text-align: center">
    <h1>welcome to chat</h1>
    <h3>chatting to <%=to.getUserName()%></h3>

    <%if (messages.isEmpty()) {%>
    <h3>No Messages yet</h3>
    <%}%>
    <ul>
        <%for (int i = 0; i < messages.size(); i++) {%>
        <li style="display: flex">
            <h1><%=messages.get(i).getSender()%> : </h1>
            <h1 style="margin-left: 10px"><%=messages.get(i).getText()%></h1>
            <h1 style="margin-left: 10px"><%=messages.get(i).getTime().toString()%></h1>
        </li>
        <%}%>
    </ul>
    <form action="message" method="post">
        <input id="message-content" type="text" name="content"/>
        <input type="submit" value="send"/>
    </form>
</div>

</body>
</html>
