<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html> --%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>ezXpense Select Organization</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<%@ include file="/jsp/common/common.jsp"%>
<script src="select2.js"></script>

<script type="text/javascript">
 	$(document).ready(function() {
 		  $(".organizationList").select2();
 		  alert("inside document ready function");
 		});
 function getOrganization(){
	 $.ajax({
		 
		    url: "/ezXpense/getallorganization.do",
		    type: 'GET',
		    success: function(data) {
		    var obj = JSON.parse(data);
   			var organization=obj.organization;	
   			
			$.each(organization, function(i,val) {	
				
				var organizationId=val.organizationId;
				var orgName=val.orgName;
				var dbName=val.dbName;
			    $('#organizationList').append('<option value='+dbName+'  name="organization" >'+orgName+'</option>');
				
			}); 
   		
    		},
   			error:function(data) {
       			alert("Error While getting organization");
    			
    	
    		} 
    
		});
 }
 
function decider(dbName){
	 alert("inside decider");
	 //var orgname=document.getElementById('selectorg').value;
	 var dbName=dbName;
	 alert("orgname="+dbName);
	 $.ajax({
		    url: "/ezXpense/getorganizationdetail.do",
		    type: 'GET',
		    data:{
		    	dbName:dbName
		    },
		    success: function(data) {
		   		window.location="/ezXpense/jsp/login.jsp";
		    },
		    error:function(status,data,er) {
		        alert("Error While adding Organization");
		    }
		});
}
 </script>
<!--  <script>
 
 $(document).ready(function() {
		$("#selectorg").on("input", function(e) {
			var val = $(this).val();
			alert("inside select function value="+val);
		}
		)
 </script> -->
<style>
#login-form {
	margin-left: 0px;
	margin-top: 120px;
}

.main {
	width: 400px;
	height: 300px;
	font-size: 20px;
}

.label {
	color: orange;
}

#organizationList {
	overflow-y: scroll;
}
</style>
</head>
<body onload="getOrganization()">
	<div class="wraper bg-blur"></div>
	<div class="mn_main_con">
		<div class="main">
			<div class="logo">
				<img src="images/logo.png" alt="" />
			</div>
			<div id="login-form">
				<label class="label">Select Organization:</label> <select
					id="organizationList" class="organizationList" name="organization"
					onchange="decider(this.value)">
					<option value="" selected="selected">Select Organizations</option>
				</select>
				<!--  <form onsubmit="decider()">
          		 <input type="text" id="selectorg" list="organizationList" class="input"/>
          		 <input type="submit" value="submit" class="btn"/>
          		 </form>
                
            	 <datalist id="organizationList" >
            	 
	           	 </datalist> -->

			</div>
		</div>
	</div>
</body>
</html>