<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="span6">
	<!-- data statistics -->
	<%@include file="datastatistics.jsp"%>
	<!-- data statistics  -->

	<!-- /widget -->
	<div class="widget widget-nopad">
		<div class="widget-header">
			<i class="icon-list-alt"></i>
			<h3>Son Eklenen Java RSS Beslemeler..</h3>
		</div>
		<!-- /widget-header -->
		<div class="widget-content">
			<ul class="news-items">
				<c:forEach var="feedJavaMessage" items="${feedJavaMessageList}">
					<li>
						<div class="news-item-date">
							<span class="news-item-day"><fmt:formatDate
									value="${feedJavaMessage.pubdate}" type="both" pattern="dd" /></span>
							<span class="news-item-month"><fmt:formatDate
									value="${feedJavaMessage.pubdate}" type="both" pattern="MMMM" /></span>
							<span class="news-item-month"><fmt:formatDate
									value="${feedJavaMessage.pubdate}" type="both" pattern="yyyy" /></span>
						</div>
						<div class="news-item-detail">
							<a href="${feedJavaMessage.link}" class="news-item-title"
								target="_blank">${feedJavaMessage.title}</a>
							<p class="news-item-preview">${feedJavaMessage.description}</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<!-- /widget-content -->
	</div>
	<!-- /widget -->
</div>
<!-- /span6 -->
