<!DOCTYPE html>

<%@page import="com.gov.nha.bis.server.properties.CommonProperties"%>
<%@page import="com.gov.nha.bis.server.properties.CommonProFunction"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@page import="com.gov.nha.bis.server.model.SessionLoginMap"%>
<%@page import="org.springframework.util.ObjectUtils"%>
<%@page import="com.gov.nha.bis.server.model.BeneficiarySeccForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
CommonProFunction PROPERTIES = CommonProperties.commonProFunction;

String requestUri="";
requestUri=request.getRequestURI().split("/")[1];
String sessid="";
sessid=session.getId();
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

SessionLoginMap sessionUser= new SessionLoginMap();
if(session.getAttribute("sessionUser")!=null){
	sessionUser =(SessionLoginMap)session.getAttribute("sessionUser");
}


%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SECC Beneficiary NFSA View</title>

<link rel="icon" type="image/ico" sizes="16x16"
	href="images/favicon.ico">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Sora:wght@100;200;300;400;500;600;700;800&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<link rel="stylesheet" href="sass/bootstrap.min.css">
<link rel="stylesheet" href="sass/setu-custom.css">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/scroller/2.0.5/css/scroller.dataTables.min.css">



<style>

#example1.table {
	width: 1250;
}

#example2.table {
	width: 1250;
}

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

<body class="mobile" onload="getStateData(),disableBtan()">

<div class="beneficiary-main-wrap">
	<div class="row flex-nowrap">
		<div class="col-auto pe-0">
			<div id="sidebar"
				class="collapse collapse-horizontal show leftbar-wrapcustom">
				<div id="sidebar-nav"
					class="list-group border-0 rounded-0 text-sm-start min-vh-100 leftbar-custom">
					<a href="javascript:void(0);"
						class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none logo-main">
						<img src="images/PMJAY-logo.png" alt=""><span>BIS</span>
					</a>

					<ul class="list-unstyled mt-2 ps-0 left-nav-custom">
						<li class="mb-1">
							<button class="btn btn-toggle align-items-center rounded"
								data-bs-toggle="collapse" data-bs-target="#home-collapse"
								aria-expanded="true">Help</button>
							<div class="collapse" id="home-collapse">
								<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> By Name or any other column
											i.e Father's name, YOB, gender etc.</a></li>
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> By Name and Father's name (
											keep space between Name and Father's name).</a></li>
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> By Any two consecutive
											columns with space.</a></li>
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> By Name or any other column
											i.e Father's name, YOB, gender etc.</a></li>
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> By Father's name and view
											family data with card status.</a></li>
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> CSV Format</a></li>
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> Excel Format</a></li>
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> PDF Format</a></li>
									<li><a href="#" class="link-dark rounded"><i
											class="fa fa-info-circle"></i> Privacy Policy</a></li>
								</ul>
							</div>
						</li>
					</ul>

				</div>
			</div>
		</div>

		<main class="col">
			<nav>
			<%@ include file="header.jsp" %>				
