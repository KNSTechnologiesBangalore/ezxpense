<html>
<head>
	 <title>::- Divisions -::</title>
	<%@ include file="/jsp/common/common.jsp"%>
</head>
<script type="text/javascript">

/* Function to append a row above the division table to add new division */
function add(){
	alert("inside add function");
	var $body=$('body');
	$(".addDivision").css("display","block"); 
} 

/* Function to delete a division */
function deleteDivision(divisionId){
	var x=confirm("You want to delete division");
	if(x==true){
	alert("deleting the division with Id:"+divisionId);
	$.ajax({
		url: "/ezXpense/siteadmin/divdelete.do",
	    type: 'POST',
	    data:
	    	{
	    		"divisionId":divisionId
	       	},
	       	success: function( data ) {
	       			
		   		    alert("Division deleted Successfully ");
		   		    window.location="/ezXpense/siteadmin/division.do";	//changed
		    },
		   
		});
	}
}
/*Function to add new division*/
function addDivision(){
	var divisionName=$('#divisionName').val();
	if(null==divisionName){
		alert("Enter valid division name");
	}
	alert(divisionName);
	$.ajax({
	    url: "/ezXpense/siteadmin/divadd.do",
	    type: 'POST',
	    data:
	    	{
	    		"divisionName":divisionName
	       	},
	       	success: function( data ) {
		   		    alert("Division added Successfully ");
		   		    window.location="/ezXpense/siteadmin/division.do";	//changed

		    },
		   
		});
	}
    
	
	
	
 /*close profile popup*/
   function closeProfilePopup(){
	var $body=$('body');
	$("#division_profile_popup").fadeOut(300);
	$body.removeClass('loading');

    } 
	
 
 /*Function to edit the selected division */
   function editDivision(divisionId,organizationId){
	   var divId=divisionId;alert("divisionId="+divId);
	 // var orgId='<%= session.getAttribute("organizationId") %>';alert(orgId);	
	 	var orgId=organizationId;alert("organizationId="+orgId);
	   document.getElementById('division_profile_popup').style.display ="inline-block";
		var $body=$('body');
		$('#form_divisionName').val($('#divisionName'+divId+orgId).text().trim());
		$('#form_divisionId').val(divId);
		 $body.addClass('loading');
		 $("#division_profile_popup").fadeIn(1000);
		 positionPopup();
		$("#focus_div").attr("tabindex",1).focus(); 
		 
	}


</script>
<style>
    	
    .addorg_error {
			display: block;
			float: right;
			color:#f01;
			font-size:10px;
	}
	.organizationListPopup{
	          position: absolute;
			  padding: 10px;
			 
			  border: solid rgb(99, 102, 103);
			  z-index: 9999;
			  background: white;
    		  color: black; 
	}
		.division_profile_popup{
	            position: absolute;
				border: 2px solid rgb(99, 102, 103);
				padding: 10px;
				background: white;
				width:50%;
				height: 230px;
				z-index: 9999;
    			color: black; 
    			
	}
	#header1{
			font-size:25px;
			font-weight:bold;
			color:#245a88;
			margin-bottom: 25px;
			margin-top: 15px;
			margin-left: 35%;
			
	}
	.status{
	 color:blue;
	 font-size: 18px;
	}
	.change_password_popup{
	 position: absolute;
				border: 2px solid rgb(99, 102, 103);
				padding: 10px;
				background: white;
				width:60%;
				height: 300px;
				z-index: 9999;
    			color: black; 
    			font-size: 18px;
				font-weight: bold;
				float: right;
	}
	.division_profile_popup {
			font-size: 18px;
			font-weight: bold;
			float: right;
			/* margin-bottom: 25px;
			margin-top: 15px;
			margin-left: 20px; */
	 }		
	
	.error_class{
			color:#f01;
			width:30%;
			float:right;
			font-size:10px;
	}
	.organizationListPopup{
			font-size: 18px;
			font-weight: bold;
			float: left;
			
			/* margin-bottom: 25px;
			margin-top: 15px;
			margin-left: 20px; */  
	 }	
	 #focus_div{
			color: #e8e8e8;
			overflow-y:scroll;
			height:450px;
			width:500px;
	}
	
	 #loader {
			  width: 100%;
			  height: 100%;
			  position: fixed;
			  top: 0;
			  left:0;
			  z-index: -1;
			  display: none;
	 }	
	  #loader img {
			   position: fixed;  
			   top: 50%;
			   left: 50%;
	 }
			
	 .loading #loader {
			    z-index: 1001;background:  rgba(54, 25, 25, .5); display
			    	: inline;
	 }	 
	 .responstable{
	 overflow-y:scroll;
	 }
    </style>
    
    
    
    
