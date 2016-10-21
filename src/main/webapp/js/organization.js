/**
 * 
 */

function validateform()
{
	var nameFilter=/[^a-zA-Z]+$/;
	var nameFilter2=/[^a-zA-Z]+$/;
	var filter=/[^a-zA-Z_0-9]+/g;
	var numberFilter=/[^0-9]+$/;
	var ipAddressFilter=/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	var a=true;
    var b=true;
	var c=true;
	var d=true;
	var e=true;
	var f=true;
	var g=true;
	var h=true;
	
	var orgName=$('#orgName').val();
	var orgShortName=$('#orgShortName').val();
	var baseURL=$('#baseURL').val();
	var dbIp=$('#dbIp').val();
	var dbPort=$('#dbPort').val();
	var dbName=$('#dbName').val();
	var startDate=$('#startDate').val();
	var endDate=$('#endDate').val();
	
	alert("inside validating org form");
	if(null== orgName || orgName.trim().length<1) {
		a=false;
		$('#orgname_error').text('* Organization  name Cannot be Empty');
	}
	
	else if(nameFilter.test(orgName)) {
		a=false;
		$('#orgname_error').text("* Organization Name should have only alphabets");
	}
	else {
		a=true;
		$('#orgname_error').text('');
	}
	
	
	if(null== orgShortName || orgShortName.trim().length<1) {
		b=false;
		$('#orgshortname_error').text('* Organization short name Cannot be Empty');
	}
	else if(nameFilter2.test(orgShortName)) {
		b=false;
		$('#orgshortname_error').text("* Organization short name should  have only alphabets");
	   
	}
	else {
		b=true;
		$('#orgshortname_error').text('');
	}
	
	
	
	if(null==baseURL || baseURL.trim().length<1) {
		c=false;
		$('#baseurl_error').text('* base url Cannot be Empty');
	}	
	else {
		c=true;
		$('#baseurl_error').text('');
	}
	
	
	if(null==dbPort || dbPort.trim().length<1) {
		e=false;
		$('#dbport_error').text('* DB Port Cannot be Empty');
	}	
	else {
		e=true;
		$('#dbport_error').text('');
	}
	if(null== dbName || dbName.trim().length<1) {
		f=false;
		$('#dbname_error').text('* DB name Cannot be Empty');
	 }	
	 else {
		f=true;
		$('#dbname_error').text('');
	 }
	 
	 if(null==startDate || startDate.trim().length<1) {
		g=false;
		$('#startdate_error').text('* Start date Cannot be Empty');
	 }	
	 else{
		g=true;
		$('#startdate_error').text('');
	 }
	 if(null== dbIp || dbIp.trim().length<1) {
			d=false;
			$('#dbip_error').text('* IP address Cannot be Empty');
	}	
	 else if(!ipAddressFilter.test(dbIp)){
			d=false;
			$('#dbip_error').text('* Invalid IP Address');
	}
		
	else {
			d=true;
			$('#dbip_error').text('');
			alert("verified");
	}
	
	if(a==true &&  b==true &&  c==true && d==true && e==true && f==true && g==true) {
		return true;
	}
	
	else {
		return false;
	}	
	
}
function validateUpdatedSiteAdmin(){
	var nameFilter2=/[^a-zA-Z]+$/;
	var nameFilter3=/[^a-zA-Z]+$/;
	var nameFilter4=/[^a-zA-Z]+$/;
	var numberFilter=/[^0-9]+$/;
	var filter= /[^a-zA-Z_0-9]+/g;
	var emailFilter=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
	
	var c=true;
	var d=true;
	var e=true;
	
	alert("inside validating updated profile of site admin");
	var firstName=$('#form_firstName').val();
	var lastName=$('#form_lastName').val();
	var emailId=$('#form_emailId').val();
	
	
    alert("first name="+firstName);
    alert("lastName="+lastName);
    alert("email id="+emailId);
    
	
	if(nameFilter3.test(firstName)) {
		c=false;
		$('#formfirstname_error').text("* First name should have only alphabets");
	    
	}
	else if(null== firstName || firstName.trim().length<1) {
		c=false;
		$('#formfirstname_error').text('* First name Cannot be Empty');
	}
	
	else {
		c=true;
		$('#formfirstname_error').text('');
	}
	if(nameFilter4.test(lastName)) {
		d=false;
		$('#formlastname_error').text("* Last name should have only alphabets");
	    
	}
	else if(null== lastName || lastName.trim().length<1) {
		d=false;
		$('#formlastname_error').text('* Last name Cannot be Empty');
	}
	
	else {
		d=true;
		$('#formlastname_error').text('');
	}

	if(null== emailId || emailId.trim().length<1) {
		e=false
		$('#formemail_error').text("* Email cannot be empty");
	    
	}
    else if(!emailFilter.test(emailId)){
		e=false;
		$('#formemail_error').text("* Email pattern does not match");
	}
	else {
		e=true;
		$('#formemail_error').text('');
	}

	if(c==true && d==true && e==true) {
		return true;
	}
	
	else {
		return false;
	}	
	
}

