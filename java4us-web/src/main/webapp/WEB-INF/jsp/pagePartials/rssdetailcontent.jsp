<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="main">

	<div class="main-inner">

		<div class="container">

			<div class="row">

				<div class="span12">

					<div class="widget ">

						<div class="widget-header">
							<div style="width: 75%; float: left">
								<i class="icon-user"></i>
								<h3>${feedMessage.title}</h3>
							</div>
							<fmt:formatDate value="${feedMessage.pubdate}"
								var="publishDate" pattern="dd MMMM yyyy HH:mm:ss" />
							<div style="width: 15%; float: right">${publishDate}</div>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">

							<div class="tabbable">

								<div class="tab-content">
									<div id="formcontrols" class="tab-pane active">
										<form id="edit-profile" class="form-horizontal">
											<fieldset>${feedMessage.description}</fieldset>
										</form>
										<div class="form-actions" align="center">
											<button onclick="goUrl()" class="btn btn-warning">GO
												TO SOURCE LINK</button>
											<input id="targetUrl" name="targetUrl" type="hidden"
												value="${feedMessage.link}">
										</div>
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
<script type="text/javascript">
	function goUrl() {
		var targetUrl = $('#targetUrl').val();
		window.location.href = targetUrl;
	}
</script>