<body class="full-layout  nav-right-hide nav-right-start-hide  nav-top-fixed responsive clearfix"data-active="dashboard " data-smooth-scrolling="1">
	<div class="vd_body">
		<!-- Header Start -->
		<%@ include file="/jsp/common/header.jsp"%>
		<!-- Header Ends -->
		<!-- Middle Content  -->
			<div class="content">
			<div class="container">
				<%@ include file="/jsp/common/leftContentFile.jsp"%>
									
				<!--  Table view -->
				<div class="vd_content-wrapper">
					<div class="vd_container">
						<div class="vd_content clearfix">
							<div class="panel panel-default">
								<div class="panel-heading">
									Divisions <a href="#" onclick="add()" class="add_row" style="float: right"><i class="fa fa-plus" aria-hidden="true"></i></a>
								</div>
								<div class="panel-body mn_panel_org">
                  				<table class="responstable" id="responsetable">
										<tr>
											<th>Division Name</th>
											<th>Action</th>
										</tr>
							<tr>
								<td>
			                      <div class="addDivision" id="addDivision" style="display:none;">
			                        <div class="org_in">
			                          <input type="text" id="divisionName" placeholder="Division Name" name="divisionName" class="mn_organi mn_ma_0"/>
			                          <span id="empnumber_error" class="error_class"></span>
			                        </div>
			                       </div>
			                     </td>
		                      	 <td>
                         			<div class="addDivision" id="addDivision" style="display:none;">
                     	 			<div class="or_submit" >
						    		<button class="org_submit" type="submit"  onclick="addDivision()">Save</button>
									 </div>
									 </div>
			                      </td>
			                  </tr>    
										
											<c:forEach items="${divisionsDtos}" var="divisionsDto">
							   					<tr>	
								   					<td id="divisionName${divisionsDto.divisionId}${divisionsDto.organisationId}">${divisionsDto.divisionName}</td>
													<td><form action=""></form>
													<a class="btn btn-success btn-xs has-tooltip" href="#"  id="division_profile"  onclick="editDivision(${divisionsDto.divisionId},${divisionsDto.organisationId});">
														<span class="loadr"></span><i class="fa fa-pencil-square-o"></i></a>
														<a class="btn btn-danger btn-xs has-tooltip" href="#" id="division_profile" onclick="deleteDivision(${divisionsDto.divisionId});"  >
														<span class="loadr"></span><i class="fa fa-trash-o"></i></a> </td>
												</tr>
											</c:forEach>
										
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End of table view -->
			</div>
		</div>
		<!-- End of Middle Content -->
	</div>
	
	       <!-----------------------------------Division Profile Popup------------------------------>
	
	  <div id="division_profile_popup" class="division_profile_popup" style="left: 350px; top: 200px; position: absolute;display:none;">
	         <a href="#" id="close_popup" onclick="closeProfilePopup();"><img src="${pageContext.request.contextPath}/images/close_icon1.png"  height="28px;" width="28px;" style="float:right;"/></a> 
	         <br/>
	         <div id="header1"> EDIT DIVISION NAME  </div>
	         <form:form class="divisionProfileForm" id="divisionProfileForm" action="${pageContext.request.contextPath}/siteadmin/editprofile.do"  commandName="divisionsDto" method="GET" onsubmit="return name();">
	               
		        <div class="form-group"> 
		            <div class=col-sm-2  style="width: 50;"></div> 
		          	<label  class="col-sm-2 control-label" style="width: 147;">Division Name :</label>
		     	<div class="col-sm-7">
		            <input type="text" class="form-control" id="form_divisionName" name="divisionName"/>
		        </div>
		        <span id="formlastname_error" class="error_class"></span> 
		         <div class="col-sm-7">
					<input type="hidden" class="form-control" id="form_divisionId" name="divisionId"  />
				</div>
		              <span id="formlastname_error" class="error_class"></span> 
		        </div>
   	                <br/><br/>
                <div class="col-sm-offset-6 col-sm-6">
                  <div class="or_submit">
              		<button type="submit" class="org_submit">Update Profile</button>
            	  </div>
          		</div>
			 </form:form>
	     </div>
	           <!----------------------------------- End of Division Profile Popup------------------------------>
	
	<!-- Footer Start -->
		<%@ include file="/jsp/common/footer.jsp"%>
		<a id="back-top" href="#" data-action="backtop"
			class="vd_back-top visible"> <i class="fa  fa-angle-up"> </i>
		</a>
		<!-- Footer END -->
 <script>
      $("#v_guide").click(function() {
        $("#v_form").toggle("slow", function() {
          // Animation complete.
        });
      });

    </script>
  </body>
</html>