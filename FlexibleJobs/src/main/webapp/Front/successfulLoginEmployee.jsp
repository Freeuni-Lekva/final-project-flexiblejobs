<%@ page import="states.State" %>
<%@ page import="accounts.Account" %>
<%@ page import="java.util.List" %><%--
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
        <%List<Account> contacts = state.getContacts();%>

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
                startChat("<%=state.getConversationWith().getUserName()%>")
            }
        <%}%>
    </script>
</head>
<body>
    <nav class="employee-main-header">
        <img class="employee-main-header-logo" src="/FlexibleJobs/Front/logo.png">
        <input class="employee-main-header-search" type="text" placeholder="Search">
        <button class="employee-main-header-profile-button">Profile</button>
        <button class="employee-main-header-logout-button" onclick="logout()">Log out</button>
        <form id="logout" action="/FlexibleJobs/logout" hidden="true" method="post">
        </form>
    </nav>

    <div class="employee-main-wrapper">
        <div class="employee-main-find-work-wrapper">
            <div class="employee-main-find-work-label">Find Work</div>
            <input class="employee-main-find-work-search" type="text" placeholder="Search for jobs">
        </div>

        <div class="employee-main-jobs-feed">
            <div class="employee-main-jobs-label-wrapper">
                <div class="employee-main-jobs-label">My feed</div>
            </div>
            <div class="employee-main-job">
                <div class="employee-job-name">
                    Business analysis
                </div>
                <div class="employee-job-description">
                    Codeigniter Developers. Long-term projects. Need to fix bugs Free of Charge. Unlimited Revision needed. Let us know your Industry experience in Application developments, type of applications developed etc. Please mention your lowest hourly & monthly rate, working hours per day, (Including the time band), and a number of days per week/month. So, it will be easy for us to evaluate all the offers and select the best offers for us
                </div>
                <div class="employee-job-payment">
                    <div class="employee-job-payment-logo">✔</div>
                    <div class="employee-job-payment-description">
                        Payment verified
                    </div>
                </div>
            </div>

            <div class="employee-main-job">
                <div class="employee-job-name">
                    Business analysis
                </div>
                <div class="employee-job-description">
                    Codeigniter Developers. Long-term projects. Need to fix bugs Free of Charge. Unlimited Revision needed. Let us know your Industry experience in Application developments, type of applications developed etc. Please mention your lowest hourly & monthly rate, working hours per day, (Including the time band), and a number of days per week/month. So, it will be easy for us to evaluate all the offers and select the best offers for us
                </div>
                <div class="employee-job-payment">
                    <div class="employee-job-payment-logo">✔</div>
                    <div class="employee-job-payment-description">
                        Payment verified
                    </div>
                </div>
            </div>

            <div class="employee-main-job">
                <div class="employee-job-name">
                    Business analysis
                </div>
                <div class="employee-job-description">
                    Codeigniter Developers. Long-term projects. Need to fix bugs Free of Charge. Unlimited Revision needed. Let us know your Industry experience in Application developments, type of applications developed etc. Please mention your lowest hourly & monthly rate, working hours per day, (Including the time band), and a number of days per week/month. So, it will be easy for us to evaluate all the offers and select the best offers for us
                </div>
                <div class="employee-job-payment">
                    <div class="employee-job-payment-logo">✔</div>
                    <div class="employee-job-payment-description">
                        Payment verified
                    </div>
                </div>
            </div>

            <div class="employee-main-job">
                <div class="employee-job-name">
                    Business analysis
                </div>
                <div class="employee-job-description">
                    Codeigniter Developers. Long-term projects. Need to fix bugs Free of Charge. Unlimited Revision needed. Let us know your Industry experience in Application developments, type of applications developed etc. Please mention your lowest hourly & monthly rate, working hours per day, (Including the time band), and a number of days per week/month. So, it will be easy for us to evaluate all the offers and select the best offers for us
                </div>
                <div class="employee-job-payment">
                    <div class="employee-job-payment-logo">✔</div>
                    <div class="employee-job-payment-description">
                        Payment verified
                    </div>
                </div>
            </div>
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
            <div class="chat-message-wrapper">
                <h4 class="chat-message-sender">Tiko :</h4>
                <h4 class="chat-message-content"> - Hello how are you?</h4>
            </div>
        </div>
        <div class="chat-footer">
            <form action="/message" method="post">
                <label for="message-input"></label>
                <input id="message-input" class="chat-message-input" type="text" name="content">
                <input class="chat-message-send-button" type="submit" value="send">
            </form>
        </div>
    </div>
</body>
</html>
