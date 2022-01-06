<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="org.springframework.util.ObjectUtils"%>
<%@page import="com.gov.nha.bis.server.model.BeneficiarySeccForm"%>
<%@page import="com.gov.nha.bis.server.model.SessionLoginMap"%>

<%
String requestUri="";
requestUri=request.getRequestURI().split("/")[1];
String sessid="";
sessid=session.getId();
String saveKyc="";
if(request.getAttribute("saveKyc")!=null){
	saveKyc=(String)request.getAttribute("saveKyc");
}

String userId="";
if(session.getAttribute("USERID")!=null){
	userId =(String)session.getAttribute("USERID");
}

BeneficiarySeccForm beneficiarySeccForm = null;
if(session.getAttribute("beneficiarySeccForm")!=null){
	beneficiarySeccForm=(BeneficiarySeccForm)session.getAttribute("beneficiarySeccForm");
}

String status="status";
if(request.getAttribute("status")!=null){
	status =(String)request.getAttribute("status");
}


SessionLoginMap sessionUser= new SessionLoginMap();
if(session.getAttribute("sessionUser")!=null){
	sessionUser =(SessionLoginMap)session.getAttribute("sessionUser");
}

%>

<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SECC Beneficiary View</title>

  <link rel="icon" type="image/ico" sizes="16x16" href="images/favicon.ico">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Sora:wght@100;200;300;400;500;600;700;800&display=swap"
    rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  <link rel="stylesheet" href="sass/bootstrap.min.css">
  <link rel="stylesheet" href="sass/setu-custom.css"> 
  
  <style>

   .digit-group input {
          width: 30px;
          height: 50px;
          /* background-color: #18182a; */

          border: none;
          border-bottom: 1px solid #000;
          line-height: 50px;
          text-align: center;
          font-size: 24px;
          font-family: "Raleway", sans-serif;
          font-weight: 200;
          /* color: rgb(150, 146, 146); */
          margin: 0 2px;
        }
        .bordertop {
          border-top: 1px solid #0d6efd;
        }
        .tabsnav .nav-item {
          text-align: center;
          flex: 1 1 10%;
        }
        .digit-group .splitter {
          padding: 0 5px;
          /* color: white; */
          font-size: 24px;
        }
        .tabsnav .nav-item .nav-link {
          background-color: #989898;
          color: #fff;
        }

        .tabsnav .nav-item .nav-link.active {
          background-color: #007bff;
        }

        .prompt {
          margin-bottom: 20px;
          font-size: 20px;
          /* color: white; */
        }
        .topbtns {
          top: -1.2rem;
          left: 0px;
          text-align: center;
        }

  </style>
</head>
 
<body class="mobile" onload="validateAction()">