</nav>
<form:form action="" method="POST" commandName="command">
<div class="beneficiary-section">
	<main>
		<section class="SECC-data mb-4">
			<div class="container">
				<div class="px-1 px-md-3">
					<div class="col-12">
						<h2>Link Your AADHAAR</h2>
						<div class="beneficiary-view-tabs mb-2">
							<ul class="nav nav-pills" id="pills-tab" role="tablist">
								<li class="nav-item" role="presentation">
									<button class="nav-link active" id="pills-nfsa-tab"
										data-bs-toggle="pill" data-bs-target="#pills-nfsa"
										type="button" role="tab" aria-controls="pills-nfsa"
										aria-selected="true">NFSA DETAIL</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link" id="pills-ekyc-tab"
										data-bs-toggle="pill" data-bs-target="#pills-ekyc"
										type="button" role="tab" aria-controls="pills-ekyc"
										aria-selected="false">eKYC DETAIL</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link" id="pills-preview-tab"
										data-bs-toggle="pill" data-bs-target="#pills-preview"
										type="button" role="tab" aria-controls="pills-preview"
										aria-selected="false">PRINT AYUSHMAN CARD</button>
								</li>
							</ul>
							<div class="tab-content tab-content-view"
								id="pills-tabContent">
								<div class="tab-pane fade show active" id="pills-nfsa"
									role="tabpanel" aria-labelledby="pills-nfsa-tab">
									<div class="tab-card-in">

										<div class="row my-3">
											<div class="col-12 col-md-3 col-sm-3 mb-2">
												<div class="custom-form-group form-group">
													<label for="">State</label>
													<form:select path="stateCodeRural" id="stateCodeRural"
							tabindex="1" class="form-select" style="width: 100%;"
							onchange="getDistrictData('D')">
							<option value="">Select State</option>
						</form:select>
					</div>
				</div>
				<div class="col-12 col-md-3 col-sm-3">
					<div class="custom-form-group form-group">
						<label for="">District</label>
						<form:select path="districtCodeRural"
							id="districtCodeRural" tabindex="2"
							class="form-select" style="width: 100%;">
							<option value="">Select District</option>

						</form:select>
					</div>
				</div>
				<div class="col-12 col-md-3 col-sm-3">
					<div class="custom-form-group form-group">
						<label for="">Ration card Number</label>

						<form:input path="rationCard" id="rationCard"
							class="form-control" />
					</div>
				</div>
				<div class="col-12 col-md-3 col-sm-3">

					<button type="button" class="btn btn-sm btn-dark"
						onclick="getRationCardDetail()">Submit</button>
				</div>
			</div>

			<div class="beneficiary-section-table" id="rationDiv"
				style="display: none">
				<div class="text-center mb-3">
					<h3>
						<b> MEMBERS DETAILS AS PER NFSA </b>
					</h3>
				</div>
				<div class="card-content p-3">
					<div class="table-responsive"
						style="margin: 0 auto; max-width: 1012px;">
						<table id="example1"
							class="table table-striped dataTable display nowrap "
							style="width: 100%">
							<thead>
								<tr>

									<th>LINK WITH</th>
									<th>MEMBER ID</th>
									<th>AADHAAR</th>
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

			<div
				class="col-12 col-sm-4 col-md-12 my-1 input-ration-wrap">
				<span id="rationError" style="color: red;"
					class="app-title"></span> <span id="rationInfo"
					style="color: green;" class="app-title"></span>
			</div>

			<div class="text-center mt-3">
				<button type="button" class="btn btn-sm btn-white"
					id="canBtn1" onclick="window.location.href='home'">Cancel</button>
				<button type="button" class="btn btn-sm btn-dark"
					id="kycBtn" onclick="window.location.href='#'">eKYC</button>
				<button type="button" class="btn btn-sm btn-dark"
					id="prnBtn" onclick="window.location.href='#'">Print</button>
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="pills-ekyc" role="tabpanel"
		aria-labelledby="pills-ekyc-tab">
		<div class="tab-card-in">

			<h3>Beneficiary Details as in NFSA</h3>
			<div class="row mx-0">
				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>Family ID: </span> <span id="rrationCard"></span>
				</div>

				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>Member ID: </span> <span id="rfamily_mem_id"></span>
				</div>

				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>Name: </span> <span id="rmember_name_eng"></span>
				</div>

				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>Gender: </span> <span id="rgender"></span>
				</div>
				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>YOB: </span> <span id="ryear_of_birth"></span>
				</div>

				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>Guardian : </span> <span id="rfather_name_eng"></span>
				</div>

				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>Mother :</span> <span id="rmother_name_eng"></span>
				</div>

				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>Spouse:</span> <span id="rspouse_name_eng"></span>
				</div>

				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>Relation: </span> <span id="rrelation_name"></span>
				</div>

				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>State : </span> <span id="rstate_name"></span>
				</div>
				<div
					class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
					<span>District: </span> <span id="rdistrict_name"></span>
				</div>
			</div>
		</div>

		</br>
		<div class="tab-card-in">
			<h3>Enter Your Aadhaar</h3>
			<div class="row mt-4">

				<div class="col-12 col-sm-12 col-md-4 col-lg-4">
					<div class="custom-form-group form-group">
						<label for="">Enter Aadhaar </label>

						<form:input path="aadhar" class="form-control"
							id="aadhar" placeholder="Aadhaar Number "
							maxlength="12" onkeypress="return isNumberKey(event)"
							onchange="validateAadhar()" />
					</div>
				</div>
				<div class="col-12 col-sm-6 col-md-4 col-lg-4">
					<div class="custom-form-group form-group">
						<label for="">How Would You like to eKYC</label>

						<div
							class="nav bg-white mb-1 custom-radio-otp justify-content-between radio-group"
							id="pills-tab" role="tablist">
							<div class="form-radio" role="presentation"
								id="otp-home-tab" data-bs-toggle="pill"
								data-bs-target="#otp-home" type="button" role="tab"
								aria-controls="otp-home" aria-selected="true">
								<input type="radio" name="ekyc-new" id="radioO"
									value="O" onclick="onOpenAction()"> <label>OTP</label>
							</div>
							<div class="form-radio" role="presentation"
								id="finger-home-tab" data-bs-toggle="pill"
								data-bs-target="#finger-home" type="button"
								role="tab" aria-controls="finger-home"
								aria-selected="false">
								<input type="radio" id="radioF" name="ekyc-new"
									value="F" onclick="onOpenAction()"><label>Finger</label>
							</div>
							<div class="form-radio" role="presentation"
								id="iris-home-tab" data-bs-toggle="pill"
								data-bs-target="#iris-home" type="button" role="tab"
								aria-controls="iris-home" aria-selected="false">
								<input type="radio" id="radioI" name="ekyc-new"
									value="I" onclick="onOpenAction()"><label>IRIS</label>
							</div>
							<div class="form-radio" role="presentation"
								id="demographic-home-tab" data-bs-toggle="pill"
								data-bs-target="#demographic-home" type="button"
								role="tab" aria-controls="demographic-home"
								aria-selected="false">
								<input type="radio" id="radioP" name="ekyc-new"
									value="P" onclick="onOpenAction()"><label>Face</label>
							</div>
						</div>
						<div class="tab-content mt-4" id="pills-tabContent">
							<div class="tab-pane fade" id="otp-home"
								role="tabpanel" aria-labelledby="otp-home-tab">
								<div class="kyc-wrap">
									<div class="form-get-btn">
										<button type="button"
											class="btn custom-btn-sm btn-dark" id="optBtn"
											onclick="getOtp()">Get OTP</button>
									</div>
									<div class="form-group" id="entOtp">
										<form:input path="otp" class="form-control"
											placeholder="Enter OTP" id="otp" size="6" />
										<button type="button" id="otpKyc"
											onclick="otpBasedKyc()"
											class="btn custom-btn-sm btn-dark">Get
											KYC</button>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="finger-home"
								role="tabpanel" aria-labelledby="finger-home-tab">
								<div class="kyc-wrap">
									<div class="form-get-btn">
										<button type="button"
											class="btn custom-btn-sm btn-dark"
											onclick="captureBioAdd('F')">Scan Your
											Finger</button>
									</div>
									<div class="form-group">
										<span id="figerIdCapture" style="display: none">
											<i class="fas fa-check text-success"></i>
										</span> <span id="figerIdFCapture" style="display: block">
											<i class="fas fa-times text-danger"></i>
										</span>
										<button type="button"
											class="btn custom-btn-sm btn-dark"
											onclick="bioBasedKyc('F')">Get KYC</button>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="iris-home"
								role="tabpanel" aria-labelledby="iris-home-tab">
								<div class="kyc-wrap">
									<div class="form-get-btn">
										<button type="button"
											class="btn custom-btn-sm btn-dark"
											onclick="captureBioAdd('I')">Scan Your
											Iris</button>
									</div>
									<div class="form-group">
										<span id="irisIdCapture" style="display: none">
											<i class="fas fa-check text-success"></i>
										</span> <span id="irisIdICapture" style="display: block">
											<i class="fas fa-times text-danger"></i>
										</span>
										<button type="button"
											class="btn custom-btn-sm btn-dark"
											onclick="bioBasedKyc('I')">Get KYC</button>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="demographic-home"
								role="tabpanel"
								aria-labelledby="demographic-home-tab">
								<div class="kyc-wrap">
									<div class="form-group">
										<div class="bar-code">
											<img id="face_id" src="faceId" alt="">
										</div>
										<button type="button"
											class="btn custom-btn-sm btn-dark"
											onclick="captureFace()">Scan Your Face</button>
									</div>
									<div class="form-get-btn">
										<button type="button"
											class="btn custom-btn-sm btn-dark"
											onclick="bioBasedKyc('P')">Get KYC</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

			<div id="kycDetailsDiv" style="display: none;">
				<h3>eKYC Details</h3>
				<div class="row mt-2">

					<div class="col-12 col-sm-4 col-md-3 col-lg-2">
						<img id="phtId_1" src="images/user-profile-l.jpg"
							class="img-thumbnail rounded mx-auto d-block" alt="">
					</div>
					<div class="col-12 col-sm-8 col-md-9 col-lg-10">
						<div class="ekyc-details-table">
							<span>Name:</span> <strong id="kycName1"> </strong>
						</div>
						<div class="ekyc-details-table">
							<span>Guardian's Name:</span> <strong id="kycFname1">
							</strong>
						</div>
						<div class="ekyc-details-table">
							<span>Gender:</span> <strong id="kycGender1">
							</strong>
						</div>
						<div class="ekyc-details-table">
							<span>DOB:</span> <strong id="kycDob1"></strong>
						</div>
						<div class="ekyc-details-table">
							<span>Address:</span> <strong id="kycAdr1">
							</strong>
						</div>
					</div>

				</div>
				
				<br>
				<br>
				<div > 
				<input type="checkbox" id="checkDeclare" name="checkDeclare" value="1">
				<font size="2">I hereby declare that the details furnished above are true and correct to the best of my knowledge and belief . In case any of the above information is found to be false or untrue or misleading or misrepresenting, I am aware that I may be held liable for it.			
                </font></div>	
				
			</div>


			<div id="errorUpdateKyc1" style="color: red;"
				class="app-title"></div>
			<div id="infoUpdateKyc1" style="color: green;"
				class="app-title"></div>
			<div class="col-12 text-danger">
				<span id="adhar_required"></span>
			</div>
			<div class="alert alert-primary p-1 mb-1 text-center"
				style="font-size: 12px; display: none" id="countdown"></div>
			<div id="errorOtp" style="color: red; display: flex"
				class="app-title"></div>
			<div id="errorKyc" style="color: red; display: none"
				class="app-title"></div>
			<div id="errorUpdateKyc"
				style="color: red; display: none" class="app-title"></div>
			<div id="infoKyc" style="color: green; display: none"
				class="app-title"></div>
			<div id="infoOtp" style="color: green;"
				class="align:left"></div>
			<div class="text-center mt-3">

				<button type="button" class="btn btn-sm btn-white"
					id="Previous2" onclick="window.location.href='#'">Previous</button>
				<button type="button" class="btn btn-sm btn-white"
					id="ekycComplete" onclick="saveNfsData()">eKYC
					Complete</button>
				<button type="button" class="btn btn-sm btn-dark"
					id="nextPrn" onclick="window.location.href='#'">Next</button>
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="pills-preview"
		role="tabpanel" aria-labelledby="pills-preview-tab">
		<div class="tab-card-in">
			<h3>Enter Your Aadhaar No.</h3>
			<div class="row mt-4">
				<div class="col-12 col-sm-12 col-md-4 col-lg-4">
					<div class="custom-form-group form-group">
						<label for="">Enter Aadhaar </label> 
							
					<form:input path="aadhar_auth" class="form-control"
							id="aadhar_auth" placeholder="Aadhaar Number "
							maxlength="12" onkeypress="return isNumberKey(event)"
							onchange="validateAadharAuth()" />		
					</div>
				</div>


				<div class="col-12 col-sm-6 col-md-4 col-lg-4">
					<div class="custom-form-group form-group">
						<label for="">How Would You like to
							Authentication</label>

						<div
							class="nav bg-white mb-1 custom-radio-otp justify-content-between radio-group"
							id="pills-tab" role="tablist">
							<div class="form-radio" role="presentation"
								id="fingerone-home-tab" data-bs-toggle="pill"
								data-bs-target="#fingerone-home" type="button"
								role="tab" aria-controls="fingerone-home"
								aria-selected="false">
								<input type="radio" 
									name="fav_language" id="radioAF"
									value="F" onclick="onAuthOpenAction()"><label>Finger</label>
							</div>
							<div class="form-radio" role="presentation"
								id="irisone-home-tab" data-bs-toggle="pill"
								data-bs-target="#irisone-home" type="button"
								role="tab" aria-controls="irisone-home"
								aria-selected="false">
								<input type="radio" id="radioAI"
									value="I" onclick="onAuthOpenAction()" name="fav_language"><label>IRIS</label>
							</div>
							<div class="form-radio" role="presentation"
								id="face-home-tab" data-bs-toggle="pill"
								data-bs-target="#face-home" type="button" role="tab"
								aria-controls="face-home" aria-selected="false">
								<input type="radio" id="radioAP"
									value="P" onclick="onAuthOpenAction()" name="fav_language"><label>Face</label>
							</div>
						</div>
						<div class="tab-content mt-4" id="pills-tabContent">
							<div class="tab-pane fade" id="fingerone-home"
								role="tabpanel" aria-labelledby="fingerone-home-tab">
								<div class="kyc-wrap">
									<div class="form-get-btn">
										<button type="button" class="btn custom-btn-sm btn-dark" onclick="captureBioAuth('F')" >Scan
											Your Finger</button>
									</div>
									<div class="form-group">
										<span id="figerIdAuthSCapture" style="display: none">
											<i class="fas fa-check text-success"></i>
										</span> <span id="figerIdAuthFCapture" style="display: block">
											<i class="fas fa-times text-danger"></i>
										</span>
										<button type="button" class="btn custom-btn-sm btn-dark" onclick="bioBasedAuth('F')">Get
											Auth</button>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="irisone-home"
								role="tabpanel" aria-labelledby="irisone-home-tab">
								<div class="kyc-wrap">
									<div class="form-get-btn">
										<button type="button" class="btn custom-btn-sm btn-dark" onclick="captureBioAuth('I')">Scan
											Your Iris</button>
									</div>
									<div class="form-group">
										<span id="irisIdAuthSCapture" style="display: none">
											<i class="fas fa-check text-success"></i>
										</span> <span id="irisIdAuthFCapture" style="display: block">
											<i class="fas fa-times text-danger"></i>
										</span>
										<button type="button" class="btn custom-btn-sm btn-dark" onclick="bioBasedAuth('I')" >Get
											Auth</button>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="face-home"
								role="tabpanel" aria-labelledby="face-home-tab">
								<div class="kyc-wrap">
									<div class="form-group">
									<div class="bar-code">
											<img id="authface_id" src="AuthfaceId" alt="">
										</div>
										<button type="button" class="btn custom-btn-sm btn-dark" onclick="captureAuthFace()">Scan Your Face</button>
									</div>
									<div class="form-get-btn">
										<button type="button" class="btn custom-btn-sm btn-dark"onclick="bioBasedAuth('P')" >Get
											Auth</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

			<div class="beneficiary-section-table">
				<div class="card-content p-3" id="prnDiv" style="display:none">
					
					<div class="table-responsive"
						style="margin: 0 auto; max-width: 1012px;">
						<table id="example2"
							class="table table-striped dataTable display nowrap "
							style="width: 100%">
							<thead>
								<tr>
									<th>S/No.</th>
									<th>REF ID</th>
									<th>FAMILY ID</th>
									<th>MEMBER ID</th>
									<th>AADHAAR</th>
									<th>PMJAY ID</th>
									<th>NAME</th>
									<th>DOWNLOAD/PRINT</th>
								</tr>
							</thead>
							<tbody>

							</tbody>

						</table>
					</div>
			
				</div>
			</div>
			
			<div class="col-12 text-danger">
				<span id="adhar_auth_required"></span>
			</div>
			
			<div class="alert alert-primary p-1 mb-1 text-center"
				style="font-size: 12px; display: none" id="countdownAuth"></div>
				
				<div id="errorAuth" style="color: red; display: flex"
				class="app-title"></div>
			<div id="errorAuth" style="color: red; display: none"
				class="app-title"></div>
				
			<div class="text-center mt-4">
				<button class="btn btn-sm btn-white"
					onclick="window.location.href='#'">Back to
										Home</button>
									<button class="btn btn-sm btn-dark"
										data-bs-toggle="modal"
										data-bs-target="#exampleModalCenter">Capture</button>
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

	<%@ include file="footer.jsp" %>

