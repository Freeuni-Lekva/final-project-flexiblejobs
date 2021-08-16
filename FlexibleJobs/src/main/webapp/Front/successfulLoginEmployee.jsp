<%@ page import="states.State" %>
<%@ page import="accounts.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="accounts.Message" %>
<%@ page import="jobs.Job" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="accounts.AccountDao" %>
<%@ page import="jobs.JobDatabase" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.util.Set" %>
<%@ page import="servlets.FlexibleJobsConstants" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/15/2021
  Time: 5:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page - FlexibleJobs</title>
    <link rel="stylesheet" href="/FlexibleJobs/Front/css/employee-main.css">
    <link href = "/FlexibleJobs/Front/logo.png" rel="icon" type="image/gif">
    <script>
        <%State state = (State) request.getSession().getAttribute("state");%>

        <%if(state == null || state.getLoggedUser() == null){%>
            window.onload = function () {
                window.location.href = "/FlexibleJobs/Front/login.jsp";
            }
        <%}%>

        <%if(state!= null && state.getLoggedUser()!=null){%>
            <%if(state.getLoggedUser().getType().equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER)){%>
                window.onload = function () {
                    window.location.href = "/FlexibleJobs/Front/successfulLoginEmployer.jsp";
                }
            <%}%>
        <%}%>

        <%if(state == null)return;%>
        <%List<Account> contacts = state.getContacts();%>
        <%List<Message> conversation = state.getConversation();%>

        <% HashSet<Job> jobs = new HashSet(); %>
        <% AccountDao accountDao = (AccountDao) request.getServletContext().getAttribute("accountDao");%>
        <% JobDatabase jobDatabase = new JobDatabase((DataSource) request.getServletContext().getAttribute("datasource"));%>
        <%if (state != null && state.getLoggedUser() != null && state.getLoggedUser().getType().equals("employee")) {%>
            <%if(!state.isSearchedByFilter()){%>
                <% Account account = state.getLoggedUser(); %>
                <% ArrayList<String> skills = accountDao.getSkills(account.getUserName()); %>
                <% if(skills!= null){ %>
                    <% for(String skill:skills){%>
                        <% Set<Integer> tmp = jobDatabase.getJobsBySkill(skill);%>
                        <%if(tmp!=null){%>
                            <% for(Integer jobId: tmp){%>
                                <% Job job = jobDatabase.getJob(jobId);%>
                                <% jobs.add(job);%>
                            <% }%>
                        <% }%>
                    <% }%>
                <%}%>
            <%} else {%>
                <%String searchKeyword = state.getSearchKeyword();%>
                <%ArrayList<String> skills = accountDao.getSkills(state.getLoggedUser().getUserName());%>
                    <% if(skills!= null){ %>
                        <% for(String skill:skills){%>
                            <% Set<Integer> tmp = jobDatabase.getJobsBySkill(skill);%>
                            <%if(tmp!=null){%>
                                <% for(Integer jobId: tmp){%>
                                    <% Job job = jobDatabase.getJob(jobId);%>
                                    <%if(job.getHeader().equals(searchKeyword)){%>
                                        <% jobs.add(job);%>
                                    <%}%>
                                <% }%>
                            <% }%>
                        <% }%>
                    <%}%>
            <%}%>
            <%state.setSearchedByFilter(false);%>
            <%state.setSearchKeyword(null);%>
        <%}%>

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
</head>
<body>
    <nav class="employee-main-header">
        <img class="employee-main-header-logo" src="/FlexibleJobs/Front/logo.png">
        <form class="employee-main-header-search-wrapper" method="post" action="/FlexibleJobs/search">
            <input class="employee-main-header-search" name="keyword" type="text" placeholder="Search">
        </form>
        <button class="employee-main-header-profile-button">Profile</button>
        <button class="employee-main-header-logout-button" onclick="logout()">Log out</button>
        <form id="logout" action="/FlexibleJobs/logout" hidden="true" method="post">
        </form>
    </nav>

    <div class="employee-main-wrapper">
        <div class="employee-main-find-work-wrapper">
            <div class="employee-main-find-work-label">Find Work</div>
<%--            <input class="employee-main-find-work-search" type="text" placeholder="Search for jobs">--%>
        </div>

        <div class="employee-main-jobs-feed">
            <div class="employee-main-jobs-label-wrapper">
                <div class="employee-main-jobs-label">My feed</div>
            </div>
            <% for (Job job : jobs) {%>
                <div class="employee-main-job">
                    <a href="/FlexibleJobs/Jobs_Front/display_job.jsp?jobId=<%=job.getJobId()%>"> See more</a>
                    <div class="employee-job-name">
                        <% String header = job.getHeader(); %>
                        <%= header%>
                    </div>
                    <div class="employee-job-description">
                        <%String desc =  job.getDescription(); %>
                        <%= desc%>
                    </div>
                    <div class="employee-job-payment">
                        <div class="employee-job-payment-logo">✔</div>
                        <div class="employee-job-payment-description">
                            Payment verified
                        </div>
                        <div class="employee-job-payment-amount">
                            <% double budget = job.getBudget();%>
                            <%= budget%>
                        </div>
                    </div>

                    <div class="employee-job-duration">
                        <% String duration = job.getJobDuration();%>
                        <%= duration%>
                    </div>
                </div>
            <%}%>
        </div>
    </div>
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
