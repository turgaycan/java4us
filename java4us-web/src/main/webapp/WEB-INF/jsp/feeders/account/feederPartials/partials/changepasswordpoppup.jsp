<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="controls">
    <label class="control-label" style="margin-top:22px"></label>
    <!-- Button to trigger modal -->
    <a href="#changePasswordModel" role="button" class="btn btn-warning" data-toggle="modal">Change Password</a>

    <!-- Modal -->
    <div id="changePasswordModel" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="changePasswordModelLabel">Change Password</h3>
        </div>
        <div class="modal-body">
            <div class="login-fields">

                <div class="content clearfix" id="modelContent">

                    <div class="login-fields">

                        <div class="field">
                            <label for="oldPassword">Password:</label>
                            <input type="password" id="oldPassword" name="oldPassword" value=""
                                   placeholder="Password" class="login password-field"/>
                        </div>
                        <!-- /field -->

                        <div class="field">
                            <label for="newPassword">New Password:</label>
                            <input type="password" id="newPassword" name="newPassword" value=""
                                   placeholder="New Password"
                                   class="login password-field"/>
                        </div>
                        <!-- /field -->
                    </div>
                    <!-- /login-fields -->
                </div>
                <!-- /content -->
                <div id="passwordResult"></div>
            </div>
            <!-- /account-container -->
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            <button id="changePassword" class="btn btn-primary">Change Password</button>
        </div>
    </div>
</div>
<!-- /controls -->