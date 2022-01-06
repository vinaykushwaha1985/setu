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

<link rel="icon" type="image/ico" sizes="16x16" href="images/favicon.ico">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Sora:wght@100;200;300;400;500;600;700;800&display=swap"
    rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  <link rel="stylesheet" href="sass/bootstrap.min.css">
  <link rel="stylesheet" href="sass/dataTables.bootstrap5.min.css">
  <link rel="stylesheet" href="sass/setu-custom.css"> 
  <!-- <link rel="stylesheet" href="css/adminlte.min.css"> -->
  <!-- DataTables -->
  <link rel="stylesheet" href="css/datatable/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="css/datatable/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="css/datatable/buttons.bootstrap4.min.css">
  

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


<style>
body {font-family: Arial, Helvetica, sans-serif;}

#myImg {
  border-radius: 5px;
  cursor: pointer;
  transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
}

/* Caption of Modal Image */
#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}

/* Add Animation */
.modal-content, #caption {  
  -webkit-animation-name: zoom;
  -webkit-animation-duration: 0.6s;
  animation-name: zoom;
  animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
  from {-webkit-transform:scale(0)} 
  to {-webkit-transform:scale(1)}
}

@keyframes zoom {
  from {transform:scale(0)} 
  to {transform:scale(1)}
}

/* The Close Button */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}
</style>


</head>

<body class="mobile" onload="getStateDataS(),openUrbanRural()">

