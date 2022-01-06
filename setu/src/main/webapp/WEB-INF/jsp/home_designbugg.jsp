<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<%
String requestUri="";
requestUri=request.getRequestURI().split("/")[1];
String sessid="";
sessid=session.getId();
String error ="";
if(request.getAttribute("error")!=null){
	error=(String)request.getAttribute("error");
}
String eMobile="";
if(request.getAttribute("eMobile")!=null){
	eMobile=(String)request.getAttribute("eMobile");
}

 %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sign In</title>

  <link rel="icon" type="image/ico" sizes="16x16" href="images/favicon.ico">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Sora:wght@100;200;300;400;500;600;700;800&display=swap"
    rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  <link rel="stylesheet" href="sass/setu-custom.css">
  <style>

.spinner {
  height: 160px;
  width: 160px;
  margin: auto;
  display: flex;
  position: absolute;
  -webkit-animation: rotation .6s infinite linear;
  -moz-animation: rotation .6s infinite linear;
  -o-animation: rotation .6s infinite linear;
  animation: rotation .6s infinite linear;
  border-left: 6px solid rgba(0, 174, 239, .15);
  border-right: 6px solid rgba(0, 174, 239, .15);
  border-bottom: 6px solid rgba(0, 174, 239, .15);
  border-top: 6px solid rgba(0, 174, 239, .8);
  border-radius: 100%;
}

@-webkit-keyframes rotation {
  from {
    -webkit-transform: rotate(0deg);
  }
  to {
    -webkit-transform: rotate(359deg);
  }
}

@-moz-keyframes rotation {
  from {
    -moz-transform: rotate(0deg);
  }
  to {
    -moz-transform: rotate(359deg);
  }
}

@-o-keyframes rotation {
  from {
    -o-transform: rotate(0deg);
  }
  to {
    -o-transform: rotate(359deg);
  }
}

@keyframes rotation {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(359deg);
  }
}

#overlay {
  position: absolute;
  display: none;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 2;
  cursor: pointer;
}
</style>
</head>
 
