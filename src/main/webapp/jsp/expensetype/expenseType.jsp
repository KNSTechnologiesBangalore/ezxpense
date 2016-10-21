<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>::- Organization Site-Admin Setup -::</title>
<%@ include file="/jsp/common/common.jsp"%>
</head>
<style>
    .expense_type_popup{
	            position: absolute;
				border: 2px solid rgb(99, 102, 103);
				padding: 10px;
				background: white;
				width:50%;
				height: 230px;
				z-index: 9999;
    			color: black; 
    			font-size: 18px;
				font-weight: bold;
				float: right;
    }
    .message_popup{
    	 		position: absolute;
				border: 2px solid rgb(99, 102, 103);
				padding: 10px;
				background: white;
				width:50%;
				height: 230px;
				z-index: 9999;
    			color: black; 
    			font-size: 18px;
				font-weight: bold;
				float: right;
    }	
	#header1{
			font-size:25px;
			font-weight:bold;
			color:#245a88;
			margin-left: 120px;
	}
	#clearfix{
		    margin-left: 80px;
	}
	.failure {
		color: #f01;
	  	font-size: 20px;
    	margin-top: 50px;
    	margin-left: 60px;
	}
	.success{
		color: #228B22;
	  	font-size: 20px;
    	margin-top: 40px;
    	margin-left: 70px;
	}
	.loader {
 	border: 14px solid #f3f3f3;
    border-radius: 50%;
    border-top: 14px solid #047ec5;
    width: 120px;
    height: 120px;
 	-webkit-animation: spin 4s linear infinite;
}
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
#blanket {
   background-color:#111;
   opacity: 0.65;
   *background:none;
   position:absolute;
   z-index: 9001;
   top:0px;
   left:0px;
   width:100%;
}
</style>
<script>
	/* function for adding new row in the table */
	function add() {
		$(".addExpenseType").css("display", "block");
	}
	/*function for adding new expense type to that data base*/
	function addExpenseType() {
		var expenseType = $('#expenseType1').val();
		$("#loader").css("display", "block");
		if(document.getElementById('checkBoxFlag').checked){
			expenseFlag=document.getElementById('checkBoxFlag').value ='Y';
        }
        else{
        	
        	expenseFlag=document.getElementById('checkBoxFlag').value ='N';
       }
		if(document.getElementById('checkBoxActive').checked){
			
			active=document.getElementById('checkBoxActive').value ='Y';
        }
        else{
        
        	active=document.getElementById('checkBoxActive').value ='N';
       }
		
		if (expenseType == null || expenseType.length < 1) {
			 $("#failure_message_popup").fadeIn(500);
			 $('#error_message').text('Expense Type Should not be null').css("display", "block");
			 $("#loader").css("display", "none");
			 $("#blanket").css("display", "block");
		//	alert("expenseType should not be null");
		} else {
			$.ajax({
				url : "/ezXpense/siteadmin/addexpensetype.do",
				type : 'POST',
				data : {
					expenseType : expenseType,
					expenseFlag : expenseFlag,
					active : active,
				},
				success : function(data) {

					if (data == "success") {
						 $("#success_message_popup").fadeIn(500);
						 $("#loader").css("display", "none");
						 $('#success_message').text('Expense Type added successfully').css("display", "block");
						 $("#blanket").css("display", "block");
					//	alert("Ezxpense Type added Successfully ");
					} else {
						$("#loader").css("display", "none");
						$("#failure_message_popup").fadeIn(500);
						$('#error_message').text(data).css("display", "block");
						$("#blanket").css("display", "block");
					}
				},
			});
		}
	}
	/*function for getting types data and displying in update popup page*/
	function div_update(expenseType,checkBoxFlag,checkBoxActive) {
	$('#expenseType').val(expenseType);
	$("#blanket").css("display", "block");
	$('#expense_type_popup').fadeIn(500).css('display','block');
	$("#success_message_popup").fadeOut(500);
	$("#failure_message_popup").fadeOut(500);
	if(checkBoxFlag == 'Y'){
		document.getElementById('checkMilageFlag').checked=true;
    }else{
    	document.getElementById('checkMilageFlag').checked=false;
   		}
	if(checkBoxActive == 'Y'){
		document.getElementById('checkActiveFlag').checked=true;
    }else{
    	document.getElementById('checkActiveFlag').checked=false;
    	}
	}
	/*function for updating existing expense type and passing to the controller*/
	function update() {
		alert("inside upadate");
		if(document.getElementById('checkBoxFlag').checked){
			expenseFlag=document.getElementById('checkBoxFlag').value ='Y';
        }
        else{
        	expenseFlag=document.getElementById('checkBoxFlag').value ='N';
       }
		if(document.getElementById('checkBoxActive').checked){
			active=document.getElementById('checkBoxActive').value ='Y';
        }
        else{
        	active=document.getElementById('checkBoxActive').value ='N';
       }
		$('#update').fadeIn(500).css('display','block');
		if (expenseType == null || expenseType.length < 1) {
			 $("#failure_message_popup").fadeIn(500);
			 $('#error_message').text('Expense Type Should not be null').css("display", "block");
			 $("#loader").css("display", "none");
			//alert("expenseType should not be null");
			
		} else {
			$.ajax({
				url : "/ezXpense/siteadmin/updateexpensetype.do",
				type : 'POST',
				data : {
					expenseType : expenseType,
					expenseFlag : expenseFlag,
					active : active,
				},
				success : function(data) {
					if (data == "success") {
						 $("#success_message_popup").fadeIn(500);
						 $("#loader").css("display", "none");
						 $('#success_message').text('Ezxpense Type Updated Successfully').css("display", "block"); 
					//	alert("Ezxpense Type Updated Successfully ");
					} else {
						alert(data);
					}
				},
			});
		}
		}
	function div_hidden() {
		 var $body=$('body');
		  $("#expense_type_popup").fadeOut(500);
		  $("#failure_message_popup").fadeOut(500);
		  $("#success_message_popup").fadeOut(500);
		  $body.removeClass('loading');
	}
	function delete_type(){
		$("#loader").css("display", "block");
	}
