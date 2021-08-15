<%--
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
        function goToLogin(){
            this.window.location.href = "/FlexibleJobs/Front/login.jsp"
        }

    </script>
</head>
<body>
    <nav class="employee-main-header">
        <img class="employee-main-header-logo" src="/FlexibleJobs/Front/logo.png">
        <input class="employee-main-header-search" type="text" placeholder="Search">
        <button class="employee-main-header-profile-button">Profile</button>
        <button class="employee-main-header-logout-button">Log out</button>
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
</body>
</html>
