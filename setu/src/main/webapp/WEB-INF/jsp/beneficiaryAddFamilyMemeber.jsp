<!DOCTYPE html>
<%@page import="com.gov.nha.bis.server.properties.CommonProperties"%>
<%@page import="com.gov.nha.bis.server.properties.CommonProFunction"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="org.springframework.util.ObjectUtils"%>
<%@page import="com.gov.nha.bis.server.model.SessionLoginMap"%>
<%@page import="com.gov.nha.bis.server.model.BeneficiarySeccForm"%>
<%@page import="java.util.List" %>
<%@page import="com.gov.nha.bis.server.model.RuralEntity"%>
<%
CommonProFunction PROPERTIES = CommonProperties.commonProFunction;
String requestUri="";
requestUri=request.getRequestURI().split("/")[1];
String sessid="";
sessid=session.getId();
String saveKyc="";
if(request.getAttribute("saveKyc")!=null){
	saveKyc=(String)request.getAttribute("saveKyc");
}

BeneficiarySeccForm beneficiarySeccForm = null;
if(session.getAttribute("beneficiarySeccForm")!=null){
	beneficiarySeccForm=(BeneficiarySeccForm)session.getAttribute("beneficiarySeccForm");
}

String cardStatus=null;
if(!ObjectUtils.isEmpty(beneficiarySeccForm)){
	cardStatus=beneficiarySeccForm.getCard_status();
}

String userId="";
if(session.getAttribute("USERID")!=null){
	userId =(String)session.getAttribute("USERID");
}
String hhd_no="";
if(session.getAttribute("hhd_no")!=null){
	hhd_no =(String)session.getAttribute("hhd_no");
}

