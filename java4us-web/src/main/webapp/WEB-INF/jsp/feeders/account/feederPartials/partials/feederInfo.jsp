<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<form id="edit-profile2" class="form-horizontal">
    <fieldset>

        <label class="control-label">Name Surname</label>

        <div class="controls">
            <input type="text" class="span6 disabled"
                   value="${feederAccountModel.getFeeder().getFullname()}"
                   disabled/>
        </div>
        <label class="control-label">Domain</label>

        <div class="controls">
            <input type="text" class="span6 disabled"
                   value="${feederAccountModel.feeder.domain}" disabled/>
        </div>
        <label class="control-label">Status</label>

        <div class="controls">
            <input
                    <c:choose>
                        <c:when test="${feederAccountModel.feeder.status eq FeederStatus.ACCEPTED.name()}">
                            class="alert-success span6 disabled"
                        </c:when>
                        <c:when test="${feederAccountModel.feeder.status.equals(FeederStatus.REJECTED or FeederStatus.SUSPENDED or FeederStatus.CANCELED)}">
                            class="alert-info span6 disabled"
                        </c:when>
                        <c:otherwise>
                            class="alert-info span6 disabled"
                        </c:otherwise>
                    </c:choose>
                    type="text"
                    value="${feederAccountModel.feeder.status}"
                    disabled/>
        </div>
        <label class="control-label">Register Date</label>

        <div class="controls">
            <input type="text" class="span6 disabled"
                   value='<fmt:formatDate
									value="${feederAccountModel.feeder.createDate}" type="both" pattern="dd-MMMM-yyyy HH:mm:sss" />'
                   disabled/>
        </div>

        <div class="control-group">
            <label class="control-label">Feeds</label>
            <c:forEach var="feed" items="${feederAccountModel.feedList}"
                       varStatus="counter">
                <div class="controls">
                    <c:choose>
                    <c:when test="${feed.status.equals(BaseStatus.Active)}">
                    <div class="alert alert-success">
                        </c:when>
                        <c:otherwise>
                        <div class="alert">
                            </c:otherwise>
                            </c:choose>
                            <strong>
                                <label
                                        style="width: 50px; text-decoration: underline;">${feed.category}</label>${feed.link}
                            </strong>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
        </div>
    </fieldset>
</form>
<!-- /form-actions -->