<div class="beneficiary-main-wrap">
	<div class="row flex-nowrap">
		<%@ include file="leftColumn.jsp" %>

		<main class="col">
			<nav>
			<%@ include file="header.jsp" %>
					
	</nav>
	<form:form action="" method="POST" commandName="command" enctype="multipart/form-data">
	<div class="beneficiary-section">
		<main>
			<section class="SECC-data">
			<div class="container">
				<div class="px-1 px-md-3">
					<div class="col-12">
						<h2><span>PMJAY - </span>State scheme's beneficiaries </h2>
						<div class="text-center">
							<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <li class="nav-item" role="presentation">               
                <div class="form-check " id="Urban-home-tab" data-bs-toggle="pill" data-bs-target="#Urban-home" role="tab" aria-controls="Urban-home" aria-selected="false">
                  <input class="form-check-input" type="radio" id="urban" name="urFlag"  value="U" onclick="openUrbanRural()" >
                  <label class="form-check-label" for="urban">
                  Urban
                  </label>
                </div>
                </li>
                <li class="nav-item" role="presentation">               
                <div class="form-check active" id="Rural-profile-tab" data-bs-toggle="pill" data-bs-target="#Rural-profile" role="tab" aria-controls="Rural-profile" aria-selected="true">
                  <input class="form-check-input" type="radio"  id="rural" name="urFlag" value="R" checked onclick="openUrbanRural()" >
                  <label class="form-check-label" for="rural">
                  Rural
                  </label>
                </div>
                </li>
              </ul>
						</div>
						
							
							<div class="tab-content"
								id="pills-tabContent">
								<div class="tab-pane fade show active" id="pills-nfsa"
									role="tabpanel" aria-labelledby="pills-nfsa-tab">
																			
										<div class="row">
								
									<div class="col-12 col-md-3 col-sm-3 mb-3">
										<div class="custom-form-group form-group">
											<form:select path="stateCodeRural" id="stateCodeRural"
										tabindex="1" class="form-select" style="width: 100%;"
										onchange="getDistrictData('D')">
										<option value="">Select State</option>
									</form:select>
									</div>
								</div>
								
								<div class="col-12 col-md-3 col-sm-3 mb-3">
									<div class="custom-form-group form-group">									  
									  <form:select path="stateScheme"	id="stateScheme" tabindex="2" class="form-select" onchange="openUrbanRural()">
									  <option value="">Select Scheme</option>                              
									  </form:select>
									</div> 
							   </div>
											
								<div class="col-12 col-md-3 col-sm-3 mb-3">
									<div class="custom-form-group form-group">
										<form:select path="districtCodeRural" id="districtCodeRural" tabindex="3" class="form-select" style="width: 100%;" onchange="openUrbanRural()">
										<option value="">Select District</option>
				
										</form:select>
									</div>
								</div>
								
									<div class="col-12 col-md-3 col-sm-3 mb-3" id="subSchemeDiv" style="display:none">
									<div class="custom-form-group form-group">
										<form:select path="subSchemeId" id="subSchemeId" tabindex="3" class="form-select" style="width: 100%;" onchange="getDucumentTypeData()">
										<option value="">Select Sub Scheme</option>
				
										</form:select>
									</div>
								</div>
									<div class="col-12 col-md-3 col-sm-3 mb-3" id="ducumentTypeDiv" style="display:none">
									<div class="custom-form-group form-group">
										<form:select path="documentTypeId" id="documentTypeId" tabindex="3" class="form-select" style="width: 100%;">
										<option value="0">Select Document Type</option>
										</form:select>
									</div>
								</div>
								
									<div class="col-12 col-md-3 col-sm-3 mb-3" id="DocumentTypeIdDiv" style="display:none">
									<div class="custom-form-group form-group scheme-select">
									<label for="" style="color: olive;">Document ID</label> 
										<input path="documentId" type="text" class="form-control" id="documentId">
									</div>
								</div>
				
				<div class="col-12 col-md-3 col-sm-3 mb-3" id="rationDiv" style="display:none">
					<div class="custom-form-group form-group scheme-select">
						<label for="" style="color: olive;">Ration card Number</label>

						<form:input path="adcdRation" id="adcdRation" tabindex="4"
							class="form-control" style="color: olive;" />
					</div>
				</div>
				
				<div class="col-12 col-md-3 col-sm-3 mb-3" id="mobileDiv" style="display:none">
					<div class="custom-form-group form-group scheme-select">
						<label for="" style="color: olive;">Mobile Number</label>
						<form:input path="adcdMobile" id="adcdMobile" tabindex="4"
							class="form-control" style="color: olive;" onkeypress="return isNumberKey(event)" maxlength="10" />
																		   
					</div>
				</div>
				
				<div class="col-12 col-md-3 col-sm-3 mb-3" id="nfsaIdDiv" style="display:none">
					<div class="custom-form-group form-group scheme-select">
						<label for="" style="color: olive;">NFSA ID</label> 
						<form:input id="nfsaId" path="nfsaId" tabindex="4" class="form-control" style="color: olive;" />
					</div>
				</div>
				
				<div class="col-12 col-md-3 col-sm-3 mb-3" id="bcowDiv" style="display:none">
					<div class="custom-form-group form-group scheme-select">
						<label for="" style="color: olive;">BCOW</label>
						<form:input id="bcowId" path="bcowId" tabindex="4" class="form-control" style="color: olive;" />
					</div>
				</div>
				
			<div class="col-12 col-md-3 col-sm-3" id="familyDiv" style="display:none">
					<div class="custom-form-group form-group scheme-select">
						<label for="" style="color: olive;">Family ID</label>
						<form:input id="familyidScheme" path="familyidScheme" tabindex="4" class="form-control" style="color: olive;" />
					</div>
				</div>
				
				<div class="col-12 col-md-3 col-sm-3 mb-3" id="ruralMmjaDiv" style="display:none">
						<div class="custom-form-group form-group mb-2">
							<form:select path="blockCodeRural" id="blockCodeRural" tabindex="4" class="form-select" aria-label="Default select example" onchange="getStateData('V')">
                        	  <option value="">Select Block</option>                            
	                           
	                         </form:select>
                      
						</div>
						<div class="custom-form-group form-group">
							
						 <form:select path="villageCodeRural" id="villageCodeRural" tabindex="5"  class="form-select" aria-label="Default select example" >
	                            <option value="">Select Village</option>  
	                          <!-- <option selected>Select Village</option>
	                            <option value="201880">Abdullahpur</option><option value="201992">Abdullapur</option><option value="201908">Abhay Chand Patti</option><option value="201837">Adampur</option><option value="201952">Adampur Akabar</option><option value="201960">Ahamadpur</option><option value="201949">Akabar Khanpur</option><option value="201843">Akbalpur</option><option value="201883">Alamgirpur</option><option value="201901">Anantpur</option><option value="201989">Ara</option><option value="201935">Araji Padumpur I</option><option value="201936">Araji Padumpur Ii</option><option value="201934">Arazi Mainipur</option><option value="201965">Arazi Siddikipur</option><option value="201996">Aurahi</option><option value="201839">Babarakha</option><option value="201946">Bagmia</option><option value="201866">Bahauddinpur</option><option value="201846">Baijapur</option><option value="201896">Baispar</option><option value="201841">Baraipur</option><option value="201835">Baraiyakazi</option><option value="201851">Basaratpur</option><option value="201943">Besahoopur</option><option value="201860">Bhaisani</option><option value="201978">Bhakura</option><option value="201872">Bhateora</option><option value="202015">Bhirapur</option><option value="202005">Bishambhara Urf Zamunipur</option><option value="201876">Chak Gopalpur</option><option value="201939">Chak Hata</option><option value="201955">Chak Hazi Bux</option><option value="201971">Chak Jamalpur</option><option value="201957">Chak Lakshiman Das</option><option value="201844">Chak Mirmiriapur</option><option value="201847">Chak Mirzapur</option><option value="201850">Chak Muglani</option><option value="201877">Chak Ziyauddinpur</option><option value="201977">Chakawa</option><option value="201988">Chandi Gahana</option><option value="201858">Chaturbhujpur</option><option value="202019">Chaukidhar Sand</option><option value="201875">Chhabilepur</option><option value="201856">Chhunchha</option><option value="201941">Chitarsari</option><option value="201838">Dakkhin Patti</option><option value="201958">Dalhanpur</option><option value="201990">Daridih</option><option value="201937">Deo Chandpur</option><option value="201867">Deokali</option><option value="201925">Dera Yusuf</option><option value="201909">Derapur</option><option value="201942">Dhannupur</option><option value="202011">Dharmasari</option><option value="201894">Dhirajpur</option><option value="201993">Dudaura</option><option value="201899">Dullipur</option><option value="201932">Fagupur Khas</option><option value="201933">Fagupur Nisf</option><option value="202013">Fazulha</option><option value="201954">Garaila</option><option value="201874">Gauspur</option><option value="201998">Ghanghanawa</option><option value="201997">Girdharpur</option><option value="201940">Gopalpur</option><option value="201962">Gosaipur</option><option value="201919">Haibatpur</option><option value="201947">Haiderpur</option><option value="201889">Hajipur Urf Faridabad</option><option value="201890">Hakaripur</option><option value="201849">Hamzapur</option><option value="201873">Harakhamalpur</option><option value="201891">Harbaspur</option><option value="201921">Hardipur</option><option value="201855">Hazipur</option><option value="201832">Hindu Baghaila</option><option value="201948">Jagadishpur Akabar</option><option value="201923">Jagdishpur</option><option value="201983">Jagmalpur</option><option value="201853">Jalalpur</option><option value="201913">Jamalpur</option><option value="201927">Jamin Abdul Quadir</option><option value="201982">Janapur</option><option value="201953">Jangipur Kalan</option><option value="201981">Jangipur Khurd</option><option value="201862">Jethpura</option><option value="202017">Jhasepur</option><option value="202002">Kadipur</option><option value="201986">Kafarpur</option><option value="201904">Kalichabad</option><option value="201871">Karanja Kalan</option><option value="201878">Karanja Khurd</option><option value="201906">Karauti</option><option value="201885">Kataka</option><option value="201905">Katghara</option><option value="201845">Kersawa</option><option value="201865">Khamhaura</option><option value="201922">Khan Patti</option><option value="201895">Kharouna</option><option value="201864">Khateerpur Bhainsa</option><option value="201984">Kishunpur</option><option value="201991">Kisunpur</option><option value="202000">Kohada</option><option value="201888">Kohara Sultanpur</option><option value="201964">Kothawar</option><option value="202020">Kukuhan</option><option value="201868">Kukuripur</option><option value="201924">Kurra Patti</option><option value="201945">Kutubpur</option><option value="201916">Ladlepur</option><option value="202003">Lakhamipur</option><option value="201886">Lalmanpur</option><option value="201900">Lilapur</option><option value="201985">Lohata</option><option value="201840">Machhali Patti</option><option value="201926">Madawibibi</option><option value="201959">Mahagawan</option><option value="201903">Maharajpur</option><option value="202010">Maharoopur</option><option value="201931">Mainipur</option><option value="201859">Malhani</option><option value="201968">Mangadpur</option><option value="201870">Manwal</option><option value="201912">Milko</option><option value="201987">Mircha</option><option value="201842">Mokalpur</option><option value="201881">Molanapur</option><option value="201882">Mominpur</option><option value="201907">Muradganj</option><option value="201893">Mustafabad</option><option value="201917">Nadiyapara</option><option value="201918">Naktopur</option><option value="201831">Narauli</option><option value="201861">Nasaruddinpur</option><option value="201852">Nawada Ishawari Singh</option><option value="201929">Nawajgah</option><option value="201854">Ojhapur</option><option value="202016">Pachewara</option><option value="202001">Padumpur</option><option value="201938">Padumpur Khas</option><option value="201995">Paigipur</option><option value="201887">Palhamau</option><option value="201910">Palhamau Khurd</option><option value="202008">Parapatti</option><option value="201897">Parasrampur</option><option value="201911">Parsani</option><option value="201951">Parsottam Urf Pandeypur</option><option value="201994">Patahana</option><option value="201966">Payagpur</option><option value="201920">Piyrepur</option><option value="202009">Premapur</option><option value="201970">Rajjipur</option><option value="202006">Ramdaspur</option><option value="201930">Rampur</option><option value="201848">Rampur Dawan Singh</option><option value="201969">Sadi Patti</option><option value="201972">Saidpur Ganaur</option><option value="201967">Saidpur Namaksar</option><option value="201834">Salahadipur</option><option value="202007">Saloni Mahimapur</option><option value="201979">Samaspur</option><option value="202014">Samodhipur</option><option value="201884">Samopur Khurd</option><option value="201892">Sarai Khawaja Jamluddinpur Urf</option><option value="201980">Satahara</option><option value="201950">Shah Daudpur Urf Kakor Gahana</option><option value="202018">Shekhawalia</option><option value="201914">Shikarpur</option><option value="201974">Siddikpur</option><option value="201961">Sultanpur</option><option value="201963">Sultanpur Gaur</option><option value="201944">Sultanpur Hai</option><option value="201902">Tajapur</option><option value="201928">Tamarashapur</option><option value="202012">Tiary</option><option value="202004">Trilochanpur</option><option value="201833">Turki Baghaila</option><option value="201836">Uttar Patti</option><option value="201973">Zafarpur</option><option value="201879">Zahiruddinpur</option><option value="201999">Zamin Pakari</option><option value="201976">Zamuhai</option><option value="201956">Zasopur</option><option value="200382">bhadethi</option> -->
                      </form:select>
						</div>
				</div>
				
				<div class="col-12 col-md-3 col-sm-3 mb-3" id="urbanMmjaDiv" style="display:none">
							<div class="custom-form-group form-group">
								<form:select path="townCodeUrban" id="townCodeUrban" tabindex="4" class="form-select" aria-label="Default select example" onchange="getStateDataUrban('W')">
	                            <option value="">Select Town</option>  
	                          <!-- <option selected>Select Town</option>
	                            <option value="7588">Jafarabad (NP)</option><option value="7587">Jaunpur (NPP)</option><option value="7590">Kerakat (NP)</option><option value="7584">Khetasarai (NP)</option><option value="7586">Machhlishahr (NP)</option><option value="7589">Mariahu (NP)</option><option value="7585">Mogra Badshahpur (NPP)</option><option value="7583">Shahganj (NPP)</option>                            -->
	                          </form:select>
							</div>
							<!-- <div class="custom-form-group form-group">
								<label for="" style="color: olive;">Ward</label>
									
								<form:select path="WardCodeUrban" id="WardCodeUrban" tabindex="8" class="form-select" aria-label="Default select example" >
							                            
		                            <option value="">Select Ward</option>
		                            <option selected>Select Ward</option>
		                            <option value="0001">0001</option><option value="0002">0002</option><option value="0003">0003</option><option value="0004">0004</option><option value="0005">0005</option><option value="0006">0006</option><option value="0007">0007</option><option value="0008">0008</option><option value="0009">0009</option><option value="0010">0010</option>
		                          </form:select>
							</div> -->
				</div>
				
				<div class="col-12 col-md-3 col-sm-3 mb-3" id="nfsaDiv" style="display:none">
							<div class="custom-form-group form-group">
								<label for="" style="color: olive;">Mobile Number</label>
		
								<input id="mmm"
									class="form-control" style="color: olive;" />
							</div>
							<div class="custom-form-group form-group">
								<label for="" style="color: olive;">Mobile Number</label>
		
								<input  id="ppp"
									class="form-control" style="color: olive;" />
							</div>
				</div>
				<!-- New Code Added By Atul -->
				<div class="col-12 col-sm-3 col-md-3 col-lg-3 mb-3" id="DocumnetBrowseDiv" style="display:None">
                              <div class="custom-form-group form-group"> 
                                <form:input class="form-control" type="file" path="formFile" id="formFile" onchange="readURL(this);" accept="image/gif, image/jpeg, image/png" />
                              </div>
                            </div>
                            
                            <div class="col-12 col-sm-2 col-md-2 col-lg-2 mb-2" id="DocumnetImageDiv" style="display:None;">
                                <img id="blah" src="" class="rounded mx-auto d-block" alt="" style="border-radius: 0.25rem; max-width: 100%; height: auto;" onclick="viewImage()">
                              </div>
				
					<div class="col-12 text-center my-3" id="btnNextDiv" style="display:none">
						<button type="button" class="btn btn-sm btn-dark" onclick="onNext()">Next</button>
					</div>
				<!-- end New Code Added By Atul -->
				
				<!-- The Modal -->
				<div id="myModal" class="modal">
				  <span class="close">&times;</span>
				  <img class="modal-content" id="img01">
				  <div id="caption"></div>
				</div>
				<!--end The Modal -->
				<div class="col-12 text-center my-3" id="searchButtonDiv" style="display:block">

					<button type="button" class="btn btn-sm btn-dark"
						onclick="submitSearch()">Get Details</button>
				</div>
				</div>
			

			<div id="datahide" class="card mt-4 beneficiary-section-table" style="display:block">
                    <div class="card-header">Beneficiaries Details</div>
                    <div class="card-content p-3"> 
                      <div class="table-responsive">
                        <table id="example1" class="table table-striped dataTable" style="width:100%">
                          <thead>
                              <tr>
                                  <th>S.No.</th>
                                  <th>Ref ID</th>
                                  <th>Name</th>
                                  <th>Father's Name</th>
                                  <th>YOB</th>
                                  <th>Gender</th>
                                  <th>Mother's Name</th>
                                  <th>Relation</th>
                                  <th>Card Status</th>
                                  <th>View</th>
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
<form:hidden path="stateCodeUrban" id="stateCodeUrban" />
<form:hidden path="districtCodeUrban" id="districtCodeUrban" />

   <form:hidden path="ruralUrbanFlag" id="ruralUrbanFlag" /> 
        
        
        <form:hidden path="name" id="name" /> 
        <form:hidden path="fathername" id="fathername" /> 
        <form:hidden path="spousenm" id="spousenm" /> 
        <form:hidden path="dob" id="dob" /> 
        <form:hidden path="genderid" id="genderid" /> 
        <form:hidden path="mothername" id="mothername" /> 
        <form:hidden path="relation" id="relation" /> 
        <form:hidden path="guid" id="guid" /> 
        
         <form:hidden path="stateName" id="stateName" /> 
         
            <form:hidden path="districtName" id="districtName" /> 
            <form:hidden path="villageName" id="villageName" /> 
            <form:hidden path="townName" id="townName" /> 
            <form:hidden path="schemeName" id="schemeName" /> 
            <form:hidden path="schemeid" id="schemeid" />
            <form:hidden path="card_status" id="card_status" />
            <form:hidden path="uidtoken" id="uidtoken" /> 
            <form:hidden path="stateCode" id="stateCode"/> 
            <form:hidden path="blockName" id="blockName" /> 
			
            <form:hidden path="wardName" id="wardName" />
            
            <form:hidden path="subSchemeName" id="subSchemeName" />
            <form:hidden path="documentTypeName" id="documentTypeName" />
            <form:hidden path="documentViewId" id="documentViewId" />
             <form:hidden path="SchemedocumentImage" id="SchemedocumentImage" />
            
			
        
		<input type="hidden" id="state_code">
		<input type="hidden" id="district_code">


