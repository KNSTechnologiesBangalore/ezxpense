
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EzXpense Logout</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<%@ include file="/jsp/common/common.jsp"%>
</head>
<body>
<body class="l-dashboard  l-footer-sticky-1">
	<div class="wraper bg-blur"></div>
	<div class="mn_main_con">
		<div class="main">
			<section class="l-main-container">

				<section class="l-container">

					<div class="l-spaced">


						<div class="l-box l-spaced-bottom">
							<!-- <div class="l-box-header">
								<h2 class="l-box-title" style="color:violet">Logout-EzXpense</h2>
							</div> -->


							<div class="l-box-body l-spaced">
								<form class="form-horizontal" autocomplete="off" action=""
									method="POST">

									<div class="logopanel">
										<h4 class="logo_image_header">
											<a href="#"><img
												src="${pageContext.request.contextPath}/images/logo_1.png"
												width=300px;></a>
										</h4>
									</div>

									<div class="error_status" style="font-size:30px; color:orange;" >
										${message} <br /> <br /> <a
											href="${pageContext.request.contextPath}/login.do"
											style="text-decoration: underline; color: #1962CF; font-size:22px;">
											Click here to Login </a>
									</div>

								</form>
							</div>
						</div>
					</div>


				</section>
			</section>
		</div>
	</div>

</body>
</html>