<body class="mobile">
  <nav>
    <div class="container-fluid nav-wrapper">
      <div class="logo"><a href="https://setu.pmjay.gov.in/setu/"><img src="images/national-health-authority-logo.png" alt="National Health Authority" width="120" height="52"></a></div>
      <div class="hamburger">
        <span></span>
        <span></span>
        <span></span>
      </div>
      <ul class="nav-list">
        <li class="active"><a href="javascript:void;">About PMJAY</a></li>
        <li><a href="javascript:void;">BIS</a></li>
        <li><a href="javascript:void;">Beneficiary Database</a></li>
        <li><a href="javascript:void;">Ayusman Mitra</a></li>
        <li><a href="javascript:void;">Help</a></li>
      </ul>
    </div>
  </nav>
 
  <div class="main-wrapper login-section">
    <main>

      <section class="signin-wrapper"> 

        <div class="signin-notes">
          <h2>Important Notes</h2>
          <p><b><i class="fa fa-star" aria-hidden="true"></i></b>For login please register yourself in NHA eco system.</p>
          <p><b><i class="fa fa-star" aria-hidden="true"></i></b>If not yet registered please <a href="https://users.nha.gov.in/UserManagement/verifyEkyc.htm">click here</a> and complete the registration process.</p>
          <p><b><i class="fa fa-star" aria-hidden="true"></i></b>User can login through mobile number and OTP only.</p>
          <p><b><i class="fa fa-star" aria-hidden="true"></i></b>Any registered user can be search ABPMJAY beneficiaries and submit his/her request for Ayushman card.</p>
        </div>

      <div class="signin-card-wrap">
        <div id="signin-verify-wrap" class="signin-card">
          <!-- <div class="signin-left">
            <img src="images/signin.png" alt="">
          </div> -->
          <div class="signin-right">
            <h2>Sign In</h2>
            <p>An OTP will be sent to your registered mobile number for verification</p>
            <form:form action=""  method="POST"  commandName="command">
              <div class="form-group">
                <label for="">Enter Your Mobile Number</label>
                <form:input type="text" path="userName" id="userName" class="form-control" name="" 
                maxlength="10" onkeypress="goLogin(event)" />
              </div>
              <a href="javascript:void(0);"  id="optBtn" onclick="signIn()"  class="btn btn-dark w-100">Sign In</a>
              <p id="errorOtp" class="msg-error"></p>
              <input type="hidden" id="key" name ="key"> 
              <input type="hidden" id="umpUrl" name ="umpUrl"> 
              <form:hidden path="password" id="password"/>
    
	        <div id="overlay" onclick="off()">
          <div class="w-100 d-flex justify-content-center align-items-center">
         <font color="white">Verifying.... </font><div class="spinner"></div>
       </div>
    	</div>
            </form:form> 
            <div class="for-login">
              <p>By Sign In/Registration, I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a> </p>
            </div>
          </div>
        </div>

        <div id="OTP-verify-wrap" class="signin-card signinotp-card"> 
          <!-- <div class="signin-left">
            <img src="images/otp-verify.png" alt="">
          </div> -->
          <div class="signin-right">
            <h2>OTP Verification</h2>
            <p>An OTP has been sent to xxx xxx xxxx</p>
            <form action="">
              <div class="form-group">
                <label for="">Enter OTP</label>
                <input type="text" class="form-control">
              </div>
              <a href="javascript:void(0);" id="otpbtn" class="btn btn-dark w-100">Verify & Proceed</a>
            </form> 
            <div class="for-login">
              <p>There might be some delay in receiving the OTP due to heavy traffic</p>
            </div>
          </div>
        </div>
      </div>
      </section>

      <section class="info-container">
        <div class="container">
          <div class="info-main-wrap">
            <div class="info-content-wrap">
              <div class="info-content-icon">
                <img src="images/call.png" alt="">
              </div>
              <div class="info-content">
                <a href="tel:14555">Toll-Free - 14555</a>
              </div>
            </div>

            <!-- <div class="info-content-wrap">
              <div class="info-content-icon">
                <img src="images/time.png" alt="">
              </div>
              <div class="info-content">
                <span>Mon - Fri, 09AM - 05PM</span>
                <span>Sat - Sun Closed</span>
              </div>
            </div> -->

            <div class="info-content-wrap">
              <div class="info-content-icon">
                <img src="images/mail.png" alt="">
              </div>
              <div class="info-content">
                <span>webmaster-pmjay[at]nha[dot]gov[dot]in</span>
              </div>
            </div>

            <div class="info-content-wrap">
              <div class="info-content-icon">
                <img src="images/map.png" alt="">
              </div>
              <div class="info-content">
                <span>9th Floor, Tower-l, Jeevan Bharati Building,</span>
                <span>Connaught Place, New Delhi - 110001</span>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>

    <footer>
      <div class="copyright-wrap">
        <span>&#169;</span> 2021 NHA All Rights Reserved.
      </div>
    </footer>

  </div>

  <!-- JS File  -->
  <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="js/setu-custom.js"></script>
  <script type="text/javascript">

    function goLogin(e){
           if(e.keyCode === 13){
        signIn();
        e.preventDefault(); 
           }
      }
   
   
   
   
   function displayError(){
     var error='<%=error%>';
     if(error.length>0){
        $('#errorOtp').html('');
       $('#errorOtp').html(error);
       $('#errorOtp').show('slow');
       document.getElementById("userName").value='<%=eMobile%>';
       
      }
   
     }
   
   
     var requestUri='<%=requestUri%>';
     function submitAction(){
       var x=true;
       
       if(document.getElementById("userName").value==""){
       alert("Please Enter User Name..");
       x=false;
       }
       else if(document.getElementById("password").value==""){
         alert("Please Enter OTP..");
         x=false;
       }
       if(x){
       document.forms[0].action="/"+requestUri+"/usrHome";
       //document.forms[0].action="/usrHome";
     document.forms[0].method="post";
     document.forms[0].submit(); 
       }
       }
       
       function cscOpAction(){
          document.forms[0].action="/"+requestUri+"/cscHome";
       document.forms[0].method="post";
       document.forms[0].submit(); 
       }
   
   
   </script>
   
   
   <script>
   function signIn(){ 
   
    var loginMode=true; 
    var requestUri='<%=requestUri%>';
     var userName =document.getElementById("userName").value;
     if(userName==""){
       alert("Please Enter Mobile");
       document.getElementById("userName").focus();
       return false;
     }
     $('#errorOtp').hide('slow');
     on();
     if(loginMode){
	
	document.getElementById("password").value="666666";
	document.forms[0].action="/"+requestUri+"/usrHome";
  	document.forms[0].method="post";
	document.forms[0].submit();
	
	}else{
     $.ajax({
         type: "POST",
          url: "/"+requestUri+"/smsOtpSender",
         data: {
           "userName":userName,
          
             },
           success: function(data){
           console.log(data);
         off();
            var obj = jQuery.parseJSON( data );
             if(obj['status'] == "Y"){
               document.getElementById("key").value =obj['jwtToken'];
                document.getElementById("umpUrl").value=obj['umpUrl'];
               authInRedirect();
             }else if(obj['status'] == "UN"){
                document.getElementById("key").value =obj['jwtToken'];
                document.getElementById("umpUrl").value=obj['umpUrl'];
                $('#errorOtp').html("You are not registered with us . For Registration <a href=\"#\" onclick=\"Redirect()\" target=\"_blank\">Click here</button></a>");
           $('#errorOtp').show('slow');
                  $('#successmessage').hide('slow');
               
              }else{
            $('#errorOtp').html("UMP Server not respond.");
            $('#errorOtp').show('slow');
            $('#successerror').hide('slow');
                 document.getElementById("optBtn").style.display = "block";
           document.getElementById("optBtn").disabled = false;
              }
          },
          error: function(e){
              alert('Error: ' + e);
          off();
          }
     
     });
     }
    }
   
   </script>
   <script>
   
   function Redirect(){
   
      var form = document.createElement("form");
        form.method = "POST";
        form.action = document.getElementById("umpUrl").value;
        form.target = "_blank";
        secretKey=document.createElement('INPUT');
        secretKey.type='HIDDEN';
        secretKey.name='secretKey';
       secretKey.value=document.getElementById("key").value;
       form.appendChild(secretKey);
   
          document.body.appendChild(form);
          form.submit();
   
   }
   
   
   function authInRedirect(){
   
      var form = document.createElement("form");
        form.method = "POST";
        form.action = document.getElementById("umpUrl").value;
        secretKey=document.createElement('INPUT');
        secretKey.type='HIDDEN';
        secretKey.name='secretKey';
       secretKey.value=document.getElementById("key").value;
       form.appendChild(secretKey);
   
          document.body.appendChild(form);
          form.submit();
   
   }
   
   
   function selfRegisterTab() { 
               window.open("https://usersbeta.pmjay.gov.in/UserManagement/verifyEkycdoot.htm", "_blank");
   }
   
   </script>
   
   <script>
   
   function on() {
     document.getElementById("overlay").style.display = "flex";
   }
   
   function off() {
     document.getElementById("overlay").style.display = "none";
   }
   
   
   </script>
   
</body>

</html>