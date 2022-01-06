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
    
    
      <style>
        
    /*OTP CSS*/
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
    .digit-group .splitter {
      padding: 0 5px;
      /* color: white; */
      font-size: 24px;
    }
    
    .prompt {
      margin-bottom: 20px;
      font-size: 20px;
      /* color: white; */
    }
      </style>
</head>
 
<body class="mobile">

<div class="beneficiary-main-wrap">
  <div class="row flex-nowrap">
    <div class="col-auto pe-0"> 
      <div id="sidebar" class="collapse collapse-horizontal show leftbar-wrapcustom">
        <div id="sidebar-nav" class="list-group border-0 rounded-0 text-sm-start min-vh-100 leftbar-custom">
          <a href="javascript:void(0);" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none logo-main">
            <img src="images/PMJAY-logo.png" alt=""><span>BIS</span>
          </a>
          
         <%@ include file="help.jsp" %>

        </div>
      </div>
    </div>
  
    <main class="col">
      <nav>
        <div class="container-fluid nav-wrapper">
          <div class="logo">
            <a href="javascript:void(0);" data-bs-target="#sidebar" data-bs-toggle="collapse" class="me-2 text-decoration-none humberger"><img src="images/humberger-white.png" alt=""></a>
            <a href="https://setu.pmjay.gov.in/setu/" class="text-white ms-1">             
              Home
            </a>
          </div>          
          
        </div>
      </nav>
  
      <div class="beneficiary-section">
        <main>
          <section class="SECC-data mb-4">
            <div class="container">
              <div class="px-1 px-md-3">
                <div class="col-12">
                  <h5></h5>
                  <div class="container-fluid border-bottom mx-auto">
                    <div class="row">
                      <div class="col-md-12 my-1 text-center">
                        <h6 class="content">
                          
                        </h6>
                      </div>
                    </div>
                  </div>
                             
                  
                </div>
              </div>
              <form:form action=""  method="POST"  commandName="command">
              <div class="row">

                <div class="beneficiary-view-tabs mb-2 text-center col-sm-10 col-md-5 mx-auto">
                  <h4>Logged-Out</h4>
                  <p>Thank you for using BIS</p>
                  <a href="#" id="optBtn" class="btn btn-dark w-80 signin-btn" onclick="selfRegisterTab()">Sign in again</a>
                </div>

               
            </div>
          </form:form>
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
  <script type="text/javascript" src="js/setu-custom.js"></script>
  <script>
	//window.open("https://connect.csc.gov.in/account/logout","log out","height=10,width=10,location=no,menubar=no,status=no,titlebar=no,toolbar=no",true);
	
    function selfRegisterTab() { 
                window.location.href="./index";
    }
    
    
    </script>

</body>

</html>