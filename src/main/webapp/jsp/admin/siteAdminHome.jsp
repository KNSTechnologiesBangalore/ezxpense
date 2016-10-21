<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>::- Organization Site-Admin Setup -::</title>
<%@ include file="/jsp/common/common.jsp"%>
</head>
<body class="full-layout  nav-right-hide nav-right-start-hide  nav-top-fixed responsive clearfix"data-active="dashboard " data-smooth-scrolling="1">
	<div class="vd_body">
		<!-- Header Start -->
		<%@ include file="/jsp/common/header.jsp"%>
		<!-- Header Ends -->
		<div class="content">
			<div class="container">
				<div class="vd_navbar vd_nav-width vd_navbar-tabs-menu vd_navbar-left">
					<div class="navbar-menu clearfix">
						<div class="vd_menu">
							<nav class="navbar navbar-default" role="navigation">
								<!-- Main Menu -->
								<div class="side-menu-container vd_menu">
									<ul class="nav navbar-nav">
										<%@ include file="/jsp/common/leftContentFile.jsp"%>
									</ul>
								</div>
							</nav>
						</div>
					</div>
				</div>
				<div class="vd_content-wrapper">
					<div class="vd_container">
						<div class="vd_content clearfix">
						<c:if test="${ not empty division}">
							<%@ include file="/jsp/division/division.jsp"%>
						</c:if>
						<c:if test="${ not empty eztypes}">
							<%@ include file="/jsp/expensetype/expenseType.jsp"%>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer Start -->
		<%@ include file="/jsp/common/footer.jsp"%>
		<a id="back-top" href="#" data-action="backtop"
			class="vd_back-top visible"> <i class="fa  fa-angle-up"> </i>
		</a>
		<!-- Footer END -->
	</div>
</body>
</html>