<div class="beneficiary-main-wrap">
  <div class="row flex-nowrap">
   <%@ include file="leftColumn.jsp" %>
    <main class="col">
       <nav>
       <%@ include file="header.jsp" %>
        
      </nav>
  
      <div class="beneficiary-section">
        <main>
          <section class="SECC-data mb-4">
            <div class="container">
              <div class="px-1 px-md-3">
                <div class="col-12">
                  <h5> Download Your Ayushman Card </h5>
                  <div class="container-fluid border-bottom mx-auto">
                    <div class="row">
                      <div class="col-md-12 my-1 text-center">
                        <h6 class="content">
                          Your card has been generated! For download please enter your linked Mobile/Aadhaar number.
                        </h6>
                      </div>
                    </div>
                  </div>
                             
                  
                </div>
              </div>

              <div class="row">
                <div class="beneficiary-view-tabs mb-1  col-sm-12 col-md-6 mx-auto">
                  <ul class="nav nav-pills" id="pills-tab" role="tablist" >
                    <li class="nav-item w-50" role="presentation">
                      <button class="nav-link active" id="pills-beneficiary-tab" data-bs-toggle="pill" data-bs-target="#pills-beneficiary" type="button" role="tab" aria-controls="pills-beneficiary" aria-selected="true">Download Card</button>
                    </li>
                   
                    <li class="nav-item w-50" role="presentation">
                      <button class="nav-link" id="pills-review-tab" data-bs-toggle="pill" data-bs-target="#pills-review" type="button" role="tab" aria-controls="pills-review" aria-selected="false">Add Member</button>
                    </li>
                  </ul>
             

                  <div class="tab-content tab-content-view" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-beneficiary" role="tabpanel" aria-labelledby="pills-beneficiary-tab">
                      <div class="tab-card-in">
                        <h3>Enter Your Mobile/Aadhar No.
                        </h3>
                        
                         <form:form  action=""  method="POST" id="registration"  commandName="command"> 
                        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                        
                          <div class="col-md-12 mb-2">
                            <div for="" class="text-center my-2">
                              An OTP will be sent to your linked mobile
                              numbers
                            </div>
                          </div>
                          <div class="
                              col-md-12 mb-2
                             
                            ">
                            <div class="row mt-4">
                            <div class="col-12 text-danger"><span id="adhar_required"></span></div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-12 d-flex align-items-center mb-4 gap-3">
                              
                                <div class="custom-form-group form-group flex-grow-1 mb-0">
                                 <label> Enter Your Mobile/Aadhaar No</label>
 								<form:input path="aadhar"  class="form-control flex-grow-1"   id="aadhar" maxlength="12" 
                     				 onkeypress="return isNumberKey(event)" onchange="validateAadhar()" />
                                 
                                </div>
                                  <button class="btn btn-sm btn-dark col-sm-4" type="button" onclick="getOtp()" id="optBtn">Get OTP</button>
                         
                              </div>

                              <div class="col-12 col-sm-6 col-md-4 col-lg-12 d-flex align-items-center mb-4 gap-3">
                                  <div class="custom-form-group form-group flex-grow-1 mb-0">
                                   <label> Enter OTP</label>
								   <form:input path="otp"  class="form-control flex-grow-1" id="otp" maxlength="6" />
                  
                                </div>
                                <button class="btn btn-sm btn-dark col-sm-4" type="button" onclick="otpVerifyDownloadCard()" >Verify & Proceed</button>
                 
                               </div>
                                <div id="errorOtp" style="color: red;" class="app-title"></div>
	  		  	                <div id="infoOtp" style="color: green;" class="app-title"></div>
                          </div>

                         <!-- <a href="/<%=requestUri%>/home"  class="mt-10 text-center w-100" >Back to Search</a> -->
                      
                         <form:hidden path="otpTxn" id="otpTxn"/>
						<form:hidden path="refernceid" id="refernceid"/> 
						<form:hidden path="stateCode" id="stateCode"/>
						<form:hidden path="pmjayId" id="pmjayId"/>
			            <form:hidden path="otpType" id="otpType"/>
			            
			            
					    
					    
					    
	                      </div>
 					
 					
			            <div id="overlay" class="overlay_full" onclick="off()">
                    <div class="fancy-spinner">
                      <div class="ring"></div>
                      <div class="ring"></div>
                      <div class="dot"></div>
                      <span>OTP Sending...</span>
                      </div>				          
					    </div>
					    
					    
					    <div id="overlayVerify" class="overlay_full" onclick="off()">
                <div class="fancy-spinner">
                  <div class="ring"></div>
                  <div class="ring"></div>
                  <div class="dot"></div>
                  <span>Verifying...</span>
                  </div>					          
					    </div>
					    
					    
                          <div class="text-center mt-3">
                            <a class="btn btn-sm btn-white" href="/<%=requestUri%>/home">Back to Search</a>
                           
                          </div>
                        </div>
                    </div>
                 
                  
                  </div>  
                  
                  <div class="tab-pane fade" id="pills-review" role="tabpanel" aria-labelledby="pills-review-tab">
                    <div class="tab-card-in">
                      <h3>Enter Your Mobile/Aadhar No.</h3>

                        <div class="col-md-12 mb-2">
                          <div for="" class="text-center my-2">
                            An OTP will be sent to your linked mob
                            numbers
                          </div>
                        </div>
                        <div class=" col-md-12 mb-2  ">
                          <div class="row mt-4">
                          <div class="col-12 text-danger"><span id="adhar_required"></span></div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-12 d-flex align-items-center mb-4 gap-3">
                             
                              <div class="custom-form-group form-group flex-grow-1 mb-0">
                               <label> Enter Your Mobile/Aadhar No</label>
								<form:input path="aadharMobile" class="form-control flex-grow-1"  id="aadharMobile" maxlength="12" 
                                onkeypress="return isNumberKey(event)" onchange="validateAadharMobile()" />
                              
                              </div>
                              <button class="btn btn-sm btn-dark col-sm-4" type="button" onclick="getOtpAdd()" id="optAddBtn">Get OTP</button>
                          
                            
                            </div>

                            <div class="col-12 col-sm-6 col-md-4 col-lg-12 d-flex align-items-center mb-4 gap-3">
                            
                                <div class="custom-form-group form-group flex-grow-1 mb-0">
                                 <label> Enter OTP</label>
 								<form:input path="otpAddMem" class="form-control flex-grow-1" id="otpAddMem" maxlength="6" />
                  
                              </div>
                              <button class="btn btn-sm btn-dark col-sm-4"  type="button" onclick="otpVerifyAddMemeber()" >Verify & Proceed</button>
                 
                            </div>
                            
                            
                                <div id="errorOtpAdd" style="color: red;" class="app-title"></div>
	  		  	 				 <div id="infoOtpAdd" style="color: green;" class="app-title"></div>
	  		  	  
	  		  					  <div id="errorOtp1" style="color: red;" class="app-title"></div>
	  		  					  <div id="infoOtp1" style="color: green;" class="app-title"></div>
		                        </div>
		
		                    <!--   <a href="/<%=requestUri%>/home"  class="mt-10 text-center w-100" >Back to Search</a> -->
		                   
		                   		 <form:hidden path="otpTxn" id="otpTxn"/>
									<form:hidden path="refernceid" id="refernceid"/> 
									<form:hidden path="stateCode" id="stateCode"/>
									<form:hidden path="pmjayId" id="pmjayId"/>
						            <form:hidden path="otpType" id="otpType"/>
						         
									
                   
                   		</div>

						 
                        <div class="text-center mt-3">
                          <a class="btn btn-sm btn-white" href="/<%=requestUri%>/home">Back to Search</a>
                       <!--   <button class="btn btn-sm btn-dark" onclick="window.location.href='#'">Next</button> -->
                        </div>
                        <div id="overlay2" class="overlay_full" onclick="off()">
                          <div class="fancy-spinner">
                            <div class="ring"></div>
                            <div class="ring"></div>
                            <div class="dot"></div>
                            <span>OTP Sending...</span>
                            </div>							          
								    </div>
								    
								    
								    <div id="overlayVerify2" class="overlay_full" onclick="offVerify2()">
                      <div class="fancy-spinner">
                        <div class="ring"></div>
                        <div class="ring"></div>
                        <div class="dot"></div>
                        <span>Verifying...</span>
                        </div>								         
								    </div>
                      </div>
                   </form:form>
                  </div>
                </div>  
              </div>
            </div>
          </section>        
        </main>

        <%@ include file="footer.jsp" %>

      </div>
    </main>

  </div>
