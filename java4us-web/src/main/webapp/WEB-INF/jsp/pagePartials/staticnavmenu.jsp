<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"><span class="icon-bar"></span><span
				class="icon-bar"></span><span class="icon-bar"></span> </a><a
				class="brand" href="${java4usRoot}">Java4Us.Net | Java ve
				Android Rss Toplayıcı </a>
			<div class="nav-collapse">
				<ul class="nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><i class="icon-cog"></i> Account <b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="javascript:;">Settings</a></li>
							<li><a href="javascript:;">Help</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><i class="icon-user"></i> Java4Us.Net <b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<c:if test="${pageContext.request.userPrincipal.name == null}">
								<li><a href="${java4usRoot}/login">Login</a></li>
							</c:if>
							<c:if test="${pageContext.request.userPrincipal.name != null}">
								<li><a href="${java4usRoot}/account">Profile</a></li>
								<li><a href="javascript:formSubmit()">Logout</a></li>
							</c:if>
							<c:url value="/j_spring_security_logout" var="logoutUrl" />
							<form action="${logoutUrl}" method="post" id="logoutForm">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
							<script type="text/javascript">
								function formSubmit() {
									document.getElementById("logoutForm")
											.submit();
								}
							</script>
						</ul></li>
				</ul>
				<form class="navbar-search pull-right">
					<input type="text" class="search-query" placeholder="Search">
				</form>
			</div>
			<!--/.nav-collapse -->
		</div>
		<!-- /container -->
	</div>
	<!-- /navbar-inner -->
</div>
<!-- /navbar -->