<div id="overlay_search" class="overlay_full" onclick="offSearch()">
  <div class="fancy-spinner">
    <div class="ring"></div>
    <div class="ring"></div>
    <div class="dot"></div>
    <span>Searching...</span>
    </div>
 </div> 
 
<div id="overlay" class="overlay_full" onclick="off()">
  <div class="fancy-spinner">
    <div class="ring"></div>
    <div class="ring"></div>
    <div class="dot"></div>
    <span>Checking Status...</span>
    </div>              
     </div> 
	
</form:form>
		</main>

	</div>
</div>

<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
  <!-- <script type="text/javascript" src="js/setu-custom.js"></script> -->
  <script src="js/jquery.min.js"></script>

<!-- DataTables  & Plugins -->
<script src="js/datatable/jquery.dataTables.min.js"></script>
<script src="js/datatable/dataTables.bootstrap4.min.js"></script>
<script src="js/datatable/dataTables.responsive.min.js"></script>
<script src="js/datatable/responsive.bootstrap4.min.js"></script>
<script src="js/datatable/dataTables.buttons.min.js"></script>
<script src="js/datatable/buttons.bootstrap4.min.js"></script>
<script src="js/datatable/jszip.min.js"></script>
<script src="js/datatable/pdfmake.min.js"></script>
<script src="js/datatable/vfs_fonts.js"></script>
<script src="js/datatable/buttons.html5.min.js"></script>
<script src="js/datatable/buttons.print.min.js"></script>
<script src="js/datatable/buttons.colVis.min.js"></script>
  



<script>
var requestUri='<%=requestUri%>';



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



