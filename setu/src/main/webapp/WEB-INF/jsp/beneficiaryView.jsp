<!DOCTYPE html>
<%@page import="com.gov.nha.bis.server.properties.CommonProperties" %>
  <%@page import="com.gov.nha.bis.server.properties.CommonProFunction" %>
    <%@ page contentType="text/html; charset=utf-8" language="java" %>
      <%@page import="com.gov.nha.bis.server.model.SessionLoginMap" %>
        <%@page import="org.springframework.util.ObjectUtils" %>
          <%@page import="com.gov.nha.bis.server.model.BeneficiarySeccForm" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
              <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

                <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                  <% 
                  CommonProFunction PROPERTIES=CommonProperties.commonProFunction;
                   String requestUri="" ;
                    requestUri=request.getRequestURI().split("/")[1];
                     String sessid="" ; 
                     sessid=session.getId();
                    BeneficiarySeccForm beneficiarySeccForm=null;
                     if(session.getAttribute("beneficiarySeccForm")!=null){
                        beneficiarySeccForm=(BeneficiarySeccForm)session.getAttribute("beneficiarySeccForm");
                   } 
                   String  cardStatus=null;
                    if(!ObjectUtils.isEmpty(beneficiarySeccForm)){
                      cardStatus=beneficiarySeccForm.getCard_status();
                   } 
                   String userId="" ;
                    if(session.getAttribute("USERID")!=null){ 
                      userId=(String)session.getAttribute("USERID");
                   }
                    SessionLoginMap sessionUser=new SessionLoginMap();
                     if(session.getAttribute("sessionUser")!=null){
                    sessionUser=(SessionLoginMap)session.getAttribute("sessionUser"); 
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
                      <link
                        href="https://fonts.googleapis.com/css2?family=Sora:wght@100;200;300;400;500;600;700;800&display=swap"
                        rel="stylesheet">

                      <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
                      <link rel="stylesheet" href="sass/bootstrap.min.css">
                      <link rel="stylesheet" href="sass/setu-custom.css">
                      <link rel="stylesheet" href="css/jquery.dataTables.min.css">
                      <link rel="stylesheet" href="css/scroller.dataTables.min.css">
                      <style>           

                       
                        .input-ration-wrap {
                          position: relative;
                        }

                        .input-ration-wrap button {
                          position: absolute;
                          bottom: 3px;
                          width: 88px;
                          right: 12px;
                        }

                        .ration-card-wrap {
                          background: #f9f7f7;
                          border: 1px solid #ededed;
                          padding: 10px;
                        }
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
                                <section class="SECC-data mb-4">
                                  <div class="container">
                                    <form:form action="" method="POST" id="registration" commandName="command">
                                      <div class="px-1 px-md-3">
                                        <div class="col-12">
                                          <h2>Link Your AADHAAR </h2>
                                          <div class="beneficiary-view-tabs mb-2">
                                            <ul class="nav nav-pills" id="pills-tab" role="tablist">
                                              <li class="nav-item" role="presentation">
                                                <button class="nav-link active" id="pills-beneficiary-tab"
                                                  data-bs-toggle="pill" data-bs-target="#pills-beneficiary"
                                                  type="button" role="tab" aria-controls="pills-beneficiary"
                                                  aria-selected="true">Beneficiary Details</button>
                                              </li>
                                              <li class="nav-item" role="presentation">
                                                <button class="nav-link" disabled id="pills-yourdetails-tab"
                                                  data-bs-toggle="pill" data-bs-target="#pills-yourdetails"
                                                  type="button" role="tab" aria-controls="pills-yourdetails"
                                                  aria-selected="false">Enter your Details</button>
                                              </li>
                                              <li class="nav-item" role="presentation">
                                                <button class="nav-link" disabled id="pills-ekyc-tab"
                                                  data-bs-toggle="pill" data-bs-target="#pills-ekyc" type="button"
                                                  role="tab" aria-controls="pills-ekyc" aria-selected="false">eKYC
                                                  Details</button>
                                              </li>
                                              <li class="nav-item" role="presentation">
                                                <button class="nav-link" disabled id="pills-review-tab"
                                                  data-bs-toggle="pill" data-bs-target="#pills-review" type="button"
                                                  role="tab" aria-controls="pills-review"
                                                  aria-selected="false">Review</button>
                                              </li>
                                            </ul>
                                            <div class="tab-content tab-content-view" id="pills-tabContent">
                                              <div class="tab-pane fade show active" id="pills-beneficiary"
                                                role="tabpanel" aria-labelledby="pills-beneficiary-tab">
                                                <div class="tab-card-in">
                                                  <div class="w-100 mb-3" id="completed" style="display:none">
                                                    <button type="button" class="btn btn-success toastsDefaultSuccess">
                                                      Beneficiary Card has been generated. Please click for download
                                                      card.

                                                    </button>
                                                  </div>

                                                  <div class="w-100 mb-3" id="inprogress" style="display:none">
                                                    <button type="button" class="btn btn-warning toastsDefaultWarning"
                                                      disabled>
                                                      Beneficiary Card generation In progress.
                                                    </button>
                                                  </div>

                                                  <div class="w-100 mb-3" id="rejected" style="display:none">
                                                    <button type="button" class="btn btn-danger toastsDefaultDanger"
                                                      disabled>
                                                      Beneficiary Card Rejected.
                                                    </button>
                                                  </div>
                                                  <h3>Beneficiary Details as in SECC Data</h3>
                                                  <div class="row mx-0">
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Name: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getName() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Guardian's Name: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getFathername() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Mother's Name:</span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getMothername()%>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Gender: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getGenderid()%>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>YOB: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getDob() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>State Name: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getStateName() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>District: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getDistrictName() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Village/Town: </span>
                                                      <%
                                                        if(ObjectUtils.isEmpty(beneficiarySeccForm.getVillageName())){%>
                                                        <strong>
                                                          <%=beneficiarySeccForm.getTownName() %>
                                                        </strong>
                                                        <%}else{ %>
                                                          <strong>
                                                            <%=beneficiarySeccForm.getVillageName() %>
                                                          </strong>
                                                          <%} %>
                                                    </div>
                                                  </div>
                                                  <div id="rationCardEnable" style="display:none">
                                                    <div class="row">

                                                      <div
                                                        class="col-12 col-sm-4 col-md-3 my-1 custom-form-group form-group">
                                                        <label for="inputDob" class="form-label">Enter Your Ration Card
                                                          No.</label>
                                                        <form:input path="rationCard" id="rationCard"
                                                          class="form-control" />
                                                      </div>
                                                      <div
                                                        class="col-12 col-sm-4 col-md-3 my-1 custom-form-group form-group">
                                                        <button type="button" class="btn btn-sm btn-dark"
                                                          onclick="getRationCardDetail()">Get Details</button>
                                                      </div>
                                                    </div>
                                                    <div class="text-center mt-2">

                                                      <div id="rationError" style="color:#f00;" class="app-title"></div>
                                                      <div id="rationInfo" style="color:green;" class="app-title"></div>
  
                                                    </div>
                                                    <div class="card mt-4 beneficiary-section-table" id="rationDiv"
                                                      style="display:none">
                                                      <div class="card-content p-2">
                                                        <div class="table-responsive table-ration">
                                                          <table id="example1"
                                                            class="table table-striped dataTable display nowrap"
                                                            style="width:100%">
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


                                                          </table>
                                                        </div>
                                                      </div>
                                                    </div>

                                                  </div>
                                                  
                                                  <div class="text-center mt-3" id="canFinalButon">
                                                    <button type="button" class="btn btn-sm btn-white"
                                                      onclick="goHome()">Cancel</button>
                                                    <button type="button" id="next1" class="btn btn-sm btn-dark"
                                                      onclick="openTab2()">Next</button>
                                                  </div>
                                                </div>
                                              </div>
                                              <div class="tab-pane fade" id="pills-yourdetails" role="tabpanel"
                                                aria-labelledby="pills-yourdetails-tab">
                                                <div class="tab-card-in">
                                                  <h3>Enter Your Details (As Per Aadhaar)</h3>

                                                  <div class="row mt-4">
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                                      <div class="custom-form-group form-group">
                                                        <label for="">Name</label>
                                                        <form:input path="demoName" id="demoName" type="text"
                                                          class="form-control" onchange="changeAadhhar()" />
                                                      </div>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                                      <div class="custom-form-group form-group">
                                                        <label for="">Guardian's Name</label>
                                                        <form:input path="demoFname" id="demoFname" class="form-control"
                                                          onchange="changeAadhhar()" />
                                                      </div>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                                      <div class="custom-form-group form-group">
                                                        <label for="">Gender</label>
                                                        <form:select path="demoGender" class="form-control"
                                                          onchange="changeAadhhar()">
                                                          <option value="">Select</option>
                                                          <option value="M">Male</option>
                                                          <option value="F">Female</option>
                                                          <option value="T">Transgender</option>
                                                        </form:select>
                                                      </div>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                                      <div class="custom-form-group form-group">
                                                        <label for="">DOB</label>
                                                        <input type="date" placeholder="DD-MM-YYYY" id="selectDoa"
                                                          class="form-control" onchange="selectDob()">
                                                      </div>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                                      <div class="custom-form-group form-group">
                                                        <label for="">Mobile Number</label>
                                                        <form:input path="demoMobile" id="demoMobile"
                                                          class="form-control" onkeypress="return isNumberKey(event)"
                                                          maxlength="10" />
                                                      </div>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                                      <div class="custom-form-group form-group">
                                                        <label for="">Pincode</label>
                                                        <form:input path="demoEmail" id="demoEmail" class="form-control"
                                                          onkeypress="return isNumberKey(event)" maxlength="6" />
                                                      </div>
                                                    </div>
                                                    <div class="col-12 col-sm-12 col-md-4 col-lg-4">
                                                      <div class="custom-form-group form-group">
                                                        <!--  <font color="RED"><span id="adhar_required"></span></font> -->
                                                        <label for="">Enter Aadhaar for Verification</label>
                                                        <form:input path="aadhar" class="form-control" type="text"
                                                          id="aadhar" placeholder="Aadhaar Number " maxlength="12"
                                                          onkeypress="return isNumberKey(event)"
                                                          onchange="validateAadhar()" />
                                                      </div>
                                                    </div>

                                                    <div class="col-12 col-sm-6 col-md-5 col-lg-5">
                                                      <div class="custom-form-group form-group">
                                                        <label for="">How Would You like to Authenticate</label>

                                                        <div
                                                          class="nav bg-white mb-1 custom-radio-otp justify-content-between radio-group"
                                                          id="pills-tab" role="tablist">
                                                          <div id="demographic-home-tab" class="form-radio" >
                                                            <input type="radio" id="radioD" name="ekyc-new" value="D"
                                                              onclick="onOpenAction()" checked role="presentation" data-bs-toggle="pill"
                                                              data-bs-target="#demographic-home" type="button" role="tab"
                                                              aria-controls="demographic-home" aria-selected="true"><label  for="radioD">Demo</label>
                                                          </div>
                                                          <div id="otp-home-tab" class="form-radio" >
                                                            <input type="radio" id="radioO" name="ekyc-new" value="O"
                                                              onclick="onOpenAction()" role="presentation" 
                                                              data-bs-toggle="pill" data-bs-target="#otp-home"
                                                              type="button" role="tab" aria-controls="otp-home"
                                                              aria-selected="false"><label for="radioO">OTP</label>
                                                          </div>
                                                          <div id="finger-home-tab" class="form-radio" >
                                                            <input type="radio" id="radioF" name="ekyc-new" value="F"
                                                              onclick="onOpenAction()" role="presentation"
                                                              data-bs-toggle="pill"
                                                             data-bs-target="#finger-home" type="button" role="tab"
                                                             aria-controls="finger-home" aria-selected="false"><label
                                                              for="radioF">Finger</label>
                                                          </div>
                                                          <div id="iris-home-tab" class="form-radio" >
                                                            <input type="radio" id="radioI" name="ekyc-new" value="I"
                                                              onclick="onOpenAction()" role="presentation" 
                                                              data-bs-toggle="pill" data-bs-target="#iris-home"
                                                              type="button" role="tab" aria-controls="iris-home"
                                                              aria-selected="false"><label for="radioI">IRIS</label>
                                                          </div>
                                                          <div id="face-home-tab" class="form-radio" >
                                                            <input type="radio" id="radioP" name="ekyc-new" value="P"
                                                              onclick="onOpenAction()" role="presentation" 
                                                              data-bs-toggle="pill" data-bs-target="#face-home"
                                                              type="button" role="tab" aria-controls="face-home"
                                                              aria-selected="false"><label for="radioP">Face</label>
                                                          </div>
                                                        </div>
                                                        <div class="tab-content mt-4" id="pills-tabContent">
                                                          <div class="authenticate-wrap tab-pane fade show active"
                                                            id="demographic-home" role="tabpanel"
                                                            aria-labelledby="demographic-home-tab">
                                                            <div id="demographicotp">
                                                              <button type="button" class="btn custom-btn-sm btn-dark"
                                                                id="demoAuthButton" onclick="demoAuth()">Demo
                                                                Auth</button>
                                                            </div>
                                                          </div>
                                                          <div class="authenticate-wrap tab-pane fade " id="otp-home"
                                                            role="tabpanel" aria-labelledby="otp-home-tab">
                                                            <div>
                                                              <h5>OTP eKYC</h5>
                                                              <button type="button" id="optBtn"
                                                                class="btn custom-btn-sm btn-dark"
                                                                onclick="getOtp()">Get OTP</button>
                                                              <div class="row mt-2" id="otpnew">
                                                                <div class="col-md-6 my-2 mx-auto" id="entOtp"
                                                                  style="position: relative;">
                                                                  <label for="inputgender" class="form-label">Enter Your
                                                                    OTP</label>
                                                                  <form:input path="otp" class="form-control" id="otp"
                                                                    maxlength="6" size="6" />
                                                                </div>
                                                              </div>
                                                            </div>
                                                          </div>
                                                          <div class="authenticate-wrap tab-pane fade" id="finger-home"
                                                            role="tabpanel" aria-labelledby="finger-home-tab">
                                                            <div id="fingersprints">
                                                              <h5>Biometric (FingerPrints) eKYC</h5>
                                                              <button type="button" class="btn custom-btn-sm btn-dark"
                                                                onclick="captureBio('F')">Scan Your Finger</button>
                                                              <span id="figerIdCapture" style="display:none"> <a
                                                                  href="#"><img src="images/checked.jpg" alt=""
                                                                    width="50" height="50" />
                                                                </a></span>
                                                            </div>
                                                          </div>
                                                          <div class="authenticate-wrap tab-pane fade" id="iris-home"
                                                            role="tabpanel" aria-labelledby="iris-home-tab">
                                                            <div id="irisprints">
                                                              <h5>Biometric(Iris) eKYC</h5>
                                                              <button class="btn custom-btn-sm btn-dark" type="button"
                                                                onclick="captureBio('I')">Capture Your
                                                                Biometric</button>
                                                              <span id="irisIdCapture" style="display:none"> <a
                                                                  href="#"><img src="images/checked.jpg" alt=""
                                                                    width="50" height="50" />
                                                                </a></span>
                                                            </div>
                                                          </div>

                                                          <div class="authenticate-wrap tab-pane fade" id="face-home"
                                                            role="tabpanel" aria-labelledby="face-home-tab">
                                                            <div id="faceprints">
                                                              <h5>Face eKYC</h5>
                                                              <img id="face_id" name="face_id" alt="Face QR Code"
                                                                src="/<%=requestUri%>/faceId" width="100" height="100">
                                                              <button class="btn custom-btn-sm btn-dark" id="faceBtn"
                                                                type="button" onclick="captureFace()">Get Face
                                                                Photo</button>
                                                              <span id="irisIdCapture" style="display:none"> <a
                                                                  href="#"><img src="images/checked.jpg" alt=""
                                                                    width="50" height="50" />
                                                                </a></span>
                                                            </div>
                                                          </div>

                                                        </div>
                                                      </div>
                                                    </div>

                                                    <div class="col-12 col-sm-6 col-md-3 col-lg-2">
                                                      <div id="scanhide" style="display: none;">
                                                        <button class="btn custom-btn-sm btn-dark" type="button"
                                                          id="reConnect" onclick="portscanView()">Scan Devices</button>
                                                      </div>
                                                    </div>

                                                  </div>

                                                  <div class="text-center mt-2">
                                                    <div id="errorFaceKyc" style="color:#f00;" class="app-title"></div>
                                                    <div id="error3" style="color:#f00;" class="app-title"></div>
                                                    <div id="info3" style="color:green;" class="app-title"></div>
                                                    <div id="countdown" style="color:green;" class="app-title"></div>
                                                    <div id="errorOtp" style="color:#f00;" class="app-title"></div>
                                                    <div id="infoOtp" style="color:green;" class="app-title"></div>
                                                    <div id="errorKyc" style="color:#f00;" class="app-title"></div>
                                                    <div id="infoKyc" style="color:green;" class="app-title"></div>
                                                    <div id="adhar_required" style="color:#f00;" class="app-title">
                                                    </div>

                                                  </div>

                                                  <div class="text-center mt-3">
                                                    <button type="button" class="btn btn-sm btn-white"
                                                      onclick="openTab1()">Previous</button>
                                                    <button type="button" class="btn btn-sm btn-dark" id="next2"
                                                      onclick="openTab33()">Next</button>
                                                  </div>
                                                </div>
                                              </div>
                                              <div class="tab-pane fade" id="pills-ekyc" role="tabpanel"
                                                aria-labelledby="pills-ekyc-tab">
                                                <div class="tab-card-in">
                                                  <h3>eKYC Details </h3>
                                                  <div class="row mt-2">
                                                    <div class="col-12 col-sm-4 col-md-3 col-lg-2">
                                                      <img id="phtId" src="images/user-profile-l.jpg"
                                                        class="img-thumbnail rounded mx-auto d-block" alt="">
                                                    </div>
                                                    <div class="col-12 col-sm-8 col-md-9 col-lg-10">
                                                      <div class="ekyc-details-table">
                                                        <span>Name:</span>
                                                        <strong id="kycName"></strong>
                                                      </div>
                                                      <div class="ekyc-details-table">
                                                        <span>Guardian's Name:</span>
                                                        <strong id="kycFname"></strong>
                                                      </div>
                                                      <div class="ekyc-details-table">
                                                        <span>Gender:</span>
                                                        <strong id="kycGender"></strong>
                                                      </div>
                                                      <div class="ekyc-details-table">
                                                        <span>DOB:</span>
                                                        <strong id="kycDob"></strong>
                                                      </div>
                                                      <div class="ekyc-details-table">
                                                        <span>Address:</span>
                                                        <strong id="kycAdr"></strong>
                                                      </div>
                                                    </div>

                                                  </div>

                                                  <div class="text-center mt-3">
                                                    <button type="button" class="btn btn-sm btn-white" id="Previous2"
                                                      onclick="openTab2()">Previous</button>
                                                    <button type="button" class="btn btn-sm btn-dark" id="next3"
                                                      onclick="openTab4()">Next</button>
                                                  </div>
                                                </div>
                                              </div>
                                              <div class="tab-pane fade" id="pills-review" role="tabpanel"
                                                aria-labelledby="pills-review-tab">
                                                <div class="tab-card-in">
                                                  <h3>Beneficiary Details as in SECC Data </h3>
                                                  <div class="row mx-0 mb-4">
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Name: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getName() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Guardian's Name: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getFathername() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Mother's Name:</span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getMothername()%>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Gender: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getGenderid()%>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>YOB: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getDob() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>State Name: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getStateName() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>District: </span>
                                                      <strong>
                                                        <%=beneficiarySeccForm.getDistrictName() %>
                                                      </strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Village/Town: </span>
                                                      <%
                                                        if(ObjectUtils.isEmpty(beneficiarySeccForm.getVillageName())){%>
                                                        <strong id="towanSpam1">
                                                          <%=beneficiarySeccForm.getTownName() %>
                                                        </strong>
                                                        <%}else{ %>
                                                          <strong id="towanSpam1">
                                                            <%=beneficiarySeccForm.getTownName() %>
                                                          </strong>
                                                          <%} %>
                                                    </div>
                                                  </div>
                                                  <!-- Add by old design-->
                                                  <span id="rationCardEnable1" style="display:none">
                                                    <div class="row">
                                                      <div class="w-100">
                                                        <h3><b>RATION CARD DETAILS BY BENEFICIARY</b></h3>

                                                      </div>
                                                     
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
                                                  <br>

                                                  <h3>Declared Details by Beneficiary </h3>
                                                  <div class="row mx-0 mb-4">
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Name: </span>
                                                      <strong id="edemoName"></strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Guardian's Name: </span>
                                                      <strong id="edemoFname"></strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Gender: </span>
                                                      <strong id="edemoGender"></strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>DOB: </span>
                                                      <strong id="edemoDob"></strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Mobile Number: </span>
                                                      <strong id="edemoMobile"></strong>
                                                    </div>
                                                    <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                                                      <span>Pincode: </span>
                                                      <strong id="edemoEmail"></strong>
                                                    </div>
                                                  </div>

                                                  <h3>eKYC Details as in Aadhaar</h3>
                                                  <div class="row mb-4" id="kycview" style="display:none;">
                                                    <div class="col-12 col-sm-4 col-md-3 col-lg-2">
                                                      <img id="phtId_1" src="images/user-profile-l.jpg"
                                                        class="img-thumbnail rounded mx-auto d-block" alt="">
                                                    </div>
                                                    <div class="col-12 col-sm-8 col-md-9 col-lg-10">
                                                      <div class="ekyc-details-table">
                                                        <span>Name:</span>
                                                        <strong id="kycName1"></strong>
                                                      </div>
                                                      <div class="ekyc-details-table">
                                                        <span>Guardian's Name:</span>
                                                        <strong id="kycFname1"></strong>
                                                      </div>
                                                      <div class="ekyc-details-table">
                                                        <span>Gender:</span>
                                                        <strong id="kycGender1"></strong>
                                                      </div>
                                                      <div class="ekyc-details-table">
                                                        <span>DOB:</span>
                                                        <strong id="kycDob1"></strong>
                                                      </div>
                                                      <div class="ekyc-details-table">
                                                        <span>Address:</span>
                                                        <strong id="kycAdr1"></strong>
                                                      </div>
                                                    </div>

                                                  </div>
                                                  <h3>AUTH/e-KYC COMPLETED BY</h3>
                                                  <div class="row mx-0 justify-content-center">
                                                    <div
                                                      class="col-6 col-sm-3 col-md-2 col-lg-2 tab-card-in-seccdata auth-tabs">
                                                      <span>Demographic </span>
                                                      <strong id="demoIdCheck" style="display:none"><i
                                                          class="fas fa-check text-success"></i></strong>
                                                      <strong id="demoIdCancel" style="display:block"><i
                                                          class="fas fa-times text-danger"></i></strong>
                                                    </div>
                                                    <div
                                                      class="col-6 col-sm-3 col-md-2 col-lg-2 tab-card-in-seccdata auth-tabs">
                                                      <span>OTP </span>
                                                      <strong id="otpIdCheck" style="display:none"><i
                                                          class="fas fa-check text-success"></i></strong>
                                                      <strong id="otpIdCancel" style="display:block"><i
                                                          class="fas fa-times text-danger"></i></strong>
                                                    </div>

                                                    <div
                                                      class="col-6 col-sm-3 col-md-2 col-lg-2 tab-card-in-seccdata auth-tabs">
                                                      <span>Biometric (FingerPrint) </span>
                                                      <strong id="fingerIdCheck" style="display:none"><i
                                                          class="fas fa-check text-success"></i></strong>
                                                      <strong id="fingerIdCancel" style="display:block"><i
                                                          class="fas fa-times text-danger"></i></strong>
                                                    </div>
                                                    <div
                                                      class="col-6 col-sm-3 col-md-2 col-lg-2 tab-card-in-seccdata auth-tabs">
                                                      <span>Biometric (Iris)</span>
                                                      <strong id="irisIdCheck" style="display:none"><i
                                                          class="fas fa-check text-success"></i></strong>
                                                      <strong id="irisIdCancel" style="display:block"><i
                                                          class="fas fa-times text-danger"></i></strong>
                                                    </div>
                                                    <div
                                                      class="col-6 col-sm-3 col-md-2 col-lg-2 tab-card-in-seccdata auth-tabs">
                                                      <span>Biometric(Face)</span>
                                                      <strong id="faceIdCheck" style="display:none"><i
                                                          class="fas fa-check text-success"></i></strong>
                                                      <strong id="faceIdCancel" style="display:block"><i
                                                          class="fas fa-times text-danger"></i></strong>
                                                    </div>
                                                  </div>



                                                  <div class="row my-3">
                                                    <div>
                                                      <input type="checkbox" id="checkDeclare" name="checkDeclare"
                                                        value="1"> I hereby declare that the details furnished above are
                                                      true and correct to the best of my knowledge and belief . In case
                                                      any of the above information is found to be false or untrue or
                                                      misleading or misrepresenting, I am aware that I may be held
                                                      liable for it.
                                                    </div>
                                                  </div>
                                                  <div class="row my-3">
                                                    <div>
                                                      <div id="errorUpdateKyc" style="color: red;" class="app-title">
                                                      </div>
                                                      <div id="infoUpdateKyc" style="color: green;" class="app-title">
                                                      </div>
                                                    </div>
                                                  </div>
                                                  <div class="text-center mt-4">
                                                    <button type="button" class="btn btn-sm btn-white" id="Previous3"
                                                      onclick="openTab3()">Previous</button>
                                                    <button type="button" class="btn btn-sm btn-white" id="finishButton"
                                                      onclick="validateDataWithFuzzyMatcher('K')">Finish</button>
                                                    <div style="display: inline-block;" id="canFinalButon1">
                                                      <button type="button" class="btn btn-sm btn-dark"
                                                        onclick="goHome()">Cancel</button>
                                                    </div>
                                                    <button type="button" class="btn btn-block btn-dark" id="backSearch"
                                                      style="display:none" onclick="goHome()">Back Search</button>
                                                  </div>
                                                </div>
                                              </div>
                                            </div>
                                          </div>

                                        </div>
                                      </div>
                                      <form:hidden path="ruralUrbanFlag" id="ruralUrbanFlag" />
                                      <form:hidden path="otpTxn" id="otpTxn" />
                                      <form:hidden path="uidtoken" id="uidtoken" />

                                      <form:hidden path="refernceid" id="refernceid" />
                                      <form:hidden path="stateName" id="stateName" />
                                      <form:hidden path="districtName" id="districtName" />
                                      <form:hidden path="pht" id="pht" />
                                      <form:hidden path="pidData" id="pidData" />
                                      <form:hidden path="authType" id="authType" />
                                      <form:hidden path="demoDob" id="demoDob" />


                                      <form:hidden path="family_mem_id" id="family_mem_id" />
                                      <form:hidden path="member_name_eng" id="member_name_eng" />
                                      <form:hidden path="mother_name_eng" id="mother_name_eng" />
                                      <form:hidden path="father_name_eng" id="father_name_eng" />
                                      <form:hidden path="spouse_name_eng" id="spouse_name_eng" />
                                      <form:hidden path="year_of_birth" id="year_of_birth" />
                                      <form:hidden path="relation_name" id="relation_name" />
                                      <form:hidden path="gender" id="gender" />
                                      <form:hidden path="state_lgd_code" id="state_lgd_code" />
                                      <form:hidden path="district_lgd_code" id="district_lgd_code" />
                                      <form:hidden path="subdistrict_lgd_code" id="subdistrict_lgd_code" />
                                      <form:hidden path="village_town_lgd_code" id="village_town_lgd_code" />
                                      <form:hidden path="pincode" id="pincode" />
                                      <form:hidden path="rural_urban" id="rural_urban" />


                                      <input type="hidden" id="demoAuthSeccess">
                                      <input type="hidden" id="otpSeccess">
                                      <input type="hidden" id="otpKycSeccess">

                                      <input type="hidden" id="fingerSeccess">
                                      <input type="hidden" id="fingerKycSeccess">

                                      <input type="hidden" id="irisSeccess">
                                      <input type="hidden" id="irisKycSeccess">
                                      <input type="hidden" id="faceSeccess">
                                      <input type="hidden" id="faceKycSeccess">
                                      <input type="hidden" id="state_name_s">
                                      <input type="hidden" id="district_name_s">
                                      <input type="hidden" id="vtc_s">
                                      <input type="hidden" id="post_s">
                                      <input type="hidden" id="uid_auth_type">

                                      <input type="hidden" id="verification_status">



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
                                          <span>Fetch eKyc...</span>
                                          </div>
                                      </div>

                                      <div id="overlayDemo" class="overlay_full" onclick="offDemo()">
                                        <div class="fancy-spinner">
                                          <div class="ring"></div>
                                          <div class="ring"></div>
                                          <div class="dot"></div>
                                          <span>Authenticatation...</span>
                                          </div>  
                                      </div>
                                      <div id="overlayOtp" class="overlay_full" onclick="offOtp()">
                                        <div class="fancy-spinner">
                                          <div class="ring"></div>
                                          <div class="ring"></div>
                                          <div class="dot"></div>
                                          <span>Send OTP...</span>
                                          </div>
                                      </div>


                                      <div id="overlayUpd" class="overlay_full" onclick="offUpd()">
                                        <div class="fancy-spinner">
                                          <div class="ring"></div>
                                          <div class="ring"></div>
                                          <div class="dot"></div>
                                          <span>Updating...</span>
                                          </div>
                                        </div>


                                      <div id="overlayRation" class="overlay_full" onclick="offRation()">
                                        <div class="fancy-spinner">
                                          <div class="ring"></div>
                                          <div class="ring"></div>
                                          <div class="dot"></div>
                                          <span>Fetching...</span>
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
                                    </form:form>
                                  </div>
                                </section>
                                
                              </main>
                              <%@ include file="footer.jsp" %>
                            </div>
                          </main>

                        </div>
                      </div>

                      
                      <script src="js/Verhoeff.js"></script>
                      <script src="js/biometric.js"></script>
                      <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
                      <script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
                      <script type="text/javascript" src="js/dataTables.bootstrap5.min.js"></script>
                      <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
                      <script type="text/javascript" language="javascript" src="js/Verhoeff.js"></script>
                      <script src="js/biometricFamily.js"></script>
                      
                      <script src="js/dataTables.scroller.min.js"></script>

                      <script type="text/javascript">
                        var requestUri = '<%=requestUri%>';

                        function onOpenAction() {
						 $('#secc-collapse').addClass('show');
	 					 $('#nfsa-collapse').removeClass('show');
	 					 $('#aassli').removeClass('active');
						 $('#adacli').addClass('active');
                          var rationStateArray = '<%=PROPERTIES.RATION_CARD_SERVICE_ENABLE_LIST%>';

                          var cardStatus = '<%=cardStatus%>';
                          if (cardStatus == "Complete" || cardStatus == "In progress" || cardStatus == "Rejected") {
                            document.getElementById("radioD").disabled = true;
                            document.getElementById("radioO").disabled = true;
                            document.getElementById("radioF").disabled = true;
                            document.getElementById("radioI").disabled = true;
                            document.getElementById("radioP").disabled = true;                            
                            document.getElementById("demoAuthButton").disabled = true;
                            document.getElementById("finishButton").disabled = true;

                            document.getElementById("demoName").disabled = true;

                            document.getElementById("demoFname").disabled = true;

                            document.getElementById("demoGender").disabled = true;
                            document.getElementById("demoDob").disabled = true;
                            document.getElementById("demoMobile").disabled = true;
                            document.getElementById("demoEmail").disabled = true;
                            document.getElementById("aadhar").disabled = true;
                            document.getElementById("next2").disabled = true;

                            if (cardStatus == "Completed") {
                              document.getElementById("completed").style.display = "block";
                            } else if (cardStatus == "In progress") {
                              document.getElementById("inprogress").style.display = "block";
                            } else if (cardStatus == "Rejected") {
                              document.getElementById("rejected").style.display = "block";
                            }

                          }
                          document.getElementById("next1").disabled = true;
                          document.getElementById("next2").disabled = true;
                          document.getElementById("next3").disabled = true;
                          document.getElementById("finishButton").disabled = true;
                          document.getElementById("Previous2").disabled = true;
                          document.getElementById("Previous3").disabled = true;
                          document.getElementById("entOtp").style.display = "none";

                          var chekedRUFlag = '<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
                          var state_code;
                          var vtcCode;
                          if (chekedRUFlag == 'R') {
                            state_code = '<%=beneficiarySeccForm.getStateCodeRural()%>';

                          } else if (chekedRUFlag == 'U') {
                            state_code = '<%=beneficiarySeccForm.getStateCodeUrban()%>';
                          }
                          if (rationStateArray.split(",").includes(state_code)) {//vinay
                            document.getElementById("rationCardEnable").style.display = "block";
                            document.getElementById("rationCardEnable1").style.display = "block";

                          } else {
                            document.getElementById("next1").disabled = false;
                          }

                          if (document.getElementById("radioD").checked) {
                            $('#radioD').addClass('active');
                            $('#radioO').removeClass('active');
                            $('#radioF').removeClass('active');
                            $('#radioI').removeClass('active');
                            $('#radioP').removeClass('active');
                            document.getElementById("otpnew").style.display = "none";
                            document.getElementById("fingersprints").style.display = "none";
                            document.getElementById("irisprints").style.display = "none";
                            document.getElementById("scanhide").style.display = "none";
                            document.getElementById("faceprints").style.display = "none";
                            document.getElementById("demographicotp").style.display = "block";

                          }
                          else if (document.getElementById("radioO").checked) {
                            $('#radioO').addClass('active');
                            $('#radioD').removeClass('active');
                            $('#radioF').removeClass('active');
                            $('#radioI').removeClass('active');
                            $('#radioP').removeClass('active');
                            document.getElementById("irisprints").style.display = "none";
                            document.getElementById("demographicotp").style.display = "none";
                            document.getElementById("fingersprints").style.display = "none";
                            document.getElementById("faceprints").style.display = "none";
                            document.getElementById("scanhide").style.display = "none";
                            document.getElementById("otpnew").style.display = "block";

                          } else if (document.getElementById("radioF").checked) {
                            $('#radioF').addClass('active');
                            $('#radioD').removeClass('active');
                            $('#radioO').removeClass('active');
                            $('#radioI').removeClass('active');
                            $('#radioP').removeClass('active');
                            portscanView();
                            document.getElementById("irisprints").style.display = "none";
                            document.getElementById("demographicotp").style.display = "none";
                            document.getElementById("otpnew").style.display = "none";
                            document.getElementById("faceprints").style.display = "none";
                            document.getElementById("scanhide").style.display = "block";
                            document.getElementById("fingersprints").style.display = "block";

                          } else if (document.getElementById("radioI").checked) {
                            $('#radioI').addClass('active');
                            $('#radioD').removeClass('active');
                            $('#radioO').removeClass('active');
                            $('#radioF').removeClass('active');
                            $('#radioP').removeClass('active');
                            portscanView();
                            document.getElementById("otpnew").style.display = "none";
                            document.getElementById("demographicotp").style.display = "none";
                            document.getElementById("fingersprints").style.display = "none";
                            document.getElementById("faceprints").style.display = "none";
                            document.getElementById("scanhide").style.display = "block";
                            document.getElementById("irisprints").style.display = "block";

                          } else if (document.getElementById("radioP").checked) {
                            $('#radioP').addClass('active');
                            $('#radioD').removeClass('active');
                            $('#radioO').removeClass('active');
                            $('#radioF').removeClass('active');
                            $('#radioI').removeClass('active');
                            //portscanView();
                            document.getElementById("otpnew").style.display = "none";
                            document.getElementById("demographicotp").style.display = "none";
                            document.getElementById("fingersprints").style.display = "none";
                            document.getElementById("faceprints").style.display = "block";
                            document.getElementById("scanhide").style.display = "none";
                            document.getElementById("irisprints").style.display = "none";

                          }

                        }

                        function changeAadhhar() {
                          document.getElementById('aadhar').value = "";
                        }

                        function validateDataWithFuzzyMatcher(type) {
                          var nameWith;
                          var fatherWith;
                          var genderWith;

                          var name_secc = '<%=beneficiarySeccForm.getName() %>';
                          var father_name_secc = '<%=beneficiarySeccForm.getFathername() %>';
                          var gender_secc = '<%=beneficiarySeccForm.getGenderid()%>';

                          if (type == 'D') {// Declare
                            nameWith = document.getElementById('demoName').value;
                            fatherWith = document.getElementById('demoFname').value;
                            genderWith = document.getElementById('demoGender').value;
                          } else if (type == 'K') { //KYC
                            nameWith = document.getElementById("kycName").innerHTML;
                            fatherWith = document.getElementById("kycFname").innerHTML;
                            genderWith = document.getElementById("kycGender").innerHTML;
                          } else if (type == 'R') { //Ration
                            nameWith = document.getElementById("member_name_eng").value;
                            fatherWith = document.getElementById("father_name_eng").value;
                            genderWith = document.getElementById("gender").value;
                          }
                          var authDemo = document.getElementById("demoAuthSeccess").value;
                          if (authDemo == 'Y') {
                            saveKycData();
                          } else {
                            onMatch();
                            $.ajax({
                              type: "POST",
                              url: "/" + requestUri + "/fuzzyMatcher",
                              data: {
                                "nameBy": name_secc,
                                "fatherBy": father_name_secc,
                                "genderBy": gender_secc,
                                "nameWith": nameWith,
                                "fatherWith": fatherWith,
                                "genderWith": genderWith,
                              },
                              success: function (data) {
                                console.log(data);
                                offMatch();
                                //  var obj = jQuery.parseJSON( data );
                                if (data == "Y") {
                                  if (type == 'K') {
                                    saveKycData();
                                  } else if (type == 'R') {
                                    document.getElementById("next1").disabled = false;
                                    $("#rationInfo").show();
                                    $("#rationInfo").html('Beneficiary Ration card details is matched with SECC details.');
                                    $("#rationError").hide();

                                  }
                                } else if (data == "N") {
                                  if (type == 'D') {
                                    document.getElementById('adhar_required').innerHTML = "Beneficiary Declare details is not matched with SECC Details.";
                                    document.getElementById("aadhar").value = "";
                                  }
                                  else if (type == 'K') {
                                    $('#errorUpdateKyc').html("Beneficiary eKYC details is not matched with SECC details.");
                                    //document.getElementById("finishButton").disabled = true;
                                  } else if (type == 'R') {
                                    $("#rationError").show();
                                    $('#rationError').html("Beneficiary Ration card details is not matched with SECC details.");
                                    $("#rationInfo").hide();
                                    document.getElementById("next1").disabled = true;
                                  }
                                }
                              },
                              error: function (e) {
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



                        function saveKycData() {


                          var requestUri = '<%=requestUri%>';
                          var authType = document.getElementById("authType").value;

                          var chekedRUFlag = '<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
                          var districtCode;
                          var blockCode;
                          var villageCode;
                          var vtcCode;
                          var wardCode;


                          if (chekedRUFlag == 'R') {
                            state_code = '<%=beneficiarySeccForm.getStateCodeRural()%>';
                            districtCode = '<%=beneficiarySeccForm.getDistrictCodeRural()%>';
                            blockCode = '<%=beneficiarySeccForm.getBlockCodeRural()%>';
                            villageCode = '<%=beneficiarySeccForm.getVillageCodeRural()%>';
                            vtcCode = " ";
                            wardCode = " ";
                          } else if (chekedRUFlag == 'U') {
                            state_code = '<%=beneficiarySeccForm.getStateCodeUrban()%>';
                            districtCode = '<%=beneficiarySeccForm.getDistrictCodeUrban()%>';
                            vtcCode = '<%=beneficiarySeccForm.getTownCodeUrban()%>';
                            wardCode = '<%=beneficiarySeccForm.getWardCodeUrban()%>';
                            blockCode = " ";
                            villageCode = " ";
                          }

                          var name_s = document.getElementById("kycName").innerHTML;
                          if (name_s == '')
                            name_s = ' ';

                          var gender_s = document.getElementById("kycGender").innerHTML;

                          if (gender_s == '')
                            gender_s = ' ';

                          var dob_s = document.getElementById("kycDob").innerHTML;
                          dob_s = formatDate1(dob_s);
                          if (dob_s == 'NaN-NaN-NaN')
                            dob_s = ' ';

                          var address_s = document.getElementById("kycAdr").innerHTML;
                          if (address_s == '')
                            address_s = ' ';

                          var father_name_s = document.getElementById("kycFname").innerHTML;


                          if (father_name_s == '')
                            father_name_s = ' ';


                          var state_name_s = document.getElementById("state_name_s").value;
                          if (state_name_s == '')
                            state_name_s = ' ';

                          var district_name_s = document.getElementById("district_name_s").value;

                          if (district_name_s == '')
                            district_name_s = ' ';

                          var vtc_s = document.getElementById("vtc_s").value;
                          if (vtc_s == '')
                            vtc_s = ' ';

                          var post_s = document.getElementById("post_s").value;
                          if (post_s == '')
                            post_s = ' ';
                          var photo_s = document.getElementById("pht").value;
                          if (photo_s == '')
                            photo_s = ' ';
                          var uid_token = document.getElementById("uidtoken").value;
                          if (uid_token == '')
                            uid_token = ' ';
                          var ruralUrbanFlag = document.getElementById("ruralUrbanFlag").value;
                          var uid_aadhaar = document.getElementById("aadhar").value;
                          uid_aadhaar = uid_aadhaar.replaceAll(" ", "");
                          if (uid_aadhaar == '')
                            uid_aadhaar = ' ';
                          var uid_auth_type = document.getElementById("uid_auth_type").value;
                          if (uid_auth_type == '')
                            uid_auth_type = ' ';
                          var operationType = "BIS2.0_GUID";

                          var name_d = document.getElementById('demoName').value;
                          if (name_d == '')
                            name_d = ' ';
                          var father_name_d = document.getElementById('demoFname').value;
                          if (father_name_d == '')
                            father_name_d = ' ';
                          var gender_d = document.getElementById('demoGender').value;
                          if (gender_d == '')
                            gender_d = ' ';

                          var dob_d = document.getElementById('demoDob').value;
                          dob_d = formatDate(dob_d);
                          if (dob_d == '')
                            dob_d = ' ';

                          var mobile_member = document.getElementById('demoMobile').value;
                          if (mobile_member == '')
                            mobile_member = ' ';
                          var pin_code_d = document.getElementById('demoEmail').value;
                          if (pin_code_d == '')
                            pin_code_d = ' ';

                          var verification_status = document.getElementById('verification_status').value;

                          var refernceid = '<%=beneficiarySeccForm.getGuid()%>';
                          var guid = '<%=beneficiarySeccForm.getGuid()%>';
                          var stateName = '<%=beneficiarySeccForm.getStateName()%>';
                          var districtName = '<%=beneficiarySeccForm.getDistrictName()%>';
                          var ahl_tin = ' ';
                          var ahl_hhid = ' ';
                          var sub_distCode = ' ';
                          var address_d = ' ';
                          var state_name_d = ' ';
                          var district_name_d = ' ';
                          var sub_dist_d = ' ';
                          var vtc_d = ' ';
                          var post_d = ' ';


                          var family_mem_id = document.getElementById("family_mem_id").value;
                          var member_name_eng = document.getElementById("member_name_eng").value;
                          var gender = document.getElementById("gender").value;
                          var relation_name = document.getElementById("relation_name").value;
                          var mother_name_eng = document.getElementById("mother_name_eng").value;
                          var father_name_eng = document.getElementById("father_name_eng").value;
                          var spouse_name_eng = document.getElementById("spouse_name_eng").value;
                          var year_of_birth = document.getElementById("year_of_birth").value;

                          var state_lgd_code = document.getElementById("state_lgd_code").value;
                          var district_lgd_code = document.getElementById("district_lgd_code").value;
                          var subdistrict_lgd_code = document.getElementById("subdistrict_lgd_code").value;
                          var village_town_lgd_code = document.getElementById("village_town_lgd_code").value;
                          var pincode = document.getElementById("pincode").value;
                          var rural_urban = document.getElementById("rural_urban").value;
                          var family_id = document.getElementById("rationCard").value;


                          if (document.getElementById("checkDeclare").checked) {
                            onUpd();
                            document.getElementById("finishButton").disabled = true;
                            $.ajax({
                              type: "POST",
                              url: "/" + requestUri + "/saveKycData",
                              data: {
                                "state_code": state_code,
                                "name_s": name_s,
                                "gender_s": gender_s,
                                "address_s": address_s,
                                "state_name_s": state_name_s,
                                "district_name_s": district_name_s,
                                "vtc_s": vtc_s,
                                "post_s": post_s,
                                "photo_s": photo_s,
                                "uid_token": uid_token,
                                "ruralUrbanFlag": ruralUrbanFlag,
                                "uid_aadhaar": uid_aadhaar,

                                "uid_auth_type": uid_auth_type,
                                "name_d": name_d,
                                "demoName": name_d,
                                "father_name_d": father_name_d,
                                "gender_d": gender_d,
                                "dob_d": dob_d,
                                "mobile_member": mobile_member,
                                "pin_code_d": pin_code_d,
                                "refernceid": refernceid,
                                "guid": guid,
                                "stateName": stateName,
                                "districtName": districtName,
                                "verification_status": verification_status,
                                "father_name_s": father_name_s,
                                "vtcCode": vtcCode,
                                "districtCode": districtCode,
                                "dob_s": dob_s,
                                "operationType": operationType,
                                "ahl_tin": ahl_tin,
                                "ahl_hhid": ahl_hhid,
                                "sub_distCode": sub_distCode,
                                "address_d": address_d,
                                "state_name_d": state_name_d,
                                "district_name_d": district_name_d,
                                "sub_dist_d": sub_dist_d,
                                "vtc_d": vtc_d,
                                "post_d": post_d,
                                "authType": authType,
                                "family_mem_id": family_mem_id,
                                "member_name_eng": member_name_eng,
                                "gender": gender,
                                "relation_name": relation_name,
                                "mother_name_eng": mother_name_eng,
                                "father_name_eng": father_name_eng,
                                "spouse_name_eng": spouse_name_eng,
                                "year_of_birth": year_of_birth,
                                "state_lgd_code": state_lgd_code,
                                "district_lgd_code": district_lgd_code,
                                "subdistrict_lgd_code": subdistrict_lgd_code,
                                "village_town_lgd_code": village_town_lgd_code,
                                "pincode": pincode,
                                "rural_urban": rural_urban,
                                "family_id": family_id,
                                "blockCode": blockCode,
                                "villageCode": villageCode,
                                "wardCode": wardCode,
                                "stateScheme":'1',

                              },
                              success: function (data) {
                                console.log(data);
                                offUpd();
                                var obj = jQuery.parseJSON(data);
                                if (obj['status'] == "Y") {

                                  if (obj['pmjayID'] == "N")
                                    $('#infoUpdateKyc').html("Beneficiary e-KYC has been Successfully completed. Reference ID :" + obj['refId']);
                                  else
                                    $('#infoUpdateKyc').html("Beneficiary e-KYC has been Successfully completed. Reference ID :" + obj['refId'] + " and PMJAY_ID :" + obj['pmjayID']);

                                  $('#errorUpdateKyc').hide('slow');
                                  $('#infoUpdateKyc').show('slow');
                                  // $('#infoUpdateKyc').delay(150000).hide(150000);
                                  // window.location.href = "/"+requestUri+"/home";
                                  document.getElementById("finishButton").disabled = true;
                                  document.getElementById("Previous3").disabled = true;
                                  document.getElementById("canFinalButon1").style.display = "none";

                                  document.getElementById("backSearch").style.display = "initial";
                                } else if (obj['status'] == "A") {

                                  $('#infoUpdateKyc').html("Beneficiary Ayshman Card in progress.");
                                  $('#errorUpdateKyc').hide('slow');
                                  $('#infoUpdateKyc').show('slow');
                                  document.getElementById("finishButton").disabled = false;

                                } else if (obj['status'] == "P") {
                                  $('#infoUpdateKyc').html("Beneficiary Ayshman Card in progress.");
                                  $('#errorUpdateKyc').hide('slow');
                                  $('#infoUpdateKyc').show('slow');
                                  document.getElementById("finishButton").disabled = false;

                                } else if (obj['status'] == "R") {
                                  $('#errorUpdateKyc').html("Beneficiary Ayshman Card Rejected.");
                                  $('#infoUpdateKyc').hide('slow');
                                  $('#errorUpdateKyc').show('slow');
                                  document.getElementById("finishButton").disabled = false;
                                } else {
                                  $('#errorUpdateKyc').html("Beneficiary e-KYC Updation failed.");
                                  $('#infoUpdateKyc').hide('slow');
                                  $('#errorUpdateKyc').show('slow');
                                  document.getElementById("finishButton").disabled = false;

                                }
                              },
                              error: function (e) {
                                if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				 					 alert('API Gateway not respond. Please try again.');
			  						}else{
             					alert('Your session has been expired. Please relogin again.');
								window.location.href='sessionExpire';
								}
                              }

                            });

                          } else {
                            alert("Please check declaration.");
                            return false;
                          }


                        }




                        function bioBasedKyc(bioValue) {


                          var requestUri = '<%=requestUri%>';

                          document.getElementById("optBtn").disabled = false;
                          document.getElementById("countdown").innerHTML = "";

                          var demoName = document.getElementById("demoName").value;
                          var demoFname = document.getElementById("demoFname").value;
                          var demoGender = document.getElementById("demoGender").value;
                          var demoDob = document.getElementById("demoDob").value;
                          var demoMobile = document.getElementById("demoMobile").value;
                          var demoEmail = document.getElementById("demoEmail").value;
                          var aadhar = document.getElementById("aadhar").value;


                          var aadhar = document.getElementById("aadhar").value;
                          var pidData = document.getElementById("pidData").value;


                          if (pidData == "") {
                            alert("Please Caputre Biometric.");
                            return false;
                          }
                          onKyc();
                          $.ajax({
                            type: "POST",
                            url: "/" + requestUri + "/bioBasedKyc",
                            data: {
                              "aadhar": aadhar,
                              "pidData": pidData,
                              "bioType": bioValue
                            },
                            success: function (data) {
                              console.log(data);
                              offKyc();
                              var obj = jQuery.parseJSON(data);
                              if (obj['status'] == "Y") {
                                $('#infoOtp').html("ekyc Successfully completed.");
                                $('#errorOtp').hide('slow');
                                $('#infoOtp').show('slow');
                                $('#kycName').html(obj['kycName']);
                                $('#kycGender').html(obj['kycGender']);
                                $('#kycDob').html(obj['kycDob']);

                                $('#kycName1').html(obj['kycName']);
                                $('#kycGender1').html(obj['kycGender']);
                                $('#kycDob1').html(obj['kycDob']);


                                document.getElementById("phtId_1").src = "data:image/jpeg;base64," + obj['pht'];
                                $('#kycFname1').html(obj['kycFname']);
                                $('#kycAdr1').html(obj['kycAdr']);


                                document.getElementById("uidtoken").value = obj['uidToken'];
                                document.getElementById("phtId").src = "data:image/jpeg;base64," + obj['pht'];
                                document.getElementById("pht").value = obj['pht'];
                                $('#kycFname').html(obj['kycFname']);
                                $('#kycAdr').html(obj['kycAdr']);

                                document.getElementById("vtc_s").value = obj['vtc_s'];
                                document.getElementById("district_name_s").value = obj['district_name_s'];
                                document.getElementById("state_name_s").value = obj['state_name_s'];
                                document.getElementById("post_s").value = obj['post_s'];
                                document.getElementById("uid_auth_type").value = '2';
                                document.getElementById("verification_status").value = 'C';


                                if (bioValue == 'F') {
                                  document.getElementById("fingerIdCheck").style.display = "block";
                                  document.getElementById("fingerIdCancel").style.display = "none";
                                  document.getElementById("radioI").disabled = true;
                                  document.getElementById("fingerKycSeccess").value = "Y";
                                } else if (bioValue == 'I') {
                                  document.getElementById("irisIdCheck").style.display = "block";
                                  document.getElementById("irisIdCancel").style.display = "none";
                                  document.getElementById("radioF").disabled = true;
                                  document.getElementById("irisKycSeccess").value = "Y";
                                }

                                document.getElementById("radioD").disabled = true;
                                document.getElementById("radioP").disabled = true;

                                document.getElementById("radioO").disabled = true;

                                document.getElementById("next2").disabled = false;
                                document.getElementById("next3").disabled = false;
                                document.getElementById("finishButton").disabled = false;
                                document.getElementById("Previous2").disabled = false;
                                document.getElementById("Previous3").disabled = false;
                                document.getElementById("authType").value = "K";
                                setDemoName();
                              } else {

                                openTab2();
                                $('#errorOtp').html("Kyc failed.");
                                $('#infoOtp').hide('slow');
                                $('#errorOtp').show('slow');

                              }
                            },
                            error: function (e) {
                              if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  				alert('API Gateway not respond. Please try again.');
			  				}else{
             				alert('Your session has been expired. Please relogin again.');
							window.location.href='sessionExpire';
							}
                            }

                          });





                        }




                        function otpBasedKyc() {


                          var requestUri = '<%=requestUri%>';

                          document.getElementById("optBtn").disabled = false;
                          document.getElementById("countdown").innerHTML = "";

                          var demoName = document.getElementById("demoName").value;
                          var demoFname = document.getElementById("demoFname").value;
                          var demoGender = document.getElementById("demoGender").value;
                          var demoDob = document.getElementById("demoDob").value;
                          var demoMobile = document.getElementById("demoMobile").value;
                          var demoEmail = document.getElementById("demoEmail").value;
                          var aadhar = document.getElementById("aadhar").value;


                          var aadhar = document.getElementById("aadhar").value;
                          var otp = document.getElementById("otp").value;
                          var otpTxn = document.getElementById("otpTxn").value;


                          if (demoName == "") {
                            alert("Please Enter the Full Name.");
                            document.getElementById("demoName").focus();
                            return false;
                          }
                          if (demoFname == "") {
                            alert("Please Enter the Father Name.");
                            document.getElementById("demoFname").focus();;
                            return false;
                          }
                          if (demoGender == "") {
                            alert("Please Select Gender.");
                            document.getElementById("demoGender").focus();;
                            return false;
                          }
                          if (demoDob == "") {
                            alert("Please Enter the DOB.");
                            document.getElementById("demoDob").focus();;
                            return false;
                          }

                          if (aadhar == "") {
                            alert("Please Enter Aadhaar Number.");
                            document.getElementById("aadhar").focus();;
                            return false;
                          }
                          if (demoEmail == "") {
                            alert("Please Pincode.");
                            document.getElementById(demoEmail).focus();;
                            return false;
                          }
                          if (otp == "") {
                            alert("Please Enter OTP.");
                            document.getElementById("otp").focus();;
                            return false;
                          }

                          onKyc();
                          $.ajax({
                            type: "POST",
                            url: "/" + requestUri + "/otpBasedKyc",
                            //url: "/otpBasedKyc",
                            data: {
                              "aadhar": aadhar,
                              "otp": otp,
                              "otpTxn": otpTxn

                            },
                            success: function (data) {
                              console.log(data);
                              offKyc();
                              var obj = jQuery.parseJSON(data);
                              if (obj['status'] == "Y") {
                                $('#infoOtp').html("ekyc Successfully completed.");
                                $('#errorOtp').hide('slow');
                                $('#infoOtp').show('slow');
                                $('#kycName').html(obj['kycName']);
                                $('#kycGender').html(obj['kycGender']);
                                $('#kycDob').html(obj['kycDob']);

                                $('#kycName1').html(obj['kycName']);
                                $('#kycGender1').html(obj['kycGender']);
                                $('#kycDob1').html(obj['kycDob']);


                                document.getElementById("phtId_1").src = "data:image/jpeg;base64," + obj['pht'];
                                $('#kycFname1').html(obj['kycFname']);
                                $('#kycAdr1').html(obj['kycAdr']);


                                document.getElementById("uidtoken").value = obj['uidToken'];
                                document.getElementById("phtId").src = "data:image/jpeg;base64," + obj['pht'];
                                document.getElementById("pht").value = obj['pht'];
                                $('#kycFname').html(obj['kycFname']);
                                $('#kycAdr').html(obj['kycAdr']);

                                document.getElementById("vtc_s").value = obj['vtc_s'];
                                document.getElementById("district_name_s").value = obj['district_name_s'];
                                document.getElementById("state_name_s").value = obj['state_name_s'];
                                document.getElementById("post_s").value = obj['post_s'];
                                document.getElementById("uid_auth_type").value = '1';
                                document.getElementById("verification_status").value = 'C';

                                document.getElementById("otpIdCancel").style.display = "none";
                                document.getElementById("otpIdCheck").style.display = "block";
                                document.getElementById("radioD").disabled = true;

                                document.getElementById("radioF").disabled = true;
                                document.getElementById("radioI").disabled = true;
                                document.getElementById("radioP").disabled = true;

                                document.getElementById("next2").disabled = false;
                                document.getElementById("next3").disabled = false;
                                document.getElementById("finishButton").disabled = false;
                                document.getElementById("Previous2").disabled = false;
                                document.getElementById("Previous3").disabled = false;

                                document.getElementById("otpKycSeccess").value = "Y";
                                document.getElementById("authType").value = "K";

                                setDemoName();
                              } else {

                                openTab2();
                                $('#errorOtp').html("Kyc failed.");
                                $('#infoOtp').hide('slow');
                                $('#errorOtp').show('slow');

                              }
                            },
                            error: function (e) {
                              if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  				alert('API Gateway not respond. Please try again.');
			  					}else{
             					alert('Your session has been expired. Please relogin again.');
								window.location.href='sessionExpire';
								}
                            }

                          });





                        }


                        function getOtp() {

                          var requestUri = '<%=requestUri%>';

                          var demoName = document.getElementById("demoName").value;
                          var demoFname = document.getElementById("demoFname").value;
                          var demoGender = document.getElementById("demoGender").value;
                          var demoDob = document.getElementById("demoDob").value;
                          var demoMobile = document.getElementById("demoMobile").value;
                          var demoEmail = document.getElementById("demoEmail").value;
                          var aadhar = document.getElementById("aadhar").value;

                          if (demoName == "") {
                            alert("Please Enter the Full Name.");
                            document.getElementById("demoName").focus();
                            return false;
                          }
                          if (demoFname == "") {
                            alert("Please Enter the Father Name.");
                            document.getElementById("demoFname").focus();;
                            return false;
                          }
                          if (demoGender == "") {
                            alert("Please Select Gender.");
                            document.getElementById("demoGender").focus();;
                            return false;
                          }
                          if (demoDob == "") {
                            alert("Please Enter the DOB.");
                            document.getElementById("demoDob").focus();;
                            return false;
                          }

                          if (aadhar == "") {
                            alert("Please Enter Aadhaar Number.");
                            document.getElementById("aadhar").focus();;
                            return false;
                          }
                          if (demoEmail == "") {
                            alert("Please Pincode.");
                            document.getElementById(demoEmail).focus();;
                            return false;
                          }


                          onOtp();
                          $.ajax({
                            type: "POST",
                            url: "/" + requestUri + "/genOtp",
                            //url: "/genOtp",
                            data: {
                              "aadhar": aadhar
                            },
                            success: function (data) {
                              console.log(data);
                              offOtp();
                              var obj = jQuery.parseJSON(data);
                              if (obj['status'] == "Y") {
                                // $('#infoOtp').html("OTP Sent to Registered mobile :  "+obj['mobile']);
                                $('#errorOtp').hide('slow');
                                $('#infoOtp').show('slow');

                                var timeleft = "180";
                                var downloadTimer = setInterval(function () {
                                  document.getElementById("countdown").innerHTML = "OTP Sent to Registered mobile :  <b>" + obj['mobile'] + "</b>  You can get OTP again after <b>" + timeleft + "</b> seconds";
                                  timeleft -= 1;
                                  document.getElementById("optBtn").disabled = true;
                                  if (timeleft <= 0) {
                                    clearInterval(downloadTimer);
                                    document.getElementById("optBtn").disabled = false;
                                    document.getElementById("countdown").innerHTML = "";

                                  }
                                  document.getElementById('otp').focus();
                                }, 1000);



                                document.getElementById("optBtn").style.display = "block";
                                document.getElementById("otpTxn").value = obj['txn'];
                                document.getElementById("entOtp").style.display = "block";
                                document.getElementById("otpSeccess").value = "Y";
                                document.getElementById("radioF").disabled = "true";
                                document.getElementById("radioI").disabled = "true";
                                document.getElementById("radioD").disabled = "true";
                                document.getElementById("radioP").disabled = "true";
                                document.getElementById("next2").disabled = false;
                                document.getElementById("next3").disabled = false;
                                document.getElementById("Previous2").disabled = false;
                                document.getElementById("Previous3").disabled = false;

                              } else {
                                $('#errorOtp').html("OTP failed.");
                                $('#infoOtp').hide('slow');
                                $('#errorOtp').show('slow');
                                $('#errorOtp').delay(1000).hide(1500);
                              }
                            },
                            error: function (e) {
                              if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  				alert('API Gateway not respond. Please try again.');
			  					}else{
             					alert('Your session has been expired. Please relogin again.');
								window.location.href='sessionExpire';
								}
                            }

                          });



                        }

                        function demoAuth() {

                          var requestUri = '<%=requestUri%>';
                          var demoName = document.getElementById("demoName").value;
                          var demoFname = document.getElementById("demoFname").value;
                          var demoGender = document.getElementById("demoGender").value;
                          var demoDob = document.getElementById("demoDob").value;
                          var demoMobile = document.getElementById("demoMobile").value;
                          var demoEmail = document.getElementById("demoEmail").value;
                          var aadhar = document.getElementById("aadhar").value;

                          if (demoName == "") {
                            alert("Please Enter the Full Name.");
                            document.getElementById("demoName").focus();
                            return false;
                          }
                          if (demoFname == "") {
                            alert("Please Enter the Father Name.");
                            document.getElementById("demoFname").focus();;
                            return false;
                          }
                          if (demoGender == "") {
                            alert("Please Select Gender.");
                            document.getElementById("demoGender").focus();;
                            return false;
                          }
                          if (demoDob == "") {
                            alert("Please Enter the DOB.");
                            document.getElementById("demoDob").focus();;
                            return false;
                          }

                          if (aadhar == "") {
                            alert("Please Enter Aadhaar Number.");
                            document.getElementById("aadhar").focus();;
                            return false;
                          }
                          onDemo();
                          $.ajax({
                            type: "POST",
                            url: "/" + requestUri + "/demoAuth",
                            //url: "/demoAuth",
                            data: {
                              "demoName": demoName,
                              "demoFname": demoFname,
                              "demoGender": demoGender,
                              "demoDob": demoDob,
                              "demoMobile": demoMobile,
                              "demoEmail": demoEmail,
                              "aadhar": aadhar
                            },
                            success: function (response) {
                              offDemo();
                              if (response == "Y") {
                                $('#info3').html("Demographic auth Successfully completed.");
                                $('#error3').hide('slow');
                                $('#info3').show('slow');
                                document.getElementById("demoIdCancel").style.display = "none";
                                document.getElementById("demoIdCheck").style.display = "block";
                                document.getElementById("radioO").disabled = true;
                                document.getElementById("radioF").disabled = true;
                                document.getElementById("radioI").disabled = true;
                                document.getElementById("radioP").disabled = true;
                                document.getElementById("next2").disabled = false;
                                document.getElementById("next3").disabled = false;
                                document.getElementById("finishButton").disabled = false;
                                document.getElementById("Previous2").disabled = false;
                                document.getElementById("Previous3").disabled = false;
                                document.getElementById("demoAuthSeccess").value = "Y";
                                document.getElementById("uid_auth_type").value = '4';
                                document.getElementById("authType").value = "D";
                                document.getElementById("verification_status").value = 'C';
                                setDemoName();
                              } else {
                                $('#error3').html("Demographic auth failed because data is not matched with Aadhaar.");
                                $('#info3').hide('slow');
                                $('#error3').show('slow');
                                //   $('#error3').delay(1000).hide(1500);
                              }
                            },
                            error: function (e) {
                              if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  				alert('API Gateway not respond. Please try again.');
			  					}else{
             					alert('Your session has been expired. Please relogin again.');
								window.location.href='sessionExpire';
								}
                            }

                          });


                        }


                        function validateAadhar() {
                          var adhhar = document.getElementById("aadhar").value;

                          if (adhhar == null || adhhar == "") {
                            document.getElementById('adhar_required').innerHTML = "Please Enter Aadhaar Number.";
                          }
                          else {
                            if (!adhhar.verhoeffCheck()) {
                              document.getElementById("aadhar").value = "";
                              document.getElementById('adhar_required').innerHTML = "Please Enter Valid Aadhaar Number.";
                            } else {
                              document.getElementById('adhar_required').innerHTML = "";
                              validateDataWithFuzzyMatcher('D');
                            }
                          }

                        }

                        function isNumberKey(evt) {
                          var charCode = (evt.which) ? evt.which : event.keyCode;
                          if (charCode != 46 && charCode > 31
                            && (charCode < 48 || charCode > 57))
                            return false;

                          return true;
                        }


                        function setDemoName() {
                          document.getElementById('edemoName').innerHTML = document.getElementById('demoName').value;
                          document.getElementById('edemoFname').innerHTML = document.getElementById('demoFname').value;
                          if (document.getElementById('demoGender').value == "M")
                            document.getElementById('edemoGender').innerHTML = "Male";
                          else if (document.getElementById('demoGender').value == "F")
                            document.getElementById('edemoGender').innerHTML = "Female";
                          else
                            document.getElementById('edemoGender').innerHTML = "Transgender";
                          document.getElementById('edemoDob').innerHTML = formatDate2(document.getElementById('demoDob').value);
                          document.getElementById('edemoMobile').innerHTML = document.getElementById('demoMobile').value;
                          document.getElementById('edemoEmail').innerHTML = document.getElementById('demoEmail').value;
                        }



                        function goHome() {

                          var requestUri = '<%=requestUri%>';
                          window.location.href = "/" + requestUri + "/home";
                        }

                        function openTab2() {
                          // document.getElementById("pills-beneficiary").style.display = "none";
                          // document.getElementById("pills-yourdetails").style.display = "block";
                          // document.getElementById("pills-ekyc").style.display = "none";
                          //document.getElementById("pills-review").style.display = "none";


                          // $(function () {
                          $('#pills-tab li:nth-child(1) button').addClass('disabled');
                          $('#pills-tab li:nth-child(2) button').removeClass('disabled');
                          $('#pills-tab li:nth-child(2) button').tab('show');
                          //  });
                        }

                        function openTab1(tab) {

                          //$(function () {
                          $('#pills-tab li:first-child button').tab('show');
                          // });
                        }

                        function openTab3(tab) {

                          //$(function () {
                          //$('#pills-tab li:nth-child(3) button').removeClass('disabled');
                          var demoSecc = document.getElementById("demoAuthSeccess").value;
                          if (demoSecc == 'Y') {
                            $('#pills-tab li:nth-child(2) button').tab('show');
                          } else {
                            $('#pills-tab li:nth-child(3) button').tab('show');
                          }

                          // });

                          // document.getElementById("pills-review").style.display = "none";
                          // document.getElementById("pills-ekyc").style.display = "block";
                        }

                        function openTab33(tab) {

                          var v = document.getElementById("kycview");
                          if (v.style.display === "none") {
                            v.style.display = "flex";
                          } else {
                            v.style.display = "none";
                          }
                          var demoSecc = document.getElementById("demoAuthSeccess").value;
                          if (demoSecc == 'Y') {
                            //$(function () {
                            $('#pills-tab li:nth-child(4) button').tab('show');
                            $('#pills-tab li:nth-child(4) button').removeClass('disabled');
                            $('#pills-tab li:nth-child(3) button').addClass('disabled');
                            document.getElementById("kycview").style.display = "none";
                            //document.getElementById("Previous3").disabled = true;

                            //});
                            // document.getElementById("pills-yourdetails").style.display = "none";
                            // document.getElementById("pills-ekyc").style.display = "none";
                            // document.getElementById("pills-review").style.display = "block";
                            // document.getElementById("kycview").style.display = "none";

                          } else if (document.getElementById("otpSeccess").value == 'Y') {
                            if (document.getElementById("otpKycSeccess").value == 'Y') {
                              //$(function () {
                              $('#custom-tabs-one-tab li:nth-child(2) button').removeClass('disabled');
                              $('#pills-tab li:nth-child(3) button').tab('show');

                              //});
                              //document.getElementById("pills-yourdetails").style.display = "none";
                              // document.getElementById("pills-ekyc").style.display = "block";
                            } else {
                              if (document.getElementById("otp").value == '') {
                                alert("Please Enter OTP.");
                                return false;
                              }
                              otpBasedKyc();
                              // $(function () {
                              $('#pills-tab li:nth-child(3) button').removeClass('disabled');
                              $('#pills-tab li:nth-child(3) button').tab('show');
                              //});
                            }
                          } else if (document.getElementById("fingerSeccess").value == 'Y') {
                            if (document.getElementById("fingerKycSeccess").value == 'Y') {
                              // $(function () {
                              $('#pills-tab li:nth-child(2) button').removeClass('disabled');
                              $('#pills-tab li:nth-child(3) button').tab('show');
                              // document.getElementById("pills-yourdetails").style.display = "none";
                              // document.getElementById("pills-ekyc").style.display = "block";
                              //});
                            } else {
                              bioBasedKyc('F');
                              // $(function () {
                              $('#pills-tab li:nth-child(3) button').removeClass('disabled');
                              $('#pills-tab li:nth-child(3) button').tab('show');
                              // });
                            }
                          } else if (document.getElementById("irisSeccess").value == 'Y') {
                            if (document.getElementById("irisKycSeccess").value == 'Y') {
                              // $(function () {
                              $('#pills-tab li:nth-child(2) button').removeClass('disabled');
                              $('#pills-tab li:nth-child(3) button').tab('show');
                              // });
                              // document.getElementById("pills-yourdetails").style.display = "none";
                              // document.getElementById("pills-ekyc").style.display = "block";
                            } else {
                              bioBasedKyc('I');
                              // $(function () {
                              $('#pills-tab li:nth-child(3) button').removeClass('disabled');
                              $('#pills-tab li:nth-child(3) button').tab('show');
                              // });
                            }
                          } else if (document.getElementById("faceSeccess").value == 'Y') {
                            if (document.getElementById("faceKycSeccess").value == 'Y') {
                              //   	$(function () {
                              $('#pills-tab li:nth-child(2) button').removeClass('disabled');
                              $('#pills-tab li:nth-child(3) button').tab('show');
                              // 	});
                            } else {
                              //   $(function () {
                              $('#pills-tab li:nth-child(3) button').removeClass('disabled');
                              $('#pills-tab li:nth-child(3) button').tab('show');
                              //   });
                            }
                          }

                          else {
                            // $(function () {
                            $('#pills-tab li:nth-child(3) button').removeClass('disabled');
                            $('#pills-tab li:nth-child(3) button').tab('show');
                            // });
                          }
                        }


                        function openTab4(tab) {
                          //  document.getElementById("pills-ekyc").style.display = "none";
                          //  document.getElementById("pills-review").style.display = "block";
                          //  document.getElementById("kycview").style.display = "block";

                          // $(function () {
                          $('#pills-tab li:nth-child(4) button').removeClass('disabled');
                          $('#pills-tab li:nth-child(4) button').tab('show');
                          // });
                        }





                      </script>



                      <!-- <script>
     $(function () {
       
     $('#txtTo').datepicker({
       dateFormat: "dd/MM/yy",
       changeMonth: true,
       changeYear: true,
       ignoreReadonly: true,
       maxDate: 'now'
   });
     }) 
                      // DropzoneJS Demo Code End
                      </script> -->


                      <script>

                        function addCommas(e) {
                          var tgt = e.target, val = tgt.value.replace(/,/g, ''),
                            amt = Math.ceil(val.length / 4), newStr = '', x = 0;
                          while (x <= amt) {
                            newStr += val.slice(x * 4, (x + 1) * 4);
                            newStr += (x < amt - 1) ? ' ' : '';
                            x++
                          }
                          tgt.value = newStr;
                        }
                        document.getElementById('aadhar').addEventListener('change', addCommas, false);

                      </script>

                      <script>


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
                          return [day, month, year].join('-');
                        }

                      </script>






                      <script>

                        function portscanView() {
                          onScan();
                          setTimeout(function () { portscan(); }, 5000);

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






                        function selectDob() {

                          var con = isFutureDate(document.getElementById("selectDoa").value);
                          if (con) {
                            alert("Date of Birth is not allowed future date.");
                            document.getElementById("selectDoa").value = "";
                          } else {
                            document.getElementById("demoDob").value = document.getElementById("selectDoa").value;
                          }
                        }

                        function isFutureDate(idate) {
                          var today = new Date().getTime(),
                            idate = idate.split("-");
                          idate = new Date(idate[0], idate[1] - 1, idate[2]).getTime();
                          return (today - idate) < 0 ? true : false;
                        }
                      </script>







                      <script type="text/javascript">

                        function getRationCardDetail() {

                          var chekedRUFlag = '<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
                          var state_code;
                          if (chekedRUFlag == 'R') {
                            state_code = '<%=beneficiarySeccForm.getStateCodeRural()%>';
                          } else if (chekedRUFlag == 'U') {
                            state_code = '<%=beneficiarySeccForm.getStateCodeUrban()%>';
                          }

                          var rationCard = document.getElementById("rationCard").value;

                          if (rationCard == "") {
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
                            url: "/" + requestUri + "/getRationCardData",
                            data: {
                              "stateCode": state_code,
                              "rationCard": rationCard,
                            },
                            success: function (data) {
                              console.log(data);
                              newData = data;
                              offRation();
                              if (newData !== null) {
                                $("#rationDiv").show();
                              }
                              var data11 = jQuery.parseJSON(newData);


                              if (data11['status'] != 'Y') {
                                $("#rationDiv").hide();
                                $("#rationError").show();
                                $("#rationError").html('No Records Found !!');
                                $("#rationInfo").hide();

                              }
                              var ration = jQuery.parseJSON(data11['Details']);
                              $("#state_lgd_code").val(ration.state_lgd_code);
                              $("#district_lgd_code").val(ration.district_lgd_code);
                              $("#subdistrict_lgd_code").val(ration.subdistrict_lgd_code);
                              $("#village_town_lgd_code").val(ration.village_town_lgd_code);
                              $("#pincode").val(ration.pincode);
                              $("#rural_urban").val(ration.rural_urban);

                              var data1 = ration.family;

                              $("#example1").dataTable().fnDestroy();

                              var table = $('#example1').DataTable({
                                "responsive": false, "lengthChange": false, "autoWidth": true, "pagingType": "full_numbers", "pageLength": 15, "fixedHeader": false,
                                "language": { "emptyTable": "No Records Found !" }, "searching": false,
                                "aaData": data1,
                                "aoColumns": [
                                  {
                                    "mData": null,
                                    render: function () {
                                      return '<input type="radio" style="position: relative;top: 2px;" id="editButton" name="editButton" onclick="editDetail(this)"/>';

                                    }
                                  },
                                  { "mData": "family_mem_id" },
                                  { "mData": "member_name_eng" },
                                  { "mData": "gender" },
                                  { "mData": "relation_name" },
                                  { "mData": "mother_name_eng" },
                                  { "mData": "father_name_eng" },
                                  { "mData": "spouse_name_eng" },
                                  { "mData": "year_of_birth" },
                                ],

                                createdRow: function (row, data1, dataIndex) {

                                  if (data1.gender === 'M') {
                                    $(row).find('td:eq(3)').html("Male")
                                  } else if (data1.gender === 'F') {
                                    $(row).find('td:eq(3)').html("Female")
                                  } else {
                                    $(row).find('td:eq(3)').html("Other")
                                  }
                                  if (data1.year_of_birth === '0') {
                                    $(row).find('td:eq(8)').html("")
                                  }

                                },
                                "scrollX": true

                              });
                            },

                            error: function (e) {
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
                        function formatDate1(date) {

                          var date1 = date.split("-").reverse().join("-");

                          return date1;
                        }


                        function editDetail(value) {

                          var requestUri = '<%=requestUri%>';

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

                          document.getElementById("family_mem_id").value = family_mem_id;
                          document.getElementById("member_name_eng").value = member_name_eng;
                          document.getElementById("gender").value = gender;
                          document.getElementById("relation_name").value = relation_name;
                          document.getElementById("mother_name_eng").value = mother_name_eng;
                          document.getElementById("father_name_eng").value = father_name_eng;
                          document.getElementById("spouse_name_eng").value = spouse_name_eng;
                          document.getElementById("year_of_birth").value = year_of_birth;

                          document.getElementById("rfamily_mem_id").innerHTML = family_mem_id;
                          document.getElementById("rmember_name_eng").innerHTML = member_name_eng;
                          document.getElementById("rgender").innerHTML = gender;
                          document.getElementById("rrelation_name").innerHTML = relation_name;
                          document.getElementById("rmother_name_eng").innerHTML = mother_name_eng;
                          document.getElementById("rfather_name_eng").innerHTML = father_name_eng;
                          document.getElementById("rspouse_name_eng").innerHTML = spouse_name_eng;
                          document.getElementById("ryear_of_birth").innerHTML = year_of_birth;
                          document.getElementById("rrationCard").innerHTML = document.getElementById("rationCard").value;


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



                      </script>
                      <script type="text/javascript">

                        function reGenFaceQrCode() {
                          var requestUri = '<%=requestUri%>';
                          document.getElementById("face_id").src = "/" + requestUri + "/faceId";
                        }




                        function captureFace() {


                          var requestUri = '<%=requestUri%>';

                          document.getElementById("optBtn").disabled = false;
                          document.getElementById("countdown").innerHTML = "";

                          var demoName = document.getElementById("demoName").value;
                          var demoFname = document.getElementById("demoFname").value;
                          var demoGender = document.getElementById("demoGender").value;
                          var demoDob = document.getElementById("demoDob").value;
                          var demoMobile = document.getElementById("demoMobile").value;
                          var demoEmail = document.getElementById("demoEmail").value;
                          var aadhar = document.getElementById("aadhar").value;


                          var aadhar = document.getElementById("aadhar").value;



                          if (demoName == "") {
                            alert("Please Enter the Full Name.");
                            document.getElementById("demoName").focus();
                            return false;
                          }
                          if (demoFname == "") {
                            alert("Please Enter the Father Name.");
                            document.getElementById("demoFname").focus();;
                            return false;
                          }
                          if (demoGender == "") {
                            alert("Please Select Gender.");
                            document.getElementById("demoGender").focus();;
                            return false;
                          }
                          if (demoDob == "") {
                            alert("Please Enter the DOB.");
                            document.getElementById("demoDob").focus();;
                            return false;
                          }

                          if (aadhar == "") {
                            alert("Please Enter Aadhaar Number.");
                            document.getElementById("aadhar").focus();;
                            return false;
                          }
                          if (demoEmail == "") {
                            alert("Please Pincode.");
                            document.getElementById(demoEmail).focus();;
                            return false;
                          }


                          onKyc();
                          $.ajax({
                            type: "POST",
                            url: "/" + requestUri + "/faceBasedKyc",
                            data: {
                              "aadhar": aadhar,
                              "bioType": 'P',
                            },
                            success: function (data) {
                              console.log(data);
                              offKyc();
                              var obj = jQuery.parseJSON(data);
                              if (obj['status'] == "Y") {
                                $('#infoOtp').html("ekyc Successfully completed.");
                                $('#errorOtp').hide('slow');
                                $('#infoOtp').show('slow');
                                $('#kycName').html(obj['kycName']);
                                $('#kycGender').html(obj['kycGender']);
                                $('#kycDob').html(obj['kycDob']);

                                $('#kycName1').html(obj['kycName']);
                                $('#kycGender1').html(obj['kycGender']);
                                $('#kycDob1').html(obj['kycDob']);


                                document.getElementById("phtId_1").src = "data:image/jpeg;base64," + obj['pht'];
                                document.getElementById("face_id").src = "data:image/jpeg;base64," + obj['facePht'];
                                $('#kycFname1').html(obj['kycFname']);
                                $('#kycAdr1').html(obj['kycAdr']);


                                document.getElementById("uidtoken").value = obj['uidToken'];
                                document.getElementById("phtId").src = "data:image/jpeg;base64," + obj['pht'];
                                document.getElementById("pht").value = obj['pht'];
                                $('#kycFname').html(obj['kycFname']);
                                $('#kycAdr').html(obj['kycAdr']);

                                document.getElementById("vtc_s").value = obj['vtc_s'];
                                document.getElementById("district_name_s").value = obj['district_name_s'];
                                document.getElementById("state_name_s").value = obj['state_name_s'];
                                document.getElementById("post_s").value = obj['post_s'];
                                document.getElementById("uid_auth_type").value = '3';
                                document.getElementById("verification_status").value = 'C';



                                document.getElementById("faceIdCheck").style.display = "block";
                                document.getElementById("faceIdCancel").style.display = "none";
                                document.getElementById("radioI").disabled = true;
                                document.getElementById("radioF").disabled = true;
                                document.getElementById("faceKycSeccess").value = "Y";
                                document.getElementById("faceSeccess").value = "Y";

                                document.getElementById("radioD").disabled = true;

                                document.getElementById("radioO").disabled = true;

                                document.getElementById("next2").disabled = false;
                                document.getElementById("next3").disabled = false;
                                document.getElementById("finishButton").disabled = false;
                                document.getElementById("Previous2").disabled = false;
                                document.getElementById("Previous3").disabled = false;
                                document.getElementById("authType").value = "K";
                                document.getElementById("faceBtn").disabled = true;
                                document.getElementById("faceBtn").style.display = "inline";

                                setDemoName();
                                $('#errorFaceKyc').hide('slow');
                              } else {
                                reGenFaceQrCode();
                                openTab2();
                                document.getElementById("faceBtn").disabled = false;
                                $('#errorFaceKyc').html("Face Photo Capture Failed.");
                                $('#errorFaceKyc').show('slow');

                              }
                            },
                            error: function (e) {
                              if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  				alert('API Gateway not respond. Please try again.');
			  					}else{
             					alert('Your session has been expired. Please relogin again.');
								window.location.href='sessionExpire';
								}
                            }

                          });







                        }


                    	// $(document).ready(function(){
                    	// 	$('table').DataTable();
                    	// });
                      </script>

                    </body>

                    </html>