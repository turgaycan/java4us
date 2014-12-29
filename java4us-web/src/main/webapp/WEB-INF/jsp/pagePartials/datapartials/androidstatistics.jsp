<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div>
    <a href="${java4usRoot}/android"><h3>Android</h3></a>
</div>
<a href="javascript:;" class="shortcut"><i
        class="shortcut-icon icon-user"></i><span
        class="shortcut-label">Feeders(${androidStatistics.feedersCount})</span>
</a> <a href="javascript:;" class="shortcut"><i
        class="shortcut-icon icon-file"></i><span
        class="shortcut-label">Feeds(${androidStatistics.feedsCount})</span>
</a> <a href="javascript:;" class="shortcut"><i
        class="shortcut-icon icon-picture"></i> <span class="shortcut-label">Feed
					Messages(${androidStatistics.feedMessagesCount})</span> </a> <a
        href="javascript:;" class="shortcut"> <i
        class="shortcut-icon icon-tag"></i><span
        class="shortcut-label">Subscribers(${androidStatistics.subscribersCount})</span>
</a>