</div>

  <!-- JS File  -->
  <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>

  <script type="text/javascript" language="javascript" src="js/Verhoeff.js"></script>

  <script type="text/javascript">


  function otpVerifyDownloadCard(){
   
 $('#infoOtp').hide('slow');
 $('#errorOtp').hide('slow');
   var requestUri='<%=requestUri%>';
   
  	var aadhar =document.getElementById("aadhar").value;
	var otp =document.getElementById("otp").value;
	var otpTxn =document.getElementById("otpTxn").value;
	
	document.getElementById("refernceid").value='<%=beneficiarySeccForm.getGuid()%>';
	var chekedRUFlag='<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
    	
    if(chekedRUFlag=='R'){
    document.getElementById("stateCode").value='<%=beneficiarySeccForm.getStateCodeRural()%>';
    }else if(chekedRUFlag=='U'){
    document.getElementById("stateCode").value='<%=beneficiarySeccForm.getStateCodeUrban()%>';
    }
    
	if(aadhar==""){
		alert("Please Enter Aadhaar Number.");
		document.getElementById("aadhar").focus();;
		return false;
	}
	if(otp==""){
		alert("Please Enter OTP.");
		document.getElementById("otp").focus();;
		return false;
	}
	
	if(aadhar.length>10)
   	 	document.getElementById("otpType").value='U';
   	 else
   		 	document.getElementById("otpType").value='M';
 		var timeleft = "5";
 		onVerify();
 	    document.forms[0].action="/"+requestUri+"/verifySmsMobileOtp";
		document.forms[0].method="post";
		document.forms[0].submit(); 
		var downloadTimer =setInterval(function(){
				$('#infoOtp').hide('slow');
				$('#errorOtp').hide('slow');
				$('#infoOtp1').show('slow');
				$('#errorOtp1').hide('slow');
				$('#infoOtp1').html( "Beneficiary Ayushman Card Downloaded.");
				timeleft -= 1;
				if(timeleft <= 0){
					clearInterval(downloadTimer);
				offVerify();
				$('#infoOtp1').hide('slow');
				$('#errorOtp1').hide('slow');
				$('#infoOtp1').html( ' ');
				document.getElementById("otp").value="";
				}
				document.getElementById('otp').focus(); 
			}, 1000);

  }


