<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="main">

	<div class="main-inner">

		<div class="container">

			<div class="row">

				<div class="span12">

					<div class="widget ">

						<div class="widget-header">
							<i class="icon-user"></i>
							<h3>Feeder Account</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">

							<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#userInfo" data-toggle="tab">User
											Information</a></li>
									<li><a href="#feederInfo" data-toggle="tab">Feeder
											Information </a></li>
								</ul>

								<div class="tab-content">
									<div id="userInfo" class="tab-pane active">
										<form id="edit-profile" class="form-horizontal">
											<fieldset>
												<label class="control-label" for="email">Email</label>
												<div class="controls">
													<input type="text" class="span6 disabled" id="email"
														value="${feederAccountModel.user.email}" disabled>
													<p class="help-block">Your Email is for cannot be
														changed.</p>
												</div>
												<label class="control-label" for="status">Status</label>
												<div class="controls">
													<input type="text" class="span6 disabled" id="status"
														value="${feederAccountModel.user.status}" disabled>
												</div>
												<c:forEach var="roles"
													items="${feederAccountModel.user.userRoles}"
													varStatus="counter">
													<label class="control-label" for="status">Role
														${counter.count}</label>
													<div class="controls">
														<input type="text" class="span6 disabled" id="status"
															value="${roles.role}" disabled>
													</div>
												</c:forEach>
											</fieldset>
										</form>
										<!-- /form-actions -->
									</div>
									<div class="tab-pane" id="feederInfo">
										<form id="edit-profile2" class="form-horizontal">
											<fieldset>

												<label class="control-label">Name Surname</label>
												<div class="controls">
													<input type="text" class="span6 disabled"
														value="${feederAccountModel.getFeeder().getFullname()}"
														disabled />
												</div>
												<label class="control-label">Domain</label>
												<div class="controls">
													<input type="text" class="span6 disabled"
														value="${feederAccountModel.feeder.domain}" disabled />
												</div>
												<label class="control-label">Status</label>
												<div class="controls">
													<c:choose>
														<c:when test="${feederAccountModel.feeder.status.equals(FeederStatus.ACCEPTED)}">
															<div class="alert alert-success">
														</c:when>
														<c:when test="${feederAccountModel.feeder.status.equals(FeederStatus.REJECTED or FeederStatus.SUSPENDED or FeederStatus.CANCELED)}">
															<div class="alert alert-info">
														</c:when>
														<c:otherwise>
															<div class="alert">
														</c:otherwise>
													</c:choose>
													<input type="text" class="span6 disabled"
														value="${feederAccountModel.feeder.status}" disabled />
												</div>
									</div>
									<label class="control-label">Register Date</label>
									<div class="controls">
										<input type="text" class="span6 disabled"
											value="${feederAccountModel.feeder.createDate}" disabled />
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
												<div>
													<strong><label
														style="width: 50px; text-decoration: underline;">${feed.category}</label>${feed.link}</strong>
												</div>
											</div>
									</div>
									</c:forEach>
									</fieldset>
									</form>
									<!-- /form-actions -->
								</div>

							</div>
						</div>
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