function validateSiteAdmin(){
	var nameFilter2=/[^a-zA-Z]+$/;
	var nameFilter3=/[^a-zA-Z]+$/;
	var nameFilter4=/[^a-zA-Z]+$/;
	var numberFilter=/[^0-9]+$/;
	var filter= /[^a-zA-Z_0-9]+/g;
	var emailFilter=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
	var pwd=true;
	var a=true;
	var b=true;
	var c=true;
	var d=true;
	var e=true;
	
	var employeeNumber=$('#employeeNumber').val();
	var username=$('#username').val();
	var firstName=$('#firstName').val();
	var lastName=$('#lastName').val();
	var emailId=$('#emailId').val();
	var password=$('#password').val();
	
    
	if(filter.test(employeeNumber)) {
		a=false;
		$('#empnumber_error').text("* Employee number should have only alphabets and numbers");
	    
	}
	else if(null== employeeNumber || employeeNumber.trim().length<1) {
		a=false;
		$('#empnumber_error').text('* Employee Number Cannot be Empty');
	}
	
	else {
		a=true;
		$('#empnumber_error').text('');
	}
	
	if(nameFilter2.test(username)) {
		b=false;
		$('#username_error').text("* User name should have only alphabets");
	    
	}
	else if(null== username || username.trim().length<1) {
		b=false;
		$('#username_error').text('* User name Cannot be Empty');
	}
	
	else {
		b=true;
		$('#username_error').text('');
	}
	
	if(nameFilter3.test(firstName)) {
		c=false;
		$('#firstname_error').text("* First name should have only alphabets");
	    
	}
	else if(null== firstName || firstName.trim().length<1) {
		c=false;
		$('#firstname_error').text('* First name Cannot be Empty');
	}
	
	else {
		c=true;
		$('#firstname_error').text('');
	}
	if(nameFilter4.test(lastName)) {
		d=false;
		$('#lastname_error').text("* Last name should have only alphabets");
	    
	}
	else if(null== lastName || lastName.trim().length<1) {
		d=false;
		$('#lastname_error').text('* Last name Cannot be Empty');
	}
	
	else {
		d=true;
		$('#lastname_error').text('');
	}
	if(password.trim().length<1){
		$('#password_error').text('* Please Enter Password');
		pwd=false;
	}
	else if(password.trim().length<8 || password.trim().lenght>20){
		$('#password_error').text('* Password Should have atleast 8 characters and maximum 20 characters');
		pwd=false;
	}
	else{
		$('#password_error').text('');
		pwd=true;
	}
	
	if(null== emailId || emailId.trim().length<1) {
		e=false
		$('#email_error').text("* Email cannot be empty");
	    
	}
    else if(!emailFilter.test(emailId)){
		e=false;
		$('#email_error').text("*Email pattern does not match");
	}
	else {
		e=true;
		$('#email_error').text('');
	}

	if(a==true &&  b==true &&  c==true && d==true && e==true && pwd==true) {
		return true;
	}
	
	else {
		return false;
	}	
}