function getOtp(){
   
   var requestUri='<%=requestUri%>';
	var guid; 	
	guid='<%=beneficiarySeccForm.getGuid()%>';
	var chekedRUFlag='<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
   var stateCode; 	
    if(chekedRUFlag=='R'){
    stateCode='<%=beneficiarySeccForm.getStateCodeRural()%>';
    }else if(chekedRUFlag=='U'){
    stateCode='<%=beneficiarySeccForm.getStateCodeUrban()%>';
    }
    
	
	var aadhar =document.getElementById("aadhar").value;
	var otpType;
	
	if(aadhar==""){
		alert("Please Enter Mobile/Aadhaar Number.");
		document.getElementById("aadhar").focus();;
		return false;
	}
   	 if(aadhar.length>10)
   	 	otpType='U';
   	 else
   		 otpType='M';	
    
    	on();
    	document.getElementById("optBtn").disabled = true;
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/benMobileOtpSender",
        data: {
       	 "aadhar": aadhar,
       	 "otpType":otpType,
       	 "stateCode":stateCode, 
       	 "guid":guid, 	
       		 },
        success: function(data){
       	 console.log(data);
       	off();
       	  var obj = jQuery.parseJSON( data );
            if(obj['status'] == "Y"){
            	 $('#errorOtp').html('');
            	  $('#infoOtp').show('slow');
                 $('#errorOtp').hide('slow');
                	var timeleft = "60";
					var downloadTimer = setInterval(function(){
						var msg="OTP Sent to Registered mobile "+obj['mobile']+", You can Get OTP again after "+timeleft+ " seconds";
						$('#infoOtp').html(msg);
					  timeleft -= 1;
					  document.getElementById("optBtn").disabled = true;
					  if(timeleft <= 0){
						clearInterval(downloadTimer);
						document.getElementById("optBtn").disabled = false;
						 $('#infoOtp').html('');
               			 $('#infoOtp').hide('slow');
						
					  }
					  document.getElementById('otp').focus(); 
					}, 1000);
					                  
                document.getElementById("pmjayId").value=obj['pmjayId'];
                
             }else if(obj['status'] == "MN"){
            	 $('#errorOtp').html("Beneficiary Mobile number is mismatched. ");
                 $('#infoOtp').hide('slow');
                  $('#errorOtp').show('slow');
                  	document.getElementById("optBtn").disabled = false;
               
                
             }else if(obj['status'] == "NN"){
            	  $('#errorOtp').html("Beneficiary is not linked with Mobile number. ");
                  $('#infoOtp').hide('slow');
                  $('#errorOtp').show('slow');
                  	document.getElementById("optBtn").disabled = false;
             }
             else{
             	 $('#errorOtp').html("OTP failed.");
                 $('#infoOtp').hide('slow');
                 $('#errorOtp').show('slow');
                 	document.getElementById("optBtn").disabled = false;
                 
             }
         },
         error: function(e){
         	document.getElementById("optBtn").disabled = false;
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
				
         }
    
    });
   
   
 
   
   }
 
 
 

