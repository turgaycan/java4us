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

                            <h3>UnSubscribe From Java4Us.Net Subscriber List</h3>
                        </div>
                        <!-- /widget-header -->

                        <div class="widget-content">

                            <div class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li class="active" id="rssFeeder">
                                        <a href="#formcontrols" data-toggle="tab">UnSubscribe Form</a>
                                    </li>
                                </ul>
                                <br>

                                <div class="tab-content">
                                    <div id="formcontrols" class="tab-pane active">
                                        <form id="edit-profile" class="form-horizontal">
                                            <fieldset>
                                                Unsubscribe process is done successfully :(
                                                <input type="hidden" value="${subscriberId}" id="id" name="id"/>
                                                <!-- /form-actions -->
                                            </fieldset>
                                        </form>
                                        <div id="subscriberResult"></div>
                                        <div class="form-actions">
                                            <button type="submit" class="btn btn-primary" id="btnSubscribe">
                                                Add to Subscribe List
                                            </button>
                                            <button class="btn" id="btnHomePage"><a
                                                    href="http://www.java4us.net"> Goto Home Page</a>
                                            </button>
                                        </div>
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