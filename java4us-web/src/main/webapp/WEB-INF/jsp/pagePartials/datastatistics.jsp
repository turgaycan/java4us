<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="widget">
	<div class="widget-header">
		<i class="icon-bookmark"></i>
		<h3>Java4Us.Net İçerik İstatistikler</h3>
	</div>
	<!-- /widget-header -->
	<div class="widget-content">
		<div class="shortcuts">
			<div>
				<h3>Java</h3>
			</div>
			<a href="javascript:;" class="shortcut"> <i
				class="shortcut-icon icon-list-alt"></i><span class="shortcut-label">Feeders(${javaStatistics.feedersCount})</span>
			</a> <a href="javascript:;" class="shortcut"> <i
				class="shortcut-icon icon-bookmark"></i><span class="shortcut-label">Feedes(${javaStatistics.feedsCount})</span>
			</a> <a href="javascript:;" class="shortcut"><i
				class="shortcut-icon icon-signal"></i> <span class="shortcut-label">Feed
					Messages(${javaStatistics.feedMessagesCount})</span> </a> <a
				href="javascript:;" class="shortcut"> <i
				class="shortcut-icon icon-comment"></i><span class="shortcut-label">Subscribers(${javaStatistics.subscribersCount})</span>
			</a>
			<div>
				<h3>Android</h3>
			</div>
			<a href="javascript:;" class="shortcut"><i
				class="shortcut-icon icon-user"></i><span class="shortcut-label">Feeders(${androidStatistics.feedersCount})</span>
			</a> <a href="javascript:;" class="shortcut"><i
				class="shortcut-icon icon-file"></i><span class="shortcut-label">Feedes(${androidStatistics.feedsCount})</span>
			</a> <a href="javascript:;" class="shortcut"><i
				class="shortcut-icon icon-picture"></i> <span class="shortcut-label">Feed
					Messages(${androidStatistics.feedMessagesCount})</span> </a> <a
				href="javascript:;" class="shortcut"> <i
				class="shortcut-icon icon-tag"></i><span class="shortcut-label">Subscribers(${androidStatistics.subscribersCount})</span>
			</a>
		</div>
		<!-- /shortcuts -->
	</div>
	<!-- /widget-content -->
</div>