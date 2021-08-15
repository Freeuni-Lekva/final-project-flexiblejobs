<%@ page import="states.State" %><%--
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

        function goToLogin(){
            this.window.location.href = "/FlexibleJobs/Front/login.jsp"
        }

        function logout(){
            <%request.getSession().setAttribute("state", null);%>
            goToLogin()
        }

        let chatStarted = /*<%--<%=sleState.isChatStarted()%>--%>;*/ false
        let chatOpened = /*<%--<%=sleState.isChatOpened()%>--%>;*/ false


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
            document.getElementsByClassName("chat-wrapper")[0].style.visibility = "visible"
            document.getElementsByClassName("chat-partner-name")[0].innerHTML = to

            chatSocket.send("updateChat")
        }

        function closeChat(){
            if (chatOpened) {
                document.getElementsByClassName("opened-chat-wrapper")[0].style.visibility = "hidden"
                document.getElementsByClassName("opened-chat-wrapper")[0].className = "chat-wrapper";
            } else {
                document.getElementsByClassName("chat-wrapper")[0].style.visibility = "hidden"
            }
            chatStarted = false
            chatOpened = false
        }
    </script>
</head>
<body>
    <nav class="employee-main-header">
        <img class="employee-main-header-logo" src="/FlexibleJobs/Front/logo.png">
        <input class="employee-main-header-search" type="text" placeholder="Search">
        <button class="employee-main-header-profile-button">Profile</button>
        <button class="employee-main-header-logout-button" onclick="logout()">Log out</button>
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
            <div class="contact"  onclick="startChat(this.children[1].innerHTML)">
                <img class="contact-picture"
                     src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png">
                <h4 class="contact-name">Other</h4>
                <!--                <form id="chat-<%--<%=acc.getUserName()%>--%>" action="/chat" hidden="true" method="post">-->
                <!--                    <input type="text" name="conversationWith" value="Other">-->
                <!--                </form>-->
            </div>
            <div class="contact"  onclick="startChat(this.children[1].innerHTML)">
                <img class="contact-picture"
                     src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png">
                <h4 class="contact-name">Mother</h4>
                <!--                <form id="chat-<%--<%=acc.getUserName()%>"--%> action="/chat" hidden="true" method="post">-->
                <!--                    <input type="text" name="conversationWith" value="Other">-->
                <!--                </form>-->
            </div>
        </div>
    </div>
    <div class="chat-wrapper">
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