List<RuralEntity> hhddata= null;
if(session.getAttribute("hhddatalist")!=null){
	hhddata=(List<RuralEntity>)session.getAttribute("hhddatalist");
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
  <title>SECC Family Details</title>

  <link rel="icon" type="image/ico" sizes="16x16" href="images/favicon.ico">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Sora:wght@100;200;300;400;500;600;700;800&display=swap"
    rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  <link rel="stylesheet" href="sass/bootstrap.min.css">
  <link rel="stylesheet" href="sass/setu-custom.css"> 
  
      
  <style>

.preview-tabs h3 {
    display: block;
    background: #f4f6f9;
    padding: 5px 5px;
    border-radius: 2px;
    font-size: 22px;
}

.input-ration-wrap { position: relative;}
.input-ration-wrap button { position: absolute; bottom: 3px; width: 88px; right: 12px;}
.ration-card-wrap { background: #f9f7f7; border: 1px solid #ededed; padding: 10px;}




</style>
  
</head>
 
<body class="mobile" onload="onOpenAction()">

<div class="beneficiary-main-wrap">
  <div class="row flex-nowrap">
    <%@ include file="leftColumn.jsp" %>
  
    <main class="col">
         <nav>
         <%@ include file="header.jsp" %>        
  </nav>
      
      <div class="beneficiary-section">
        <main>
         <form:form action=""  method="POST" enctype="multipart/form-data" commandName="command" id="fileUploadForm">            
          <section class="SECC-data mb-4">
            <div class="container">
              <div class="px-1 px-md-3">
                <div class="col-12">
                  <h2>Family Member Details 
                  <a class="btn btn-sm btn-dark" style=" float: right;" href="/<%=requestUri%>/home">Back</a>
                  </h2>
                  <div class="beneficiary-view-tabs mb-2">
                    <div class="nav nav-pills" id="pills-tab" role="tablist">
                      <div class="nav-item active" id="tab1" role="presentation">
                        <button class="nav-link active" id="pills-familydtl-tab" type="button" role="tab" >Family Details</button>
                      </div>
                      <div class="nav-item" id="tab2" role="presentation">
                        <button class="nav-link" id="pills-newmemdtl-tab" type="button" role="tab" >New Member Details</button>
                      </div>
                      <div class="nav-item" id="tab3" role="presentation">
                        <button class="nav-link" id="pills-memekyc-tab" type="button" role="tab" >Member eKYC Details</button>
                      </div>
                      <div class="nav-item" id="tab4" role="presentation">
                        <button class="nav-link" id="pills-existing-tab" type="button" role="tab" >Existing Beneficiary</button>
                      </div>
                      <div class="nav-item" id="tab5" role="presentation">
                        <button class="nav-link" id="pills-preview-tab" type="button" role="tab" >Preview</button>
                      </div>
                    </div>
                    <div class="tab-content tab-content-view" id="pills-tabContent">
                      <div class="tab-pane fade show active" id="tabData1" role="tabpanel" aria-labelledby="pills-familydtl-tab">
                        <div class="tab-card-in">
                          <div class="beneficiary-section-table">                           
                            <div class="card-content p-3"> 
                              <div class="table-responsive">
                                <table id="example1" class="table table-striped dataTable" style="width:100%">
						                 <thead>
						                  <tr>
						                  	<th>S.NO</th> 
						                    <th>REF_ID</th>
						                    <th>FAMILY MEMBER NAME</th>
											<th>GENDER</th>
						                    <th>Father/Spouse Name</th>
						                    <th>Relation</th>
											<th>CURRENT CARD STATUS</th>
						                  </tr>
						                  </thead>
	                                   <tbody>              
						                 <c:forEach var="listValue" items="${hhddatalist}" varStatus="counter">
						                  <tr>
							                  <td><input id="family" path="family" name="family" style="position: relative; top: 2px; margin-left: 2px;" type="radio" value="${listValue.name}/${listValue.fathername}/${listValue.genderid}/${listValue.guid}"  /></td>
							                  <td>${listValue.guid}</td>
							                  <td>${listValue.name}</td>
											   <c:choose>
						  						<c:when test="${listValue.genderid eq 1}">
							                  	<td>Male</td>
							                  </c:when>
							                  <c:when test="${listValue.genderid eq 2}">
							                  	<td>Female</td>
							                  </c:when>
							                  <c:otherwise>
						  						  Transgender
						  					</c:otherwise>
							                  </c:choose>
											  
							                  <td>${listValue.fathername}</td>
							                  <td>${listValue.relation}</td> 
							                  <c:choose>
						  						<c:when test="${listValue.card_status eq 0}">
							                  	<td>Card not made</td>
							                  </c:when>
							                  <c:when test="${listValue.card_status eq 49}">
							                  	<td>In progress</td>
							                  </c:when>
							                  <c:when test="${listValue.card_status eq 99}">
							                  	<td>Complete</td>
							                  </c:when>
							                  
							                  <c:when test="${listValue.card_status eq 96}">
							                  	<td>eKYC pending</td>
							                  </c:when>
							                  
							                    <c:when test="${listValue.card_status eq 98}">
							                  	<td>Rejected</td>
							                  </c:when>
							                  <c:otherwise>
						  						  Card not made
						  					</c:otherwise>
							                  </c:choose>
						                 </tr>
						                 </c:forEach>
						                  </tbody>
						                  
						                </table>
                              </div>
                            </div>
                          </div>
                            
                            <div class="text-center mt-3">
                            <button type="button" class="btn btn-sm btn-dark"  id="next" onclick="openTab2()">Next</button>						
                  
                            </div>
                          </div>
                      </div>
                      <div class="tab-pane fade" id="tab2Data" id="pills-newmemdtl" role="tabpanel" aria-labelledby="pills-newmemdtl-tab">
                        <div class="tab-card-in">
                          <h3>I <label id="namefather"></label> undersigned want to add myself to the SECC database as PMJAY beneficiary because of Relation As </h3>
                            <div class="row mt-4">
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                 <!-- <label for="">Select</label> -->
                                  <select id="relationid" class="form-select">
                                     <option value="">Select</option>
		                            <option value="1">Marriage</option>
		                            <option value="2">New Born</option>
                                  </select>
                                 
                                </div> 
                              </div>
                             
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Name</label>
                                  <form:input  type="text" name="demoName" class="form-control" id="demoName" path="demoName"  />
                                 
                                </div>                                
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Guardian's Name</label>
                                  <form:input type="text" name="" class="form-control" id="demoFname" path="demoFname"/>
                     
                                </div> 
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">DOB</label>
                                  
                                 <form:input type="date" name="demoDob" class="form-control" id="demoDob" path="demoDob" onchange="selectDob()"/>
                     
                                </div> 
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Gender</label>
                                  <form:select id="demoGender" class="custom-select" path ="demoGender">
		                            <option selected value="">Select Gender</option>
		                            <option value="M">Male</option>
		                            <option value="F">Female</option>
		                            <option value="O">Transgender</option>
		                          </form:select>
                                </div> 
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Pincode</label>
                                  <form:input type="text" name="demoPincode" class="form-control"  id="demoPincode" path="demoPincode" onkeypress="return isNumberKey(event)" maxlength="6" />
                        
                                </div> 
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Mobile Number</label>
                                   <form:input type="text" name="demoMobile" class="form-control" id="demoMobile" path="demoMobile"
                          			onkeypress="return isNumberKey(event)" maxlength="10" />
                                </div> 
                              </div>                                                         
                                                            
                            </div>

							<div id="rationCardEnable" style="display:none" >
							
                            <div class="row mt-4">
                            <div class="col-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Ration Card Number</label>
                                  <form:input path="rationCard" id="rationCard" class="form-control"  />
                                </div>
                              </div>
                              <div class="col-12 col-sm-12 col-md-4 col-lg-4" >
                                <button type="button" id="btnRashan" class="btn btn-sm btn-dark" onclick="getRationCardDetail()" >Get Details</button>                              
                              </div>
                            
                            </div>
							</div>
                            <div class="beneficiary-section-table" id="rashanDataDiv" style="display:none">                           
                              <div class="card-content p-3"> 
                                <div class="table-responsive">
                                  <table id="example2" class="table table-striped dataTable" style="width:100%">
                                     <thead>
					                  <tr>
					                  
					                 <th>MATCH WITH</th>
					                 <th>MEMBER ID</th>
					                <th>NAME</th>
									<th>GENDER</th>
									<th>RELATIONSHIP</th>
					                <th>MOTHER'S NAME</th>
					                <th>FATHER'S NAME</th>
					                <th>SPOUSE</th>
					                <th>YOB</th>
								      </tr>
					                  </thead>
					                  <tbody>
					                
					                  </tbody>
					                  
					                </table>
                                </div>
                              </div>
                            </div>

							
	
                            <div class="text-center mt-3">
                            <button type="button" class="btn btn-sm btn-white" onclick="openTab1()">Previous</button>	
                            <button type="button" class="btn btn-sm btn-dark" id="next1" onclick="openTab31()">Next</button>						
                    		
                            </div>
								
								<div id="overlayRation" class="overlay_full" onclick="offRation()">
                  <div class="fancy-spinner">
                    <div class="ring"></div>
                    <div class="ring"></div>
                    <div class="dot"></div>
                    <span>Fetching...</span>
                    </div>							      
							 </div>
							
                          </div>
                      </div>
                      <div class="tab-pane fade"  id="tab3Data" role="tabpanel" aria-labelledby="pills-memekyc-tab">
                        <div class="tab-card-in">
                          <div class="row mt-4">
                            <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                            <div class="col-12 text-danger">
                            </div>
                              <div class="custom-form-group form-group">
                                <label for="">Enter Your Aadhaar </label>
                                 <form:input path="aadhar" class="form-control" id="aadhar"   placeholder="Aadhaar Number " maxlength="12" onkeypress="return isNumberKey(event)" onchange="validateAadhar()" />
                               
                              </div>                                
                            </div>
                           
                           
                           <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">How Would You like to eKYC</label>

                                    <div class="nav bg-white mb-1 custom-radio-otp justify-content-between radio-group" id="pills-tab" role="tablist">                                       
                                      <div id="otp-home-tab" class="form-radio" >
	                                        <input type="radio" id="radioO" name="ekyc-new" value="O" onclick="onOpenAction()"  role="presentation" data-bs-toggle="pill" data-bs-target="#otp-home" type="button" role="tab" aria-controls="otp-home" aria-selected="true">
	                                        <label>OTP</label>
                                      </div>
                                     <div id="finger-home-tab" class="form-radio">
                                    
	                                       <input type="radio" id="radioF" name="ekyc-new" value="F" onclick="onOpenAction()"  role="presentation"  data-bs-toggle="pill" data-bs-target="#finger-home" type="button" role="tab" aria-controls="finger-home" aria-selected="false">
	                                       <label>Finger</label>
                                      </div>
                                       <div id="iris-home-tab" class="form-radio" >
                                    
	                                       <input type="radio" id="radioI" name="ekyc-new"   value="I" onclick="onOpenAction()" role="presentation"  data-bs-toggle="pill" data-bs-target="#iris-home" type="button" role="tab" aria-controls="iris-home" aria-selected="false">
	                                       <label>IRIS</label>
                                      </div>
                                       <div class="form-radio" id="demographic-home-tab" >
                                       
                                         <input type="radio" id="radioP" name="ekyc-new"   value="P"onclick="onOpenAction()" role="presentation"  data-bs-toggle="pill" data-bs-target="#demographic-home" type="button" role="tab" aria-controls="demographic-home" aria-selected="false">
                                         <label >Face</label>
                                      </div>
                                    </div>
                              
                                <div class="tab-content mt-4" id="pills-tabContent">
                                  <div class="tab-pane fade" id="otp-home" role="tabpanel" aria-labelledby="otp-home-tab">
                                		
                                     <div class="alert alert-primary p-1 mb-1 text-center" style="font-size:12px; display:none" id="countdown"></div>
		                                           
                                    <div class="kyc-wrap" >
                                    
                                      <div class="form-get-btn">
                                       <button type="button" id="optBtn"  class="btn custom-btn-sm btn-dark" onclick="getOtp()" >Get OTP</button>
                                     
                                      
                                      </div>
                      
                      				 <div class="form-group" id="entOtp">
		                                    <form:input path="otp" class="form-control" placeholder="Enter OTP" id="otp" size="6" />
		                                     <button type="button" id="otpKyc" class="btn custom-btn-sm btn-dark" onclick="otpBasedKyc()">Request KYC</button>                
		                              </div>              
		                              <!-- <div id="errorOtp" style="color: red; display:flex" class="app-title"></div>
			  		  				   <div id="errorKyc" style="color: red; display:none" class="app-title"></div>
			  		  				   <div id="infoKyc" style="color: green; display:none" class="app-title"></div> -->
			  		  				    
                                   
                                </div>
                                </div>  
                                      
                                  
                                      
                                      
                                     <div class="tab-pane fade" id="finger-home" role="tabpanel" aria-labelledby="finger-home-tab">
                                        
                                         <div class="kyc-wrap">
                                          <div class="form-get-btn">
                                           <button type="button" class="btn custom-btn-sm btn-dark" onclick="captureBioAdd('F')">Capture Your Biometric</button>
			                            
                                            </div>
                                          <div class="form-group">
                                            <div class="bar-code">
                                            <span id="figerIdCapture" style="display:none">
                                            <a  href="#"><img src="images/checked.jpg" alt="" width="50" height="50" /> </a>
                                            </div>
                                             <button type="button" id="otpKyc" class="btn custom-btn-sm btn-dark" onclick="bioBasedKyc('F')">Request KYC</button>
			                           
                                           </div>
                                        </div>
                                      </div>
                                      
                                  
                                      
                                    
                                       <div class="tab-pane fade" id="iris-home" role="tabpanel" aria-labelledby="iris-home-tab">
                                        <div class="kyc-wrap">
                                          <div class="form-get-btn">
                                             <button type="button" class="btn custom-btn-sm btn-dark" onclick="captureBioAdd('I')" >Capture Your Biometric</button>
                                          </div>
                                          <div class="form-group">
                                          <span id="irisIdCapture" style="display:none"> <a  href="#"><img src="images/checked.jpg" alt="" width="50" height="50" /> 
                                            
                                            <button type="button" id="otpKyc" custom-btn-sm btn-dark" onclick="bioBasedKyc('I')">Request KYC</button>
                                          </div>
                                        </div>
                                      </div>
                                               
                                       <div class="tab-pane fade" id="demographic-home" role="tabpanel" aria-labelledby="demographic-home-tab">
                                       
                                           
                                           <div class="kyc-wrap">
                                          
                                          <div class="form-get-btn">
                                            <img id="face_id" name="face_id" alt="Face QR Code" src="/<%=requestUri%>/faceId" width="100" height="100"> 
                                          </div>
                                          <div class="form-group">
                                         <button type="button" id="faceBtn" class="btn custom-btn-sm btn-dark" onclick="captureFace()" >Get Face Photo</button>
			                             	 <span id="irisIdCapture" style="display:none">
			                             	  <a  href="#">
										 	</a>
										 
										 </span>
										 
                                          </div>
                                           
                                           
                                           
                                        </div>
                                      </div>
                                    </div>
                                    
                                     </div>
                                    
						 </div>
                      </div>
						
						<div id="kycDetailsDiv" style="display: none;">
                          <h3>eKYC DETAILS AS IN AADHAAR </h3>
                            <div class="row mt-2">
                              <div class="col-12 col-sm-4 col-md-3 col-lg-2">
                              <img id="phtId_1" class="img-thumbnail rounded mx-auto d-block"  src="https://hsv-eishockey.net/wp-content/uploads/2016/06/avatar_dummy-300x300.png?x33861" alt="" >
                               
                              </div>
                              <div class="col-12 col-sm-8 col-md-9 col-lg-10">
                                <div id="infoOtp" style="color: green;" class="align:left"></div>
				  		  				  
                                <div class="ekyc-details-table">
                                  <span>Name:</span>
                                  <strong id="kycName1">  </strong>
                                </div>
                                <div class="ekyc-details-table">
                                  <span>Guardian's Name:</span>
                                  <strong id="kycFname1"> </strong>
                                </div>
                                <div class="ekyc-details-table">
                                  <span>Gender:</span>
                                  <strong id="kycGender1"> </strong>
                                </div>
                                <div class="ekyc-details-table">
                                  <span>DOB:</span>
                                  <strong id="kycDob1"></strong>
                                </div>
                                <div class="ekyc-details-table">
                                  <span>Address:</span>
                                  <strong id="kycAdr1"> </strong>
                                  
                                  	 
	  		  		              	
                                </div>
                              </div>
                                  
                              </div>
                                                           
                         </div>
                         <div class="text-center mt-2">
                       
                          <div id="errorOtp" style="color: #f00; display:flex" class="app-title"></div>
                          <div id="errorKyc" style="color: #f00; display:none" class="app-title"></div>
                          <div id="infoKyc" style="color: green; display:none" class="app-title"></div>
                          <div id="errorFaceKyc" style="color:#f00;" class="app-title"></div>
                          <div id="errorUpdateKyc1" style="color: #f00;" class="app-title"></div>
	  		  		      <div id="infoUpdateKyc1" style="color: green;" class="app-title"></div>
	  		  		      
	  		  		      <div style="color: #f00;" class="app-title">
	  		  		      <strong><span id="adhar_required"></span></strong>
	  		  		      </div>
	  		  		     
                         </div>
                            <div class="text-center mt-3">
                            	
                            	<button type="button" class="btn btn-sm btn-white" onclick="openPreviousTab2()">Previous</button>						
                  				<button type="button" class="btn btn-sm btn-dark" id="next3" onclick="openTab4()">Next</button>						
                    
                            </div>
                            
                            <div id="overlay" class="overlay_full" onclick="offScan()">
                              <div class="fancy-spinner">
                                <div class="ring"></div>
                                <div class="ring"></div>
                                <div class="dot"></div>
                                <span>Scan Device...</span>
                                </div>							          
	 						</div>
	 
							 <div id="overlayKyc" class="overlay_full" onclick="offKyc()">
                <div class="fancy-spinner">
                  <div class="ring"></div>
                  <div class="ring"></div>
                  <div class="dot"></div>
                  <span>Scan Device...</span>
                  </div>
							</div> 

							<div id="overlayDemo"  class="overlay_full" onclick="offDemo()">
                <div class="fancy-spinner">
                  <div class="ring"></div>
                  <div class="ring"></div>
                  <div class="dot"></div>
                  <span>Authenticatation...</span>
                  </div>
							</div> 
							<div id="overlayOtp"  class="overlay_full" onclick="offOtp()">
                <div class="fancy-spinner">
                  <div class="ring"></div>
                  <div class="ring"></div>
                  <div class="dot"></div>
                  <span>Send OTP...</span>
                  </div>
							     
							 </div>  
                          </div>
                      </div>
                      <div class="tab-pane fade" id="tab4Data" role="tabpanel" aria-labelledby="pills-existing-tab">
                        <div class="tab-card-in">
                          <div class="row mt-4">
                            <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                              <div class="custom-form-group form-group">
                                <label for="">PMJAY Beneficiary Name</label>
                                 <form:input type="text" id="pbenname"  name="pbenname"  class="form-control" path="pbenname"  disabled="true" />
                              </div>                                
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                              <div class="custom-form-group form-group">
                                <label for="">Guardian's Name</label>
                              <form:input type="text" id="gurname" name="gurname" path="gurname" class="form-control"  disabled="true"/>
                              </div> 
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                              <div class="custom-form-group form-group">
                                <label for="">HHID</label>
                              <form:input type="text" name="hhdid" class="form-control" id="hhdid" value="<%=hhd_no %>" path="hhdid"  disabled="true"/>
                              </div> 
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                              <div class="custom-form-group form-group">
                                <label for="">Gender</label>
                               <form:select path="genderid" name="genderid" class="custom-select" id="genderid"  disabled="true">
	                            <option value="">Select Gender</option>
	                            <option value="M">Male</option>
	                            <option value="F">Female</option>
	                            <option value="O">Transgender</option>
	                          </form:select>
                              </div> 
                            </div>
                          </div>  
                          <div class="row mt-1">
                            <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                              <div class="custom-form-group form-group">
                                <label for="">Mr./Mrs./Ms./Miss <span id="nbm"></span></label>
                              </div> 
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                              <div class="custom-form-group form-group">  
                                <label style="position:relative;">of Mr./Mrs./Ms./Miss <span id="beny"></span></label>                          
                              
                               <form:input  type="file" class="form-control" id="docFile" aria-describedby="inputGroupFileAddon04" aria-label="Upload" path="" onchange="checkFileExt()" />
                            
                              </div>
                            </div>                              
                            <div class="col-12 col-sm-12 col-md-4 col-lg-4">
                              <div class="custom-form-group form-group mt-4 mb-0">
                                <label for="">Document Name</label>
                               <form:input type="text" name="docName"  class="form-control" id="docName" path="docName"   />
                              </div>
                            </div>   
                            
                            <div class="col-md-12">
								<span id="lblError" style="color: red;text-align: center"></span> <br />
								<span id="lblSuccess" style="color: green;text-align: center"></span> <br />
						   </div>	
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            
                              <div class="custom-form-group form-group form-check">
                              
                              <input id="checkDeclare" name="checkDeclare" class="form-check-input" type="checkbox" value="" />
                              
                              <label id="declare" class="form-check-label" for="flexCheckDefault" style="position: relative; left: auto; top: auto;" >
                              I hereby declare that I agree to add another member to my household ID for availing the PMJAY benefits
                              </label>
                              
                              </div>
                            </div>                           
                          </div>

                            <div class="text-center mt-4">
                            <button type="button" class="btn btn-sm btn-white" onclick="openPreviousTab3()">Previous</button>	
                             
                              <button type="button" class="btn btn-sm btn-dark" id="next2" onclick="openTab33()">Next</button>
                              
                            </div>
                          </div>
                      </div>

                      <div class="tab-pane fade" id="tab5Data" role="tabpanel" aria-labelledby="pills-preview-tab">
                        <div class="tab-card-in">
                          <h3>New Member Details </h3>
                          <div class="row mx-0 mb-4">
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Name: </span>
                              <strong id="prnewname"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Guardian's Name: </span>
                              <strong id="prnewfname"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>DOB:</span>
                              <strong id="prnewdob"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Gender: </span>
                              <strong id="prnewGender">Male</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Pincode: </span>
                              <strong id="prnewpincode"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Mobile Number: </span>
                             <strong id="prnewmobile"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Relation: </span>
                              <strong id="prnewrelation"></strong>
                            </div>
                          </div>
                          
                          
                          
						<!-- Start Design Need to be changed for Rashan Card Detail -->
						
						
					  <span id="rationCardEnable1" style="display:none">
					   <div class="row" >
                        <div class="w-100">
						<h5><b>RATION CARD DETAILS BY NEW MEMBER</b></h5>
                            
                          </div>
                          <!--
                          <div class="col-md-12">
                             <label for="inputgender" class="form-label mb-0"></label>
                             <div class="d-flex flex-column gap-3 ">
                             </div>
                          </div>
                          -->
						  <div class="col-3 my-1">
                             <label for="inputname" class="form-label">Family ID :</label>
							  <span id="rrationCard"></span>
                          </div>
                          <div class="col-3 my-1">
                             <label for="inputname" class="form-label">Member ID :</label>
							  <span id="rfamily_mem_id"></span>
                          </div>
                          <div class="col-3 my-1">
                             <label for="inputfname" class="form-label">Name :</label>
							  <span id="rmember_name_eng"></span>
                          </div>
                          <div class="col-3 my-1">
                             <label for="inputmname" class="form-label">Gender :</label>
							  <span id="rgender"></span>
                          </div>
                          <div class="col-3 my-1">
                             <label for="inputDob" class="form-label">YOB :</label>
							 <span id="ryear_of_birth"></span>
                          </div>
                          <div class="col-3 my-1">
                             <label for="inputDob" class="form-label">Father's Name :</label>
							 <span id="rfather_name_eng"></span>
                          </div>
                          <div class="col-3 my-1">
                             <label for="inputDob" class="form-label">Mother's Name :</label>
							 <span id="rmother_name_eng"></span> 
                          </div>
						  <div class="col-3 my-1">
						     <label for="inputDob" class="form-label">Spouse :</label>
							 <span id="rspouse_name_eng"></span>
                          </div>
						   <div class="col-3 my-1">
                             <label for="inputDob" class="form-label">Relation :</label>
							 <span id="rrelation_name"></span>
                          </div>
						</div>
						</span>
						<!-- End Rashan Card Details -->
						
						
                          <h3>New Member eKyc Details </h3>                       
                          <div class="row mb-4">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                              <p>Aadhaar No:-  <strong id="prkycaadhar"></strong> </p>
                            </div>
                            <div class="col-12 col-sm-4 col-md-3 col-lg-2">
                             
                              <img id="prkycphoto" height="140px" width="80px" src="" class="border p-2 w-100" alt="" >
                           
                            </div>
                            <div class="col-12 col-sm-8 col-md-9 col-lg-10">
                              <div class="ekyc-details-table">
                                <span>Name:</span>
                                <strong id="prkycname"> </strong>
                              </div>
                              <div class="ekyc-details-table">
                                <span>Guardian's Name:</span>
                                <strong id="prkycfname"> </strong>
                              </div>
                              <div class="ekyc-details-table">
                                <span>Gender:</span>
                                <strong id="prkycgender"> </strong>
                              </div>
                              <div class="ekyc-details-table">
                                <span>DOB:</span>
                                 <strong id="prkycdob"> </strong>
                              </div>
                              <div class="ekyc-details-table">
                                <span>Address:</span>
                                <strong id="prkycaddress"> </strong>
                              </div>
                            </div>                                                         
                          </div>

                          <h3>Existing Beneficiary </h3>
                          <div class="row mx-0 mb-4">
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>PMJAY Beneficiary Name: </span>
                              <strong id="pjbname"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Guardian's Name: </span>
                              <strong id="pjbfname"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>HHID:</span>
                              <strong id="pjbhhdid"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Gender: </span>
                              <strong id="pjbgender"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Document Name: </span>
                              <strong id="pjbdname"> </strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Guid: </span>
                              <strong id="pjbguid"></strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>File: </span>
                               <strong id="pjbfile"></strong>
                            </div>
                          </div>





                          <h3>New Member eKyc Detail Complete BY </h3>
                          <div class="row mx-0 justify-content-center">                            
                            <div class="col-6 col-sm-3 col-md-3 col-lg-3 tab-card-in-seccdata auth-tabs">
                             <label for="inputfname" class="form-label">OTP </label>
                             
                             <span id="otpIdCheck" style="display:none"> <a href="#"><img src="images/checked.jpg" alt="" width="50" height="50" />  </a> </span>
							 <span id="otpIdCancel" style="display:block"> <a href="#"><img src="images/canceled.jpg" alt="" width="25" height="25" /> </a></span>
                            
                            </div>
                            
                            <div class="col-6 col-sm-3 col-md-3 col-lg-3 tab-card-in-seccdata auth-tabs">
                              
                             <label for="inputmname" class="form-label">Biometric(FingerPrint)  </label>
							 <span id="fingerIdCheck" style="display:none"> <a  href="#"><img src="images/checked.jpg" alt="" width="50" height="50" /> 
							 </a></span>
							 <span id="fingerIdCancel" style="display:block"> <a  href="#"><img src="images/canceled.jpg" alt="" width="25" height="25" />
							 </a></span>
                            
                            </div>
                            
                            <div class="col-6 col-sm-3 col-md-3 col-lg-3 tab-card-in-seccdata auth-tabs">
                             
                             <label for="inputmname" class="form-label">Biometric(Iris) </label>
                             <span id="irisIdCheck" style="display:none"> <a  href="#"><img src="images/checked.jpg" alt="" width="50" height="50" /> 
							 </a></span>
							 <span id="irisIdCancel" style="display:block"> <a  href="#"><img src="images/canceled.jpg" alt="" width="25" height="25" />
							</a></span>
							
                            </div>
                              <div class="col-6 col-sm-3 col-md-3 col-lg-3 tab-card-in-seccdata auth-tabs">
                             
                             <label for="inputmname" class="form-label">Biometric(Face) </label>
                             <span id="faceIdCheck" style="display:none"> <a  href="#"><img src="images/checked.jpg" alt="" width="50" height="50" /> 
							 </a></span>
							 <span id="faceIdCancel" style="display:block"> <a  href="#"><img src="images/canceled.jpg" alt="" width="25" height="25" />
							</a></span>
							
                            </div>
                            
			                <div> 
							
							<div id="errorUpdateKyc" style="color: red;" class="app-title"></div>
				  		  	<div id="infoUpdateKyc" style="color: green;" class="app-title"></div>					
							
							</div>	
                            
                          </div>

                     <div class="text-center mt-4">
                            
                     <form:hidden path="ruralUrbanFlag" id="ruralUrbanFlag"/>
					 <form:hidden path="otpTxn" id="otpTxn"/>
                     <form:hidden path="uidtoken" id="uidtoken"/>
                    
                     <form:hidden path="refernceid" id="refernceid"/>
                     <form:hidden path="stateName" id="stateName"/>
                     <form:hidden path="districtName" id="districtName"/>
					 <form:hidden path="pht" id="pht"/>
					 <form:hidden path="pidData" id="pidData"/>
					 <form:hidden path="authType" id="authType"/>
					 <form:hidden path="demoDob" id="demoDob"/>
					 <form:hidden path="blockName" id="blockName"/>
					 
					 <form:hidden path="family_mem_id" id="family_mem_id"/>
					 <form:hidden path="member_name_eng" id="member_name_eng"/>
					 <form:hidden path="mother_name_eng" id="mother_name_eng"/>
					 <form:hidden path="father_name_eng" id="father_name_eng"/>
					 <form:hidden path="spouse_name_eng" id="spouse_name_eng"/>
					 <form:hidden path="year_of_birth" id="year_of_birth"/>
					 <form:hidden path="relation_name" id="relation_name"/>
					 <form:hidden path="gender" id="gender"/>
					 <form:hidden path="state_lgd_code" id="state_lgd_code"/>
					<form:hidden path="district_lgd_code" id="district_lgd_code"/>
					<form:hidden path="subdistrict_lgd_code" id="subdistrict_lgd_code"/>
					<form:hidden path="village_town_lgd_code" id="village_town_lgd_code"/> 
					<form:hidden path="pincode" id="pincode"/>
					<form:hidden path="rural_urban" id="rural_urban"/>
					<form:hidden path="relation" id="relation"/>
					<form:hidden path="peName" id="peName"/>
					<form:hidden path="peGurName" id="peGurName"/>
					<form:hidden path="peGuid" id="peGuid"/>
					<form:hidden path="hhd_no" id="hhd_no"/>
					 <form:hidden path="townName" id="townName" /> 
					
					<input type="hidden" id="phtId_1" >
					<input type="hidden" id="phtId" >
					<input type="hidden" id="kycName1" >
					 
					 <input type="hidden" id="demoAuthSeccess" >
					 <input type="hidden" id="otpSeccess" >
					  <input type="hidden" id="otpKycSeccess" >
					  
					  <input type="hidden" id="fingerSeccess" >
					  <input type="hidden" id="fingerKycSeccess" >
					  
					   <input type="hidden" id="irisSeccess" >
					  <input type="hidden" id="irisKycSeccess" >
					  
					   <input type="hidden" id="state_name_s" >
					   <input type="hidden" id="district_name_s" >
					   <input type="hidden" id="vtc_s" >
					   <input type="hidden" id="post_s" >
					   <input type="hidden" id="uid_auth_type" >
					   
					   <input type="hidden" id="verification_status" >
						<input type="hidden" id="prnewname" >
						<input type="hidden" id="prnewfname" >
						<input type="hidden" id="prnewdob" >
						<input type="hidden" id="prnewGender" >
						<input type="hidden" id="prnewpincode" >
						<input type="hidden" id="prnewmobile" >
						<input type="hidden" id="prnewrelation" >
						<input type="hidden" id="prkycaadhar" >
						<input type="hidden" id="prkycphoto" >
						<input type="hidden" id="prkycname" >
						<input type="hidden" id="prkycfname" >
						<input type="hidden" id="prkycgender" >
						<input type="hidden" id="prkycdob" >
						<input type="hidden" id="prkycaddress" >
						<input type="hidden" id="pjbname" >
						<input type="hidden" id="pjbfname" >
						<input type="hidden" id="pjbhhdid" >
						<input type="hidden" id="pjbgender" >
						<input type="hidden" id="pjbfile" >
						<input type="hidden" id="pjbdname" >
						<input type="hidden" id="kycFname1" >
						<input type="hidden" id="kycGender1" >				
						<input type="hidden" id="kycAdr1" >
						<input type="hidden" id="seguid" >
	                   
	                   <input type="hidden" id="faceKycSeccess" >
	                   <input type="hidden" id="faceSeccess" >
		                           
                        <button type="button" class="btn btn-sm btn-white" onclick="openPreviousTab4()">Previous</button>						
                        <button type="button" class="btn btn-sm btn-white" id="finishButton" onclick="saveKycData()">Submit</button>
						<button type="button" class="btn btn-sm btn-dark" onclick="window.location.href='beneficiaryCardDonwload'">Cancel</button>
						<button type="button" class="btn btn-sm btn-dark" onclick="goHome()"  >Back Search</button>	
						
						
							 
							 <div id="overlayUpd" class="overlay_full" onclick="offUpd()">
                <div class="fancy-spinner">
                  <div class="ring"></div>
                  <div class="ring"></div>
                  <div class="dot"></div>
                  <span>Updating...</span>
                  </div>							       
							 </div>
							 
							
							
							<div id="overlayMatch" class="overlay_full" onclick="offMatch()">
                <div class="fancy-spinner">
                  <div class="ring"></div>
                  <div class="ring"></div>
                  <div class="dot"></div>
                  <span>Matching...</span>
                  </div>
							     
							</div> 
						
						
						
                         </div>
                        </div>
                      </div>
                    </div>  
                  </div>               
                  
                </div>
              </div>
            </div>
          </section>

          
        </main>
     </form:form>
        <%@ include file="footer.jsp" %>

      </div>
    </main>

  </div>
</div>

  <!-- JS File  -->
  <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript" src="js/dataTables.bootstrap5.min.js"></script>
  <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" language="javascript" src="js/Verhoeff.js"></script>
  <script src="js/biometricFamily.js"></script>

    <script>
     // var currentTab = 0;

     // showTab(currentTab);

     // function showTab(n) {
     //   var x = document.getElementsByClassName("tab");
     //   x[n].style.display = "block";

             
     //   fixStepIndicator(n);
    //  }

   //   function next(n) {
    //    var x = document.getElementsByClassName("tab");
    //    x[currentTab].style.display = "none";

    //    currentTab = currentTab + n;

    //    if (currentTab >= x.length) {
    //      return false;
     //   }
	//	Addnext();
   //     showTab(currentTab);        
   //   }
	  


     // function fixStepIndicator(n) {
     //   var i,
     //     x = document.getElementsByClassName("step");
      //  for (i = 0; i < x.length; i++) {
      //    x[i].className = x[i].className.replace(" active", "");
      //  }

      //  x[n].className += " active";
    //  }
    </script>

    <style>
      .card-header .steps {
        display: flex;
        column-count: 3;
        justify-content: center;
        align-items: center;
      }
      .card-header .steps .step {
        width: 100%;
        text-align: center;
        /* border-bottom: 1px solid #877cdf; */
        line-height: 0.1em;
        /* margin: 10px 0 20px; */
        padding: 10px;
      }
      .card-header .steps .step span {
        display: inline-block;
        font-weight: 400;
        color: #212529;
        text-align: center;
        vertical-align: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        background-color: transparent;
        /* border: 1 px solid transparent; */
        padding: 0.375 rem 0.75 rem;
        font-size: 1rem;
        line-height: 1.5;
        border-radius: 0.25 rem;
        transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out;
        border: none;
        /* border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; */
      }
      .card-header .steps .step.active span {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
        box-shadow: none;
        /* background: #a98bf9;
        color: white;
        border: 1px solid #a98bf9; */
      }
      .card-body {
        padding: 16px;
        min-height: 250px;
        display: flex;
        justify-items: center;
        align-items: center;
      }
      .card-body .tabs {
        width: 100%;
        height: 100%;
        justify-content: center;
        display: flex;
        align-items: center;
      }
      .card-body .tabs .tab {
        display: none;
      }
      .card-body .tabs .tab.active {
        display: block !important;
      }
      .card-footer {
        padding: 16px;
        border-top: 1px solid #d5d0d0;
      }
      .card-footer button {
        /* border-radius: 5px;
        padding: 15px 25px;
        width: 150px;
        margin: 10px auto;
        border: none;
        border-radius: 0.085rem;
        font-weight: 500;
        font-size: 16px;
        color: white;
        background: #aa9ffe;
        box-shadow: 0px 5px 0px 0px #877cdf; */
      }
      .card-footer button:active {
        outline: none;
        transform: translate(0px, 5px);
        -webkit-transform: translate(0px, 5px);
        box-shadow: 0px 1px 0px 0px;
      }

      @media only screen and (max-width: 420px) {
        .card-footer button {
          width: 100%;
        }
      }
    </style>
	
	<script>
$(function () {
	$("#example1").DataTable({
	      "responsive": false, "lengthChange": false, "autoWidth": false,
	      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
	    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
	
	//alert($('#example1_filter').html());
	//$('#example1_filter').find('input[type="search"]').attr('placeholder','search name');
});


$(function () {
	$("#example2").DataTable({
	      "responsive": false, "lengthChange": false, "autoWidth": ture,
	    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
	
});
</script>
	
<script>

var requestUri='<%=requestUri%>';

function getRationCardDetail(){

   var chekedRUFlag='<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
   var state_code;
   if(chekedRUFlag=='R'){
    state_code='<%=beneficiarySeccForm.getStateCodeRural()%>';
   }else if(chekedRUFlag=='U'){
    state_code='<%=beneficiarySeccForm.getStateCodeUrban()%>';
   }
   
    var rationCard=document.getElementById("rationCard").value;	


	var relationid=document.getElementById("relationid").value;
	var demoName=document.getElementById("demoName").value;
	var demoFname=document.getElementById("demoFname").value;
	var demoGender=document.getElementById("demoGender").value;
	var demoDob=document.getElementById("demoDob").value;
	var demoMobile=document.getElementById("demoMobile").value;
	var demoEmail=document.getElementById("demoPincode").value;
	
	
	
	if(relationid==""){
		alert("Please Select Member Relationship.");
		document.getElementById("relationid").focus();
		return false;
	}
	if(demoName==""){
		alert("Please Enter the Full Name.");
		document.getElementById("demoName").focus();
		return false;
	}
	if(demoFname==""){
		alert("Please Enter the Guardian's Name.");
		document.getElementById("demoFname").focus();;
		return false;
	}
	if(demoGender==""){
		alert("Please Select Gender.");
		document.getElementById("demoGender").focus();;
		return false;
	}
	if(demoDob==""){
		alert("Please Enter the DOB.");
		document.getElementById("demoDob").focus();;
		return false;
	}
	
	if(demoEmail==""){
		alert("Please Pincode.");
		document.getElementById(demoEmail).focus();;
		return false;
	}
	if(demoMobile==""){
		alert("Please Enter Mobile Number.");
		document.getElementById(demoMobile).focus();;
		return false;
	}

if(rationCard==""){
		alert("Please Enter the Ration Card Number.");
		document.getElementById("rationCard").focus();;
		return false;
}
$("#rationDiv").hide();
$("#rationError").hide();
$("#rationInfo").hide();
onRation();
$.ajax({
        type: "POST",
        url: "/"+requestUri+"/getRationCardData",
        data: {
        "stateCode": state_code,
        "rationCard":rationCard,
       		 },
        success: function(data){
            newData = data;
            offRation();
            if(newData !== null){
            $("#rationDiv").show();
            }
            
		   var data11 = jQuery.parseJSON( newData );
           
		   
		   if(data11['status']!='Y'){
			
	
			getRationCardDetail()
			  $("#rationError").show();
			  $("#rationError").html('No Records Found !!');
			  $("#rationInfo").hide();

			}
		document.getElementById("rashanDataDiv").style.display="block";
		document.getElementById("btnRashan").disabled = true;
		document.getElementById("next1").disabled = false;
		var ration=	jQuery.parseJSON(data11['Details']);
		$("#state_lgd_code").val(ration.state_lgd_code);
		$("#district_lgd_code").val(ration.district_lgd_code);
		$("#subdistrict_lgd_code").val(ration.subdistrict_lgd_code);
		$("#village_town_lgd_code").val(ration.village_town_lgd_code);
		$("#pincode").val(ration.pincode);
		$("#rural_urban").val(ration.rural_urban);
	
		var data1 =ration.family;
       
       	   $("#example2").dataTable().fnDestroy();
       	   
		    var table = $('#example2').DataTable( {
				"responsive": false, "lengthChange": false, "autoWidth": false,"pagingType": "full_numbers","pageLength": 15,"fixedHeader": false,
				"language": {"emptyTable": "No Records Found !"  },"searching": false,
      		  "aaData": data1,
      		  "aoColumns": [
			  { "mData": null,
                      render: function () {
                           return '<input type="radio" style="position: relative;top: 2px;" id="editButton" name="editButton" onclick="editDetail(this)"/>';

                      }
                }, 
			    { "mData": "family_mem_id"},
      		    { "mData": "member_name_eng"},
				{ "mData": "gender"},
				{ "mData": "relation_name"},
      		    { "mData": "mother_name_eng"},
      		    { "mData": "father_name_eng"},
      		    { "mData": "spouse_name_eng"},
      		    { "mData": "year_of_birth"},
      		  	],
				
				createdRow: function (row, data1, dataIndex) 
      		        {
      		    		 
        			   if(data1.gender === 'M')
      		            {
      		            	$(row).find('td:eq(3)').html("Male")
      		            }else  if(data1.gender === 'F')
      		            {
      		            	$(row).find('td:eq(3)').html("Female")
      		            }else 
      		            {
      		            	$(row).find('td:eq(3)').html("Other")
      		            }
      		    		if(data1.year_of_birth === '0')
      		            {
      		            	$(row).find('td:eq(8)').html("")
      		            }
      					
      				},
      		   "scrollX": true
      		 		
      		});
         },

         error: function(e){
			  offRation();
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
			
         }
    });


}

function editDetail(value) {

		var requestUri='<%=requestUri%>';
	
		var row = jQuery(value).closest('tr')
		var family_mem_id = row[0].children[1].innerHTML;
		var family_mem_id = family_mem_id.trim();
		var member_name_eng = row[0].children[2].innerHTML;
		var gender = row[0].children[3].innerHTML;

		var relation_name = row[0].children[4].innerHTML;
		var mother_name_eng = row[0].children[5].innerHTML;
		var father_name_eng = row[0].children[6].innerHTML;
		var spouse_name_eng = row[0].children[7].innerHTML;
		var year_of_birth = row[0].children[8].innerHTML;
	 
		document.getElementById("family_mem_id").value=family_mem_id;
		document.getElementById("member_name_eng").value=member_name_eng;
		document.getElementById("gender").value=gender;
		document.getElementById("relation_name").value=relation_name;
		document.getElementById("mother_name_eng").value=mother_name_eng;
		document.getElementById("father_name_eng").value=father_name_eng;
		document.getElementById("spouse_name_eng").value=spouse_name_eng;
		document.getElementById("year_of_birth").value=year_of_birth;
		
		document.getElementById("rfamily_mem_id").innerHTML=family_mem_id;
		document.getElementById("rmember_name_eng").innerHTML=member_name_eng;
		document.getElementById("rgender").innerHTML=gender;
		document.getElementById("rrelation_name").innerHTML=relation_name;
		document.getElementById("rmother_name_eng").innerHTML=mother_name_eng;
		document.getElementById("rfather_name_eng").innerHTML=father_name_eng;
		document.getElementById("rspouse_name_eng").innerHTML=spouse_name_eng;
		document.getElementById("ryear_of_birth").innerHTML=year_of_birth;
		document.getElementById("rrationCard").innerHTML=document.getElementById("rationCard").value;
		

		$("#rationInfo").hide();
		$("#rationError").hide();
		
		validateDataWithFuzzyMatcher('R');
		
	}

	function arrayToList(array) {
    let list = null;
    for (let i = array.length - 1; i >= 0; i--) {
        list = { value: array[i], rest: list };
    }
    return list;
}

function validateDataWithFuzzyMatcher(type){
   var nameWith;
   var fatherWith;
   var genderWith;
   var name_secc;
   var father_name_secc;
   var gender_secc;
 
 if(type=='K'){ //KYC

	name_secc = document.getElementById('demoName').value; 
   father_name_secc = document.getElementById('demoFname').value; 
   gender_secc = document.getElementById('demoGender').value;

 	nameWith = document.getElementById('kycName1').value; 
    fatherWith= document.getElementById('kycFname1').value;
    genderWith= document.getElementById('kycGender1').value;
	
	
}else if(type=='R'){ //Ration

	name_secc=document.getElementById("member_name_eng").value;
   father_name_secc=document.getElementById("father_name_eng").value;
   gender_secc=document.getElementById("gender").value;

 	nameWith = document.getElementById('demoName').value; 
   fatherWith= document.getElementById('demoFname').value; 
    genderWith= document.getElementById('demoGender').value; 
}
var authDemo=document.getElementById("demoAuthSeccess").value;
if(authDemo=='Y'){
 saveKycData();
}else{	
onMatch();
	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/fuzzyMatcher",
         data: {
       	 "nameBy": name_secc,
       	 "fatherBy": father_name_secc,
       	 "genderBy": gender_secc,
       	 "nameWith": nameWith,
       	 "fatherWith": fatherWith,
       	 "genderWith": genderWith,
       		},
        success: function(data){
       	 console.log(data);
       	offMatch();
       	//  var obj = jQuery.parseJSON( data );
       	 if(data == "Y"){
       	   if(type=='K'){
       	 $("#pills-existing-tab").addClass("active");
          $("#tab4Data").addClass("show");
          $("#tab4Data").addClass("active");
          
  		 $("#pills-memekyc-tab").removeClass("active");
          $("#tab3Data").removeClass("show");
          $("#tab3Data").removeClass("active");
            
              $("#pills-newmemdtl-tab").removeClass("active");
              $("#tab2Data").removeClass("show");
              $("#tab2Data").removeClass("active");
              
           $("#pills-familydtl-tab").removeClass("active");
           $("#tabData1").removeClass("active");
           $("#tabData1").removeClass("show");
            $('#errorUpdateKyc1').hide();
       	    } else if(type=='R'){
					document.getElementById("next1").disabled = false;
					$("#rationInfo").show();
					$("#rationInfo").html('Beneficiary Ration card details is matched with Member details.');
					$("#rationError").hide();
					
			}
          }else if(data == "N"){
           if(type=='D'){
             document.getElementById('adhar_required').innerHTML="Beneficiary Declare details is not matched with Member Details.";
             document.getElementById("aadhar").value="";
             }
           else if(type=='K'){
           $('#errorUpdateKyc1').show();
             $('#errorUpdateKyc1').html("Beneficiary eKYC details is not matched with Member details.");
        	  document.getElementById("finishButton").disabled = true;
      	  }else if(type=='R'){
			  $("#rationError").show();
             $('#rationError').html("Beneficiary Ration card details is not matched with Member details.");
        	 $("#rationInfo").hide();
        	// document.getElementById("next1").disabled = true;
      	  }
      	  }
        	},
         error: function(e){
			 offMatch();
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
				
         }
    
    });
  } 
 
 } 
 




	function AddFamily(){	
		
            var family = $('input[name="family"]:checked').val();
            
            var fields = family.split('/');
            var nam=fields[0]+'/'+fields[1];
            $('#namefather').html(nam);
            $('#beny').html(fields[0]);  
            $('#nbm').html(fields[1]);  
            document.getElementById("genderid").value=fields[2];
            document.getElementById("seguid").value=fields[3];
            document.getElementById("pbenname").value=fields[0];
            document.getElementById("gurname").value=fields[1];
           
            
	}
	function Addnext(){	
           
            var newnam=document.getElementById("demoName").value;
            $('#nbm').html(newnam);   
           
            
	}
	
	 function validateAadhar()
{
        var adhhar= document.getElementById("aadhar").value;

        if (adhhar==null || adhhar=="")
        {
         document.getElementById('adhar_required').innerHTML="Please Enter Aadhaar Number.";
        }
        else 
        {
		if(!adhhar.verhoeffCheck())  {     
             document.getElementById("aadhar").value="";
             document.getElementById('adhar_required').innerHTML="Please Enter Valid Aadhaar Number.";             
        }else{
       	 document.getElementById('adhar_required').innerHTML="";
       	 //validateDataWithFuzzyMatcher('D');
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
	function onOpenAction(){
	$('#secc-collapse').addClass('show');
	$('#nfsa-collapse').removeClass('show');
 	var rationStateArray='<%=PROPERTIES.RATION_CARD_SERVICE_ENABLE_LIST%>';
	
	var cardStatus='<%=cardStatus%>';
	//alert(cardStatus);
	
	document.getElementById("next1").disabled = true;
	document.getElementById("next2").disabled = true;
	document.getElementById("next3").disabled = true;
	//document.getElementById("next").disabled = true;
	document.getElementById("finishButton").disabled = true; 
	//document.getElementById("Previous2").disabled = true; 
	//document.getElementById("Previous3").disabled = true; 
	document.getElementById("entOtp").style.display="none"; 
	
	var chekedRUFlag='<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
   var state_code;
   var vtcCode;
   if(chekedRUFlag=='R'){
    state_code='<%=beneficiarySeccForm.getStateCodeRural()%>';
    
   }else if(chekedRUFlag=='U'){
    state_code='<%=beneficiarySeccForm.getStateCodeUrban()%>';
   }
 if(rationStateArray.split(",").includes(state_code)){//vinay
	document.getElementById("rationCardEnable").style.display = "block";
	document.getElementById("rationCardEnable1").style.display = "block";
	
	}else{
		document.getElementById("next1").disabled = false;
	}
	
	if (document.getElementById("radioO").checked) {
		  
		$("#radioF").removeClass("active");
		$("radioP").removeClass("active");
		$("#radioI").removeClass("active");
		$("#radioO").addClass("active");
		
		//$("#iris-home").removeClass("show");
		//$("#demographic-home").removeClass("show");
		//$("#finger-home").removeClass("show");
		//$("#otp-home").addClass("active");
		//$("#otp-home").addClass("show");
		
	}
	else if (document.getElementById("radioF").checked) {
		 portscanView();
		 
		 $("#radioO").removeClass("active");
		 $("radioP").removeClass("active");
		 $("#radioI").removeClass("active");
		 $("#radioF").addClass("active");
		
	}
	else if (document.getElementById("radioI").checked) {
		  portscanView();
	    $("#radioO").removeClass("active");
		 $("radioP").removeClass("active");
		 $("#radioF").removeClass("active");
		 $("#radioI").addClass("active");
		  
	   
	}else if (document.getElementById("radioP").checked) {
	
	     $("#radioO").removeClass("active");
		 $("radioI").removeClass("active");
		 $("#radioF").removeClass("active");
		 $("#radioP").removeClass("active");
		  
	}
	   
 }
 function portscanView() {
	onScan();
	setTimeout(function() { portscan(); }, 5000);
			
}

   
   
   function bioBasedKyc(bioValue){
   
  
   var requestUri='<%=requestUri%>';
   
  document.getElementById("optBtn").disabled = false;
  document.getElementById("countdown").innerHTML = "";
   
  var aadhar =document.getElementById("aadhar").value;
	
	
	var aadhar =document.getElementById("aadhar").value;
	var pidData =document.getElementById("pidData").value;
	
	if(aadhar==""){
		alert("Please Enter Aadhaar Number.");
		document.getElementById("aadhar").focus();;
		return false;
	}
	if(pidData==""){
		alert("Please Caputre Biometric.");
		return false;
	}
	 onKyc() ;
		$.ajax({
        type: "POST",
         url: "/"+requestUri+"/bioBasedKyc",
         data: {
       	 "aadhar": aadhar,
       	 "pidData": pidData,
       	 "bioType":bioValue
       		 },
        success: function(data){
       	 console.log(data);
       		offKyc() ;
       	  var obj = jQuery.parseJSON( data );
            if(obj['status'] == "Y"){
				document.getElementById("kycDetailsDiv").style.display = "block";
            	 $('#infoOtp').html("ekyc Successfully completed.");
                 $('#errorOtp').hide('slow');
                  $('#infoOtp').show('slow');
                $('#kycName').html(obj['kycName']);
                $('#kycGender').html(obj['kycGender']);
                $('#kycDob').html(obj['kycDob']);
				
				$('#kycName1').html(obj['kycName']);
                $('#kycGender1').html(obj['kycGender']);
                $('#kycDob1').html(obj['kycDob']);
				
				//document.getElementById("otpLevel").style.display = "none";
				document.getElementById("countdown").innerHTML = "";
				document.getElementById("countdown").style.display = "none";
				document.getElementById("countdown").value ="";
				
				document.getElementById("kycDob1").value=obj['kycDob'];
				document.getElementById("kycName1").value=obj['kycName'];
				document.getElementById("kycFname1").value=obj['kycFname'];
				document.getElementById("kycGender1").value=obj['kycGender'];
				document.getElementById("kycAdr1").value=obj['kycAdr'];
				document.getElementById("phtId_1").src="data:image/jpeg;base64,"+obj['pht'];
				 $('#kycFname1').html(obj['kycFname']);
                  $('#kycAdr1').html(obj['kycAdr']);
				
				
				
                document.getElementById("uidtoken").value=obj['uidToken'];
                document.getElementById("phtId").src="data:image/jpeg;base64,"+obj['pht'];
				document.getElementById("pht").value=obj['pht'];
                $('#kycFname').html(obj['kycFname']);
                $('#kycAdr').html(obj['kycAdr']);
                
                document.getElementById("vtc_s").value=obj['vtc_s'];
                document.getElementById("district_name_s").value=obj['district_name_s'];
                document.getElementById("state_name_s").value=obj['state_name_s'];
                document.getElementById("post_s").value=obj['post_s'];
                document.getElementById("uid_auth_type").value='2';
                document.getElementById("verification_status").value='C';
                
                 
			   if(bioValue=='F'){
				 document.getElementById("fingerIdCheck").style.display = "block";
				 document.getElementById("fingerIdCancel").style.display = "none";
				 document.getElementById("radioI").disabled = true;  
				 document.getElementById("fingerKycSeccess").value="Y";
				 }else if(bioValue=='I'){
				  document.getElementById("irisIdCheck").style.display = "block";
				  document.getElementById("irisIdCancel").style.display = "none";
				 document.getElementById("radioF").disabled = true; 
				 document.getElementById("irisKycSeccess").value="Y";
				 }
				 
				// document.getElementById("radioD").disabled = true; 
				
				document.getElementById("radioO").disabled = true; 
				
                document.getElementById("next2").disabled = false;
				document.getElementById("next3").disabled = false;
				document.getElementById("finishButton").disabled = false; 
				//document.getElementById("Previous2").disabled = false; 
				//document.getElementById("Previous3").disabled = false; 
				document.getElementById("authType").value="K";
				setDemoName();
             }else{
				 $('#errorOtp').html("Kyc failed.");
				 document.getElementById("next3").disabled = true;
                 $('#infoOtp').hide('slow');
                 $('#errorOtp').show('slow');
                 
             }
         },
         error: function(e){
           if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
				
         }
    
    });
   
 
   
   
   
   }
   
  function goHome(){
	   
	   var requestUri='<%=requestUri%>';
	   window.location.href="/"+requestUri+"/home";
   }
   

function otpBasedKyc(){
   
   
   var requestUri='<%=requestUri%>';
   
   document.getElementById("optBtn").disabled = false;
   document.getElementById("countdown").innerHTML = "";
   
   var aadhar =document.getElementById("aadhar").value;
	
	
	var aadhar =document.getElementById("aadhar").value;
	var otp =document.getElementById("otp").value;
	var otpTxn =document.getElementById("otpTxn").value;
	
	
	
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
 	
		onKyc() ;
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/otpBasedKyc",
        //url: "/otpBasedKyc",
        data: {
       	 "aadhar": aadhar,
       	 "otp": otp,
       	 "otpTxn": otpTxn
       	   	
       		 },
        success: function(data){
       	 console.log(data);
       	offKyc() ;
       	  var obj = jQuery.parseJSON( data );
            if(obj['status'] == "Y"){
				document.getElementById("kycDetailsDiv").style.display = "block";
            	 $('#infoOtp').html("ekyc Successfully completed.");
            	// document.getElementById("otpLevel").style.display = "none";
            	  document.getElementById("otp-home").style.display = "none";
            	 
            	  document.getElementById("otpKyc").style.display = "none";
            	  
            	 document.getElementById("countdown").innerHTML = "";
            	 document.getElementById("countdown").value ="";
            	 document.getElementById("countdown").style.display="none";
            	
            	 
                $('#errorOtp').hide('slow');
                $('#infoOtp').show('slow');
                $('#kycName').html(obj['kycName']);
                $('#kycGender').html(obj['kycGender']);
                $('#kycDob').html(obj['kycDob']);
				
				$('#kycName1').html(obj['kycName']);
                $('#kycGender1').html(obj['kycGender']);
                $('#kycDob1').html(obj['kycDob']);
                document.getElementById("kycDob1").value=obj['kycDob'];
				document.getElementById("kycName1").value=obj['kycName'];
				document.getElementById("kycFname1").value=obj['kycFname'];
				document.getElementById("kycGender1").value=obj['kycGender'];
				document.getElementById("kycAdr1").value=obj['kycAdr'];
				document.getElementById("phtId_1").src="data:image/jpeg;base64,"+obj['pht'];
				 $('#kycFname1').html(obj['kycFname']);
                  $('#kycAdr1').html(obj['kycAdr']);
				
				
                document.getElementById("uidtoken").value=obj['uidToken'];
                //document.getElementById("phtId").src="data:image/jpeg;base64,"+obj['pht'];
				document.getElementById("pht").value=obj['pht'];
                 $('#kycFname').html(obj['kycFname']);
                  $('#kycAdr').html(obj['kycAdr']);
                  
                document.getElementById("vtc_s").value=obj['vtc_s'];
                document.getElementById("district_name_s").value=obj['district_name_s'];
                document.getElementById("state_name_s").value=obj['state_name_s'];
                document.getElementById("post_s").value=obj['post_s'];
                 document.getElementById("uid_auth_type").value='1'; 
                 document.getElementById("verification_status").value='C';
                  
				 document.getElementById("otpIdCancel").style.display = "none";
				 document.getElementById("otpIdCheck").style.display = "block";
				 //document.getElementById("radioD").disabled = true;
				// document.getElementById("finalDiv").style.display = "block"; 
				document.getElementById("otp").style.display = "none";
				document.getElementById("optBtn").style.display = "none";
				document.getElementById("otpKyc").style.display = "none";
				
				document.getElementById("radioF").disabled = true; 
				document.getElementById("radioI").disabled = true;  
                
				document.getElementById("next2").disabled = false;
				document.getElementById("next3").disabled = false;
				document.getElementById("finishButton").disabled = false; 
				//document.getElementById("Previous2").disabled = false; 
				//document.getElementById("Previous3").disabled = false; 
				
				document.getElementById("otpKycSeccess").value="Y";
				document.getElementById("authType").value="K";
				
				  setDemoName();
             }else{
				 
				 //openTab2();
             	 $('#errorOtp').html("Kyc failed.");
             	  document.getElementById("next3").disabled = true;
                 $('#infoOtp').hide('slow');
                 $('#errorOtp').show('slow');
                 
             }
         },
         error: function(e){
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
				
         }
    
    });
   
 
   
   
   
   }

function getOtp(){
   
   var requestUri='<%=requestUri%>';
	
	var aadhar =document.getElementById("aadhar").value;
	
	
	if(aadhar==""){
		alert("Please Enter Aadhaar Number.");
		document.getElementById("aadhar").focus();;
		return false;
	}
	
	onOtp()	;
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/genOtp",
        //url: "/genOtp",
        data: {
       	 "aadhar": aadhar  	
       		 },
        success: function(data){
       	 console.log(data);
       	offOtp();
       	  var obj = jQuery.parseJSON( data );
            if(obj['status'] == "Y"){
            	// $('#infoOtp').html("OTP Sent to Registered mobile :  "+obj['mobile']);
                 $('#errorOtp').hide('slow');
                 $('#infoOtp').show('slow');
                 
              var timeleft = "60";
 			  var downloadTimer = setInterval(function(){
			  document.getElementById("countdown").innerHTML = "OTP Sent to Registered mobile: <b>"+obj['mobile'] +"</b>  You can get OTP again after <b>"+timeleft+ "</b> seconds";
			  timeleft -= 1;
			   document.getElementById("countdown").style.display = "block";
			  
			   
			  document.getElementById("optBtn").disabled = true;
			  document.getElementById("entOtp").style.display="flex";
			
			  if(timeleft <= 0){
				clearInterval(downloadTimer);
				document.getElementById("optBtn").disabled = false;
				document.getElementById("countdown").innerHTML = "";
				document.getElementById("countdown").style.display = "none";
				document.getElementById("entOtp").style.display="none";
				
			  }
			 document.getElementById('otp').focus(); 
			}, 1000);
                 
                 
                 
                document.getElementById("optBtn").style.display="block";
                document.getElementById("otpTxn").value=obj['txn'];
                document.getElementById("entOtp").style.display="flex"; 
				document.getElementById("otpSeccess").value="Y";
				document.getElementById("radioF").disabled="true";
				document.getElementById("radioI").disabled="true";
				//document.getElementById("radioD").disabled="true";
				//document.getElementById("next2").disabled = false;
				document.getElementById("next3").disabled = false;
				//document.getElementById("Previous2").disabled = false; 
				//document.getElementById("Previous3").disabled = false; 
				
             }else{
             	 $('#errorOtp').html("OTP failed.");
                 $('#infoOtp').hide('slow');
                 $('#errorOtp').show('slow');
                 $('#errorOtp').delay(1000).hide(1500);
             }
         },
         error: function(e){
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
					
         }
    
    });
   
 
   
   }


		
		function onScan() {
		  document.getElementById("overlay").style.display = "flex";
		}
		
		function offScan() {
		  document.getElementById("overlay").style.display = "none";
		}
		
		function onKyc() {
		  document.getElementById("overlayKyc").style.display = "flex";
		}
		
		function offKyc() {
		  document.getElementById("overlayKyc").style.display = "none";
		}
		
		function onDemo() {
		  document.getElementById("overlayDemo").style.display = "flex";
		}
		
		function offDemo() {
		  document.getElementById("overlayDemo").style.display = "none";
		}
		
		function onOtp() {
		  document.getElementById("overlayOtp").style.display = "flex";
		}
		
		function offOtp() {
		  document.getElementById("overlayOtp").style.display = "none";
		}
		
		function onUpd() {
		  document.getElementById("overlayUpd").style.display = "flex";
		}
		
		function offUpd() {
		  document.getElementById("overlayUpd").style.display = "none";
		}
		
		
		function onRation() {
		  document.getElementById("overlayRation").style.display = "flex";
		}
		
		function offRation() {
		  document.getElementById("overlayRation").style.display = "none";
		}
		
		function onMatch() {
		  document.getElementById("overlayMatch").style.display = "flex";
		}
		
		function offMatch() {
		  document.getElementById("overlayMatch").style.display = "none";
		}
		


  function openTab2(){
	 var family = $('input[name="family"]:checked').val();
	 if (family === undefined || family === null) {
	 alert("Please select radio button ");
     return false;
}
            
            var fields = family.split('/');
            var nam=fields[0]+'/'+fields[1];
            $('#namefather').html(nam);
            $('#beny').html(fields[1]);  
            $('#nbm').html(fields[0]);  
			
			
			if(fields[2]==1)
            document.getElementById("genderid").value='M';
        
			else if(fields[2]==2)
			document.getElementById("genderid").value='F';
			
			else	
			 document.getElementById("genderid").value='O';
		
            document.getElementById("pbenname").value=fields[0];
            document.getElementById("gurname").value=fields[1];
            document.getElementById("seguid").value=fields[3];
           //document.getElementById("tab2").addClass("active");
          
           $("#pills-newmemdtl-tab").addClass("active");
           $("#tab2Data").addClass("show");
           $("#tab2Data").addClass("active");
           $("#pills-familydtl-tab").removeClass("active");
           $("#tabData1").removeClass("active");
           $("#tabData1").removeClass("show");
            
	 //next(1);
	 
  }
  
  function openTab1(tab){
		
	       $("#pills-newmemdtl-tab").removeClass("active");
           $("#tab2Data").removeClass("show");
           $("#tab2Data").removeClass("active");
           $("#pills-familydtl-tab").addClass("active");
           $("#tabData1").addClass("active");
           $("#tabData1").addClass("show");
  }
  
   function openPreviousTab2(tab){
		
		
		
	       $("#pills-newmemdtl-tab").addClass("active");
           $("#tab2Data").addClass("show");
           $("#tab2Data").addClass("active");
           
           $("#pills-memekyc-tab").removeClass("active");
           $("#tab3Data").removeClass("show");
           $("#tab3Data").removeClass("active");
  }
  
  function openPreviousTab3(tab){
		
	      $("#pills-existing-tab").removeClass("active");
          $("#tab4Data").removeClass("show");
          $("#tab4Data").removeClass("active");
          
  		 $("#pills-memekyc-tab").addClass("active");
          $("#tab3Data").addClass("show");
          $("#tab3Data").addClass("active");
            
  }
   function openPreviousTab4(tab){
		
	      $("#pills-existing-tab").addClass("active");
          $("#tab4Data").addClass("show");
          $("#tab4Data").addClass("active");
          
  		 $("#pills-preview-tab").removeClass("active");
          $("#tab5Data").removeClass("show");
          $("#tab5Data").removeClass("active");
            
  }
  function openTab4(tab){
  		
		validateDataWithFuzzyMatcher('K');
  }
  function openTab31(tab){
	  
	var relationid=document.getElementById("relationid").value;
	var demoName=document.getElementById("demoName").value;
	var demoFname=document.getElementById("demoFname").value;
	var demoGender=document.getElementById("demoGender").value;
	var demoDob=document.getElementById("demoDob").value;
	var demoMobile=document.getElementById("demoMobile").value;
	var demoEmail=document.getElementById("demoPincode").value;
	
	
	if(relationid==""){
		alert("Please Select Member Relationship.");
		document.getElementById("relationid").focus();
		return false;
	}
	if(demoName==""){
		alert("Please Enter the Full Name.");
		document.getElementById("demoName").focus();
		return false;
	}
	if(demoFname==""){
		alert("Please Enter the Father Name.");
		document.getElementById("demoFname").focus();;
		return false;
	}
	if(demoDob==""){
		alert("Please Enter the DOB.");
		document.getElementById("demoDob").focus();;
		return false;
	}
	if(demoGender==""){
		alert("Please Select Gender.");
		document.getElementById("demoGender").focus();;
		return false;
	}
	
	
	
	if(demoEmail==""){
		alert("Please Pincode.");
		document.getElementById(demoEmail).focus();;
		return false;
	}
	
	      $("#pills-memekyc-tab").addClass("active");
          $("#tab3Data").addClass("show");
          $("#tab3Data").addClass("active");
            
              $("#pills-newmemdtl-tab").removeClass("active");
              $("#tab2Data").removeClass("show");
              $("#tab2Data").removeClass("active");
              
           $("#pills-familydtl-tab").removeClass("active");
           $("#tabData1").removeClass("active");
           $("#tabData1").removeClass("show");
	//next(1);
  
  }
  function openTab3(tab){	
  
  if(document.getElementById("otpSeccess").value=='Y'){
	  if(document.getElementById("otpKycSeccess").value=='Y'){
		//  next(1);
	  }else{
		  if(document.getElementById("otp").value==''){
			alert("Please Enter OTP.");	
				return false;
			}	
		otpBasedKyc();
		//next(1);
	  }
  }else if(document.getElementById("fingerSeccess").value=='Y'){
			if(document.getElementById("fingerKycSeccess").value=='Y'){
				// next(1);
			}else{
		 	bioBasedKyc('F');
			 //next(1);
			}
	}else if(document.getElementById("irisSeccess").value=='Y'){
			if(document.getElementById("irisKycSeccess").value=='Y'){
				// next(1);
			}else{
		 	bioBasedKyc('I');
			// next(1);
			}
	}
	
  
 }
  function openTab33(tab){
	  
	
	var fup = document.getElementById('docFile');
	var fileName = fup.value;

	if(fileName=='')
	{
		alert("Please upload document file.");
		document.getElementById('docFile').focus(); 
		return false;
	}
	if(document.getElementById("docName").value==""){
	alert("Please Enter Document Name..");
	return false;
	}

	if(!document.getElementById("checkDeclare").checked){
		alert("Please checked declare");
		return false;	
	}
	setDemoName();
		  //next(1);

		  $("#pills-preview-tab").addClass("active");
          $("#tab5Data").addClass("show");
          $("#tab5Data").addClass("active");
          
 		  $("#pills-existing-tab").removeClass("active");
          $("#tab4Data").removeClass("show");
          $("#tab4Data").removeClass("active");
          
  		  $("#pills-memekyc-tab").removeClass("active");
          $("#tab3Data").removeClass("show");
          $("#tab3Data").removeClass("active");
            
          $("#pills-newmemdtl-tab").removeClass("active");
          $("#tab2Data").removeClass("show");
          $("#tab2Data").removeClass("active");
              
          $("#pills-familydtl-tab").removeClass("active");
          $("#tabData1").removeClass("active");
          $("#tabData1").removeClass("show");
		

  }
  
  function formatDate(date) {
		var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;
    return [year, month, day].join('-');
}

  function formatDate2(date) {
		var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;
    return [day,month,year].join('-');
}

  
  function setDemoName(){
	 document.getElementById('prnewname').innerHTML=document.getElementById('demoName').value;
	 if(document.getElementById('relationid').value=="1"){
	 	document.getElementById('prnewrelation').innerHTML="Marriage";
	 	document.getElementById('relation').value="SPOUSE";
	 }else if(document.getElementById('relationid').value=="2"){
		document.getElementById('prnewrelation').innerHTML="New Born";
		if(document.getElementById('demoGender').value=="M")
		document.getElementById('relation').value="SON";
		else if(document.getElementById('demoGender').value=="F")
		document.getElementById('relation').value="DAUGHTER"; 
		else
		document.getElementById('relation').value="OTHER";
		
	}	
	 document.getElementById('prnewfname').innerHTML=document.getElementById('demoFname').value;  
	 document.getElementById('prnewdob').innerHTML=formatDate2(document.getElementById('demoDob').value); 
	 if(document.getElementById('demoGender').value=="M")
		document.getElementById('prnewGender').innerHTML="Male";  
	else if(document.getElementById('demoGender').value=="F")
		document.getElementById('prnewGender').innerHTML="Female"; 
	else
		document.getElementById('prnewGender').innerHTML="Transgender";  
	document.getElementById('prnewpincode').innerHTML=document.getElementById('demoPincode').value;
	document.getElementById('prnewmobile').innerHTML=document.getElementById('demoMobile').value;
	document.getElementById('prkycaadhar').innerHTML=document.getElementById('aadhar').value;
	document.getElementById("prkycphoto").src="data:image/jpeg;base64,"+document.getElementById('pht').value;
	document.getElementById('prkycphoto').innerHTML=document.getElementById('pht').value;

	document.getElementById('prkycname').innerHTML=document.getElementById('kycName1').value;
	document.getElementById('prkycfname').innerHTML=document.getElementById('kycFname1').value;
	document.getElementById('prkycgender').innerHTML=document.getElementById('kycGender1').value;
	document.getElementById('prkycdob').innerHTML=document.getElementById('kycDob1').value;
	document.getElementById('prkycaddress').innerHTML=document.getElementById('kycAdr1').value;
	document.getElementById('pjbname').innerHTML=document.getElementById('pbenname').value;
	document.getElementById('pjbfname').innerHTML=document.getElementById('gurname').value;
	document.getElementById('pjbhhdid').innerHTML=document.getElementById('hhdid').value;
	document.getElementById('pjbguid').innerHTML=document.getElementById('seguid').value;
	
	 if(document.getElementById('genderid').value=="M")
		document.getElementById('pjbgender').innerHTML="Male";  
	else if(document.getElementById('genderid').value=="F")
		document.getElementById('pjbgender').innerHTML="Female"; 
	else
		document.getElementById('pjbgender').innerHTML="Transgender";  
	document.getElementById('pjbfile').innerHTML=document.getElementById('docFile').value;
	document.getElementById('pjbdname').innerHTML=document.getElementById('docName').value;
	
   }
   
   
  

function showDetail(value) {
		var row = jQuery(value).closest('tr')
		var guid = row[0].children[1].innerHTML;
		var guid = guid.trim();
		var name = row[0].children[2].innerHTML;
		var fathername = row[0].children[3].innerHTML;
		var relation = row[0].children[4].innerHTML;
		var gender = row[0].children[5].innerHTML;
		var status = row[0].children[6].innerHTML;	
		var nam=name+'/'+fathername;
        $('#namefather').html(nam);
        $('#beny').html(name);  
        $('#nbm').html(name);  
        document.getElementById("genderid").value=gender;
        document.getElementById("pbenname").value=name;
        document.getElementById("gurname").value=fathername;
        document.getElementById("seguid").value=guid;
	// next(1);
	}
	
	function saveKycData(){

   
   var requestUri='<%=requestUri%>';
   var authType=	document.getElementById("authType").value;
   
   var chekedRUFlag='<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
   var districtCode;
   var blockCode;
   var villageCode;
   var vtcCode;
   var wardCode;
   var villageName="";
   var districtName="";
   var stateName="";
   var blockName="";
   if(chekedRUFlag=='R'){
    state_code='<%=beneficiarySeccForm.getStateCodeRural()%>';
    districtCode='<%=beneficiarySeccForm.getDistrictCodeRural()%>';
    blockCode='<%=beneficiarySeccForm.getBlockCodeRural()%>';
    blockName='<%=beneficiarySeccForm.getBlockName()%>';
    villageCode='<%=beneficiarySeccForm.getVillageCodeRural()%>';
    villageName='<%=beneficiarySeccForm.getVillageName()%>';
    districtName='<%=beneficiarySeccForm.getDistrictName()%>';
    vtcCode=" ";
    wardCode=" ";
   }else if(chekedRUFlag=='U'){
    state_code='<%=beneficiarySeccForm.getStateCodeUrban()%>';
    districtCode='<%=beneficiarySeccForm.getDistrictCodeUrban()%>';
   	vtcCode='<%=beneficiarySeccForm.getTownCodeUrban()%>';
   	wardCode='<%=beneficiarySeccForm.getWardCodeUrban()%>';
  	districtName='<%=beneficiarySeccForm.getDistrictName()%>';
   	blockCode=" ";
   	villageCode=" ";
   }
   
    var stateName='<%=beneficiarySeccForm.getStateName()%>';
	
   	var name_s = 	document.getElementById("prkycname").innerHTML;
   	if(name_s=='')
   	name_s=' ';
   	
	var gender_s =  document.getElementById("prkycgender").innerHTML;
	
	if(gender_s=='')
   	gender_s=' ';
   	
	var dob_s	=    document.getElementById("prkycdob").innerHTML;
	dob_s =formatDate(dob_s);
	if(dob_s=='NaN-NaN-NaN')
	  dob_s=' ';
   	
    var address_s	=    document.getElementById("prkycaddress").innerHTML;
    if(address_s=='')
   	address_s=' ';
   	
    var father_name_s= document.getElementById("prkycfname").innerHTML;
    
     
    if(father_name_s=='')
    	father_name_s=' ';
   	
   	
    var state_name_s=document.getElementById("state_name_s").value;
    if(state_name_s=='')
   	state_name_s=' ';
   	
    var district_name_s=document.getElementById("district_name_s").value;
    
    if(district_name_s=='')
   	district_name_s=' ';
   	
    var vtc_s= document.getElementById("vtc_s").value;
    if(vtc_s=='')
   	vtc_s=' ';
    
    var post_s=document.getElementById("post_s").value;
    if(post_s=='')
   	post_s=' ';
	var photo_s	=    document.getElementById("pht").value;
	if(photo_s=='')
   	photo_s=' ';
	var uid_token	=    document.getElementById("uidtoken").value;
	if(uid_token=='')
   	uid_token=' ';
	var ruralUrbanFlag	=chekedRUFlag;    //document.getElementById("ruralUrbanFlag").value;
	var uid_aadhaar = document.getElementById("aadhar").value;
		uid_aadhaar=uid_aadhaar.replaceAll(" ", "");
	if(uid_aadhaar=='')
   		uid_aadhaar=' ';	
	var uid_auth_type= document.getElementById("uid_auth_type").value;
	if(uid_auth_type=='')
   		uid_auth_type=' ';
	var operationType="BIS2.0_GUID";
	
	var name_d = document.getElementById('demoName').value; 
	if(name_d=='')
   		name_d=' '; 
	var  father_name_d= document.getElementById('demoFname').value; 
	if(father_name_d=='')
   		father_name_d=' ';
	var gender_d = document.getElementById('demoGender').value;
	if(gender_d=='')
   		gender_d=' ';
		 
	var dob_d = document.getElementById('demoDob').value; 
	 dob_d =formatDate(dob_d);
	 if(dob_d=='')
   		dob_d=' ';
	 
	var mobile_member  = document.getElementById('demoMobile').value; 
	if(mobile_member=='')
   		mobile_member=' '; 
	var pin_code_d = document.getElementById('demoPincode').value;
	if(pin_code_d=='')
   		pin_code_d=' ';
	
	var verification_status = document.getElementById('verification_status').value;
	
	var refernceid ='<%=beneficiarySeccForm.getGuid()%>';
	var guid  ='<%=beneficiarySeccForm.getGuid()%>';
	
	var ahl_tin=' ';
	var ahl_hhid=' ';
	var sub_distCode=' ';
	var address_d=' ';
	var state_name_d=' ';
	var district_name_d=' ';
	var sub_dist_d=' ';
	var vtc_d=' ';
	var post_d=' ';
	
	
		var family_mem_id = document.getElementById("family_mem_id").value;
		var member_name_eng = document.getElementById("member_name_eng").value;
		var gender = document.getElementById("gender").value;
		var relation_name = document.getElementById("relation_name").value;
		var mother_name_eng =  document.getElementById("mother_name_eng").value;
		var father_name_eng = document.getElementById("father_name_eng").value;
		var spouse_name_eng =document.getElementById("spouse_name_eng").value;
		var year_of_birth = document.getElementById("year_of_birth").value;
		
		var state_lgd_code = document.getElementById("state_lgd_code").value;
		var district_lgd_code = document.getElementById("district_lgd_code").value;
		var subdistrict_lgd_code = document.getElementById("subdistrict_lgd_code").value;
		var village_town_lgd_code = document.getElementById("village_town_lgd_code").value;
		var pincode = document.getElementById("pincode").value;
		//var rural_urban = chekedRUFlag;
		var family_id =document.getElementById("rationCard").value;
	  	var relation=document.getElementById("relation").value;
		
	var peName=document.getElementById('pbenname').value;
	var peGurName=document.getElementById('gurname').value;
	var hhd_no=document.getElementById('hhdid').value;
	var peGuid =document.getElementById('seguid').value;
	
	var form = $('#fileUploadForm')[0];

    var formData = new FormData(form);
	
formData.append("docFile", docFile.files[0]);
formData.append("state_code", state_code);
formData.append("name_s", name_s);
formData.append("gender_s", gender_s);
formData.append("address_s", address_s);
formData.append("state_name_s", state_name_s);
formData.append("district_name_s", district_name_s);
formData.append("vtc_s", vtc_s);
formData.append("post_s", post_s);
formData.append("photo_s",photo_s);
formData.append("uid_token",uid_token);
formData.append("ruralUrbanFlag",ruralUrbanFlag);
formData.append("uid_aadhaar",uid_aadhaar);

formData.append("uid_auth_type",uid_auth_type);
formData.append("name_d",name_d);

formData.append("father_name_d",father_name_d);
formData.append("gender_d",gender_d);
formData.append("dob_d",dob_d);
formData.append("mobile_member",mobile_member);
formData.append("pin_code_d",pin_code_d);
//formData.append("refernceid",refernceid);
formData.append("guid",guid);
formData.append("stateName",stateName);
formData.append("districtName",districtName);
formData.append("verification_status",verification_status);
formData.append("father_name_s" , father_name_s);
formData.append("vtcCode",vtcCode);
formData.append("districtCode",districtCode);
formData.append("dob_s",dob_s);
formData.append("operationType",operationType);
formData.append("ahl_tin",ahl_tin);
formData.append("ahl_hhid",ahl_hhid);
formData.append("sub_distCode",sub_distCode);
formData.append("address_d",address_d);
formData.append("state_name_d",state_name_d);
formData.append("district_name_d",district_name_d);
formData.append("sub_dist_d",sub_dist_d);
formData.append("vtc_d",vtc_d);
formData.append("post_d",post_d);
//formData.append("authType",authType);

//formData.append("family_mem_id",family_mem_id);
//formData.append("member_name_eng",member_name_eng);
//formData.append("gender",gender);
//formData.append("relation_name",relation_name);
//formData.append("mother_name_eng",mother_name_eng);
//formData.append("father_name_eng",father_name_eng);
//formData.append("spouse_name_eng",spouse_name_eng);
//formData.append("year_of_birth",year_of_birth);

//formData.append("state_lgd_code",state_lgd_code);
//formData.append("district_lgd_code",district_lgd_code);
//formData.append("subdistrict_lgd_code",subdistrict_lgd_code);
//formData.append("village_town_lgd_code",village_town_lgd_code);
//formData.append("pincode",pincode);
//formData.append("rural_urban",rural_urban);
formData.append("family_id",family_id);
formData.append("blockCode",blockCode);
formData.append("villageCode",villageCode);
formData.append("wardCode",wardCode);
//formData.append("relation",relation);
//formData.append("peName",peName);
//formData.append("peGurName",peGurName);
formData.append("hhd_no",hhd_no);
//formData.append("peGuid",peGuid);

formData.append("villageName",villageName);
formData.append("blockName",blockName);
	

	onUpd();
	document.getElementById("finishButton").disabled = true;
	$.ajax({
        type: "POST",
		enctype: 'multipart/form-data',
        url: "/"+requestUri+"/updateFamilyDetails",
        data: formData,
		 processData: false,
         contentType: false,
        success: function(data){
       	console.log(data);
       	offUpd();
       	  var obj = jQuery.parseJSON( data );
            if(obj['status'] == "Y"){
            
            if(obj['pmjayID'] == "N")
             $('#infoUpdateKyc').html("New Beneficiary Member has been Successfully Added. Reference ID :"+obj['refId']);
            else
			 $('#infoUpdateKyc').html("New Beneficiary Member has been Successfully Added. Reference ID :"+obj['refId'] +" and PMJAY_ID :"+obj['pmjayID']);
             
             $('#errorUpdateKyc').hide('slow');	
             $('#infoUpdateKyc').show('slow');
			// $('#infoUpdateKyc').delay(150000).hide(150000);
           // window.location.href = "/"+requestUri+"/home";
		   document.getElementById("finishButton").disabled = true;
		//   document.getElementById("canFinalButon").style.display="none";
		   document.getElementById("backSearch").style.display="block";
			}else  if(obj['status'] == "A"){
			
				$('#infoUpdateKyc').html("Beneficiary Ayshman Card in progress.");
				$('#errorUpdateKyc').hide('slow');
				$('#infoUpdateKyc').show('slow');
				document.getElementById("finishButton").disabled = false;
			
			}else  if(obj['status'] == "P"){
				$('#infoUpdateKyc').html("Beneficiary Ayshman Card in progress.");
				$('#errorUpdateKyc').hide('slow');
				$('#infoUpdateKyc').show('slow');
				document.getElementById("finishButton").disabled = false;
			
			}else  if(obj['status'] == "R"){
				$('#errorUpdateKyc').html("Beneficiary Ayshman Card Rejected.");
				$('#infoUpdateKyc').hide('slow');
				$('#errorUpdateKyc').show('slow');
				document.getElementById("finishButton").disabled = false;	
			}else{
				$('#errorUpdateKyc').html("New Beneficiary updation failed.");
				$('#infoUpdateKyc').hide('slow');
				$('#errorUpdateKyc').show('slow');
				document.getElementById("finishButton").disabled = false;
                 
             }
         },
         error: function(e){
           if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
				
         }
    
    });
   
		
      
   
   }
	
	
	</script>
	
	
	<script>
	
	function selectDob(){

	var con=isFutureDate(document.getElementById("demoDob").value);
	if(con){
		alert("Date of Birth is not allowed future date.");
		document.getElementById("demoDob").value="";
	}else{
	document.getElementById("demoDob").value=document.getElementById("demoDob").value;
	}	
}

function isFutureDate(idate){
var today = new Date().getTime(),
    idate = idate.split("-");
	idate = new Date(idate[0], idate[1] - 1, idate[2]).getTime();
	return (today - idate) < 0 ? true : false;
}

function addCommas(e) {
    var tgt = e.target, val = tgt.value.replace(/,/g, ''),
        amt = Math.ceil(val.length/4), newStr = '', x = 0; 
    while ( x <= amt ) {
        newStr += val.slice(x*4,(x+1)*4);
        newStr += ( x < amt-1 ) ? ' ' : '';
        x++
    }
    tgt.value = newStr;
}
document.getElementById('aadhar').addEventListener('change', addCommas, false);



function checkFileExt(){
var allowedFiles = [ ".pdf",".PDF",".png",".PNG",".jpeg",".JPEG",".JPG",".jpg" ];
var fileUpload = $("#docFile");
var lblError = $("#lblError");
var lblSuccess = $("#lblSuccess");
var refresh;
var regex = new RegExp(
		"([a-zA-Z0-9\s_\\.\-:])+("
				+ allowedFiles
						.join('|')
				+ ")$");
if (!regex.test(fileUpload.val()
		.toLowerCase())) {
	lblError
			.html("Please upload files having extensions: <b>"
					+ allowedFiles
							.join(', ')
					+ "</b> only.");
	lblSuccess.html('');	
	lblSuccess.hide();
	lblError.show();
	$("#docFile").val('');
	return false;
}else{
	lblError.html('');
	lblError.hide();
}


	
}


</script>

<script  type="text/javascript">
  
  	function reGenFaceQrCode(){
    	var requestUri='<%=requestUri%>';
   		document.getElementById("face_id").src="/"+requestUri+"/faceId";
    }
	
	
	
function captureFace(){
		
   
   var requestUri='<%=requestUri%>';
   
   document.getElementById("optBtn").disabled = false;
   document.getElementById("countdown").innerHTML = "";
   
  	var aadhar =document.getElementById("aadhar").value;
	
	
	if(aadhar==""){
		alert("Please Enter Aadhaar Number.");
		document.getElementById("aadhar").focus();;
		return false;
	}
	
	 onKyc() ;
		$.ajax({
        type: "POST",
         url: "/"+requestUri+"/faceBasedKyc",
         data: {
       	  "aadhar": aadhar,
       	  "bioType":'P',
       	 },
        success: function(data){
       	 console.log(data);
       		offKyc() ;
       	  var obj = jQuery.parseJSON( data );
            if(obj['status'] == "Y"){
            	 $('#infoOtp').html("ekyc Successfully completed.");
                 $('#errorOtp').hide('slow');
                 $('#infoOtp').show('slow');
                 $('#kycName').html(obj['kycName']);
                 $('#kycGender').html(obj['kycGender']);
                $('#kycDob').html(obj['kycDob']);
				
				$('#kycName1').html(obj['kycName']);
                $('#kycGender1').html(obj['kycGender']);
                $('#kycDob1').html(obj['kycDob']);
                
				document.getElementById("kycDob1").value=obj['kycDob'];
				document.getElementById("kycName1").value=obj['kycName'];
				document.getElementById("kycFname1").value=obj['kycFname'];
				document.getElementById("kycGender1").value=obj['kycGender'];
				document.getElementById("kycAdr1").value=obj['kycAdr'];
				
				document.getElementById("phtId_1").src="data:image/jpeg;base64,"+obj['pht'];
				document.getElementById("face_id").src="data:image/jpeg;base64,"+obj['facePht'];
				 $('#kycFname1').html(obj['kycFname']);
                 $('#kycAdr1').html(obj['kycAdr']);
				
			
                document.getElementById("uidtoken").value=obj['uidToken'];
                document.getElementById("phtId").src="data:image/jpeg;base64,"+obj['pht'];
				document.getElementById("pht").value=obj['pht'];
                $('#kycFname').html(obj['kycFname']);
                $('#kycAdr').html(obj['kycAdr']);
                
                document.getElementById("vtc_s").value=obj['vtc_s'];
                document.getElementById("district_name_s").value=obj['district_name_s'];
                document.getElementById("state_name_s").value=obj['state_name_s'];
                document.getElementById("post_s").value=obj['post_s'];
                document.getElementById("uid_auth_type").value='3';
                document.getElementById("verification_status").value='C';
                
                 
			 
				 document.getElementById("faceIdCheck").style.display = "flex";
				 document.getElementById("faceIdCancel").style.display = "none";
				 document.getElementById("radioI").disabled = true; 
				 document.getElementById("radioF").disabled = true;  
				 document.getElementById("faceKycSeccess").value="Y";
				 document.getElementById("faceSeccess").value="Y";
				 
				 //document.getElementById("radioD").disabled = true; 
				
				document.getElementById("radioO").disabled = true; 
				
                document.getElementById("next2").disabled = false;
                document.getElementById("kycDetailsDiv").style.display="block";
              
				document.getElementById("next3").disabled = false;
				document.getElementById("finishButton").disabled = false; 
				//document.getElementById("Previous2").disabled = false; 
				//document.getElementById("Previous3").disabled = false; 
				document.getElementById("authType").value="K";
				document.getElementById("faceBtn").disabled = true; 
				 
				
				setDemoName();
				 $('#errorFaceKyc').hide('slow');
             }else{
				 reGenFaceQrCode();
				 //document.getElementById("faceBtn").disabled = false; 
	         	 $('#errorFaceKyc').html("Face Photo Capture Failed.");
                 $('#errorFaceKyc').show('slow');
                 
             }
         },
         error: function(e){
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
				
         }
    
    });
   
 
	}
  
  </script>
  



</body>

</html>