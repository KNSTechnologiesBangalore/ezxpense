<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>ezXpense Login</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<%@ include file="/jsp/common/common.jsp"%>
<!--  <script>
 function getOrganization(){
	 $.ajax({
		 
		    url: "/ezXpense/getallorganization.do",
		    type: 'GET',
		    success: function(data) {
		    var obj = JSON.parse(data);
   			var organization=obj.organization;	
   			
			$.each(organization, function(i,val) {	
				//needs to create table row...
				var organizationId=val.organizationId;
				var orgName=val.orgName;
				var dbName=val.dbName;
			    $('#organizationList').append('<option value='+dbName+'  name="organization">'+orgName+'</option>');
				
			}); 
   		
    		},
   			error:function(data) {
       			alert("Error While adding organization");
    			
    	
    		} 
    
		});
 } -->
</script>
</head>
<!-- <body onload="getOrganization()"> -->
<body>
	<div class="wraper bg-blur"></div>
	<div class="mn_main_con">
		<div class="main">
			<div class="logo">
				<img src="${pageContext.request.contextPath}/images/logo.png" alt="" />
			</div>
			<div id="login-form">
				<div>
					<ul class="form-header">
						<li>SIGN IN</li>
					</ul>
					<c:if test="${ not empty error}">
						<div id="login_head_text" class="status" style="color: #FF0000;">
							${error}</div>
					</c:if>
				</div>
				<div class="user-image">
					<i class="fa fa-user user"></i>
				</div>
				<!--Login Form-->
				<form id="loginForm" class="login-form"
					action="<c:url value= '/login' />" method="post">
					<ul class="login">

						<!--  <li>
          
             
               <select id="organizationList" name="organization">
                  <option value="" selected="selected">Select Organizations</option>		 
               </select>
          </li> -->
						<li><input id="loginEmail" type="text" class="input"
							name="username" placeholder="User Name" /></li>
						<li class="inline"><input type="password" class="input"
							name="password" placeholder="User Password" /></li>
						<li><span class="remember"> <input type="checkbox"
								id="check" /> <label for="check">Remember Me</label>
						</span><span class="remember1"><a href=""
								class="forgota dropdown-toggle" data-toggle="dropdown">Forgot
									Password</a></span></li>
						<li><input class="btn" type="submit" value="SIGN IN" /></li>
					</ul>
				</form>
				<!--<div class="form-footer">Don't Have An Account <a href="">Sign Up</a> </div>-->
			</div>
			<div class="forgot_password mn_border_top">
				<form action="" name="forgot">
					<div class="ritpul">
						<h2>Forgot Password</h2>
						<div class="col-md-12">
							<div class="rowput">
								<input type="email" aria-required="true" required=""
									placeholder="Email Address" name="email" class="input" />
							</div>
						</div>
						<div class="clear"></div>
						<div class="col-md-12">
							<input type="submit" value="Forgot Password" class="btn" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


	<script>
$(".forgota").click(function(){
	$(".forgot_password").slideToggle();
});
</script>
</body>
</html>
