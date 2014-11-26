<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<form id="edit-profile" class="form-horizontal">
    <fieldset>
        <label class="control-label" for="email">Email</label>

        <div class="controls">
            <input type="text" class="span6 disabled" id="email"
                   value="${feederAccountModel.user.email}" disabled>

            <p class="help-block">Your Email is for cannot be
                changed.</p>
        </div>
        <label class="control-label">Status</label>

        <div class="controls">
            <input type="text" class="alert-success span6"
                   value="${feederAccountModel.user.status}" disabled>
        </div>
        <c:forEach var="roles"
                   items="${feederAccountModel.user.userRoles}"
                   varStatus="counter">
            <label class="control-label">Role
                    ${counter.count}</label>

            <div class="controls">
                <input type="text" class="span6 disabled"
                       value="${roles.role}" disabled>
            </div>
        </c:forEach>
    </fieldset>
</form>
<!-- /form-actions -->