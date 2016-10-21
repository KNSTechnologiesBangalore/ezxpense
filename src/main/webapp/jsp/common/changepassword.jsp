<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>::- Change Password -::</title>
<%@ include file="/jsp/common/common.jsp"%>
</head>
<script>
function changePasswordValidation(format){
	var currentPassword=$('#currentPassword').val();
	var newPassword=$('#newPassword').val();
	var confirmPassword=$('#confirmPassword').val();
	var format = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
	var a,b,c=false;
	if(currentPassword==null || currentPassword.length<1){
		$('#current_error_msg').text('Please Enter Current Password');
		a=false;
	}
	else if(!format.test(currentPassword)){
		$('#current_error_msg').text('Password should contain at least one lowercase(a-z), one uppercase(A-Z) letters, numbers and length of password should be between 6-20!');
		a=false;
	}else{
		$('#current_error_msg').text('');
		a=true;
	}
	if(newPassword==null || newPassword.length<1){
		$('#new_error_msg').text('Please Enter New Password');
		b=false;
	}
	else if(!format.test(newPassword)){
		$('#new_error_msg').text('Password should contain at least one lowercase(a-z), one uppercase(A-Z) letters, numbers and length of password should be between 6-20!');
		b=false;
	}
	else{
		$('#new_error_msg').text('');
		b=true;
	}
	if(confirmPassword==null || confirmPassword.length<1){
		$('#confirm_error_msg').text('Please Enter Confirm Password');
		c=false;
	}
	else if(!format.test(confirmPassword)){
		$('#confirm_error_msg').text('Password should match with new Password');
		c=false;
	}
	else if(newPassword!=confirmPassword){
		$('#confirm_error_msg').text('Confirm Password should match with new Password');
		c=false;
	}
	else{
		$('#confirm_error_msg').text('');
		c=true;
	}
	if(a==true && b==true && c==true){
		return true;
	}
	else{
		return false;
	}
}
</script>
<style>
.validation_error {
	color: #f01;
}
</style>
<body
	class="full-layout  nav-right-hide nav-right-start-hide nav-top-fixed responsive clearfix"
	data-active="dashboard " data-smooth-scrolling="1">
	<div class="vd_body">
		<!-- Header Start -->
		<%@ include file="/jsp/common/header.jsp"%>
		<!-- Header Ends -->
		<div class="content">
			<div class="container">
				<%@ include file="/jsp/common/leftContentFile.jsp"%>
				<div class="vd_content-wrapper">
					<div class="vd_container">
						<div class="vd_content clearfix">

							<div class="panel panel-default">
								<div class="panel-heading" style="font-weight: 600;">Change
									Password</div>
								<div class="custab">
									<c:if test="${ not empty message}">
										<div align="center" class="alert alert-success"
											style="width: 35%;">${message}</div>
									</c:if>
									<c:if test="${not empty errormessage}">
										<div align="center" class="alert alert-danger"
											style="width: 35%;">${errormessage}</div>
									</c:if>
									<div
										style="max-width: 400px; padding: 10px 20px; margin: auto; margin-top: 10px;">
										<form class="form-horizontal" data-toggle="validator"
											method="POST" commandName="changePasswordDto"
											action="${pageContext.request.contextPath}/changepassword.do"
											onsubmit="return changePasswordValidation(this)">
											<div class="form-group">
												<label for="currentPassword" class="control-label">Enter
													Current Password</label> <input type="password"
													name="currentPassword" class="form-control"
													id="currentPassword" placeholder="Enter Current Password">
												<label class="validation_error" id="current_error_msg"></label>
											</div>
											<div class="form-group">
												<label for="newPassword" class="control-label">Enter
													New Password</label> <input type="password" name="newPassword"
													class="form-control" id="newPassword"
													placeholder="Enter New Password"> <label
													class="validation_error" id="new_error_msg"></label>
											</div>
											<div class="form-group">
												<label for="confirmPassword" class="control-label">Confirm
													New Password</label> <input type="password" name="confirmPassword"
													class="form-control" id="confirmPassword"
													placeholder="Enter New Password Again"> <label
													class="validation_error" id="confirm_error_msg"></label>
											</div>
											<div class="form-group">
												<button style="width: 50%; margin-left: 25%;" type="submit"
													class="org_submit">Submit</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer Start -->
	<%@ include file="/jsp/common/footer.jsp"%>
	<!-- Footer END -->
	<!-- .vd_body END  -->
	<a id="back-top" href="#" data-action="backtop"
		class="vd_back-top visible"> <i class="fa  fa-angle-up"> </i>
	</a>
</body>

</html>

