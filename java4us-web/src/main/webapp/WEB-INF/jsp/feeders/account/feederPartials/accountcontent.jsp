<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="main">

    <div class="main-inner">

        <div class="container">

            <div class="row">

                <div class="span12">

                    <div class="widget ">

                        <div class="widget-header">
                            <i class="icon-user"></i>

                            <h3>Feeder Account</h3>
                        </div>
                        <!-- /widget-header -->

                        <div class="widget-content">

                            <div class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#userInfo" data-toggle="tab">User
                                        Information </a></li>
                                    <li><a href="#feederInfo" data-toggle="tab">Feeder
                                        Information </a></li>
                                </ul>

                                <div class="tab-content">
                                    <div id="userInfo" class="tab-pane active">
                                        <%@include file="partials/userinfo.jsp" %>
                                    </div>
                                    <div id="feederInfo" class="tab-pane">
                                        <%@include file="partials/feederinfo.jsp" %>
                                    </div>
                                </div>
                            </div>
                            <div class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li><a href="#feedMessagesInfo" data-toggle="tab">Feed
                                        Messages </a></li>
                                </ul>

                                <div class="tab-content">
                                    <div id="feedMessagesInfo" class="tab-pane">
                                        <%@include file="partials/feedmessagescontent.jsp" %>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- /widget-content -->
                    </div>
                    <!-- /widget -->
                </div>
                <!-- /span8 -->

            </div>
            <!-- /row -->

        </div>
        <!-- /container -->

    </div>
    <!-- /main-inner -->
</div>
<!-- /main -->