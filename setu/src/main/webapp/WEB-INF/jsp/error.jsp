
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
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Setu || Logout</title>
<link rel="shortcut icon" href="images/BISCenterLogo.png"type="image/x-icon" />

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  
  
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
<body class="hold-transition sidebar-mini" >
<!-- Site wrapper -->
<div class="wrapper">
  <!-- Navbar -->

  <!-- /.navbar -->

  <nav class=" navbar navbar-expand-lg navbar-light " style="background-color: #02467a!important;">
    <div class="container-fluid">
      <a class="navbar-brand" href="bis">
        <img class="w-100" src="image/nha-logo-white1.png" alt="">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
      
        <a class="navbar-brand ml-auto" href="bis">
          <img src="image/pmjay_logo.png" alt="">
        </a>
       </div>
       
      </div>
    
      
    </nav>

  <!-- Main Sidebar Container -->
 

  <!-- Content Wrapper. Contains page content -->
  <div class="">
    <!-- <div class="content-wrapper"> -->
    <!-- Content Header (Page header) -->
    <section class="content-header">
    <form:form action=""  method="POST"  commandName="command">
      <div class="container-fluid">
        <div class="row mb-3">
          <div class="col-sm-6">
            <h1></h1>
          </div>
         
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="card card-primary card-tabs">
              
              <div class="card-body">
                
                <div class="row justify-content-center align-items-center">
                  <div class="col-md-4 bg-white shadow-sm rounded p-5 d-flex flex-wrap text-center justify-content-center">
                  
                  <span>Please retry later due to some technical problems. </span>
				  <div class="col-md-12" style="color:green" id="countdown"></div>
				 
                  <button type="button" class="btn btn-primary w-100 my-3" style="display:block"  id="optBtn" onclick="selfRegisterTab()">Sign in Again</button>
				
				 </div>
				  
                  
				
				 
                 </div>

                </div>
				<br>
				
				<br>
				
				<br>
				
				<br>
				
				<br>



              </div>
              <!-- /.card -->
            </div>
          </div>
        </div>
      </div>
	  
	  
  </form:form>
     
    </section>

    <!-- Main content -->
   
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  
  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->

<!-- <script src="js/security.js"></script> -->





<script>

function selfRegisterTab() { 
            window.location.href="./index";
}


</script>

</body>
</html>
