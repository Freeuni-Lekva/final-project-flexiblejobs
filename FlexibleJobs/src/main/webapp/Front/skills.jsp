<%@ page import="states.State" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/14/2021
  Time: 8:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - FlexibleJobs</title>
    <link rel="stylesheet" href="/FlexibleJobs/Front/css/register.css">
    <link href = "/FlexibleJobs/Front/logo.png" rel="icon" type="image/gif">
    <script>
        <%State state = (State) request.getSession().getAttribute("state");%>

        <%if(state != null && state.getLoggedUser() != null){%>
        <%if (state.getLoggedUser().getType().equals("employee")){%>
        window.location.href = "/FlexibleJobs/Front/successfulLoginEmployee.jsp"
        <%} else if(state.getLoggedUser().getType().equals("employer")){%>
        window.location.href = "/FlexibleJobs/Front/successfulLoginEmployer.jsp"
        <%} else if(state.getLoggedUser().getType().equals("administrator")){%>
        window.location.href = "/FlexibleJobs/Front/successfulLoginAdmin.jsp"
        <%}%>
        <%}%>

        function allowDrop(ev) {
            ev.preventDefault();
        }

        function drag(ev) {
            ev.dataTransfer.setData("node", ev.target.id);
        }

        function drop(ev) {
            ev.preventDefault();
            const data = ev.dataTransfer.getData("node");
            const target = ev.target.id
            if (document.getElementById(target).className === "skills-common-input") {
                document.getElementById(document.getElementById(target).parentElement.id).append(document.getElementById(data))
            } else {
                ev.target.append(document.getElementById(data));
            }
        }

        window.onload = function () {
            const inputs = document.getElementsByClassName("skills-common-input");
            for( let i = 0; i < inputs.length; i++){
                inputs[i].addEventListener("dragstart", function (event) {
                    drag(event)
                })
            }

            document.getElementById("skills-right-side-wrapper").addEventListener("drop",function (event){
                drop(event)
            })
            document.getElementById("skills-right-side-wrapper").addEventListener("dragover",function (event){
                allowDrop(event)
            })
            document.getElementById("skills-left-side-wrapper").addEventListener("drop",function (event){
                drop(event)
            })
            document.getElementById("skills-left-side-wrapper").addEventListener("dragover",function (event){
                allowDrop(event)
            })
        }
    </script>
</head>
<body>
    <nav class="skills-header">
        <img class="skills-header-logo" src="/FlexibleJobs/Front/logo.png">
    </nav>

    <div class="skills-wrapper">
        <form class="skills-form" id="skills-form" action="https://developer.mozilla.org/en-US/docs/Web/API/HTML_Drag_and_Drop_API/Multiple_items" method="post">
            <label class="skills-label" for="skills-common-input">Skills for Dasakmebuli</label>
            <div class="skills-sides-labels-wrapper">
                <div id="skills-left-side-label" class="skills-left-side-label">
                    Skills to choose
                </div>
                <div id="skills-right-side-label" class="skills-right-side-label">
                    Chosen skills
                </div>
            </div>
            <div class="skills-sides-wrapper">
                <div id="skills-left-side-wrapper" class="skills-left-side-wrapper">
                    <input class="skills-common-input" id="skills-common-input" name="a" type="text" disabled="true"
                           value="Java" draggable="true"/>
                    <input class="skills-common-input" id="skills-common-input2" name="b" type="text" disabled="true"
                           value="Spring" draggable="true"/>
                    <input class="skills-common-input" id="skills-common-input3" name="c" type="text" disabled="true"
                           value="Python" draggable="true"/>
                    <input class="skills-common-input" id="skills-common-input4" name="c" type="text" disabled="true"
                           value="CSS" draggable="true"/>
                    <input class="skills-common-input" id="skills-common-input5" name="c" type="text" disabled="true"
                           value="HTML" draggable="true"/>
                    <input class="skills-common-input" id="skills-common-input6" name="c" type="text" disabled="true"
                           value="Angular" draggable="true"/>

                </div>
                <div id="skills-right-side-wrapper" class="skills-right-side-wrapper">

                </div>
            </div>

            <input class="skills-submit-button" type="submit" value="Add skills and continue">
        </form>
    </div>
</body>
</html>
