<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="java4usRoot" value="${pageContext.request.contextPath}" scope="application"/>
<sec:authorize access="isAnonymous()">
    <c:set var="userLoggedIn" value="false"/>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <c:set var="userLoggedIn" value="true"/>
</sec:authorize>
<jsp:useBean id="_urlService" class="com.java4us.commons.service.url.UrlService" scope="application"/>