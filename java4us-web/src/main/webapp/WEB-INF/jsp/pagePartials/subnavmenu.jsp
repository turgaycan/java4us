<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="subnavbar">
    <div class="subnavbar-inner">
        <div class="container">
            <ul class="mainnav">
                <li class="active"><a href="index.html"><i class="icon-dashboard"></i><span>Dashboard</span> </a> </li>
                <li><a href="reports.html"><i class="icon-list-alt"></i><span>Reports</span> </a> </li>
                <li>
                    <div class="control-group">
                        <label class="control-label">Social Buttons</label>
                        <div class="controls">
                            <button class="btn btn-facebook-alt"><i class="icon-facebook-sign"></i> Facebook</button>
                            <button class="btn btn-twitter-alt"><i class="icon-twitter-sign"></i> Twitter</button>
                            <button class="btn btn-google-alt"><i class="icon-google-plus-sign"></i> Google+</button>
                            <button class="btn btn-linkedin-alt"><i class="icon-linkedin-sign"></i> Linked In</button>
                            <button class="btn btn-pinterest-alt"><i class="icon-pinterest-sign"></i> Pinterest</button>
                            <button class="btn btn-github-alt"><i class="icon-github-sign"></i> Github</button>
                        </div> <!-- /controls -->	
                    </div> <!-- /control-group -->
                </li>
                <li style="padding-left: 10px;padding-right: 10px;">
                    <div class="controls">
                        <label class="control-label">Add Rss To Java4Us</label>
                        <a href="/java4us-web/kayit" role="button" class="btn btn-warning">Add Rss for Feeder</a>
                    </div>
                </li>
                <li style="padding-left: 10px;">
                    <div class="controls">
                        <label class="control-label">Subscribe To Java4Us</label>
                        <!-- Button to trigger modal -->
                        <a href="#myModal" role="button" class="btn btn-warning" data-toggle="modal">Signup for Subscriber</a>

                        <!-- Modal -->
                        <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h3 id="myModalLabel">Signup for Subscriber</h3>
                            </div>
                            <div class="modal-body">
                                <div class="account-container-subscriber register">

                                    <div class="content clearfix" id="modelContent">                                                			

                                        <div class="login-fields">

                                            <div class="field">
                                                <label for="firstname">First Name:</label>
                                                <input type="text" id="subscriberFirstname" name="firstname" value="" placeholder="First Name" class="login" />
                                            </div> <!-- /field -->

                                            <div class="field">
                                                <label for="lastname">Last Name:</label>	
                                                <input type="text" id="subscriberLastname" name="lastname" value="" placeholder="Last Name" class="login" />
                                            </div> <!-- /field -->


                                            <div class="field">
                                                <label for="email">Email Address:</label>
                                                <input type="text" id="subscriberEmail" name="email" value="" placeholder="Email" class="login"/>
                                            </div> <!-- /field -->
                                        </div> <!-- /login-fields -->  
                                    </div> <!-- /content -->
                                    <div id="subscriberResult"></div>                                                        
                                </div> <!-- /account-container -->
                            </div>
                            <div class="modal-footer">
                                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                <button id="newSubscriber" class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
                    </div> <!-- /controls -->
                </li>
            </ul>
        </div>
        <!-- /container --> 
    </div>
    <!-- /subnavbar-inner --> 
</div>
<!-- /subnavbar -->