</div>

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
<form:hidden path="village_town_lgd_code"
	id="village_town_lgd_code" />
<form:hidden path="pincode" id="pincode" />
<form:hidden path="rural_urban" id="rural_urban" />
<form:hidden path="uid_aadhaar" id="uid_aadhaar" />
<form:hidden path="otpTxn" id="otpTxn" />
<form:hidden path="uidtoken" id="uidtoken" />
<form:hidden path="pidData" id="pidData" />
<form:hidden path="authType" id="authType" />

<form:hidden path="pht" id="pht" />

<input type="hidden" id="phtId_1">
<input type="hidden" id="phtId">
<input type="hidden" id="kycName1">

<input type="hidden" id="demoAuthSeccess">
<input type="hidden" id="otpSeccess">
<input type="hidden" id="otpKycSeccess">

<input type="hidden" id="fingerSeccess">
<input type="hidden" id="fingerKycSeccess">

<input type="hidden" id="irisSeccess">
<input type="hidden" id="irisKycSeccess">

<input type="hidden" id="state_name_s">
<input type="hidden" id="district_name_s">
<input type="hidden" id="vtc_s">
<input type="hidden" id="post_s">
<input type="hidden" id="uid_auth_type">

<input type="hidden" id="verification_status">
<input type="hidden" id="prnewname">
<input type="hidden" id="prnewfname">
<input type="hidden" id="prnewdob">
<input type="hidden" id="prnewGender">
<input type="hidden" id="prnewpincode">
<input type="hidden" id="prnewmobile">
<input type="hidden" id="prnewrelation">
<input type="hidden" id="prkycaadhar">
<input type="hidden" id="prkycphoto">
<input type="hidden" id="prkycname">
<input type="hidden" id="prkycfname">
<input type="hidden" id="prkycgender">
<input type="hidden" id="prkycdob">
<input type="hidden" id="prkycaddress">
<input type="hidden" id="pjbname">
<input type="hidden" id="pjbfname">
<input type="hidden" id="pjbhhdid">
<input type="hidden" id="pjbgender">
<input type="hidden" id="pjbfile">
<input type="hidden" id="pjbdname">
<input type="hidden" id="kycFname1">
<input type="hidden" id="kycFname">
<input type="hidden" id="kycGender1">
<input type="hidden" id="kycAdr1">
<input type="hidden" id="kycAdr">
<input type="hidden" id="seguid">