function validateAadhar()
{
        var adhhar= document.getElementById("aadhar").value;
        if (adhhar==null || adhhar=="")
        {
         document.getElementById('adhar_required').innerHTML="Please Enter Mobile/Aadhaar Number.";
        }
        else 
        {
        if(adhhar.length >10){

		if(!adhhar.verhoeffCheck())  {     
             document.getElementById("aadhar").value="";
             document.getElementById('adhar_required').innerHTML="Please Enter Valid Aadhaar Number.";             
        }else{
       	 document.getElementById('adhar_required').innerHTML="";
        }
        }else{
        
		var regmm='^([0|+[0-9]{1,5})?([5-9][0-9]{9})$';
		var regmob = new RegExp(regmm);
  
		if(!regmob.test(adhhar)){
			document.getElementById('adhhar').focus(); 
			alert("Invalid mobile number");
		        return false;
		}    
       } 
   }
    }
   
   
   
   function getOtpAdd(){
   
   var requestUri='<%=requestUri%>';
	var guid; 	
	guid='<%=beneficiarySeccForm.getGuid()%>';
	var chekedRUFlag='<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
   var stateCode; 	
    if(chekedRUFlag=='R'){
    stateCode='<%=beneficiarySeccForm.getStateCodeRural()%>';
    }else if(chekedRUFlag=='U'){
    stateCode='<%=beneficiarySeccForm.getStateCodeUrban()%>';
    }
    
	
	var aadhar =document.getElementById("aadharMobile").value;
	var otpType;
	
	if(aadhar==""){
		alert("Please Enter Mobile/Aadhaar Number.");
		document.getElementById("aadhar").focus();;
		return false;
	}
   	 if(aadhar.length>10)
   	 	otpType='U';
   	 else
   		 otpType='M';	
    
    	on2();
		document.getElementById("optAddBtn").disabled = true;
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/benAddMobileOtpSender",
        data: {
       	 "aadharMobile": aadhar,
       	 "otpType":otpType,
       	 "stateCode":stateCode, 
       	 "guid":guid, 	
       		 },
        success: function(data){
       	 console.log(data);
       	off2();
       	  var obj = jQuery.parseJSON( data );
            if(obj['status'] == "Y"){
            	 $('#errorOtpAdd').html('');
            	  $('#infoOtpAdd').show('slow');
                 $('#errorOtpAdd').hide('slow');
                	var timeleft = "60";
					var downloadTimer = setInterval(function(){
						var msg="OTP Sent to Registered mobile "+obj['mobile']+", You can Get OTP again after "+timeleft+ " seconds";
						$('#infoOtpAdd').html(msg);
					  timeleft -= 1;
					  document.getElementById("optAddBtn").disabled = true;
					  if(timeleft <= 0){
						clearInterval(downloadTimer);
						document.getElementById("optAddBtn").disabled = false;
						 $('#infoOtpAdd').html('');
               			 $('#infoOtpAdd').hide('slow');
						
					  }
					  document.getElementById('otpAddMem').focus(); 
					}, 1000);
					                  
                document.getElementById("pmjayId").value=obj['pmjayId'];
                
             }else if(obj['status'] == "MN"){
            	 $('#errorOtpAdd').html("Beneficiary Mobile number is mismatched. ");
                 $('#infoOtpAdd').hide('slow');
                  $('#errorOtpAdd').show('slow');
               document.getElementById("optAddBtn").disabled = false;
                
             }else if(obj['status'] == "NN"){
            	  $('#errorOtpAdd').html("Beneficiary is not linked with Mobile number. ");
                  $('#infoOtpAdd').hide('slow');
                  $('#errorOtpAdd').show('slow');
				  document.getElementById("optAddBtn").disabled = false;
             }
             else{
             	 $('#errorOtpAdd').html("OTP failed.");
                 $('#infoOtpAdd').hide('slow');
                 $('#errorOtpAdd').show('slow');
				 document.getElementById("optAddBtn").disabled = false;
                 
             }
         },
         error: function(e){
			 document.getElementById("optAddBtn").disabled = false;
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
					
         }
    
    });
   
   
 
   
   }

   
   function validateAadharMobile()
{
        var adhhar= document.getElementById("aadharMobile").value;
        if (adhhar==null || adhhar=="")
        {
         document.getElementById('adhar_requiredAdd').innerHTML="Please Enter Mobile/Aadhaar Number.";
        }
        else 
        {
        if(adhhar.length >10){

		if(!adhhar.verhoeffCheck())  {     
             document.getElementById("aadharMobile").value="";
             document.getElementById('adhar_requiredAdd').innerHTML="Please Enter Valid Aadhaar Number.";             
        }else{
       	 document.getElementById('adhar_requiredAdd').innerHTML="";
        }
        }else{
        
		var regmm='^([0|+[0-9]{1,5})?([5-9][0-9]{9})$';
		var regmob = new RegExp(regmm);
  
		if(!regmob.test(adhhar)){
			document.getElementById('adhhar').focus(); 
			alert("Invalid mobile number");
		        return false;
		}    
       } 
   }
    }

   
    function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
 } 


