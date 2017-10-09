<%--suppress XmlDuplicatedId --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>PRS - Authentication</title>
    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/loginAnimated.css">
</head>
<body>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">


<div class="container">
    <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-6 col-md-offset-3">
            <div id="iosBlurBg">
                <div id="whiteBg"></div>
            </div>
            <div id="bottomEnter"></div>
            <div id="bottomBlurBg"></div>

            <!-- Login Form -->
            <div class="loginForm">
                <div class="title">
                    <p>LOG INTO<br><span>Purchase Request</span><br><span>System</span></p>
                    <hr>
                    <hr class="short">
                </div>
                <form form class="form-horizontal" action="login" method="POST">
                    <input type="hidden" name="action" value="login">


                    <div class="col-3">
                        <!-- Username -->
                        <input id="userName" name="userName" class="effect-2" type="text" placeholder="Username" required="">
                        <span class="focus-border"></span>
                        <!-- Password -->
                        <input id="password" name="password" class="effect-2" type="password" placeholder="Password" required="">
                        <span class="focus-border"></span>
                    </div>
                    <!-- Login -->
                    <div class="large-12 columns">
                        <div class="wrapper">
                            <button id="loginButton" name="loginButton" class="btn btn--border btn--default btn--animated">Login</button>
                        </div>
                    </div>

                    <!-- Go back to login page / Future release -->
                    <div class="forget">
                        <button class="btn btn-default btn-sm">FORGOT PASSWORD?</button>
                    </div>

                </form>
            </div>
            <!-- Go back to login page / Future release -->
            <a href="loginPage.jsp">
                <div class="enterButton">
                    <i class="fa fa-lock fa-2x text-white"></i><br>
                    <span class="enterText text-white">Register</span>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