</script>
<body class="full-layout  nav-right-hide nav-right-start-hide  nav-top-fixed responsive clearfix"data-active="dashboard " data-smooth-scrolling="1">
	<div class="vd_body" id="modal">
		<!-- Header Start -->
		<%@ include file="/jsp/common/header.jsp"%>
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
										<%@ include file="/jsp/common/leftContentFile.jsp"%>
									</ul> 
								</div>
							</nav>
						</div>
					</div>
				</div>
				<div id="blanket" style="display:none;"></div>
				<div class="vd_content-wrapper">
					<div class="vd_container">

						<div class="panel panel-default">
							<div class="panel-heading">
								Expense Type <a href="#" onclick="add()" class="add_row"style="float: right"><i class="fa fa-plus"aria-hidden="true"></i></a>
							</div>
							<div class="panel-body mn_panel_org">
								<table class="responstable">
									<tr>
										<th>Expense Type</th>
										<th>Mileage Flag</th>
										<th>Active Flag</th>
										<th>Status</th>
									</tr>
									<tr>
									<td>
											<div class="addExpenseType" id="addExpenseType"	style="display: none;">
												<div class="org_in">
													<input type="text" id="expenseType1"	placeholder="expenseType" name="expenseType"class="mn_organi mn_ma_0" />	
												</div>
											</div>
										</td>
										<td>
										<div class="addExpenseType" id="addExpenseType"style="display: none;">
												<div class="org_in">
													<input type="checkbox" class="checkBoxClass" id="checkBoxFlag" name="flags">
												</div>
										</div>
										</td>
										<td>
										<div class="addExpenseType" id="addExpenseType"style="display: none;">
												<div class="org_in">
													<input type="checkbox" class="checkBoxClass" id="checkBoxActive" name="active">
												</div>
										</div>
										</td>
										<td>
										<div class="addExpenseType" id="addExpenseType"	style="display: none;">
												<div class="org_in">
													<button class="org_submit" type="submit"onclick="addExpenseType()" style="border-radius: 20px;">
														<i class="fa fa-floppy-o"></i> Save
													</button>
												</div>
										</div>
										</td>
									</tr>
									
									<c:forEach var="expenseTypeDto" items="${expenseTypeDto}">
									<tr>
										<td>${expenseTypeDto.expenseType}</td>										
										<td>
										<c:choose >
										<c:when test="${expenseTypeDto.mileage_flag eq 'Y'}">
										<input type="checkbox" class="checkBoxClass" id="checkBoxFlag2" name="flag" value="1" checked disabled readonly/>								
										</c:when>
										<c:otherwise>
										<input type="checkbox" class="checkBoxClass" id="checkBoxFlag2" name="flag" value="0" readonly/>
										</c:otherwise>
										</c:choose>
										</td>
										<td>
										<c:choose >
										<c:when test="${expenseTypeDto.active_flag eq 'Y'}">
										<input type="checkbox" class="checkBoxClass"id="checkBoxActive2" name="active" value="1" checked disabled readonly/>
										</c:when>
										<c:otherwise>
										<input type="checkbox" class="checkBoxClass"id="checkBoxActive2" name="active" value="0" readonly/>
										</c:otherwise>
										</c:choose>
										</td>
										<td><a onclick="div_update('${expenseTypeDto.expenseType}','${expenseTypeDto.mileage_flag}','${expenseTypeDto.active_flag}')" class="btn btn-success btn-xs has-tooltip" ><span class="loadr" ></span> <i class="fa fa-pencil-square-o"></i>
										</a> <a onclick="delete_type()"href="${pageContext.request.contextPath}/siteadmin/deleteexpensetype.do?expenseType=${expenseTypeDto.expenseType}" class="btn btn-danger btn-xs has-tooltip"><span class="loadr"></span> <i class="fa fa-trash-o" ></i> </a>
										</td>
										
											<!-- /****************************popup for update data*****************************************************/ -->
											<div id="expense_type_popup" class="expense_type_popup" style="left: 280px; top: 100px;width: 485px; position: absolute;display:none;">
													<div id="update_popup${expenseTypeDto.expenseType}${flag}${checkBoxActive}">
													<div id="header1"> EDIT EXPENSE TYPE
														<a href="#" style="background-color: #FFF;float: right;"><img onclick="div_hidden();" src="${pageContext.request.contextPath}/images/close_icon1.png"  height="28px;" width="28px;" /></a>
														  </div> 
	        											<br/>											
														<div id="clearfix">
														<label>Expense_Type :</label> <input id="expenseType"name="expenseType" placeholder="ExpenseType" type="text"  style="font-weight: 800;">
														</div>
														<div id="clearfix">
														<label>Milage_Flag :</label><input type="checkbox"class="checkBoxClass" id="checkMilageFlag" name="flags" style="margin-left: 21px;">
														</div>
														<div id="clearfix">
														<label>Active_flag :</label><input type="checkbox"class="checkBoxClass" id="checkActiveFlag" name="active" style="margin-left: 26px;">
														</div>
														<div id="clearfix">
															<button class="org_submit" type="submit"onclick="update()"style="margin-left: 90px;margin-top: 5px;border-radius: 20px;">
																<i class="fa fa-floppy-o fa-lg"></i> Update
															</button>
														</div>

													</div>
											</div>
										</tr>					
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div id="loader" class="loader" style="display:none;margin-left: 53%;"></div>
			</div>			
		</div>

		<div id="failure_message_popup" class="message_popup" style="left: 40%; top: 102px;width: 425px; position: absolute;display:none;">
			<a href="#" style="background-color: #FFF;float: right;"><img onclick="div_hidden();" src="${pageContext.request.contextPath}/images/close_icon1.png"  height="28px;" width="28px;" /></a>
			<div id="header1">ALERT MESSAGE</div>
			<label class="failure" id="error_message"></label>
		</div>
		<div id="success_message_popup" class="message_popup" style="left: 40%; top: 102px;width: 425px; position: absolute;display:none;">
			<a href="#" style="background-color: #FFF;float: right;"><img onclick="div_hidden();" src="${pageContext.request.contextPath}/images/close_icon1.png"  height="28px;" width="28px;" /></a>
			<div id="header1">ALERT MESSAGE</div>
			<label class="success" id="success_message"></label>
			<div id="clearfix">
			<a href="${pageContext.request.contextPath}/siteadmin/expensetype.do">
				<button class="org_submit" type="submit"style="margin-left: 75px;margin-top: 45px;">
					<i class="fa fa-check-circle fa-lg"></i> Ok
				</button>
			</a>
			</div>
		</div>
		<!-- Footer Start -->
		<%@ include file="/jsp/common/footer.jsp"%>
		<a id="back-top" href="#" data-action="backtop"	class="vd_back-top visible">
			<i class="fa  fa-angle-up"></i>
		</a>
		<!-- Footer END -->
	</div>
	<div id="updates"style="display: none; float: left;">
		<h3>ExpenseType Update
			<a href="#" id="close_popup" onclick="div_hidden();"style="background: #ffffff;color: #000000;position: absolute;top: 0px;right: 0px;"><i class="fa fa-times-circle fa-lg" aria-hidden="true" ></i></a>
		</h3>											
	</div>
</body>
</html>