<%@ page import="accounts.Account" %>
<%@ page import="jobs.JobDatabase" %>
<%@ page import="states.State" %>

<%@ page import="java.util.Set" %>
<%@ page import="jobs.Job" %>
<%@ page import="java.util.List" %>
<%@ page import="accounts.Message" %>
<%@ page import="accounts.AccountDao" %>
<%--Created by IntelliJ IDEA.
  User: User
  Date: 03.08.2021
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
State state = (State) request.getSession().getAttribute("state");
List<Account> contacts = state.getContacts();
List<Message> conversation = state.getConversation();
Account acc=(Account) request.getSession().getAttribute("loggedUser");
    AccountDao accountDao=(AccountDao) request.getServletContext().getAttribute("accountDao");
    double d=accountDao.getRating(acc.getUserName());
    int b=accountDao.getCurrentBalance(acc.getUserName());
    acc.setBalance(b);
    acc.setRating(d);
Set<Job> jobs=JobDatabase.getJobsByEmployer(acc.getUserName());
%>
<head>

    <title>FlexibleJobs | World's best freelancing Webpage</title>
    <link rel="stylesheet" href="/FlexibleJobs/Front/css/employee-main.css">

    Welcome <%=acc.getPersonalData().getFirstName()%><br>
    <a href="/FlexibleJobs/updateData">Settings</a><br>
    <a href="/FlexibleJobs/Front/updatePassword.jsp">Change Password</a><br>
    <a href="/FlexibleJobs/Jobs_Front/Jobcreation.jsp">Post a job</a><br>
    <a href="/FlexibleJobs/Front/UserProfile.jsp?username=<%=acc.getUserName()%>">Profile</a><br>
    <button class="employee-main-header-logout-button" onclick="logout()">Log out</button>
    <form id="logout" action="/FlexibleJobs/logout" hidden="true" method="post">
    </form>

</head>
<body>


<ul>
    <%
        if(jobs.size()==0){
    %>
        <h1>You have not posted a job yet</h1>
    <%
        }
        for (Job job:jobs) {
    %>
            <li>
                <hr>
                <h1><%=job.getHeader()%></h1>
                <label>Status:<%=job.getJobStatus()%></label><br>
                <label>Date posted:<%=job.getDate()%></label><br>
                <a href="/FlexibleJobs/Front/EmployerJob.jsp?jobId=<%=job.getJobId()%>">See More</a>
                <hr>
            </li>
    <%
        }

    %>

</ul>
<script>
    <%if(state.isChatStarted()){%>
    const chatSocket = new WebSocket("ws://localhost:8080/FlexibleJobs/chat-endpoint");

    chatSocket.onmessage = function (ev) {
        if (ev.data === "updateChat") {
            document.getElementById("chatUpdate").submit()
        }
    };
    <%}%>

    function goToLogin(){
        this.window.location.href = "/FlexibleJobs/Front/login.jsp"
    }

    let chatStarted = <%=state.isChatStarted()%>;

    let chatOpened = <%=state.isChatOpened()%>;


    function expandChat() {
        if (chatStarted) {
            if (chatOpened) {
                document.getElementsByClassName("opened-chat-wrapper")[0].className = "chat-wrapper";
                chatOpened = false
            } else {
                document.getElementsByClassName("chat-wrapper")[0].className = "opened-chat-wrapper";
                chatOpened = true
            }
        }
    }

    function startChat(to) {
        chatStarted = true
        chatOpened = true
        document.getElementsByClassName("opened-chat-wrapper")[0].style.visibility = "visible"
        document.getElementsByClassName("chat-partner-name")[0].innerHTML = to
    }

    function closeChat(){
        if (chatOpened) {
            document.getElementsByClassName("opened-chat-wrapper")[0].style.visibility = "hidden"
        } else {
            document.getElementsByClassName("chat-wrapper")[0].style.visibility = "hidden"
            document.getElementsByClassName("chat-wrapper")[0].className = "opened-chat-wrapper";
        }
        chatStarted = false
        chatOpened = false
    }

    function submitToChatServlet(to){
        document.getElementById("chat-" + to).submit()
    }

    function logout(){
        document.getElementById("logout").submit()
    }

    <%if(state.isChatStarted()){%>
    window.onload = function (){
        startChat("<%=state.getConversationWith().getUserName()%>");
        document.getElementsByClassName("chat-body")[0].scroll(0,document.getElementsByClassName("chat-body")[0].scrollHeight);
        <%if(state.isReloadForChatPartner()){%>
        chatSocket.send("updateChat");
        <%}%>
    }
    <%}%>

    <%if(state.isReloadForChatPartner()){%>
    <%state.setReloadForChatPartner(false);%>
    <%}%>
</script>
<div class="contacts-wrapper">
    <div class="contacts-header">
        <h3 class="contacts-tag">Contacts</h3>
    </div>
    <div class="contacts-body">
        <%for(Account contact : contacts){%>
        <div class="contact"  onclick="submitToChatServlet(this.children[1].innerHTML)">
            <img class="contact-picture"
                 src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png">
            <h4 class="contact-name"><%=contact.getUserName()%></h4>
            <form id="chat-<%=contact.getUserName()%>" action="/FlexibleJobs/chat" hidden="true" method="post">
                <input type="text" name="conversationWith" value="<%=contact.getUserName()%>">
            </form>
        </div>
        <%}%>
    </div>
</div>
<div class="opened-chat-wrapper">
    <div class="chat-header" onclick="expandChat()">
        <h3 class="chat-partner-name"></h3>
        <div class="chat-close-button" onclick="closeChat()">&#10006;</div>
    </div>
    <div class="chat-body">
        <%if(conversation == null || conversation.size() == 0){%>
        <div>No messages yet</div>
        <%} else {%>
        <%for(Message message : conversation){%>
        <div class="chat-message-wrapper">
            <h4 class="chat-message-sender"><%=message.getSender()%> :</h4>
            <h4 class="chat-message-content"> - <%=message.getText()%></h4>
        </div>
        <%}%>
        <%}%>
    </div>
    <div class="chat-footer">
        <form action="/FlexibleJobs/message" method="post">
            <label for="message-input"></label>
            <input id="message-input" class="chat-message-input" type="text" name="content">
            <input class="chat-message-send-button" type="submit" value="send">
        </form>
    </div>
    <form id="chatUpdate" action="/FlexibleJobs/chat" hidden="true" method="get"></form>
</div>
</body>
</html>
