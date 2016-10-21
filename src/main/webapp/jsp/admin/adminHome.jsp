
<!DOCTYPE html>
<html>

<head>

<title>::- Organization Setup -::</title>
<%@ include file="/jsp/common/common.jsp"%>
<script src="${pageContext.request.contextPath}/js/organization.js"
	type="text/javascript"></script>

<script>
    /* Added by Firdous, Function to addthe new organization*/
    function addOrganization(){
		if(confirm("Do you want to add new organization")==true){
			$.ajax({
			    url: "/ezXpense/admin/addorganization.do",
			    type: 'GET',
			    success: function(data) {
			   		
			   		window.location="/ezXpense/admin/homepage.do";
			    },
			    error:function(status,data,er) {
			        alert("Error While adding Organization");
			    }
			});
		}
	}
    
    /*Added by Firdous,Function to delete the site admin*/
     function loadOnValues(){
                var conditionalVariable ='<c:out value="${organizationDto.onSiteFlag}"/>';
                var imageFile='<c:out value="${organizationDto.logoFile}"/>';
                alert("image file name"+imageFile);
                if(conditionalVariable==null || conditionalVariable =='n'){
                    document.getElementById("onSiteFlag").checked = false;
                } else {
                     document.getElementById("onSiteFlag").checked = true;       
                }   
      }    
    function deleteUser(userId){
    	
    	
    }
    
    /* Added by Firdous,Function to get the organization by organization id */
    function getOrganization(id){
    	var orgId=id;
    	window.location="/ezXpense/adminhome.do?orgId="+orgId;
    	
    }
    
    /* Added by Firdous,Function to append a row above the site admin table to add new site admin */
    function add(){
    	alert("inside add function");
    	var $body=$('body');
    	$(".addSiteAdmin").css("display","block");
    	/* $("#responsetable tbody").append( "<tr>"+ "<td><input type='text' placeholder='Employee Number' name='' class='mn_organi mn_ma_0'></td>"+ "</tr>"); */ 
    } 

    
    /* Added by Firdous,Function to validate the updated password of site admin */
    function validatePassword(){
    	var newPassword=$('#newPassword').val();
    	var confirmPassword=$('#confirmPassword').val();
    	var format = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
    	var b,c=false;
    	
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
    	if(b==true && c==true){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
	
    
    /*Added by Firdous,Function to display the list of organization and select the organization */
    function selectOrganization(){
    	var $body=$('body');
    	$("#organizationListPopup").css("display","block");
    	$body.addClass('loading');
		$("#organizationListPopup").fadeIn(1000);
		positionPopup();
		$("#focus_div").attr("tabindex",1).focus();
    	
    }
    
    /*Added by Firdous,function to update the on site flag */
    function updateCheck(){
    	if(document.getElementById('onSiteFlag').checked){
            document.getElementById('onSiteFlag').value ='y';
        }
        else{
        	
            document.getElementById('onSiteFlag').value ='n';
       }
    }
    
    /*Added by Firdous,Function to show the popup to change the password of site admin*/
    function changepasswordpopup(userId){
    	 var $body=$('body');
		 $("#change_password_popup").css("display","block");
		 $('#form2_userId').val(userId);
		 $('#form2_username').val($('#username'+userId).text().trim());
		 $('#form2_orgName').val($('#orgName').val());
		 $('#form2_dbName').val($('#dbName').val());
		 $('#form2_emailId').val($('#emailId'+userId).text().trim());
		 $('#form2_orgId').val($('#organizationId').val());
		 $body.addClass('loading');
		 $("#change_password_popup").fadeIn(1000);
		 positionPopup();
		 $("#focus_div").attr("tabindex",1).focus();
    }
    
    
    function positionPopup(){
  	  if(!$("#organizationListPopup,#user_profile_popup,#change_password_popup").is(':visible')){
  	    return;
  	  } 
  	  $("#organizationListPopup,#user_profile_popup,#change_password_popup").css({
  	       left: ($(window).width() - $('#organizationListPopup,#change_password_popup').width())/10,
	       top: ($(window).width() - $('#organizationListPopup,#change_password_popup').width()) / 21,
	       left: ($(window).width() - $('#user_profile_popup').width())/2,
  	       top: ($(window).width() - $('#user_profile_popup').width()) / 21,
  	       position:'absolute'
  	  });
  	
  	}
    
    /*Added by Firdous.Function to close the organization list popup*/
    function closePopup(){
		  var $body=$('body');
		  $("#organizationListPopup").fadeOut(500);
		  $body.removeClass('loading');
	
     } 
    
    /*Added by Firdous,Function to close the change password popup */
    function closeChangePasswordPopup(){
    	  var $body=$('body');
		  $("#change_password_popup").fadeOut(500);
		  $body.removeClass('loading');
    }
    
    
    /*Added by Firdous,Function to display the site admin user profile, to update*/
    function viewUserProfile(userId){
		 var $body=$('body');
		 $("#user_profile_popup").css("display","block");
		 $('#form_password').val($('#password'+userId).text().trim());
		 $('#form_employeeNumber').val($('#employeeNumber'+userId).text().trim());
		 $('#form_firstName').val($('#firstName'+userId).text().trim());
		 $('#form_lastName').val($('#lastName'+userId).text().trim());
		 $('#form_middleInitial').val($('#middleInitial'+userId).text().trim());
		 $('#form_startDate').val($('#startDate'+userId).text().trim());
		 $('#form_endDate').val($('#endDate'+userId).text().trim());
		 $('#form_emailId').val($('#emailId'+userId).text().trim());
		 $('#form_orgId').val($('#organizationId').val());
		 $('#form_dbName').val($('#dbName').val());
		 $('#form_userId').val(userId);
		 $('#form_username').val($('#username'+userId).text().trim());
		 $('#form_orgName').val($('#orgName').val());
		 $body.addClass('loading');
		 $("#user_profile_popup").fadeIn(1000);
		 positionPopup();
		 $("#focus_div").attr("tabindex",1).focus();
	}
    
    
    /*Added by Firdous,Function to add the site admin of an organization */
    function addSiteAdmin(){
    	var validationResult=validateSiteAdmin();
    	if(validationResult==true){
    	var organizationId=$('#organizationId').val();
    	var orgName=$('#orgName').val();
    	var dbName=$('#dbName').val();
    	var employeeNumber=$('#employeeNumber').val();
		var username=$('#username').val();
		var firstName=$('#firstName').val();
		var lastName=$('#lastName').val();
		var middleInitial=$('#middleInitial').val();
		var startDate=$('#employeeStartDate').val();
		var endDate=$('#employeeEndDate').val();
		var password=$('#password').val();
	    var emailId=$('#emailId').val();
		$.ajax({
		    url: "/ezXpense/admin/addsiteadmin.do",
		    type: 'POST',
		    
		    data:
		    	{
		    		organizationId:organizationId,
		    		orgName:orgName,
		    		dbName:dbName,
		    		employeeNumber:employeeNumber,
		    		username:username,
		    		firstName:firstName,
		    		lastName:lastName,
		    		middleInitial:middleInitial,
		    		emailId:emailId,
		    		password:password,
		    		startDate:startDate,
		    		endDate:endDate,
		       	},
		       	success: function(data) {
		       		
		       		if(data=="success"){
			   		    alert("Site Admin added Successfully ");
			   		    window.location="/ezXpense/adminhome.do?orgId="+organizationId;
		       		}
		       		else{
		       			alert(data);
		       			
		       		}
			    },
			   
			});
    	}
		
    	
    	
    }
    //close user profile popup
    function closeUserProfilePopup(){
		var $body=$('body');
		$("#user_profile_popup").fadeOut(300);
		$body.removeClass('loading');
	
     } 
    </script>
<style>
.addorg_error {
	display: block;
	float: right;
	color: #f01;
	font-size: 10px;
}

.organizationListPopup {
	position: absolute;
	padding: 10px;
	border: solid rgb(99, 102, 103);
	z-index: 9999;
	background: white;
	color: black;
}

.user_profile_popup {
	position: absolute;
	border: 2px solid rgb(99, 102, 103);
	padding: 10px;
	background: white;
	width: 60%;
	height: 800px;
	z-index: 9999;
	color: black;
}

#header1 {
	font-size: 25px;
	font-weight: bold;
	color: #245a88;
	margin-bottom: 25px;
	margin-top: 15px;
	margin-left: 45%;
}

.status {
	color: blue;
	font-size: 18px;
}

.change_password_popup {
	position: absolute;
	border: 2px solid rgb(99, 102, 103);
	padding: 10px;
	background: white;
	width: 60%;
	height: 300px;
	z-index: 9999;
	color: black;
	font-size: 18px;
	font-weight: bold;
	float: right;
}

.user_profile_popup {
	font-size: 18px;
	font-weight: bold;
	float: right;
	/* margin-bottom: 25px;
			margin-top: 15px;
			margin-left: 20px; */
}

.error_class {
	color: #f01;
	width: 30%;
	float: right;
	font-size: 10px;
}

.organizationListPopup {
	font-size: 18px;
	font-weight: bold;
	float: left;

	/* margin-bottom: 25px;
			margin-top: 15px;
			margin-left: 20px; */
}

#focus_div {
	color: #e8e8e8;
	overflow-y: scroll;
	height: 450px;
	width: 500px;
}

