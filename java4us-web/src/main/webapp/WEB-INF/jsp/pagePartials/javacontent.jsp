<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="main">

    <div class="main-inner">

        <div class="container">

            <div class="row">
                <div class="span6" style="float: left;">
                    <!-- social statistics -->
                    <%@include file="socialsstatistics.jsp" %>
                    <!-- social statistics  -->
                </div>
                <div class="span6">
                    <!-- data statistics -->
                    <%@include file="datastatistics.jsp" %>
                    <!-- data statistics  -->
                </div>
            </div>
        </div>
        <!-- search box-->
        <%@include file="searchbox.jsp"%>
        <!-- search box-->
        <div class="container">

            <div class="row">

                <div class="span12">
                    <div class="widget ">

                        <div class="widget-header">
                            <i class="icon-user"></i>
                            <c:if test="${datastatics eq 'JAVA'}">
                                <h3>Java RSS Feed Messages..</h3>
                            </c:if>
                            <c:if test="${datastatics eq 'ANDROID'}">
                                <h3>Android RSS Feed Messages..</h3>
                            </c:if>
                        </div>
                        <!-- /widget-header -->

                        <div class="widget-content">

                            <!-- /widget -->
                            <div class="widget widget-nopad">
                                <%@include file="pagination.jsp"%>
                                <!-- /widget-header -->
                                <div class="widget-content">
                                    <ul class="news-items">
                                        <c:forEach var="feedJavaMessage" items="${pagingMessages}">
                                            <li>
                                                <div class="news-item-date">
                                                <span class="news-item-day"><fmt:formatDate
                                                        value="${feedJavaMessage.pubdate}" type="both" pattern="dd"/></span>
                                                <span class="news-item-month"><fmt:formatDate
                                                        value="${feedJavaMessage.pubdate}" type="both" pattern="MMMM"/></span>
                                                <span class="news-item-month"><fmt:formatDate
                                                        value="${feedJavaMessage.pubdate}" type="both" pattern="yyyy"/></span>
                                                </div>
                                                <div class="news-item-detail">
                                                    <a href="${_urlService.getRssDetailPageUrl(feedJavaMessage.title, feedJavaMessage.id)}"
                                                       class="news-item-title"
                                                       target="_blank">${feedJavaMessage.title}</a>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <%@include file="pagination.jsp"%>
                                <!-- /widget-content -->
                            </div>
                            <!-- /widget -->

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