function validateAction(){
$('#secc-collapse').addClass('show');
$('#nfsa-collapse').removeClass('show');
document.getElementById("otp").value="";

var status='<%=status%>';
if(status=='F'){

	 $('#errorOtp').html("Beneficiary Ayushman Card Generation Under process. Please try sometime latter.");
	 $('#infoOtp').hide('slow');
	 $('#errorOtp').show('slow');
	// $('#errorOtp').delay(1000).hide(1500);
}
else if(status=='N'){
	 $('#errorOtp').html("OTP Authentication failed.");
     $('#infoOtp').hide('slow');
     $('#errorOtp').show('slow');
  //   $('#errorOtp').delay(1000).hide(1500);
}
else if(status=='AN'){
	 $('#errorOtpAdd').html("OTP Authentication failed.");
     $('#infoOtpAdd').hide('slow');
     $('#errorOtpAdd').show('slow');
  //   $('#errorOtp').delay(1000).hide(1500);
}

}


function otpVerifyAddMemeber(){
   
 $('#infoOtp').hide('slow');
 $('#errorOtp').hide('slow');
   var requestUri='<%=requestUri%>';
   
  	var aadhar =document.getElementById("aadharMobile").value;
	var otp =document.getElementById("otpAddMem").value;
	var otpTxn =document.getElementById("otpTxn").value;
	
	document.getElementById("refernceid").value='<%=beneficiarySeccForm.getGuid()%>';
	var chekedRUFlag='<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
    	
    if(chekedRUFlag=='R'){
    document.getElementById("stateCode").value='<%=beneficiarySeccForm.getStateCodeRural()%>';
    }else if(chekedRUFlag=='U'){
    document.getElementById("stateCode").value='<%=beneficiarySeccForm.getStateCodeUrban()%>';
    }
    
	if(aadhar==""){
		alert("Please Enter Aadhaar Number.");
		document.getElementById("aadhar").focus();;
		return false;
	}
	if(otp==""){
		alert("Please Enter OTP.");
		document.getElementById("otp").focus();;
		return false;
	}
	
	if(aadhar.length>10)
   	 	document.getElementById("otpType").value='U';
   	 else
   		document.getElementById("otpType").value='M';
 		onVerify2();
 	    document.forms[0].action="/"+requestUri+"/addFamilyMemeber";
		document.forms[0].method="post";
		document.forms[0].submit(); 
		

  }






function on() {
  document.getElementById("overlay").style.display = "flex";
}

function off() {
  document.getElementById("overlay").style.display = "none";
}


function on2() {
  document.getElementById("overlay2").style.display = "flex";
}

function off2() {
  document.getElementById("overlay2").style.display = "none";
}

function onVerify() {
  document.getElementById("overlayVerify").style.display = "flex";
}

function offVerify() {
  document.getElementById("overlayVerify").style.display = "none";
}


function onVerify2() {
  document.getElementById("overlayVerify2").style.display = "flex";
}

function offVerify2() {
  document.getElementById("overlayVerify2").style.display = "none";
}

function openTab2(){
		$("#pills-beneficiary-tab").removeClass("active");
	       $("#pills-review-tab").addClass("active");
	       
           $("#pills-review").addClass("show");
           $("#pills-review").addClass("active");
           $("#pills-beneficiary").removeClass("active");
           $("#pills-beneficiary").removeClass("show");
         
  }
  


</script>


</body>

</html>