<input type="hidden" id="faceKycSeccess">
<input type="hidden" id="faceSeccess">


<input type="hidden" id="pidAuthData">

<input type="hidden" id="figerIdAuthSCapture">
<input type="hidden" id="figerIdAuthFCapture">
<input type="hidden" id="fingerASeccess">
<input type="hidden" id="irisIdAuthSCapture">
<input type="hidden" id="irisIdAuthFCapture">
<input type="hidden" id="irisAuthSeccess">

<input type="hidden" id="fingerAuthSeccess">
<input type="hidden" id="irisAuthSeccess">
<input type="hidden" id="faceAuthSeccess">
<input type="hidden" id="faceASeccess">



<div id="overlayRation" class="overlay_full" onclick="offRation()">
	<div class="fancy-spinner">
		<div class="ring"></div>
		<div class="ring"></div>
		<div class="dot"></div>
		<span>Please wait...</span>
		</div>	
</div>

<div id="overlay" onclick="offScan()">
	<div class="fancy-spinner">
		<div class="ring"></div>
		<div class="ring"></div>
		<div class="dot"></div>
		<span>Scan Device...</span>
		</div>	
</div>


</form:form>
		</main>

	</div>
</div>

<!-- JS File  -->
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap5.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script src="js/Verhoeff.js"></script>
<script src="js/biometricNfsa.js"></script>

<script type="text/javascript"
	src="https://cdn.datatables.net/scroller/2.0.5/js/dataTables.scroller.min.js"></script>

<!--  <script type="text/javascript" src="js/setu-custom.js"></script>  -->





<script>
var requestUri='<%=requestUri%>';


function disableBtan(){
	
	document.getElementById("kycBtn").disabled = true;
	document.getElementById("prnBtn").disabled = true;
	
}

clearInterval(downloadTimer);




