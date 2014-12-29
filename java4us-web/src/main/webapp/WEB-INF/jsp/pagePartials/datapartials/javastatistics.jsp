<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div>
    <a href="${java4usRoot}/java"><h3>Java</h3></a>
</div>
<a href="javascript:;" class="shortcut"> <i
        class="shortcut-icon icon-list-alt"></i><span
        class="shortcut-label">Feeders(${javaStatistics.feedersCount})</span>
</a> <a href="javascript:;" class="shortcut"> <i
        class="shortcut-icon icon-bookmark"></i><span
        class="shortcut-label">Feeds(${javaStatistics.feedsCount})</span>
</a> <a href="javascript:;" class="shortcut"><i
        class="shortcut-icon icon-signal"></i> <span class="shortcut-label">Feed
					Messages(${javaStatistics.feedMessagesCount})</span> </a> <a
        href="javascript:;" class="shortcut"> <i
        class="shortcut-icon icon-comment"></i><span
        class="shortcut-label">Subscribers(${javaStatistics.subscribersCount})</span>
</a>