function getStateDataS() {
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
	getStateShemeData();
}
function getStateShemeData(type) {
    
	
    	var stateCode= document.getElementById("stateCodeRural").value;
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/stateSchemeData",
        data: {"modeFlag": type,
        		"stateCode":stateCode
       		 },
        success: function(data){

            newData = data;
            console.log(newData);
			$("#stateScheme option").remove();
            var obj = jQuery.parseJSON( data );
        	 var count=0;
         for (var key in obj) {

            	var val =  obj[key].schemeid;
             var values =  obj[key].schemeid+"_"+obj[key].subschemeflag;
           
             var x = document.getElementById("stateScheme");
             if(count==0){
             var option = document.createElement("option");
             option.text ="Select Scheme";
             option.value = "";
             x.add(option);
             }
             if(val!='1'){
             var option = document.createElement("option");
             option.text = obj[key].schemename;
             option.value = values;
             x.add(option);
			}
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


function getSubStateShemeData() {
    var e = document.getElementById("stateScheme");
	var stateScheme = e.value.replace("_0", "");
	stateScheme = stateScheme.replace("_1", "");
    	var stateCode= document.getElementById("stateCodeRural").value;
    	
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/stateSubSchemeData",
        data: {
        		"stateCode":stateCode,
        		"schemeid":stateScheme,
        		
       		 },
        success: function(data){

            newData = data;
            console.log(newData);
			$("#subSchemeId option").remove();
            var obj = jQuery.parseJSON( data );
        	 var count=0;
         for (var key in obj) {

             var values =  obj[key];
             var x = document.getElementById("subSchemeId");
             if(count==0){
             var option = document.createElement("option");
             option.text ="Select Sub Scheme";
             option.value = "";
             x.add(option);
             }
             if(values!='1'){
             var option = document.createElement("option");
              option.text = key;
             option.value = values;
             x.add(option);
			}
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


function getDucumentTypeData() {
    
	    var subSchemeId= document.getElementById("subSchemeId").value;
    	var stateScheme= document.getElementById("stateScheme").value;
    	
	
      stateScheme = stateScheme.replace("_0", "");
	  stateScheme = stateScheme.replace("_1", "");
	
    	
    	$.ajax({
        type: "POST",
         url: "/"+requestUri+"/stateSubSchemeDucumentTypeData",
        data: {
        		"schemeid":stateScheme,
        		"subSchemeId":subSchemeId,
        		
       		 },
        success: function(data){

            newData = data;
            console.log(newData);
			$("#documentTypeId option").remove();
            var obj = jQuery.parseJSON( data );
        	 var count=0;
         for (var key in obj) {

             var values =  obj[key];
             var x = document.getElementById("documentTypeId");
             if(count==0){
             var option = document.createElement("option");
             option.text ="Select Document Type";
             option.value = "";
             x.add(option);
             }
             if(values!='1'){
             var option = document.createElement("option");
              option.text = key;
             option.value = values;
             x.add(option);
			}
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

function openUrbanRural(){
	var stateCode= document.getElementById("stateCodeRural").value;
  var scheme1=document.getElementById("stateScheme").value;																   
	$('#secc-collapse').removeClass('show');
	$('#nfsa-collapse').addClass('show');
	$('#aassli').addClass('active');
	$('#adacli').removeClass('active');
	var e = document.getElementById("stateScheme");
	var subsceme=false;
	if(e.value.includes("_1")){
	subsceme=true;
	}
	
    var stateScheme = e.value.replace("_0", "");
	stateScheme = stateScheme.replace("_1", "");
	
    if (document.getElementById("rural").checked) {
	      if(stateScheme =='5'){
	       document.getElementById("mobileDiv").style.display="block";
	        document.getElementById("rationDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	          document.getElementById("nfsaIdDiv").style.display="none";
	           document.getElementById("bcowDiv").style.display="none";
	            document.getElementById("familyDiv").style.display="none";
             
	         //for subschemediv
	         document.getElementById("ducumentTypeDiv").style.display="none";
	         document.getElementById("subSchemeDiv").style.display="none";
	         document.getElementById("DocumentTypeIdDiv").style.display="none";
	         document.getElementById("DocumnetBrowseDiv").style.display="none";
	         document.getElementById("DocumnetImageDiv").style.display="none";
	         document.getElementById("searchButtonDiv").style.display="block";
	          document.getElementById("btnNextDiv").style.display="none";
	        
	      }
	     
	     else if(stateScheme =='4'){
	       document.getElementById("rationDiv").style.display="block";
	        document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	          document.getElementById("nfsaIdDiv").style.display="none";
	          document.getElementById("urbanMmjaDiv").style.display="none";
	          document.getElementById("bcowDiv").style.display="none";
	          document.getElementById("familyDiv").style.display="none";
	          //for subschemediv
	         document.getElementById("ducumentTypeDiv").style.display="none";
	         document.getElementById("subSchemeDiv").style.display="none";
	         document.getElementById("DocumentTypeIdDiv").style.display="none";
	         document.getElementById("DocumnetBrowseDiv").style.display="none";
	         document.getElementById("DocumnetImageDiv").style.display="none";
	         document.getElementById("searchButtonDiv").style.display="block";
	          document.getElementById("btnNextDiv").style.display="none";
	      }
	      else if(stateScheme =='3'){
	        document.getElementById("rationDiv").style.display="none";
	        document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	        document.getElementById("ruralMmjaDiv").style.display="block";
	        document.getElementById("familyDiv").style.display="none";
	        //for subschemediv
	         document.getElementById("ducumentTypeDiv").style.display="none";
	         document.getElementById("subSchemeDiv").style.display="none";
	         document.getElementById("DocumentTypeIdDiv").style.display="none";
	         document.getElementById("DocumnetBrowseDiv").style.display="none";
	         document.getElementById("DocumnetImageDiv").style.display="none";
	         document.getElementById("searchButtonDiv").style.display="block";
	          document.getElementById("btnNextDiv").style.display="none";
	        
	      }
	       else if(stateScheme =='2'){
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="block";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="none";
	         document.getElementById("familyDiv").style.display="none";
	       
			
	      }
	     else if(stateScheme =='6'){
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="block";
	         document.getElementById("familyDiv").style.display="none";
			 //for subschemediv
	         document.getElementById("ducumentTypeDiv").style.display="none";
	         document.getElementById("subSchemeDiv").style.display="none";
	         document.getElementById("DocumentTypeIdDiv").style.display="none";
	         document.getElementById("DocumnetBrowseDiv").style.display="none";
	         document.getElementById("DocumnetImageDiv").style.display="none";
	         document.getElementById("searchButtonDiv").style.display="block";
	          document.getElementById("btnNextDiv").style.display="none";
	         
	      }
	       else if(stateScheme =='1'){
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="none";
	         //for subschemediv
																											
	         document.getElementById("ducumentTypeDiv").style.display="none";
	         document.getElementById("subSchemeDiv").style.display="none";
	         document.getElementById("DocumentTypeIdDiv").style.display="none";
	         document.getElementById("DocumnetBrowseDiv").style.display="none";
	         document.getElementById("DocumnetImageDiv").style.display="none";
	         document.getElementById("searchButtonDiv").style.display="block";
	          document.getElementById("btnNextDiv").style.display="none";
	         
	       document.getElementById("familyDiv").style.display="none";
	     }else if(stateCode=="24" && scheme1.includes("_0")){
																				 
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="none";
	         document.getElementById("familyDiv").style.display="block";
	
	         document.getElementById("ducumentTypeDiv").style.display="none";
	         document.getElementById("subSchemeDiv").style.display="none";
	         document.getElementById("DocumentTypeIdDiv").style.display="none";
	         document.getElementById("DocumnetBrowseDiv").style.display="none";
	         document.getElementById("DocumnetImageDiv").style.display="none";
	         document.getElementById("searchButtonDiv").style.display="block";
	         document.getElementById("btnNextDiv").style.display="none";
	      }
		  else if(stateCode=="24" && scheme1.includes("_1")){
		  
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="none";
	         document.getElementById("familyDiv").style.display="none";
		  
			 document.getElementById("ducumentTypeDiv").style.display="block";
	         document.getElementById("subSchemeDiv").style.display="block";
	         document.getElementById("DocumentTypeIdDiv").style.display="block";
	         document.getElementById("DocumnetBrowseDiv").style.display="block";
	         document.getElementById("DocumnetImageDiv").style.display="block";
	         document.getElementById("searchButtonDiv").style.display="none";
	         document.getElementById("btnNextDiv").style.display="block";
	         
			getSubStateShemeData();
		   		
	      }
	     
	      document.getElementById("ruralUrbanFlag").value=document.getElementById("rural").value;
	      
	     getStateData('B');
	      
        
    }
    else if (document.getElementById("urban").checked) {
		     if(stateScheme =='5'){
		       document.getElementById("mobileDiv").style.display="block";
	           document.getElementById("rationDiv").style.display="none";
	           document.getElementById("ruralMmjaDiv").style.display="none";
	           document.getElementById("nfsaIdDiv").style.display="none";
	           document.getElementById("urbanMmjaDiv").style.display="none";
	           document.getElementById("bcowDiv").style.display="none";
				document.getElementById("familyDiv").style.display="none";													   
		      }
		     
		     else if(stateScheme =='4'){
		        document.getElementById("rationDiv").style.display="block";
	       	    document.getElementById("mobileDiv").style.display="none";
	       	    document.getElementById("ruralMmjaDiv").style.display="none";
	       	    document.getElementById("nfsaIdDiv").style.display="none";
	       	    document.getElementById("urbanMmjaDiv").style.display="none";
	       	    document.getElementById("bcowDiv").style.display="none";
					document.getElementById("familyDiv").style.display="none";													 
		      }
		    else if(stateScheme =='3'){
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="block";
	         document.getElementById("bcowDiv").style.display="none";
					document.getElementById("familyDiv").style.display="none";												 
	      }
	       else if(stateScheme =='2'){
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="block";
	         document.getElementById("bcowDiv").style.display="none";
	         document.getElementById("familyDiv").style.display="none";
	      }
	       else if(stateScheme =='6'){
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="block";
	         document.getElementById("familyDiv").style.display="none";
	         }
	         else if(stateScheme =='1'){
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="none";
																	 
	        document.getElementById("familyDiv").style.display="none";
	         
	      }else if(stateCode=="24" && scheme1.includes("_0")){
	        document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="none";
	         document.getElementById("familyDiv").style.display="block";
			 //sub scheme div show hide
			 document.getElementById("ducumentTypeDiv").style.display="none";
	         document.getElementById("subSchemeDiv").style.display="none";
	         document.getElementById("DocumentTypeIdDiv").style.display="none";
	         document.getElementById("DocumnetBrowseDiv").style.display="none";
	         document.getElementById("DocumnetImageDiv").style.display="none";
	         document.getElementById("searchButtonDiv").style.display="none";
	         document.getElementById("btnNextDiv").style.display="none";
			 
			}else if(stateCode=="24" && scheme1.includes("_1")){
			
	         document.getElementById("rationDiv").style.display="none";
	         document.getElementById("mobileDiv").style.display="none";
	         document.getElementById("urbanMmjaDiv").style.display="none";
	         document.getElementById("ruralMmjaDiv").style.display="none";
	         document.getElementById("nfsaIdDiv").style.display="none";
	         document.getElementById("bcowDiv").style.display="none";
	         document.getElementById("familyDiv").style.display="none";
	 		 //sub scheme div show hide
			 document.getElementById("ducumentTypeDiv").style.display="block";
	         document.getElementById("subSchemeDiv").style.display="block";
	         document.getElementById("DocumentTypeIdDiv").style.display="block";
	         document.getElementById("DocumnetBrowseDiv").style.display="block";
	         document.getElementById("DocumnetImageDiv").style.display="block";
	         document.getElementById("searchButtonDiv").style.display="none";
	          document.getElementById("btnNextDiv").style.display="block";
	         
			getSubStateShemeData();
	      }
	      
		      document.getElementById("ruralUrbanFlag").value=document.getElementById("urban").value;
		      getStateDataUrban('T');
    }
    
  }


function onSearch() {
    document.getElementById("overlay_search").style.display = "flex";
  }
  
  function offSearch() {
    document.getElementById("overlay_search").style.display = "none";
  }
  
  function on() {
    document.getElementById("overlay").style.display = "flex";
  }
  
  function off() {
    document.getElementById("overlay").style.display = "none";
  }


$(document).ready(function() {
    $('#example1').DataTable( {
        "scrollX": true
    } );
} );




function submitSearch(){
	 var state=document.getElementById("stateCodeRural").value;
     var dist=document.getElementById("districtCodeRural").value;
	 var scheme1=document.getElementById("stateScheme").value;													   
     var scheme=document.getElementById("stateScheme").value;
								  
  scheme = scheme.replace("_0", "");
  scheme = scheme.replace("_1", "");								
  var mobile=document.getElementById("adcdMobile").value;
  var nfsa=document.getElementById("nfsaId").value;
  var towncode=document.getElementById("townCodeUrban").value;
  var urFlag= document.getElementById("ruralUrbanFlag").value;  
  var blockCodeRural= document.getElementById("blockCodeRural").value;
  var villageCodeRural= document.getElementById("villageCodeRural").value;
  var bcowId= document.getElementById("bcowId").value;
  
	if(state==""){
    alert("Please select State");    
	return false;
  }else if(scheme==""){
    alert("Please select Scheme");
    return false;
  }else if(dist==""){
    alert("Please select District");
    return false;
  }else if(scheme=="5" && mobile==""){		
		alert("Please Enter Mobile");
    	return false;		
	}	else if(scheme=="2" && nfsa==""){		
		alert("Please Enter NFSA ID");
    	return false;
		
	}else if(scheme=="3" && urFlag=="U" && towncode==""){		
		alert("Please Select Town Code");
    	return false;
		
	}else if(scheme=="3" && urFlag=="R" && blockCodeRural==""){		
		alert("Please Select Block Code");
    	return false;		
	}else if(scheme=="3" && urFlag=="R" && villageCodeRural==""){		
		alert("Please Select Village Code");
    	return false;		
	}
	else if(scheme=="3" && urFlag=="R" && villageCodeRural==""){		
		alert("Please Select Village Code");
    	return false;		
	}
	else if(scheme=="6"  && bcowId==""){		
		alert("Please Enter Bcow Id");
    	return false;		
	}
  
	//onSearch();
    var requestUri='<%=requestUri%>';
    
   var stateSchemeval=scheme;
     if(stateSchemeval=="3"){
     	submitSearchMmja();
     }else if(stateSchemeval=="6"){
     	submitSearchBocw();
	}else if(state=="24" && scheme1.includes("_0")){
      var famid=document.getElementById("familyidScheme").value;
      if(famid==""){
        alert("Please Enter Family ID");    
	      return false;
     }
      submitSearchOther();				  
     }else if(stateSchemeval=="2"){
      var state_code=document.getElementById("stateCodeRural").value;   
   	  var rationCard=document.getElementById("nfsaId").value;
	if(rationCard==""){
		alert("Please Enter the Ration Card Number.");
		document.getElementById("rationCard").focus();;
		return false;
	}

	
	$("#rationDiv").hide();
	$("#rationError").hide();
	$("#rationInfo").hide();
	onSearch();
	$.ajax({
        type: "POST",
        url: "/"+requestUri+"/getRationCardData",
        data: {
        "stateCode": state_code,
        "rationCard":rationCard,
       		 },
        success: function(data){
            newData = data;
            console.log(newData);
            offSearch();
            if(newData !== null){
         //   $("#rationDiv").show();
            $("#datahide").show();
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
		console.log(data1);
       	var i=1;
       	$("#example1").dataTable().fnDestroy();
       offSearch();
         var i=1;
              var table = $('#example1').DataTable( {
          "responsive": false, "lengthChange": false, "autoWidth": false,"pagingType": "full_numbers","pageLength": 50,
           "language": {"emptyTable": "No beneficiary available"  },
              "aaData": data1,
              
              "aoColumns": [
                { "render": function(ration,type,full,meta){return i++;}   },           
                { "mData": "family_mem_id"},
                { "mData": "member_name_eng"},
                { "mData": "father_name_eng"},
                { "mData": "year_of_birth"},
                { "mData": "gender"},
                { "mData": "mother_name_eng"},
                { "mData": "relation_name"},
                { "mData": null},
                { "mData": null,
                        render: function () {
                             return '<a href="#" id="editButton" onclick="editDetail(this)">View</a>';
  
                        }
                      }
                   ],
                   createdRow: function (row, data1, dataIndex) 
      		        {
      		    		$(row).find('td:eq(8)').html("Card not made") 
        			   if(data1.gender === 'M')
      		            {
      		            	$(row).find('td:eq(5)').html("Male")
      		            }else  if(data1.gender === 'F')
      		            {
      		            	$(row).find('td:eq(5)').html("Female")
      		            }else 
      		            {
      		            	$(row).find('td:eq(5)').html("Other")
      		            }
      		    		if(data1.year_of_birth === '0')
      		            {
      		            	$(row).find('td:eq(4)').html("")
      		            }
      					
      				},
              
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
              $('#example1_filter').find('input[type="search"]').attr('title','You can search Name, Father’s name, DOB etc from here').attr('style','cursor:pointer');
           },   

         error: function(e){
          offSearch();
            if('Bis_Login'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}	
         }
    });
    }else{
    
    
	
    if(urFlag=="R"){
    var stateCodeRural=document.getElementById("stateCodeRural").value;	
    
    var districtCodeRural=document.getElementById("districtCodeRural").value;	
     var stateScheme=document.getElementById("stateScheme").value;
    stateScheme = stateScheme.replace("_0", "");
  	stateScheme = stateScheme.replace("_1", "");										
	var adcdRation =document.getElementById("adcdRation").value;	
	var adcdMobile =document.getElementById("adcdMobile").value;	
    
    var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
    onSearch();
    $.ajax({
          type: "POST",
          url: "/"+requestUri+"/beneficiarySchemeSearch",
          data: {"stateCodeRural": stateCodeRural,
				"districtCodeRural":districtCodeRural,
                "ruralUrbanFlag" :ruralUrbanFlag,
				"adcdRation":adcdRation,
				"adcdMobile":adcdMobile,
				"stateScheme":stateScheme,
              },
          success: function(data){
              newData = data;
              console.log(newData);
              if(newData !== null){
              $("#datahide").show();
              }
        var data1 = jQuery.parseJSON( newData );
            //  var data1 =eval(newData.villageList);
            $("#example1").dataTable().fnDestroy();
       offSearch();
         var i=1;
              var table = $('#example1').DataTable( {
          "responsive": false, "lengthChange": false, "autoWidth": false,"pagingType": "full_numbers","pageLength": 50,
           "language": {"emptyTable": "No beneficiary available"  },
              "aaData": data1,
              
              "aoColumns": [
                { "render": function(newData,type,full,meta){return i++;}   },           
                { "mData": "guid"},
                { "mData": "name"},
                { "mData": "fathername"},
                //{ "mData": "spousenm"},
                { "mData": "dob"},
                { "mData": "genderid"},
                { "mData": "mothername"},
                { "mData": "relation"},
                { "mData": "card_status"},
                { "mData": null,
                        render: function () {
                             return '<a href="#" id="editButton" onclick="editDetail(this)">View</a>';
  
                        }
                      }
                   ],
                   createdRow: function (row, data, dataIndex) 
                    {
                
                  console.log("row : "+JSON.stringify(data));
                    //alert(""+data.)
                    if(data.card_status === 0)
                        {
                          $(row).find('td:eq(8)').html("Card not made")
                      
                        }else if(data.card_status === 99)
                        {
                          //console.log("exe 1");
                          $(row).find('td:eq(8)').html("Complete")
                      $(row).find('td:eq(8)').css("background-color", "#20c997");
                        }	
                    else if(data.card_status === 49) {
                      $(row).find('td:eq(8)').html("In progress")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }
                      else if(data.card_status === 96){ 
                      $(row).find('td:eq(8)').html("eKYC pending")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }	
                    else if(data.card_status === 98) {
                    $(row).find('td:eq(8)').html("Rejected")
                      $(row).find('td:eq(8)').css("background-color", "#e74c3c");
                        }
                      
                      if(data.dob !=null){
                      if(data.dob.includes("-")) {
                      var dobY = formatDate(data.dob);
                      $(row).find('td:eq(4)').html(dobY)
                    }
                    }
                    
                    if(data.genderid === '1')
                        {
                          $(row).find('td:eq(5)').html("Male")
                        }else  if(data.genderid === '2')
                        {
                          $(row).find('td:eq(5)').html("Female")
                        }else  if(data.genderid === '0')
                        {
                          $(row).find('td:eq(5)').html("Other")
                        }
                    
                  
                },
              
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
              $('#example1_filter').find('input[type="search"]').attr('title','You can search Name, Father’s name, DOB etc from here').attr('style','cursor:pointer');
           },
  
           error: function(e){
         offSearch();
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}	
               
           }
      });
    
      
    }else if(urFlag=="U"){
      
	  var stateCodeRural=document.getElementById("stateCodeRural").value;	
    
	var districtCodeRural=document.getElementById("districtCodeRural").value;	
  var stateScheme=document.getElementById("stateScheme").value;  	
    stateScheme = stateScheme.replace("_0", "");
  	stateScheme = stateScheme.replace("_1", "");												
      var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
  	var adcdRation =document.getElementById("adcdRation").value;	
	var adcdMobile =document.getElementById("adcdMobile").value;
    onSearch();
    $.ajax({
          type: "POST",
         url: "/"+requestUri+"/beneficiarySchemeSearch",
          data: {"stateCodeRural": stateCodeRural,
				"districtCodeRural":districtCodeRural,
                "ruralUrbanFlag" :ruralUrbanFlag,
				"adcdRation":adcdRation,
				"adcdMobile":adcdMobile,
				"stateScheme":stateScheme,
              },
            success: function(data){
           newData = data;
              console.log(newData);
              if(newData !== null){
                  $("#datahide").show();
                  }
        var data1 = jQuery.parseJSON( newData );
            //  var data1 =eval(newData.villageList);
        $("#example1").dataTable().fnDestroy();
       offSearch();
         var i =1;
              var table = $('#example1').DataTable( {
          "responsive": false, "lengthChange": false, "autoWidth": false,"pagingType": "full_numbers","pageLength": 50,
           "language": {"emptyTable": "No beneficiary available"  },
              "aaData": data1,
              "aoColumns": [
                { "render": function(newData,type,full,meta){return i++;}   },
                { "mData": "guid"},
                { "mData": "name"},
                { "mData": "fathername"},
                //{ "mData": "spousenm"},
                { "mData": "dob"},
                { "mData": "genderid"},
                { "mData": "mothername"},
                { "mData": "relation"},
                 { "mData": "card_status"},
                { "mData": null,
                        render: function () {
                             return '<a  href="#" id="editButton" onclick="editDetail(this)">View</a>';
  
                        }
                      }
                   ],
                   createdRow: function (row, data, dataIndex) 
                    {
                  console.log("row : "+JSON.stringify(data));
                    //alert(""+data.)
                    if(data.card_status === 0)
                        {
                          $(row).find('td:eq(8)').html("Card not made")
                      
                        }else if(data.card_status === 99)
                        {
                          //console.log("exe 1");
                          $(row).find('td:eq(8)').html("Complete")
                      $(row).find('td:eq(8)').css("background-color", "#20c997");
                        }	
                    else if(data.card_status === 49) {
                      $(row).find('td:eq(8)').html("In progress")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }
                      else if(data.card_status === 96){ 
                      $(row).find('td:eq(8)').html("eKYC pending")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }	
                    else if(data.card_status === 98) {
                    $(row).find('td:eq(8)').html("Rejected")
                      $(row).find('td:eq(8)').css("background-color", "#e74c3c");
                        }
                      
                      if(data.dob !=null){
                      if(data.dob.includes("-")) {
                      var dobY = formatDate(data.dob);
                      $(row).find('td:eq(4)').html(dobY)
                    }
                    }
                    
                    if(data.genderid === '1')
                        {
                          $(row).find('td:eq(5)').html("Male")
                        }else  if(data.genderid === '2')
                        {
                          $(row).find('td:eq(5)').html("Female")
                        }else  if(data.genderid === '0')
                        {
                          $(row).find('td:eq(5)').html("Other")
                        }
                    
                  
                },
                "fnRowCallback" : function(nRow, aData, iDisplayIndex){
                  $("td:first", nRow).html(iDisplayIndex +1);
                 return nRow;
              },
              
                   
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
              
              
              $('#example1_filter').find('input[type="search"]').attr('title','You can search Name, Father’s name, DOB etc from here').attr('style','cursor:pointer');
  
           },
           error: function(e){
          offSearch();
                 if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
           }
      });
       
	  
	  
    }else{
		 offSearch();
	}
}
}
function submitSearchBocw(){
	var stateCodeRural=document.getElementById("stateCodeRural").value;	
    var bcowId=document.getElementById("bcowId").value;	
    var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
    onSearch();
    $.ajax({
          type: "POST",
          url: "/"+requestUri+"/getBocwDetails",
          data: {"stateCode": stateCodeRural,
          	"bcowId": bcowId,
              },
          success: function(data){
              newData = data;
              console.log(newData);
              if(newData !== null){
              $("#datahide").show();
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
		console.log(data1);
       	var i=1;
       	$("#example1").dataTable().fnDestroy();
       offSearch();
         var i=1;
              var table = $('#example1').DataTable( {
          "responsive": false, "lengthChange": false, "autoWidth": false,"pagingType": "full_numbers","pageLength": 50,
           "language": {"emptyTable": "No beneficiary available"  },
              "aaData": data1,
              
              "aoColumns": [
                { "render": function(ration,type,full,meta){return i++;}   },           
                { "mData": "family_mem_id"},
                { "mData": "member_name_eng"},
                { "mData": "father_name_eng"},
                { "mData": "year_of_birth"},
                { "mData": "gender"},
                { "mData": "mother_name_eng"},
                { "mData": "relation_name"},
                { "mData": null},
                { "mData": null,
                        render: function () {
                             return '<a href="#" id="editButton" onclick="editDetail(this)">View</a>';
  
                        }
                      }
                   ],
                   createdRow: function (row, data1, dataIndex) 
      		        {
      		    		$(row).find('td:eq(8)').html("Card not made") 
        			   if(data1.gender === 'M')
      		            {
      		            	$(row).find('td:eq(5)').html("Male")
      		            }else  if(data1.gender === 'F')
      		            {
      		            	$(row).find('td:eq(5)').html("Female")
      		            }else 
      		            {
      		            	$(row).find('td:eq(5)').html("Other")
      		            }
      		    		if(data1.year_of_birth === '0')
      		            {
      		            	$(row).find('td:eq(4)').html("")
      		            }
      					
      				},
              
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
              $('#example1_filter').find('input[type="search"]').attr('title','You can search Name, Father’s name, DOB etc from here').attr('style','cursor:pointer');
           },   
  
           error: function(e){
         offSearch();
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}	
               
           }
      });
}
 
function submitSearchMmja(){
    
  
    var requestUri='<%=requestUri%>';
    var urFlag= document.getElementById("ruralUrbanFlag").value;
    if(urFlag=="R"){
    var stateCodeRural=document.getElementById("stateCodeRural").value;	
    
    var districtCodeRural=document.getElementById("districtCodeRural").value;	
    
    var blockCodeRural =document.getElementById("blockCodeRural").value;	
    
    var villageCodeRural=document.getElementById("villageCodeRural").value;	
    
    var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
    onSearch();
    $.ajax({
          type: "POST",
          url: "/"+requestUri+"/beneficiarySearchMmja",
        //    url: "/beneficiarySearch",
          data: {"stateCodeRural": stateCodeRural,
              "districtCodeRural":districtCodeRural,
              "blockCodeRural" : blockCodeRural,
                "villageCodeRural" :villageCodeRural,
                "ruralUrbanFlag" :ruralUrbanFlag,
              },
          success: function(data){
              newData = data;
              console.log(newData);
              if(newData !== null){
              $("#datahide").show();
              }
        var data1 = jQuery.parseJSON( newData );
            //  var data1 =eval(newData.villageList);
            $("#example1").dataTable().fnDestroy();
       offSearch();
         var i=1;
              var table = $('#example1').DataTable( {
          "responsive": false, "lengthChange": true, "autoWidth": false,"pagingType": "full_numbers","pageLength": 50,
          "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
           "language": {"emptyTable": "No beneficiary available"  },
              "aaData": data1,
              
              "aoColumns": [
                { "render": function(newData,type,full,meta){return i++;}   },           
                { "mData": "guid"},
                { "mData": "name"},
                { "mData": "fathername"},
                //{ "mData": "spousenm"},
                { "mData": "dob"},
                { "mData": "genderid"},
                { "mData": "mothername"},
                { "mData": "relation"},
                { "mData": "card_status"},
                { "mData": null,
                        render: function () {
                             return '<a href="#" id="editButton" onclick="editDetail(this)">View</a>';
  
                        }
                      }
                   ],
                   createdRow: function (row, data, dataIndex) 
                    {
                
                  console.log("row : "+JSON.stringify(data));
                    //alert(""+data.)
                    if(data.card_status === 0)
                        {
                          $(row).find('td:eq(8)').html("Card not made")
                      
                        }else if(data.card_status === 99)
                        {
                          //console.log("exe 1");
                          $(row).find('td:eq(8)').html("Complete")
                      $(row).find('td:eq(8)').css("background-color", "#20c997");
                        }	
                    else if(data.card_status === 49) {
                      $(row).find('td:eq(8)').html("In progress")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }
                      else if(data.card_status === 96){ 
                      $(row).find('td:eq(8)').html("eKYC pending")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }	
                    else if(data.card_status === 98) {
                    $(row).find('td:eq(8)').html("Rejected")
                      $(row).find('td:eq(8)').css("background-color", "#e74c3c");
                        }
                      
                      if(data.dob !=null){
                      if(data.dob.includes("-")) {
                      var dobY = formatDate(data.dob);
                      $(row).find('td:eq(4)').html(dobY)
                    }
                    }
                    
                    if(data.genderid === '1')
                        {
                          $(row).find('td:eq(5)').html("Male")
                        }else  if(data.genderid === '2')
                        {
                          $(row).find('td:eq(5)').html("Female")
                        }else  if(data.genderid === '0')
                        {
                          $(row).find('td:eq(5)').html("Other")
                        }
                    
				  
                },
              
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
              $('#example1_filter').find('input[type="search"]').attr('title','You can search Name, Father’s name, DOB etc from here').attr('style','cursor:pointer');
           },
  
           error: function(e){
         offSearch();
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}	
               
           }
      });
    
      
    }else if(urFlag=="U"){
      
      var stateCodeUrban=document.getElementById("stateCodeRural").value;	
    
    var districtCodeUrban=document.getElementById("districtCodeRural").value;	
      
      var townCodeUrban=document.getElementById("townCodeUrban").value;	
      
      //var WardCodeUrban=document.getElementById("WardCodeUrban").value;	
      
      var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
      
    onSearch();
    $.ajax({
          type: "POST",
         url: "/"+requestUri+"/beneficiarySearchMmja",
        //    url: "/beneficiarySearch",
          data: {"stateCodeUrban": stateCodeUrban,
              "districtCodeUrban":districtCodeUrban,
              "townCodeUrban" : townCodeUrban,
               // "WardCodeUrban" :WardCodeUrban,
                "ruralUrbanFlag" :ruralUrbanFlag,
              },
            success: function(data){
           newData = data;
              console.log(newData);
              if(newData !== null){
                  $("#datahide").show();
                  }
        var data1 = jQuery.parseJSON( newData );
            //  var data1 =eval(newData.villageList);
        $("#example1").dataTable().fnDestroy();
       offSearch();
         var i =1;
              var table = $('#example1').DataTable( {
          "responsive": false, "lengthChange": true, "autoWidth": false,"pagingType": "full_numbers","pageLength": 50,
          "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
           "language": {"emptyTable": "No beneficiary available"  },
              "aaData": data1,
              "aoColumns": [
                { "render": function(newData,type,full,meta){return i++;}   },
                { "mData": "guid"},
                { "mData": "name"},
                { "mData": "fathername"},
                //{ "mData": "spousenm"},
                { "mData": "dob"},
                { "mData": "genderid"},
                { "mData": "mothername"},
                { "mData": "relation"},
                 { "mData": "card_status"},
                { "mData": null,
                        render: function () {
                             return '<a  href="#" id="editButton" onclick="editDetail(this)">View</a>';
  
                        }
                      }
                   ],
                   createdRow: function (row, data, dataIndex) 
                    {
                  console.log("row : "+JSON.stringify(data));
                    //alert(""+data.)
                    if(data.card_status === 0)
                        {
                          $(row).find('td:eq(8)').html("Card not made")
                      
                        }else if(data.card_status === 99)
                        {
                          //console.log("exe 1");
                          $(row).find('td:eq(8)').html("Complete")
                      $(row).find('td:eq(8)').css("background-color", "#20c997");
                        }	
                    else if(data.card_status === 49) {
                      $(row).find('td:eq(8)').html("In progress")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }
                      else if(data.card_status === 96){ 
                      $(row).find('td:eq(8)').html("eKYC pending")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }	
                    else if(data.card_status === 98) {
                    $(row).find('td:eq(8)').html("Rejected")
                      $(row).find('td:eq(8)').css("background-color", "#e74c3c");
                        }
                      
                      if(data.dob !=null){
                      if(data.dob.includes("-")) {
                      var dobY = formatDate(data.dob);
                      $(row).find('td:eq(4)').html(dobY)
                    }
                    }
                    
                    if(data.genderid === '1')
                        {
                          $(row).find('td:eq(5)').html("Male")
                        }else  if(data.genderid === '2')
                        {
                          $(row).find('td:eq(5)').html("Female")
                        }else  if(data.genderid === '0')
                        {
                          $(row).find('td:eq(5)').html("Other")
                        }
                    
				  
                },
                "fnRowCallback" : function(nRow, aData, iDisplayIndex){
                  $("td:first", nRow).html(iDisplayIndex +1);
                 return nRow;
              },
                     
				   
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
              
              
              $('#example1_filter').find('input[type="search"]').attr('title','You can search Name, Father’s name, DOB etc from here').attr('style','cursor:pointer');
  
           },
           error: function(e){
          offSearch();
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
function submitSearchOther(){
    var requestUri='<%=requestUri%>';
    var urFlag= document.getElementById("ruralUrbanFlag").value;
    var stateScheme=document.getElementById("stateScheme").value;  	
    stateScheme = stateScheme.replace("_0", "");
  	stateScheme = stateScheme.replace("_1", "");
    var familyid=document.getElementById("familyidScheme").value;  
    
    if(urFlag=="R"){
    var stateCodeRural=document.getElementById("stateCodeRural").value;	    
    var districtCodeRural=document.getElementById("districtCodeRural").value;	    
    var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
    onSearch();
    $.ajax({
          type: "POST",
          url: "/"+requestUri+"/beneficiarySearchOther",
          data: {"stateCodeRural": stateCodeRural,
              "districtCodeRural":districtCodeRural,
              "schemeid" : stateScheme,
                "familyidScheme" :familyid,
                "ruralUrbanFlag" :ruralUrbanFlag,
              },
          success: function(data){
            newData = data;
              console.log(newData);
              if(newData !== null){
              $("#datahide").show();
              }
        var data1 = jQuery.parseJSON( newData );
            //  var data1 =eval(newData.villageList);
            $("#example1").dataTable().fnDestroy();
       offSearch();
         var i=1;
              var table = $('#example1').DataTable( {
          "responsive": false, "lengthChange": true, "autoWidth": false,"pagingType": "full_numbers","pageLength": 50,
          "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
           "language": {"emptyTable": "No beneficiary available"  },
              "aaData": data1,
              
              "aoColumns": [
                { "render": function(newData,type,full,meta){return i++;}   },           
                { "mData": "family_mem_id"},
                { "mData": "membername"},
                { "mData": "father_husbandname"},
                { "mData": "age"},
                { "mData": "gender"},
                { "mData": "membername"},
                { "mData": "relationcode"},
                { "mData": "cardcode"},
                { "mData": null,
                        render: function () {
                             return '<a href="#" id="editButton" onclick="editDetail(this)">View</a>';
  
                        }
                      }
                   ],
                   createdRow: function (row, data, dataIndex) 
                    {
                
                  console.log("row : "+JSON.stringify(data));
                    //alert(""+data.)
                    //$(row).find('td:eq(8)').html("Card not made") 
                    if(data.cardcode === 0)
                        {
                          $(row).find('td:eq(8)').html("Card not made")
                      
                        }else if(data.cardcode === 99)
                        {
                          //console.log("exe 1");
                          $(row).find('td:eq(8)').html("Complete")
                      $(row).find('td:eq(8)').css("background-color", "#20c997");
                        }	
                    else if(data.cardcode === 49) {
                      $(row).find('td:eq(8)').html("In progress")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }
                      else if(data.cardcode === 96){ 
                      $(row).find('td:eq(8)').html("eKYC pending")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }	
                    else if(data.cardcode === 98) {
                    $(row).find('td:eq(8)').html("Rejected")
                      $(row).find('td:eq(8)').css("background-color", "#e74c3c");
                        } else if(data.cardcode === 101) {
                    $(row).find('td:eq(8)').html("Delevered")
                      $(row).find('td:eq(8)').css("background-color", "#e74c3c");
                        }
                      
                       if(data.age !=null){
                       if(data.age.includes("/")) {
                       var dobY = formatDate3(data.age);
                       $(row).find('td:eq(4)').html(dobY)
                     }else if(data.age.includes("-")) {
                      $(row).find('td:eq(4)').html(0)
                     }
                     }
                     if(data.father_husbandname === 'null')
                        {
                        $(row).find('td:eq(3)').html("")
                        } else if(data.father_husbandname === 'NA')
                        {
                          $(row).find('td:eq(3)').html("")
                        }
                    if(data.gender === 'M')
                        {
                          $(row).find('td:eq(5)').html("Male")
                        }else  if(data.gender === 'F')
                        {
                          $(row).find('td:eq(5)').html("Female")
                        }else  
                        {
                          $(row).find('td:eq(5)').html("Other")
                        }
                    
                  
                },
              
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
              $('#example1_filter').find('input[type="search"]').attr('title','You can search Name, Father’s name, DOB etc from here').attr('style','cursor:pointer');
           },
  
           error: function(e){
         offSearch();
            if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}	
               
           }
      });
    
	
	  
    }else if(urFlag=="U"){
      
      var stateCodeUrban=document.getElementById("stateCodeRural").value;	
    
    var districtCodeUrban=document.getElementById("districtCodeRural").value;	
      
      var townCodeUrban=document.getElementById("townCodeUrban").value;	
      
      //var WardCodeUrban=document.getElementById("WardCodeUrban").value;	
      
      var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
      
    onSearch();
    $.ajax({
          type: "POST",
         url: "/"+requestUri+"/beneficiarySearchOther",
          data: {"stateCodeUrban": stateCodeUrban,
              "districtCodeUrban":districtCodeUrban,
              "schemeid" : stateScheme,
              "familyidScheme" :familyid,
              "ruralUrbanFlag" :ruralUrbanFlag,
              },
            success: function(data){
           newData = data;
              console.log(newData);
              if(newData !== null){
                  $("#datahide").show();
                  }
        var data1 = jQuery.parseJSON( newData );
            //  var data1 =eval(newData.villageList);
        $("#example1").dataTable().fnDestroy();
       offSearch();
         var i =1;
              var table = $('#example1').DataTable( {
          "responsive": false, "lengthChange": true, "autoWidth": false,"pagingType": "full_numbers","pageLength": 50,
          "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
           "language": {"emptyTable": "No beneficiary available"  },
              "aaData": data1,
			  
              "aoColumns": [
                { "render": function(newData,type,full,meta){return i++;}   },           
                { "mData": "family_mem_id"},
                { "mData": "membername"},
                { "mData": "father_husbandname"},
                { "mData": "age"},
                { "mData": "gender"},
                { "mData": "membername"},
                { "mData": "relationcode"},
                { "mData": "cardcode"},
                { "mData": null,
                        render: function () {
                             return '<a  href="#" id="editButton" onclick="editDetail(this)">View</a>';
  
                        }
                      }
                   ],
                   createdRow: function (row, data, dataIndex) 
                    {
				
                  console.log("row : "+JSON.stringify(data));
                    //alert(""+data.)
																	
                    if(data.cardcode === 0)
                        {
                          $(row).find('td:eq(8)').html("Card not made")
                      
                        }else if(data.cardcode === 99)
                        {
                          //console.log("exe 1");
                          $(row).find('td:eq(8)').html("Complete")
                      $(row).find('td:eq(8)').css("background-color", "#20c997");
                        }	
                    else if(data.cardcode === 49) {
                      $(row).find('td:eq(8)').html("In progress")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }
                      else if(data.cardcode === 96){ 
                      $(row).find('td:eq(8)').html("eKYC pending")
                      $(row).find('td:eq(8)').css("background-color", "#ffa500");
                        }	
                    else if(data.cardcode === 98) {
                    $(row).find('td:eq(8)').html("Rejected")
                      $(row).find('td:eq(8)').css("background-color", "#e74c3c");
					 } else if(data.cardcode === 101) {
                      $(row).find('td:eq(8)').html("Delevered")
                       $(row).find('td:eq(8)').css("background-color", "#e74c3c");
                     														 
                        }
                      if(data.age !=null){
                       if(data.age.includes("/")) {
                       var dobY = formatDate3(data.age);
                       $(row).find('td:eq(4)').html(dobY)
                     }else if(data.age.includes("-")) {
                      $(row).find('td:eq(4)').html(0)
                     }
                     }
                     if(data.father_husbandname === 'null')
                        {
                          $(row).find('td:eq(3)').html("NA")
                        }
                    if(data.gender === 'M')
                        {
                          $(row).find('td:eq(5)').html("Male")
                        }else  if(data.gender === 'F')
                        {
                          $(row).find('td:eq(5)').html("Female")
                        }else  
                        {
                          $(row).find('td:eq(5)').html("Other")
                        }
                    
                  
                },
              
                "fnRowCallback" : function(nRow, aData, iDisplayIndex){
                  $("td:first", nRow).html(iDisplayIndex +1);
                 return nRow;
              },
                 
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
                
              $('#example1_filter').find('input[type="search"]').attr('title','You can search Name, Father’s name, DOB etc from here').attr('style','cursor:pointer');
			 
  
           },
           error: function(e){
          offSearch();
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

function formatDate(date) {
    if(date.length>4){
      year=date.substring(0,4);
    }else{
    year=date;
    }	
      return year;
  }
function formatDate3(date) {
    if(date.length>4){
      year=date.substring(6,10);
    }else{
    year=date;
    }	
      return year;
  }





 function editDetail(value) {
  
  var requestUri='<%=requestUri%>';
  var stateCode;
  on();
  var scheme=document.getElementById("stateScheme").value;  
    scheme = scheme.replace("_0", "");
  	scheme = scheme.replace("_1", "");							  
    if(document.getElementById("ruralUrbanFlag").value=='R'){
       document.getElementById("stateName").value  =$("#stateCodeRural option:selected").text();
       document.getElementById("districtName").value =$("#districtCodeRural option:selected").text();
       if(scheme=="3"){
       document.getElementById("villageName").value =$("#villageCodeRural option:selected").text();
       document.getElementById("blockName").value =$("#blockCodeRural option:selected").text();
       }
       stateCode=document.getElementById("stateCodeRural").value;
    }else if(document.getElementById("ruralUrbanFlag").value=='U'){
        document.getElementById("stateName").value  =$("#stateCodeRural option:selected").text();
       document.getElementById("districtName").value =$("#districtCodeRural option:selected").text();
      document.getElementById("townName").value =$("#townCodeUrban option:selected").text();
       document.getElementById("wardName").value =$("#wardCodeUrban option:selected").text();
       stateCode=document.getElementById("stateCodeUrban").value;
       if(scheme=="3"){
       document.getElementById("stateCodeUrban").value  =document.getElementById("stateCodeRural").value;
  	document.getElementById("stateCodeUrban").value  =document.getElementById("districtCodeRural").value;
  	}
    }	
  	stateCode=document.getElementById("stateCodeRural").value;
  	document.getElementById("schemeName").value  =$("#stateScheme option:selected").text();
	var scheme1=document.getElementById("stateScheme").value;;
    scheme1 = scheme1.replace("_0", "");
  	scheme1 = scheme1.replace("_1", "");	
  	document.getElementById("schemeid").value  =scheme1;
	var row = jQuery(value).closest('tr')
      var guid = row[0].children[1].innerHTML;
      var guid = guid.trim();
      var name = row[0].children[2].innerHTML;
      var fathername = row[0].children[3].innerHTML;
      
    //	var spousenm = row[0].children[3].innerHTML;
      var dob = row[0].children[4].innerHTML;
      var genderid = row[0].children[5].innerHTML;
      var mothername = row[0].children[6].innerHTML;
      var relation = row[0].children[7].innerHTML;
      var card_status = row[0].children[8].innerHTML;
      
      document.getElementById("guid").value=guid;
      document.getElementById("name").value=name;
      document.getElementById("fathername").value=fathername;
    //	document.getElementById("spousenm").value=spousenm;
      document.getElementById("dob").value=dob;
      document.getElementById("genderid").value=genderid;
      document.getElementById("mothername").value=mothername;
      document.getElementById("relation").value=relation;
      document.getElementById("card_status").value=card_status;
      document.getElementById("stateCode").value=stateCode;
      
    if(card_status=='Complete'){
        document.forms[0].action="/"+requestUri+"/beneficiarySchemeCardDonwload";
        document.forms[0].method="post";
        document.forms[0].submit();
    }else if(card_status=='eKYC pending'){
        document.forms[0].action="/"+requestUri+"/beneficiaryDemoKycView";
        document.forms[0].method="post";
        document.forms[0].submit();
    } else{
       
     $.ajax({
          type: "POST",
           url: "/"+requestUri+"/getUidToken",
          data: {
            "guid": guid,
        "stateCode":stateCode,
             },
         success: function(data){
            console.log(data);
           off();
             var obj = jQuery.parseJSON( data );
             if(obj['status'] == "Y"){
        document.forms[0].action="/"+requestUri+"/beneficiarySchemeView";
        document.forms[0].method="post";
        document.forms[0].submit();
            }else if(obj['status'] == "D"){
        document.forms[0].action="/"+requestUri+"/beneficiarySchemeView";
        document.forms[0].method="post";
        document.forms[0].submit();
             }else{
        document.forms[0].action="/"+requestUri+"/beneficiarySchemeView";
           document.forms[0].method="post";
        document.forms[0].submit();
                 
               }
           },
           error: function(e){
         off();
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
 
 
  function onNext() {
  
  var requestUri='<%=requestUri%>';
  var stateCode;
  on();
  var scheme=document.getElementById("stateScheme").value;  
    scheme = scheme.replace("_0", "");
  	scheme = scheme.replace("_1", "");							  
    if(document.getElementById("ruralUrbanFlag").value=='R'){
       document.getElementById("stateName").value  =$("#stateCodeRural option:selected").text();
       document.getElementById("districtName").value =$("#districtCodeRural option:selected").text();
       if(scheme=="3"){
       document.getElementById("villageName").value =$("#villageCodeRural option:selected").text();
       document.getElementById("blockName").value =$("#blockCodeRural option:selected").text();
       }
       stateCode=document.getElementById("stateCodeRural").value;
    }else if(document.getElementById("ruralUrbanFlag").value=='U'){
        document.getElementById("stateName").value  =$("#stateCodeRural option:selected").text();
       document.getElementById("districtName").value =$("#districtCodeRural option:selected").text();
      document.getElementById("townName").value =$("#townCodeUrban option:selected").text();
       document.getElementById("wardName").value =$("#wardCodeUrban option:selected").text();
       stateCode=document.getElementById("stateCodeUrban").value;
       if(scheme=="3"){
       document.getElementById("stateCodeUrban").value  =document.getElementById("stateCodeRural").value;
  	document.getElementById("stateCodeUrban").value  =document.getElementById("districtCodeRural").value;
  	}
    }	
  	stateCode=document.getElementById("stateCodeRural").value;
  	document.getElementById("schemeName").value  =$("#stateScheme option:selected").text();
	var scheme1=document.getElementById("stateScheme").value;;
    scheme1 = scheme1.replace("_0", "");
  	scheme1 = scheme1.replace("_1", "");	
  	document.getElementById("schemeid").value  =scheme1;
	var stateCodeRural= document.getElementById("stateCodeRural").value;
    var stateScheme= document.getElementById("stateScheme").value;
	var districtCodeRural= document.getElementById("districtCodeRural").value;
	var subSchemeId= document.getElementById("subSchemeId").value;
	var documentTypeId= document.getElementById("documentTypeId").value;
	var documentId= document.getElementById("documentId").value;
	var formFile= document.getElementById("formFile").value;
	
	document.getElementById("subSchemeName").value  =$("#subSchemeId option:selected").text();
	document.getElementById("documentTypeName").value  =$("#documentTypeId option:selected").text();
	document.getElementById("documentViewId").value = document.getElementById("documentId").value;
	document.getElementById("SchemedocumentImage").value = document.getElementById("formFile").value;
    
    let photo = document.getElementById("formFile").files[0];
	let formData = new FormData();
     
	formData.append("photo", photo);
    document.forms[0].action="/"+requestUri+"/beneficiarySchemeView";
    document.forms[0].method="post";
    document.forms[0].submit();
 
    }
 
 
 
</script>

<script  type="text/javascript">
  function reGenFaceAuthQrCode(){
   	var requestUri='<%=requestUri%>';
   		document.getElementById("authface_id").src="/"+requestUri+"/AuthfaceId";
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
			
				
			</div>
			<div class="modal-footer justify-content-center" id="imgsave" style="display: none">
				<button type="button" class="btn btn-sm btn-dark" onclick="saveCapturePhoto()">Save</button>
			</div>
		</div>
	</div>
</div>
<script>

let click_button = document.querySelector("#click-photo");
let canvas = document.querySelector("#canvas");

click_button.addEventListener('click', function() {
   	canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
   	let image_data_url = canvas.toDataURL('image/jpeg');
	document.getElementById("imgph").style.display="block";
	document.getElementById("imgsave").style.display="flex";
	
   	// data url of the image
   	document.getElementById("imgphoto").value=image_data_url;
   	console.log(image_data_url);
});


function saveCapturePhoto(value) {
	var requestUri='<%=requestUri%>';	
	var photo=document.getElementById("imgphoto").value.replace("data:image/jpeg;base64,", "");
	var rationcard=document.getElementById("rationCard").value;
	var state_code=document.getElementById("stateCodeRural").value
	var district_code=document.getElementById("districtCodeRural").value
	var refId=document.getElementById("capRefId").value;
	var familyId=document.getElementById("capFamilyId").value;
	var memberId=document.getElementById("capMemberId").value;
	var aadhaar=document.getElementById("capAadhaar").value;
	var pmjay=document.getElementById("capPmjayId").value;
	var rural_urban=document.getElementById("rural_urban").value;
	if(pmjay==""){
		alert("Please Print Ayushman Card.");
		return false;
	}
	
	on() ;
	$.ajax({
       type: "POST",
        url: "/"+requestUri+"/capturePhotoSave",
        data: {
      	  "guid": refId,
      	  "nfsaid": familyId,
      	  "memberid":memberId,
      	  "photo":photo,
      	  "state_code":state_code,
      	  "district_code":district_code, 
      	  "rural_urban":rural_urban,      	        	   
      	 },
       success: function(data){
      	 console.log(data);
      	 off() ;          
         var obj = jQuery.parseJSON( data );
         if(obj['status'] == "Y"){
         alert("Save Success Fully");
         	//write some success message 
         }else{
			 //write some error message  
            }
        },
        error: function(e){
        off();
           if('<%=(String)session.getAttribute("Bis_Login")%>'=='Bis_Login'){
				  alert('API Gateway not respond. Please try again.');
			  }else{
             	alert('Your session has been expired. Please relogin again.');
					window.location.href='sessionExpire';
			}
        }
   });
}


function getStateDataUrban(type) {
  
    var requestUri='<%=requestUri%>';
    
      if(type=="S"){
        
		
		
        $.ajax({
          type: "POST",
         // url: "/urbanMaster",
          url: "/"+requestUri+"/urbanMaster",
          data: {"modeFlag": type    	
              },
          success: function(data){
  
              newData = data;
              console.log(newData);
        $("#stateCodeUrban option").remove();
              var obj = jQuery.parseJSON( data );
             var count=0;
           for (var key in obj) {
  
               var values =  obj[key];
               var x = document.getElementById("stateCodeUrban");
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
      } else if(type=="D"){
        var stateCode= document.getElementById("stateCodeUrban").value;
        
   
         $.ajax({
          type: "POST",
          //  url: "/urbanMaster",
          url: "/"+requestUri+"/urbanMaster",
          data: {"modeFlag": type,
              "stateCode":stateCode
              },
          success: function(data){
  
              newData = data;
              console.log(newData);
        $("#districtCodeUrban option").remove();
              var obj = jQuery.parseJSON( data );
             var count=0;
           for (var key in obj) {
  
               var values =  obj[key];
               var x = document.getElementById("districtCodeUrban");
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
      }else if(type=="T"){
        var stateCode= document.getElementById("stateCodeRural").value;
        var districtCode= document.getElementById("districtCodeRural").value;
          
		
		
        $.ajax({
          type: "POST",
           // url: "/urbanMaster",
          url: "/"+requestUri+"/urbanMaster",
          data: {"modeFlag": type,
              "stateCode":stateCode,
              "districtCode" :districtCode
              },
          success: function(data){
  
              newData = data;
              console.log(newData);
              if(districtCode==""){
              }else{
        		$("#townCodeUrban option").remove();
        	  }
              var obj = jQuery.parseJSON( data );
             var count=0;
           for (var key in obj) {
  
               var values =  obj[key];
               var x = document.getElementById("townCodeUrban");
               if(count==0){
               var option = document.createElement("option");
               option.text ="Select Town";
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
      }else if(type=="W"){
        
        var stateCode= document.getElementById("stateCodeRural").value;
        var districtCode= document.getElementById("districtCodeRural").value;
        var townCode= document.getElementById("townCodeUrban").value;
          
		
		
        $.ajax({
          type: "POST",
           // url: "/urbanMaster",
          url: "/"+requestUri+"/urbanMaster",
          data: {"modeFlag": type,
              "stateCode":stateCode,
              "districtCode" :districtCode,
              "townCode" : townCode
              },
          success: function(data){
  
              newData = data;
              console.log(newData);
        $("#WardCodeUrban option").remove();
              var obj = jQuery.parseJSON( data );
             var count=0;
           for (var key in obj) {
  
               var values =  obj[key];
               var x = document.getElementById("WardCodeUrban");
               if(count==0){
               var option = document.createElement("option");
               option.text ="Select Ward";
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
      
  }
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
      if (charCode != 46 && charCode > 31
        && (charCode < 48 || charCode > 57))
        return false;

      return true;
  }
function getStateData(type) {
  
    var requestUri='<%=requestUri%>';
    var element = document.getElementById("datahide");
      element.style.display = "none";
      if(type=="S"){
        
        
        
        $.ajax({
          type: "POST",
           url: "/"+requestUri+"/ruralMaster",
          //url: "/ruralMaster",
          data: {"modeFlag": type    	
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
      } else if(type=="D"){
        var stateCode= document.getElementById("stateCodeRural").value;
        
   
         $.ajax({
          type: "POST",
           url: "/"+requestUri+"/ruralMaster",
          //url: "/ruralMaster",
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
      }else if(type=="B"){
        var stateCode= document.getElementById("stateCodeRural").value;
        var districtCode= document.getElementById("districtCodeRural").value;
        
        
        
        $.ajax({
          type: "POST",
           url: "/"+requestUri+"/ruralMaster",
          //url: "/ruralMaster",
          data: {"modeFlag": type,
              "stateCode":stateCode,
              "districtCode" :districtCode
              },
          success: function(data){
  
              newData = data;
              console.log(newData);
              if(districtCode==""){
              }else{
        $("#blockCodeRural option").remove();
       }
              var obj = jQuery.parseJSON( data );
             var count=0;
           for (var key in obj) {
  
               var values =  obj[key];
               var x = document.getElementById("blockCodeRural");
               if(count==0){
               var option = document.createElement("option");
               option.text ="Select Block";
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
      }else if(type=="V"){
        var stateCode= document.getElementById("stateCodeRural").value;
        var districtCode= document.getElementById("districtCodeRural").value;
        var blockCode= document.getElementById("blockCodeRural").value;
        
        
        
        $.ajax({
          type: "POST",
          url: "/"+requestUri+"/ruralMaster",
         // url: "/ruralMaster",
          data: {"modeFlag": type,
              "stateCode":stateCode,
              "districtCode" :districtCode,
              "blockCode" :blockCode
              },
          success: function(data){
  
              newData = data;
              console.log(newData);
        $("#villageCodeRural option").remove();
              var obj = jQuery.parseJSON( data );
             var count=0;
           for (var key in obj) {
  
               var values =  obj[key];
               var x = document.getElementById("villageCodeRural");
               if(count==0){
               var option = document.createElement("option");
               option.text ="Select Village";
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
      
  }
  
	function readURL(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	
	    reader.onload = function (e) {
	      $('#blah').attr('src', e.target.result).width(150).height(200);
	    };
	
	    reader.readAsDataURL(input.files[0]);
	  }
	}


function viewImage(){

	var modal = document.getElementById("myModal");
	
	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var img = document.getElementById("blah");
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	img.onclick = function(){
	  modal.style.display = "block";
	  modalImg.src = this.src;
	  captionText.innerHTML = this.alt;
	}
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
	  modal.style.display = "none";
	}
	
}

</script>
