<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="span6">
	<!-- social statistics -->
	<%@include file="socialsstatistics.jsp"%>
	<!-- social statistics  -->

	<div class="widget">
		<div class="widget-header">
			<i class="icon-file"></i>
			<h3>En Son Eklenen Android RSS Beslemeler</h3>
		</div>
		<!-- /widget-header -->
		<div class="widget-content">
			<ul class="messages_layout">
				<c:forEach var="feedAndroidMessage"
					items="${feedAndroidMessageList}" varStatus="counter">
					<c:choose>
						<c:when test="${counter.count % 2 == 0}">
							<li class="from_user left"><a href="#" class="avatar"><img
									src="<c:url value="/resources/img/message_avatar1.png"/>" /></a>
								<div class="message_wrap">
									<span class="arrow"></span>
									<div class="info">
										<a href="${feedAndroidMessage.link}" target="_blank" class="name">${feedAndroidMessage.title}</a> <span class="time">1
											hour ago</span>
										<div class="options_arrow">
											<div class="dropdown pull-right">
												<a class="dropdown-toggle " id="dLabel" role="button"
													data-toggle="dropdown" data-target="#" href="#"> <i
													class=" icon-caret-down"></i>
												</a>
												<ul class="dropdown-menu " role="menu"
													aria-labelledby="dLabel">
													<li><a href="#"><i
															class=" icon-share-alt icon-large"></i> Reply</a></li>
													<li><a href="#"><i class=" icon-trash icon-large"></i>
															Delete</a></li>
													<li><a href="#"><i class=" icon-share icon-large"></i>
															Share</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="text">${feedAndroidMessage.description}</div>
								</div></li>
						</c:when>
						<c:otherwise>
							<li class="by_myself right"><a href="#" class="avatar"><img
									src="<c:url value="/resources/img/message_avatar2.png"/>" /></a>
								<div class="message_wrap">
									<span class="arrow"></span>
									<div class="info">
										<a href="${feedAndroidMessage.link}" target="_blank" class="name">${feedAndroidMessage.title} </a> <span class="time">4
											hours ago</span>
										<div class="options_arrow">
											<div class="dropdown pull-right">
												<a class="dropdown-toggle " id="dLabel" role="button"
													data-toggle="dropdown" data-target="#" href="#"> <i
													class=" icon-caret-down"></i>
												</a>
												<ul class="dropdown-menu " role="menu"
													aria-labelledby="dLabel">
													<li><a href="#"><i
															class=" icon-share-alt icon-large"></i> Reply</a></li>
													<li><a href="#"><i class=" icon-trash icon-large"></i>
															Delete</a></li>
													<li><a href="#"><i class=" icon-share icon-large"></i>
															Share</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="text">${feedAndroidMessage.description}</div>
								</div></li>
						</c:otherwise>
					</c:choose>


				</c:forEach>
			</ul>
		</div>
		<!-- /widget-content -->
	</div>
	<!-- /widget -->
</div>
<!-- /span6 -->