#loader {
	width: 100%;
	height: 100%;
	position: fixed;
	top: 0;
	left: 0;
	z-index: -1;
	display: none;
}

#loader img {
	position: fixed;
	top: 50%;
	left: 50%;
}

.loading #loader {
	z-index: 1001;
	background: rgba(54, 25, 25, .5);
	display: inline;
}

.responstable {
	overflow-y: scroll;
}
</style>

</head>

<body onload="loadOnValues()"
	class="full-layout  nav-right-hide nav-right-start-hide  nav-top-fixed    responsive    clearfix"
	data-active="dashboard " data-smooth-scrolling="1">
	<div class="vd_body">
		<!-- Header Start -->
		<%@ include file="/jsp/common/adminHeader.jsp"%>
		<!-- Header Ends -->
		<div class="content">
			<div class="container">
				<div
					class="vd_navbar vd_nav-width vd_navbar-tabs-menu vd_navbar-left">
					<div class="navbar-menu clearfix">
						<div class="vd_menu">
							<nav class="navbar navbar-default" role="navigation">
								<!-- Main Menu -->
								<div class="side-menu-container vd_menu">
									<ul class="nav navbar-nav">
										<li class="panel panel-default active" id="dropdown"><a
											data-toggle="collapse" href="#dropdown-lvl3"> <span
												class="menu-icon"><span class="fa fa-tachometer"></span></span>
												<span class="menu-text">Admin</span> <span class="caret"></span>
										</a> <!-- Dropdown level 1 -->
											<div id="dropdown-lvl3" class="panel-collapse collapse">
												<div class="panel-body vd_menu">
													<ul class="nav navbar-nav">
														<!-- Dropdown level 2 -->
														<li class="panel panel-default" id="dropdown"><a
															data-toggle="collapse" href="#dropdown-lvl4"> <span
																class="menu-icon"><span class="fa fa-cog"></span></span>
																<span class="menu-text">Setup</span> <span class="caret"></span>
														</a>
															<div id="dropdown-lvl4" class="panel-collapse collapse">
																<div class="panel-body vd_menu">
																	<ul class="nav navbar-nav">
																		<li><a href="#"><span class="menu-icon"><i
																					class="fa fa-sitemap"></i></span> <span class="menu-text">Organization
																			</span></a></li>
																	</ul>
																</div>
															</div></li>
														<!-- Dropdown level 3 -->
														<li class="panel panel-default" id="dropdown"><a
															data-toggle="collapse" href="#dropdown-lvl5"> <span
																class="menu-icon"><span class="fa fa-file-text"></span></span>
																<span class="menu-text">Reports</span> <span
																class="caret"></span>
														</a>
															<div id="dropdown-lvl5" class="panel-collapse collapse">
																<div class="panel-body vd_menu">
																	<ul class="nav navbar-nav">
																		<li><a href="#"><span class="menu-icon"><i
																					class="fa fa-check-square" aria-hidden="true"></i></span>
																				<span class="menu-text">&lt;to be done&gt;</span> </a></li>
																	</ul>
																</div>
															</div></li>
													</ul>
												</div>
											</div></li>
									</ul>
								</div>
							</nav>
						</div>
					</div>
				</div>




				<!-- Middle Content Start -->
				<div class="vd_content-wrapper">
					<div class="vd_container">
						<div class="vd_content clearfix">
							<div class="panel panel-default">
								<div class="panel-heading">
									Organization Details <a class="pull-right" href="#"
										onClick="addOrganization()"><i class="fa fa-plus"
										aria-hidden="true"></i> </a>
								</div>
								<c:if test="${not empty message}">
									<div class="status">${message}</div>
									<br>
									<br>
								</c:if>
								<c:if test="${not empty status}">
									<div class="status">${status }</div>
								</c:if>
								<div class="panel-body">
									<div class="col-md-12 custyle">
										<div class="custab">
											<div class="col-md-12">
												<form:form class="form-horizontal" autocomplete="off"
													action="${pageContext.request.contextPath}/admin/addorganization.do"
													method="POST" commandName="organizationDto"
													onsubmit="return validateform();"
													enctype="multipart/form-data">

													<div class="col-md-6">
														<div class="row">
															<div class="col-md-4">
																<div class="org_se">
																	<label class="mn_org">Organization Name<span>*</span></label>
																</div>
															</div>
															<div class="col-md-8">
																<div class="org_in">
																	<div class="mn_organi">
																		<form:hidden name="organizationId"
																			path="organizationId" id="organizationId"
																			value="${organizationDto.organizationId}" />
																		<form:input name="orgName" path="orgName" type="text"
																			id="orgName" placeholder="Organization Name"
																			value="${organizationDto.orgName}" />
																		<a class="pull-right" href="#"
																			onClick="selectOrganization()"><i
																			class="fa fa-search" aria-hidden="true"></i></a>
																		<div class="organizationListPopup"
																			id="organizationListPopup" style="display: none;">

																			<div id="focus_div">
																				<a href="#" id="close_popup" onclick="closePopup()"><img
																					src="${pageContext.request.contextPath}/images/close_icon1.png"
																					height="15px;" width="15px;" style="float: right;" /></a>

																				<table class="responstable" id="responsetable">

																					<tr>
																						<th>Select</th>
																						<th>Organization Id</th>
																						<th>Organization Name</th>
																						<th>Organization Short name</th>
																					</tr>
																					<c:forEach var="organization"
																						items="${organizations}">
																						<tr>
																							<td><input type="radio" id="organizationId"
																								name="orgId"
																								value="${organization.organizationId}"
																								onchange="getOrganization(${organization.organizationId});"></td>
																							<td>${organization.organizationId}</td>
																							<td>${organization.orgName}</td>
																							<td>${organization.orgShortName}</td>
																						</tr>
																					</c:forEach>


																				</table>

																			</div>
																		</div>

																	</div>
																	<span id="orgname_error" class="addorg_error"> <form:errors
																			class="addorg_error" />
																	</span>
																</div>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-4">
																<div class="org_se">
																	<label class="mn_org">Organization Short Name<span>*</span></label>
																</div>
															</div>
															<div class="col-md-8">
																<div class="org_in">
																	<form:input class="mn_organi" name="orgShortName"
																		path="orgShortName" type="text" id="orgShortName"
																		placeholder="Organization Short Name"
																		value="${organizationDto.orgShortName}" />
																</div>
																<span id="#orgshortname_error" class="addorg_error">
																	<form:errors class="addorg_error" />
																</span>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-6">
																<div class="org_se">
																	<label class="mn_org">On Site Flag (Yes for on
																		site) <span>*</span>
																	</label>
																</div>
															</div>
															<div class="col-md-6">
																<div class="org_in">
																	<form:checkbox path="onSiteFlag" name="onSiteFlag"
																		value="y" id="onSiteFlag" onclick="updateCheck();" />
																	Yes
																</div>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-4">
																<div class="org_se">
																	<label class="mn_org">Base Url<span>*</span></label>
																</div>
															</div>
															<div class="col-md-8">
																<div class="org_in">
																	<form:input class="mn_organi" name="baseURL"
																		path="baseURL" id="baseURL" type="text"
																		placeholder="URL" value="${organizationDto.baseURL}" />
																</div>
																<span id="baseurl_error" class="addorg_error"> <form:errors
																		class="addorg_error" />
																</span>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-4">
																<div class="org_se">
																	<label class="mn_org">Db Ip<span>*</span></label>
																</div>
															</div>
															<div class="col-md-4">
																<div class="org_in">
																	<form:input class="mn_organi" path="dbIp" name="dbIp"
																		id="dbIp" type="text" placeholder="IP Address"
																		value="${organizationDto.dbIp}" />
																</div>
																<span id="dbip_error" class="addorg_error"> <form:errors
																		class="addorg_error" />
																</span>
															</div>
															<div class="col-md-1">
																<div class="org_se">
																	<label class="mn_org">Port<span>*</span></label>
																</div>
															</div>
															<div class="col-md-3">
																<div class="org_in">
																	<form:input class="mn_organi" name="dbPort"
																		path="dbPort" id="dbPort" type="text"
																		placeholder="Port Number"
																		value="${organizationDto.dbPort}" />
																</div>
																<span id="dbport_error" class="addorg_error"> <form:errors
																		class="addorg_error" />
																</span>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-4">
																<div class="org_se">
																	<label class="mn_org">Db Name<span>*</span></label>
																</div>
															</div>
															<div class="col-md-4">
																<div class="org_in">
																	<form:input class="mn_organi" path="dbName" id="dbName"
																		name="dbName" type="text" placeholder="DB Name"
																		value="${organizationDto.dbName}" />
																</div>
																<span id="dbname_error" class="addorg_error"> <form:errors
																		class="addorg_error" />
																</span>
															</div>
															<div class="col-md-1">
																<div class="org_se">
																	<label class="mn_org">Curr<span>*</span></label>
																</div>
															</div>
															<div class="col-md-3">
																<div class="org_in">
																	<form:select path="currency" name="currency"
																		id="currency" class="mn_organi">
																		<%-- <form:option value="${organizationDto.currency}">${organizationDto.currency}</form:option> --%>
																		<option value="">SELECT</option>
																		<option value="INR"
																			${organizationDto.currency eq 'INR' ? 'selected' : ''}>INR</option>
																		<option value="USD"
																			${organizationDto.currency eq 'USD' ? 'selected' : ''}>USD</option>
																		<option value="EUR"
																			${organizationDto.currency eq'EUR' ? 'selected' : ''}>EUR</option>
																	</form:select>
																</div>
															</div>
														</div>
													</div>

													<div class="col-md-6">
														<div class="row">
															<div class="col-md-4">
																<div class="org_se">
																	<label class="mn_org">Logo File</label>
																</div>
															</div>
															<div class="col-md-8">
																<div class="org_in">
																	<img
																		src="/var/organization/images/${organizationDto.logoFile}"
																		onerror="this.src='${pageContext.request.contextPath}/images/logo.png'"
																		height="65px;" width="66px;" /><br />

																	<form:input class="mn_organi" path="logoImage"
																		id="logoImage" name="logoImage" type="file"
																		placeholder="logo" />
																</div>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-4">
																<div class="org_se">
																	<label class="mn_org">Start Date<span>*</span></label>
																</div>
															</div>
															<div class="col-md-3">
																<div class="org_in">
																	<fmt:formatDate value="${organizationDto.startDate}"
																		var="startFormat" type="date" pattern="MM/dd/yyyy" />
																	<form:input class="inputs_group1 mn_organi"
																		name="startDate" id="startDate" type="text"
																		placeholder="Select Date" path="startDate"
																		value="${startFormat}" />
																</div>
																<span id="startdate_error" class="addorg_error">
																	<form:errors class="addorg_error" />
																</span>
															</div>
															<div class="col-md-2">
																<div class="org_se">
																	<label class="mn_org">End Date</label>
																</div>
															</div>
															<div class="col-md-3">
																<div class="org_in">
																	<fmt:formatDate value="${organizationDto.endDate}"
																		var="startFormat" type="date" pattern="MM/dd/yyyy" />
																	<form:input class="inputs_group1 mn_organi"
																		name="endDate" id="endDate" type="text"
																		placeholder="Select Date" path="endDate"
																		value="${startFormat}" />
																</div>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-10"></div>
															<div class="col-md-2">
																<div class="or_submit">
																	<button class="org_submit" type="submit">Save</button>
																</div>
															</div>
														</div>
													</div>
												</form:form>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									Site Admin Details <a href="#" onclick="add()" class="add_row"
										style="float: right"><i class="fa fa-plus"
										aria-hidden="true"></i></a>
								</div>

								<div class="panel-body mn_panel_org">
									<table class="responstable" id="responsetable">
										<tr>
											<th>Employee Number</th>
											<th data-th="Driver details"><span>User Name</span></th>
											<th>Password</th>
											<th>Last Name</th>
											<th>First Name</th>
											<th>Middle Initial</th>
											<th>Start date</th>
											<th>End date</th>
											<th>Email id</th>
											<th>Status</th>
										</tr>
										<tr>

											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="org_in">
														<input type="text" id="employeeNumber"
															placeholder="Employee Number" name="employeeNumber"
															class="mn_organi mn_ma_0" /> <span id="empnumber_error"
															class="error_class"></span>
													</div>
												</div>
											</td>
											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="org_in">
														<input type="text" id="username" placeholder="User Name"
															name="username" class="mn_organi mn_ma_0" /> <span
															id="username_error" class="error_class"></span>
													</div>
												</div>
											</td>
											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="org_in">
														<input type="text" id="password" placeholder="password"
															name="password" class="mn_organi mn_ma_0" /> <span
															id="password_error" class="error_class"></span>
													</div>
												</div>
											</td>
											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="org_in">
														<input type="text" id="lastName" placeholder="Last Name"
															name="lastName" class="mn_organi mn_ma_0" /> <span
															id="lastname_error" class="error_class"></span>
													</div>
												</div>
											</td>
											<td>
												<div class="org_in">
													<div class="addSiteAdmin" id="addSiteAdmin"
														style="display: none;">
														<input type="text" id="firstName" placeholder="First Name"
															name="firstName" class="mn_organi mn_ma_0" /> <span
															id="firstname_error" class="error_class"></span>
													</div>
												</div>
											</td>
											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="org_in">
														<input type="text" id="middleInitial"
															placeholder="Middle Initial" name="middleInitial"
															class="mn_organi mn_ma_0" />
													</div>
												</div>
											</td>

											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="org_in">

														<input type="text" id="employeeStartDate"
															placeholder="Start Date" id="startDate" name="startDate"
															class="mn_organi mn_ma_0" />
													</div>
												</div>
											</td>
											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="org_in">
														<input type="text" id="employeeEndDate"
															placeholder="End Date" id="endDate" name="endDate"
															class="mn_organi mn_ma_0" />
													</div>
												</div>
											</td>
											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="org_in">
														<input type="text" id="emailId" placeholder="Email Id"
															class="mn_organi mn_ma_0" /> <span id="email_error"
															class="error_class"></span>
													</div>
												</div>
											</td>
											<td>
												<div class="addSiteAdmin" id="addSiteAdmin"
													style="display: none;">
													<div class="or_submit">
														<button class="org_submit" type="submit"
															onclick="addSiteAdmin()">Save</button>
													</div>
												</div>
											</td>

										</tr>
										<c:forEach var="siteAdmin" items="${siteAdmins}">
											<tr>
												<td id="employeeNumber${siteAdmin.userId}">${siteAdmin.employeeNumber}</td>
												<td id="username${siteAdmin.userId }">${siteAdmin.username}</td>
												<td id="password${siteAdmin.userId }"><a href="#"
													id="change_password${siteAdmin.userId}"
													onclick="changepasswordpopup(${siteAdmin.userId})">Change
														password</a></td>
												<td id="lastName${siteAdmin.userId }">${siteAdmin.lastName }</td>
												<td id="firstName${siteAdmin.userId }">${siteAdmin.firstName }</td>
												<td id="middleInitial${siteAdmin.userId}">${siteAdmin.middleInitial }</td>
												<td id="startDate${siteAdmin.userId}"><fmt:formatDate
														value="${siteAdmin.startDate}" pattern="MM/dd/YYYY" /></td>
												<td id="endDate${siteAdmin.userId}"><fmt:formatDate
														value="${siteAdmin.endDate}" pattern="MM/dd/YYYY" /></td>
												<td id="emailId${siteAdmin.userId}">${siteAdmin.emailId }</td>
												<td><a class="btn btn-success btn-xs has-tooltip"
													id="user_profile${siteAdmin.userId}"
													onclick="viewUserProfile(${siteAdmin.userId});"><span
														class="loadr"></span><i class="fa fa-pencil-square-o"></i></a>
													<a href="#" onclick="deleteUser(${siteAdmin.userId})"
													class="btn btn-danger btn-xs has-tooltip"><span
														class="loadr"></span><i class="fa fa-trash-o"></i></a></td>
											</tr>
										</c:forEach>




									</table>
								</div>
								<div class="clearfix"></div>
								<div id="loader"></div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<!----------------------------------- User Profile Popup------------------------------>
			<div id="user_profile_popup" class="user_profile_popup"
				style="display: none;">
				<a href="#" id="close_popup" onclick="closeUserProfilePopup();"><img
					src="${pageContext.request.contextPath}/images/close_icon1.png"
					height="28px;" width="28px;" style="float: right;" /></a> <br />
				<div id="header1">EDIT USER PROFILE</div>
				<form:form class="userProfileForm" id="userProfileForm"
					action="${pageContext.request.contextPath}/admin/editprofile.do"
					commandName="ezXpenseUserDto" method="POST"
					onsubmit="return validateUpdatedSiteAdmin();">

					<div class="form-group">
						<div class=col-sm-2></div>
						<label class="col-sm-2 control-label">Last Name :</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="form_lastName"
								name="lastName" />
						</div>
						<span id="formlastname_error" class="error_class"> </span>

					</div>
					<br />
					<br />
					<div class="form-group">
						<div class=col-sm-2></div>
						<label class="col-sm-2 control-label">First Name :</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="form_firstName"
								name="firstName" />
						</div>
						<span id="formfirstname_error" class="error_class"> </span>

					</div>
					<br />
					<br />
					<div class="form-group">
						<div class=col-sm-2></div>
						<label class="col-sm-2 control-label">Middle Initial:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="form_middleInitial"
								name="middleInitial" />
						</div>

					</div>
					<br />
					<br />
					<div class="form-group">
						<div class=col-sm-2></div>
						<label class="col-sm-2 control-label">Start Date :</label>
						<div class="col-sm-7">

							<input type="text" class="form-control" id="form_startDate"
								name="startDate" />
						</div>

					</div>
					<br />
					<br />
					<div class="form-group">
						<div class=col-sm-2></div>
						<label class="col-sm-2 control-label">End Date :</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="form_endDate"
								name="endDate" />
						</div>

					</div>
					<br />
					<br />
					<div class="form-group">
						<div class=col-sm-2></div>
						<label class="col-sm-2 control-label">Email Id :</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="form_emailId"
								name="emailId" />
						</div>
						<span id="formemail_error" class="error_class"> </span>

					</div>
					<br />
					<br />
					<input type="hidden" id="form_employeeNumber" name="employeeNumber" />
					<input type="hidden" id="form_userId" name="userId" />
					<input type="hidden" id="form_orgId" name="orgId" />
					<input type="hidden" id="form_dbName" name="dbName" />
					<input type="hidden" id="form_username" name="username" />
					<input type="hidden" id="form_password" name="password" />
					<input type="hidden" id="form_orgName" name="orgName" />
					<div class="col-sm-offset-6 col-sm-6">
						<div class="or_submit">
							<button type="submit" class="org_submit">Update Profile</button>
						</div>
					</div>


				</form:form>


			</div>
			<!----------------------------------- End of User Profile Popup------------------------------>
			<!-- --------------------------------Change password of site admin popup----------- -->
			<div id="change_password_popup" class="change_password_popup"
				style="display: none;">
				<a href="#" id="close_popup" onclick="closeChangePasswordPopup();"><img
					src="${pageContext.request.contextPath}/images/close_icon1.png"
					height="28px;" width="28px;" style="float: right;" /></a> <br />
				<div id="header1">Change SITE ADMIN Password</div>
				<form:form class="changePasswordForm" id="changePasswordForm"
					action="${pageContext.request.contextPath}/admin/changepasswordofsiteadmin.do"
					commandName="changeSiteAdminPasswordDto" method="POST"
					onsubmit="return validatePassword();">


					<div class="form-group">
						<div class=col-sm-2></div>
						<label class="col-sm-2 control-label">Password :</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="newPassword"
								name="newPassword" />
						</div>
						<span id="new_error_msg" class="error_class"> </span>
					</div>
					<br />
					<br />

					<div class="form-group">
						<div class=col-sm-2></div>
						<label class="col-sm-2 control-label">Update Password :</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="confirmPassword"
								name="confirmPassword" />
						</div>
						<span id="confirm_error_msg" class="error_class"> </span>

					</div>
					<br />
					<br />

					<input type="hidden" id="form2_userId" name="userId" />
					<input type="hidden" id="form2_orgId" name="orgId" />
					<input type="hidden" id="form2_dbName" name="dbName" />
					<input type="hidden" id="form2_username" name="username" />
					<input type="hidden" id="form2_emailId" name="emailId" />
					<input type="hidden" id="form2_orgName" name="orgName" />
					<div class="col-sm-offset-6 col-sm-6">
						<div class="or_submit">
							<button type="submit" class="org_submit">Update Password</button>
						</div>
					</div>
				</form:form>
			</div>
			<!-- --------------------------------End Change password of site admin popup----------- -->
			<!-- Footer Start -->
			<%@ include file="/jsp/common/footer.jsp"%>
			<!-- Footer END -->
		</div>
	</div>
	<!-- .vd_body END  -->
	<a id="back-top" href="#" data-action="backtop"
		class="vd_back-top visible"> <i class="fa  fa-angle-up"> </i>
	</a>

	<script>
      $(document).ready(function() {
        $('#startDate').datepicker({
          minDate: 0,
          onClose: function() {
            var minDate = $(this).datepicker('getDate');
            minDate.setDate(minDate.getDate() + 1);
            date_selected = (minDate.getMonth() + 1) + '/' + (minDate.getDate()) + '/' + minDate.getFullYear();
            $('#endDate').datepicker("option", "minDate", date_selected);
          }
        });

        $('#endDate').datepicker({
          minDate: 1
        });

      });
      $(document).ready(function() {
          $('#employeeStartDate').datepicker({
            minDate: 0,
            onClose: function() {
              var minDate = $(this).datepicker('getDate');
              minDate.setDate(minDate.getDate() + 1);
              date_selected = (minDate.getMonth() + 1) + '/' + (minDate.getDate()) + '/' + minDate.getFullYear();
              $('#employeeEndDate').datepicker("option", "minDate", date_selected);
            }
          });

          $('#employeeEndDate').datepicker({
            minDate: 1
          });

        });
      $(document).ready(function() {
          $('#form_startDate').datepicker({
            minDate: 0,
            onClose: function() {
              var minDate = $(this).datepicker('getDate');
              minDate.setDate(minDate.getDate() + 1);
              date_selected = (minDate.getMonth() + 1) + '/' + (minDate.getDate()) + '/' + minDate.getFullYear();
              $('#form_endDate').datepicker("option", "minDate", date_selected);
            }
          });

          $('#form_endDate').datepicker({
            minDate: 1
          });

        });
    </script>
	<!-- Specific Page Scripts Put Here -->
	<script>
      $("#v_guide").click(function() {
        $("#v_form").toggle("slow", function() {
          // Animation complete.
        });
      });

    </script>
</body>

</html>
