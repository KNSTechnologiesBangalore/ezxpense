<!-- Created By bhagya On july 14th,2016 
	Page For Showing the Error Message to web user-->
<!DOCTYPE html>

<html>
<head>

	<title>ezXpense-Error</title>
	
</head>
<body class="l-dashboard  l-footer-sticky-1">

    <section class="l-main-container">
   
	
		<section class="l-container">
	      
	        <div class="l-spaced">
	        
	        <div class="l-box l-spaced-bottom"> 
	        <!-- Middle Content Start -->
			<div class="l-box-header">
				<h2 class="l-box-title">Error</h2>
			</div>
			
			
			<div class="l-box-body l-spaced">
			<form class="form-horizontal" autocomplete="off" action="" method="POST" >
          		
          		<div class="logopanel">
					<h4 class="logo_image_header"><a href="#"><img src="${pageContext.request.contextPath}/images/logo.png" width=450px;></a> </h4> 
				</div>
				
            	<div class="error_status">
			          ${message}
			    </div>
        		
			</form>
			</div>
			</div>
			</div>
				
   			
	        
        </section>
    </section>

   </body>
</html>