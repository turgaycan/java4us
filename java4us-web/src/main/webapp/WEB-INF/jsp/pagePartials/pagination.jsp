<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<c:set var="pagingUrl" value="${paginationRootUrl}/p"/>
<div class="pagination pagination-centered">
    <div style="float: left; padding-left: 70px;">Page <b>${paginationModel.currentPage}</b> of
        <b>${paginationModel.totalPage}</b></div>
    <ul>
        <c:if test="${paginationModel.currentPage >= 3}">
            <li><a href="${pagingUrl}/1">«</a></li>
        </c:if>
        <c:if test="${paginationModel.currentPage > 1}">
            <li><a href="${pagingUrl}/${paginationModel.previousPage}">Prev</a></li>
        </c:if>
        <c:if test="${paginationModel.currentPage == 2}">
            <li><a href="${pagingUrl}/${paginationModel.currentPage - 1}">${paginationModel.currentPage - 1}</a></li>
        </c:if>
        <c:if test="${paginationModel.currentPage >= 3}">
            <li><a href="${pagingUrl}/${paginationModel.currentPage - 2}">${paginationModel.currentPage - 2}</a></li>
            <li><a href="${pagingUrl}/${paginationModel.currentPage - 1}">${paginationModel.currentPage - 1}</a></li>
        </c:if>
        <li class="active">
            <a href="${pagingUrl}/${paginationModel.currentPage}">${paginationModel.currentPage}</a>
        </li>
        <c:if test="${paginationModel.currentPage <= paginationModel.totalPage - 2}">
            <li><a href="${pagingUrl}/${paginationModel.currentPage + 1}">${paginationModel.currentPage + 1}</a></li>

            <li><a href="${pagingUrl}/${paginationModel.currentPage + 2}">${paginationModel.currentPage + 2}</a></li>
        </c:if>
        <c:if test="${paginationModel.totalPage >= 3 && paginationModel.currentPage < paginationModel.totalPage}">
            <li class="disabled"><a href="#">...</a></li>
            <li><a href="${pagingUrl}/${paginationModel.currentPage + 1}">Next</a></li>
            <li><a href="${pagingUrl}/${paginationModel.totalPage}">»</a></li>
        </c:if>

    </ul>
</div>