function getStateRationData(state) {
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/ruralMaster",
        data: {"modeFlag": 'S'    	
       		 },
        success: function(data){

            newData = data;
            console.log(newData);
            
			
            var obj = jQuery.parseJSON( data );
         for (var key in obj) {
			
			var values =  obj[key];
			if(parseInt(state)==values){
				document.getElementById("rstate_name").innerHTML =key;
				break;
		}            
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



function getStateData() {
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/ruralMaster",
        data: {"modeFlag": 'S'    	
       		 },
        success: function(data){

            newData = data;
            console.log(newData);
            
			$("#stateCodeRural option").remove();
            var obj = jQuery.parseJSON( data );
        	 var count=0;
         for (var key in obj) {

             var values =  obj[key];
             var x = document.getElementById("stateCodeRural");
             if(count==0){
             var option = document.createElement("option");
             option.text ="Select State";
             option.value = "";
             x.add(option);
             }
             var option = document.createElement("option");
              option.text = key;
             option.value = values;
             x.add(option);

             count++;
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


function getDistrictRationData(type,district) {
    
	
    	var stateCode= document.getElementById("stateCodeRural").value;
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/ruralMaster",
        data: {"modeFlag": type,
        		"stateCode":stateCode
       		 },
        success: function(data){

            newData = data;
            console.log(newData);
		    var obj = jQuery.parseJSON( data );
        	 var count=0;
         for (var key in obj) {
			var values =  obj[key];
			if(parseInt(district)==values){
				document.getElementById("rdistrict_name").innerHTML =key;
				break;
		}            
			
        
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



function getDistrictData(type) {
    
	
    	var stateCode= document.getElementById("stateCodeRural").value;
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/ruralMaster",
        data: {"modeFlag": type,
        		"stateCode":stateCode
       		 },
        success: function(data){

            newData = data;
            console.log(newData);
			$("#districtCodeRural option").remove();
            var obj = jQuery.parseJSON( data );
        	 var count=0;
         for (var key in obj) {

             var values =  obj[key];
             var x = document.getElementById("districtCodeRural");
             if(count==0){
             var option = document.createElement("option");
             option.text ="Select District";
             option.value = "";
             x.add(option);
             }
             var option = document.createElement("option");
              option.text = key;
             option.value = values;
             x.add(option);

             count++;
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


function getRationCardDetail(){

 
   var state_code=document.getElementById("stateCodeRural").value
   
  
   
   var rationCard=document.getElementById("rationCard").value;	

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
			 $("#rationDiv").hide();
			  $("#rationError").show();
			  $("#rationError").html('No Records Found !!');
			  $("#rationInfo").hide();

			}
		var ration=	jQuery.parseJSON(data11['Details']);
		$("#state_lgd_code").val(ration.state_lgd_code);
		$("#district_lgd_code").val(ration.district_lgd_code);
		$("#subdistrict_lgd_code").val(ration.subdistrict_lgd_code);
		$("#village_town_lgd_code").val(ration.village_town_lgd_code);
		$("#pincode").val(ration.pincode);
		$("#rural_urban").val(ration.rural_urban);
	
		var data1 =ration.family;
       
       	   $("#example1").dataTable().fnDestroy();
       	   
		    var table = $('#example1').DataTable( {
				"responsive": true, "lengthChange": true, "autoWidth": false,"pagingType": "full_numbers","pageLength": 10,"fixedHeader": false,
				"language": {"emptyTable": "No Records Found !"  },"searching": true,"scrollX": true,
      		  "aaData": data1,
      		  "aoColumns": [
			  { "mData": null,
                      render: function () {
                           return '<input type="radio" style="position: relative;top: 2px;" id="editButton" name="editButton" onclick="editDetail(this)"/>';

                      }
                }, 
			    { "mData": "family_mem_id"},
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
      		            	$(row).find('td:eq(4)').html("Male")
      		            }else  if(data1.gender === 'F')
      		            {
      		            	$(row).find('td:eq(4)').html("Female")
      		            }else 
      		            {
      		            	$(row).find('td:eq(4)').html("Other")
      		            }
      		    		if(data1.year_of_birth === '0')
      		            {
      		            	$(row).find('td:eq(9)').html("")
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


$(document).ready(function() {
    $('#example1').DataTable( {
        "scrollX": true
    } );
} );


function editDetail(value) {

		var requestUri='<%=requestUri%>';
	
		var row = jQuery(value).closest('tr')
		var family_mem_id = row[0].children[1].innerHTML;
		var family_mem_id = family_mem_id.trim();
		var uid_aadhaar =row[0].children[2].innerHTML;
		uid_aadhaar=uid_aadhaar.trim();
		var member_name_eng = row[0].children[3].innerHTML;
		var gender = row[0].children[4].innerHTML;

		var relation_name = row[0].children[5].innerHTML;
		var mother_name_eng = row[0].children[6].innerHTML;
		var father_name_eng = row[0].children[7].innerHTML;
		var spouse_name_eng = row[0].children[8].innerHTML;
		var year_of_birth = row[0].children[9].innerHTML;
	 
		document.getElementById("family_mem_id").value=family_mem_id;
		document.getElementById("member_name_eng").value=member_name_eng;
		document.getElementById("gender").value=gender;
		document.getElementById("relation_name").value=relation_name;
		document.getElementById("mother_name_eng").value=mother_name_eng;
		document.getElementById("father_name_eng").value=father_name_eng;
		document.getElementById("spouse_name_eng").value=spouse_name_eng;
		document.getElementById("year_of_birth").value=year_of_birth;
		document.getElementById("uid_aadhaar").value=year_of_birth;
		
		
		document.getElementById("rfamily_mem_id").innerHTML = family_mem_id;
		document.getElementById("rmember_name_eng").innerHTML = member_name_eng;
		document.getElementById("rgender").innerHTML = gender;
		document.getElementById("rrelation_name").innerHTML = relation_name;
		document.getElementById("rmother_name_eng").innerHTML = mother_name_eng;
		document.getElementById("rfather_name_eng").innerHTML = father_name_eng;
		document.getElementById("rspouse_name_eng").innerHTML = spouse_name_eng;
		document.getElementById("ryear_of_birth").innerHTML = year_of_birth;
		document.getElementById("rrationCard").innerHTML = document.getElementById("rationCard").value;
		
		getStateRationData(document.getElementById("state_lgd_code").value);
		
		getDistrictRationData('D',document.getElementById("district_lgd_code").value);
		
		
		
		
		var state_code=document.getElementById("stateCodeRural").value
		var district_code=document.getElementById("districtCodeRural").value
		var rationCard=document.getElementById("rationCard").value;	
		var rural_urban =document.getElementById("rural_urban").value;
		$("#rationInfo").hide();
		$("#rationError").hide();
		
		validateCardStatus(state_code,district_code,family_mem_id,uid_aadhaar,rationCard,rural_urban);
		
	}

	function arrayToList(array) {
    let list = null;
    for (let i = array.length - 1; i >= 0; i--) {
        list = { value: array[i], rest: list };
    }
    return list;
}

function validateCardStatus(state_code,district_code,family_mem_id,uid_aadhaar,rationCard,rural_urban){
	
onRation();
	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/checkCardStatus",
         data: {
       	 "uid_aadhaar": uid_aadhaar,
       	 "state_code": state_code,
       	 "rationCard": rationCard,
		 "district_code":district_code,
		 "rural_urban":rural_urban,
	   		},
        success: function(data){
       	 console.log(data);
       	offRation();
       	 if(data == "C"){
             $('#rationInfo').html("Beneficiary Ayushman Card Generated. For download please click on Print button. ");
        	  $("#rationInfo").show();
        	  $("#rationError").hide();
        	  document.getElementById("prnBtn").disabled = false;
      	  }else if(data=='N'){
			 $("#rationError").hide();
             $('#rationInfo').html("Beneficiary eKYC is pending.");
        	 $("#rationInfo").show();
        	 document.getElementById("kycBtn").disabled = false;
      	  }
      	  
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

function isNumberKey(evt) {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
 } 
 
  function validateAadhar(){
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
       	
        }
        }
        
    }

function validateAadharAuth(){
        var adhhar= document.getElementById("aadhar_auth").value;

        if (adhhar==null || adhhar=="")
        {
         document.getElementById('adhar_auth_required').innerHTML="Please Enter Aadhaar Number.";
        }
        else 
        {
		if(!adhhar.verhoeffCheck())  {     
             document.getElementById("aadhar_auth").value="";
             document.getElementById('adhar_auth_required').innerHTML="Please Enter Valid Aadhaar Number.";             
        }else{
       	 document.getElementById('adhar_auth_required').innerHTML="";
       	
        }
        }
        
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
document.getElementById('aadhar_auth').addEventListener('change', addCommas, false);



function onOpenAction(){
	
	
	
	if (document.getElementById("radioO").checked) {
		document.getElementById("entOtp").style.display="none"; 
	}
	else if (document.getElementById("radioF").checked) {
		 portscanView();
	}
	else if (document.getElementById("radioI").checked) {
		  portscanView();
	
	}else if (document.getElementById("radioP").checked) {
	
	   
	}
	   
 }
 function portscanView() {
	onScan();
	setTimeout(function() { portscan(); }, 5000);
			
}

var downloadTimer;
function getOtp(){
   
   var requestUri='<%=requestUri%>';

var aadhar =document.getElementById("aadhar").value;


if(aadhar==""){
	alert("Please Enter Aadhaar Number.");
	document.getElementById("aadhar").focus();;
	return false;
}

onRation();;
   	$.ajax({
       type: "POST",
        url: "/"+requestUri+"/genOtp",
       data: {
      	 "aadhar": aadhar  	
      		 },
       success: function(data){
      	 console.log(data);
      	offRation();
      	  var obj = jQuery.parseJSON( data );
           if(obj['status'] == "Y"){
                $('#errorOtp').hide('slow');
                $('#infoOtp').show('slow');
                
             var timeleft = "180";
			   downloadTimer = setInterval(function(){
		  document.getElementById("countdown").innerHTML = "OTP Sent to Registered mobile: <b>"+obj['mobile'] +"</b>  You can get OTP again after <b>"+timeleft+ "</b> seconds";
		  timeleft -= 1;
		   document.getElementById("countdown").style.display = "block";
		  document.getElementById("optBtn").disabled = true;
		  document.getElementById("entOtp").style.display="flex";
		 // document.getElementById("otpnew").style.display="block";
		  
		  if(timeleft <= 0){
			clearInterval(downloadTimer);
			document.getElementById("optBtn").disabled = false;
			 document.getElementById("countdown").style.display = "none";
			document.getElementById("countdown").innerHTML = "";
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
		//	document.getElementById("next3").disabled = false;
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
  
  
  function otpBasedKyc(){
  
  
  var requestUri='<%=requestUri%>';
  
  document.getElementById("optBtn").disabled = false;
 //document.getElementById("countdown").innerHTML = "";

 
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
	
	onRation() ;
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
      	offRation() ;
      	  var obj = jQuery.parseJSON( data );
           if(obj['status'] == "Y"){
			document.getElementById("kycDetailsDiv").style.display = "block";
			clearInterval(downloadTimer);
			document.getElementById("countdown").innerHTML = "ekyc Successfully completed.";
           	// $('#infoOtp').html("ekyc Successfully completed.");
           	// document.getElementById("otpLevel").style.display = "none";
           	  document.getElementById("otp-home").style.display = "none";
           	 
           	  document.getElementById("otpKyc").style.display = "none";
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
             //   $('#kycFname').html(obj['kycFname']);
              //   $('#kycAdr').html(obj['kycAdr']);
                 
               document.getElementById("vtc_s").value=obj['vtc_s'];
               document.getElementById("district_name_s").value=obj['district_name_s'];
               document.getElementById("state_name_s").value=obj['state_name_s'];
               document.getElementById("post_s").value=obj['post_s'];
                document.getElementById("uid_auth_type").value='1'; 
                document.getElementById("verification_status").value='C';
                 
		//	 document.getElementById("otpIdCancel").style.display = "none";
		//	 document.getElementById("otpIdCheck").style.display = "block";
			 //document.getElementById("radioD").disabled = true;
			// document.getElementById("finalDiv").style.display = "block"; 
			document.getElementById("otp").style.display = "none";
			document.getElementById("optBtn").style.display = "none";
			document.getElementById("otpKyc").style.display = "none";
			
			document.getElementById("radioF").disabled = true; 
			document.getElementById("radioI").disabled = true;  
               
		//	document.getElementById("next2").disabled = false;
		//	document.getElementById("next3").disabled = false;
		//	document.getElementById("finishButton").disabled = false; 
			//document.getElementById("Previous2").disabled = false; 
			//document.getElementById("Previous3").disabled = false; 
			
			document.getElementById("otpKycSeccess").value="Y";
			document.getElementById("authType").value="K";
			
			
            }else{
			 
			 //openTab2();
            	 $('#errorOtp').html("Kyc failed.");
            //	  document.getElementById("next3").disabled = true;
                $('#infoOtp').hide('slow');
                $('#errorOtp').show('slow');
                
            }
        },
        error: function(e){
		 offRation() ;
           if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
	  alert('API Gateway not respond. Please try again.');
  }else{
          	alert('Your session has been expired. Please relogin again.');
		window.location.href='sessionExpire';
}
      }
 
 });

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
 onRation() ;
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
      		offRation() ;
      	  var obj = jQuery.parseJSON( data );
           if(obj['status'] == "Y"){
			document.getElementById("kycDetailsDiv").style.display = "block";
			document.getElementById("countdown").style.display = "block";
           	document.getElementById("countdown").innerHTML = "ekyc Successfully completed.";
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
             //  document.getElementById("phtId").src="data:image/jpeg;base64,"+obj['pht'];
			document.getElementById("pht").value=obj['pht'];
            //   $('#kycFname').html(obj['kycFname']);
             //  $('#kycAdr').html(obj['kycAdr']);
               
               document.getElementById("vtc_s").value=obj['vtc_s'];
               document.getElementById("district_name_s").value=obj['district_name_s'];
               document.getElementById("state_name_s").value=obj['state_name_s'];
               document.getElementById("post_s").value=obj['post_s'];
               document.getElementById("uid_auth_type").value='2';
               document.getElementById("verification_status").value='C';
               
                
		   if(bioValue=='F'){
			 document.getElementById("radioI").disabled = true;  
			 document.getElementById("fingerKycSeccess").value="Y";
			 document.getElementById("radioP").disabled = true; 
			 }else if(bioValue=='I'){
			 document.getElementById("radioF").disabled = true; 
			 document.getElementById("irisKycSeccess").value="Y";
			  document.getElementById("radioP").disabled = true; 
			 }
			 else if(bioValue=='P'){
			  document.getElementById("radioI").disabled = true; 
                 document.getElementById("radioF").disabled = true;  
                 document.getElementById("faceKycSeccess").value="Y";
                 document.getElementById("faceSeccess").value="Y";
			 }
			 
			
			document.getElementById("radioO").disabled = true; 
			
           	document.getElementById("authType").value="K";
			
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

 onRation() ;
	$.ajax({
       type: "POST",
        url: "/"+requestUri+"/facePhoto",
        data: {
      	  "aadhar": aadhar,
      	  "bioType":'P',
      	 },
       success: function(data){
      	 console.log(data);
      		offRation() ;
      	  var obj = jQuery.parseJSON( data );
           if(obj['status'] == "Y"){
           	 $('#errorOtp').hide('slow');
                $('#infoOtp').show('slow');
			 
               document.getElementById("face_id").src="data:image/jpeg;base64,"+obj['facePht'];
			document.getElementById("pidData").value=obj['pidData'];
			$('#errorFaceKyc').hide('slow');
            }else{
			 reGenFaceQrCode();
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

  function reGenFaceQrCode(){
   	var requestUri='<%=requestUri%>';
   		document.getElementById("face_id").src="faceId";
    }



function formatDate1(date) {
    
    var date1 = date.split("-").reverse().join("-");
   
    return date1;
    }


function saveNfsData(){

var requestUri = '<%=requestUri%>';
var authType = document.getElementById("authType").value;


var state_code=document.getElementById("stateCodeRural").value
var district_code=document.getElementById("districtCodeRural").value
var rationCard=document.getElementById("rationCard").value;	
var rural_urban =document.getElementById("rural_urban").value;

var name_s = document.getElementById("kycName1").innerHTML;
if (name_s == '')
name_s = ' ';

var gender_s = document.getElementById("kycGender1").innerHTML;

if (gender_s == '')
gender_s = ' ';

var dob_s = document.getElementById("kycDob1").innerHTML;
dob_s = formatDate1(dob_s);
if (dob_s == 'NaN-NaN-NaN')
dob_s = ' ';

var address_s = document.getElementById("kycAdr1").innerHTML;
if (address_s == '')
address_s = ' ';

var father_name_s = document.getElementById("kycFname1").innerHTML;


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
var ruralUrbanFlag = document.getElementById("rural_urban").value;
var uid_aadhaar = document.getElementById("aadhar").value;
uid_aadhaar = uid_aadhaar.replaceAll(" ", "");

if (uid_aadhaar == '')
uid_aadhaar = ' ';

var uid_auth_type = document.getElementById("uid_auth_type").value;
if (uid_auth_type == '')
uid_auth_type = ' ';

var operationType = "BIS2.0_GUID";


var verification_status = document.getElementById('verification_status').value;
var stateName = document.getElementById('rstate_name').innerHTML;
var districtName = document.getElementById('rdistrict_name').innerHTML;

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
onRation();
document.getElementById("ekycComplete").disabled = true;
$.ajax({
  type: "POST",
  url: "/" + requestUri + "/saveKycNfsa",
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
	"stateName": stateName,
	"districtName": districtName,
	"verification_status": verification_status,
	"father_name_s": father_name_s,
	"districtCode": district_code,
	"district_code": district_code,
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
	
  },
  success: function (data) {
	console.log(data);
	offRation();
	var obj = jQuery.parseJSON(data);
	if (obj['status'] == "Y") {

				document.getElementById("countdown").style.display = "block";
            	document.getElementById("countdown").innerHTML = "Beneficiary e-KYC has been Successfully completed. Reference ID :" + obj['refId'] + " and PMJAY_ID :" + obj['pmjayID'];
 
	  $('#errorUpdateKyc').hide('slow');
	  $('#countdown').show('slow');
	  
	  //document.getElementById("finishButton").disabled = true;
	  //document.getElementById("Previous3").disabled = true;                                  
	  //document.getElementById("canFinalButon1").style.display = "none";
	  
	  //document.getElementById("backSearch").style.display = "initial";
	} 
	else {
	  $('#errorUpdateKyc').html("Beneficiary e-KYC Updation failed.");
	  $('#countdown').hide('slow');
	  $('#errorUpdateKyc').show('slow');
	  document.getElementById("ekycComplete").disabled = false;

	}
  },
  error: function (e) {
  document.getElementById("ekycComplete").disabled = false;
  offRation();
	alert('Your session has been expired. Please relogin again.');
	window.location.href = 'sessionExpire';
  }

});

} else {

alert("Please check declaration.");
return false;
}



}


function onAuthOpenAction(){
	
 if (document.getElementById("radioAF").checked) {
		 portscanView();
	}
	else if (document.getElementById("radioAI").checked) {
		  portscanView();
	
	}else if (document.getElementById("radioAP").checked) {
	
	   
	}
	   
 }
 
   
   
   
 function bioBasedAuth(bioValue){


var requestUri='<%=requestUri%>';
  
 document.getElementById("countdownAuth").innerHTML = "";
  
 var aadhar =document.getElementById("aadhar_auth").value;


var aadhar =document.getElementById("aadhar_auth").value;
var pidData =document.getElementById("pidAuthData").value;

if(aadhar==""){
	alert("Please Enter Aadhaar Number.");
	document.getElementById("aadhar_auth").focus();;
	return false;
}
if(pidData==""){
	alert("Please Caputre Biometric.");
	return false;
}
 onRation() ;
	$.ajax({
       type: "POST",
        url: "/"+requestUri+"/bioBasedAuth",
        data: {
      	 "aadhar": aadhar,
      	 "pidData": pidData,
      	 "bioType":bioValue
      		 },
       success: function(data){
      	 console.log(data);
      		offRation() ;
      	  var obj = jQuery.parseJSON( data );
           if(obj['status'] == "Y"){
			document.getElementById("countdownAuth").style.display="block";
			document.getElementById("countdownAuth").innerHTML = "Authentication Completed. ";			  
                
		   if(bioValue=='F'){
			 document.getElementById("radioAI").disabled = true;  
			 document.getElementById("fingerAuthSeccess").value="Y";
			 document.getElementById("radioAP").disabled = true; 
			 }else if(bioValue=='I'){
			 document.getElementById("radioAF").disabled = true; 
			 document.getElementById("irisAuthSeccess").value="Y";
			  document.getElementById("radioAP").disabled = true; 
			 }
			 else if(bioValue=='P'){
			  document.getElementById("radioAI").disabled = true; 
                 document.getElementById("radioAF").disabled = true;  
                 document.getElementById("faceAuthSeccess").value="Y";
                 document.getElementById("faceASeccess").value="Y";
			 }
			 
			
		   	document.getElementById("authType").value="A";
			document.getElementById("prnDiv").style.display="block";
			getAyushmanCardDetail();
			
            }else{
			 $('#errorAuth').html("Authentication failed.");
			    $('#errorAuth').show('slow');
				document.getElementById("countdownAuth").style.display="none";
                
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
 


function getAyushmanCardDetail(){

 
   var state_code=document.getElementById("stateCodeRural").value
   var district_code=document.getElementById("districtCodeRural").value
   var rationCard=document.getElementById("rationCard").value;
   var aadhaar=document.getElementById("aadhar_auth").value;   

if(rationCard==""){
		alert("Please Enter the Ration Card Number.");
		document.getElementById("rationCard").focus();;
		return false;
}
if(aadhar_auth==""){
		alert("Please Enter the Aadhaar Number.");
		document.getElementById("aadhar_auth").focus();;
		return false;
}
onRation();
$.ajax({
        type: "POST",
        url: "/"+requestUri+"/getCardDetails",
        data: {
        "state_code": state_code,
        "district_code":district_code,
		"aadhar_auth": aadhaar,
        "rationCard":rationCard,
       		 },
        success: function(data){
            newData = data;
            offRation();
          
		   var data1 = jQuery.parseJSON( newData );
		    console.log(newData);
			var i=1;
           $("#example2").dataTable().fnDestroy();
       	   
		    var table = $('#example2').DataTable( {
				"responsive": true, "lengthChange": true, "autoWidth": false,"pagingType": "full_numbers","pageLength": 10,"fixedHeader": false,
				"language": {"emptyTable": "No Records Found !"  },"searching": true,"scrollX": true,
      		  "aaData": data1,
      		  "aoColumns": [
				{ "render": function(newData,type,full,meta){return i++;}   }  , 
			    { "mData": "guid"},
				{ "mData": "nfsaid"},
      		    { "mData": "memberid"},
				{ "mData": "uidenc"},
				{ "mData": "pmjayid"},
      		    { "mData": "name"},
      		    { "mData": null,
                        render: function () {
                             return '<a href="#" id="editButton" onclick="printCard(this)">Print Ayushman Card</a>';
  
                        }
                 },
      		  	],
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


function captureAuthFace(){
	
  
  var requestUri='<%=requestUri%>';
  
  document.getElementById("optBtn").disabled = false;
  document.getElementById("countdownAuth").innerHTML = "";
  
 var aadhar =document.getElementById("aadhar_auth").value;


if(aadhar==""){
	alert("Please Enter Aadhaar Number.");
	document.getElementById("aadhar_auth").focus();;
	return false;
}

 onRation() ;
	$.ajax({
       type: "POST",
        url: "/"+requestUri+"/authfacePhoto",
        data: {
      	  "aadhar": aadhar,
      	  "bioType":'P',
      	 },
       success: function(data){
      	 console.log(data);
      		offRation() ;
      	  var obj = jQuery.parseJSON( data );
           if(obj['status'] == "Y"){
           	 $('#errorAuth').hide('slow');
           document.getElementById("authface_id").src="data:image/jpeg;base64,"+obj['facePht'];
			document.getElementById("pidAuthData").value=obj['pidData'];
			$('#errorFaceKyc').hide('slow');
            }else{
			 reGenFaceAuthQrCode();
			 $('#errorAuth').html("Face Photo Capture Failed.");
             $('#errorAuth').show('slow');
                
            }
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

  function reGenFaceAuthQrCode(){
   	var requestUri='<%=requestUri%>';
   		document.getElementById("authface_id").src="AuthfaceId";
    }




  
</script>


</body>

</html>


<div class="modal fade" id="exampleModalCenter" tabindex="-1"
	aria-labelledby="exampleModalCenterTitle" aria-modal="true"
	role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalCenterTitle">Capture
					Photo with Ayushman Card</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="text-center my-2">
					<button type="button" class="btn btn-sm btn-dark">Capture</button>
				</div>
				<div class="col-12 col-md-5 mx-auto text-center my-2">
					<img src="images/user-profile-l.jpg"
						class="img-thumbnail rounded mx-auto d-block" alt="">
				</div>
			</div>
			<div class="modal-footer justify-content-center">
				<button type="button" class="btn btn-sm btn-white">Re-capture</button>
				<button type="button" class="btn btn-sm btn-dark">Save</button>
			</div>
		</div>
	</div>
</div>