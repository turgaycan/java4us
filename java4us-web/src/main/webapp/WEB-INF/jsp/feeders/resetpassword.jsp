<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../pagePartials/common/commonvariables.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Java4Us.Net Feeder Login Page</title>

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>"
          rel="stylesheet" type="text/css"/>
    <link
            href="<c:url value="/resources/css/bootstrap-responsive.min.css"/>"
            rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/css/font-awesome.css"/>"
          rel="stylesheet" type="text/css"/>
    <link
            href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
            rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/resources/css/pages/signin.css"/>"
          rel="stylesheet" type="text/css"/>

</head>

<body>

<div class="navbar navbar-fixed-top">

    <div class="navbar-inner">

        <div class="container">

            <a class="btn btn-navbar" data-toggle="collapse"
               data-target=".nav-collapse"> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span>
            </a> <a class="brand" href="${java4usRoot}/">Java4Us.Net | Java and Android Technologies RSS Feeds</a>

            <div class="nav-collapse">
                <ul class="nav pull-right">

                    <li class=""><a href="${java4usRoot}/register" class=""> Don't have
                        an account? </a></li>

                    <li class=""><a href="${java4usRoot}/" class=""> <i
                            class="icon-chevron-left"></i> Back to Homepage
                    </a></li>
                </ul>

            </div>
            <!--/.nav-collapse -->

        </div>
        <!-- /container -->

    </div>
    <!-- /navbar-inner -->

</div>
<!-- /navbar -->


<div class="account-container">

    <div class="content clearfix">
        <c:if test="${not empty error}">
            <div class="alert">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">${msg}</div>
        </c:if>
        <form action="<c:url value='/feeders/resetpassword' />"
              method='POST'>

            <h1>Reset Password</h1>

            <div class="login-fields">

                <p>Please provide your details</p>

                <div class="field">
                    <label for="email">Email</label>
                    <input type="text"
                           id="email" name="email" value=""
                           placeholder="Email"
                           class="username-field"/>
                </div>
                <!-- /field -->
                <div>
                    <label class="control-label" for="securityImg"></label>

                    <div class="controls">
                        <img src="${java4usRoot}/captcha/captcha.htm"
                             id="securityImg"/>
                    </div>
                    <!-- /controls -->
                </div>

                <br/>

                <div class="field">
                    <label for="securityCode">Security Code</label>

                    <input type="text" id="securityCode"
                           name="securityCode" class="password-field"
                           placeholder="Security Code"
                           value="">
                    <!-- /controls -->
                </div>

            </div>

            <br />
            <!-- /login-fields -->

            <div class="login-actions">

                <button class="button btn btn-success btn-large" name="submit"
                        type="submit" value="submit">Reset Password
                </button>

            </div>
            <!-- .actions -->
        </form>

    </div>
    <!-- /content -->

</div>
<!-- /account-container -->


<div class="login-extra">
    <a href="${java4usRoot}/feeders/login">Login</a>
</div>
<!-- /login-extra -->

<script src="<c:url value="/resources/js/jquery-1.7.2.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/signin.js"/>"></script>

</body>

</html>