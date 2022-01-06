<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@page import="org.springframework.util.ObjectUtils"%>
<%@page import="com.gov.nha.bis.server.model.SessionLoginMap"%>
<%@page import="com.gov.nha.bis.server.model.BeneficiarySeccForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
String requestUri="";
requestUri=request.getRequestURI().split("/")[1];
String sessid="";
sessid=session.getId();
String saveKyc="";
if(request.getAttribute("saveKyc")!=null){
	saveKyc=(String)request.getAttribute("saveKyc");
}

BeneficiarySeccForm beneficiarySeccForm = new BeneficiarySeccForm();
if(session.getAttribute("beneficiarySeccForm")!=null){
	beneficiarySeccForm=(BeneficiarySeccForm)session.getAttribute("beneficiarySeccForm");
}


String userId="";
if(session.getAttribute("USERID")!=null){
	userId =(String)session.getAttribute("USERID");
}

String viewAction="";
if(session.getAttribute("viewAction")!=null){
	viewAction =(String)session.getAttribute("viewAction");
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
  <title>SECC Beneficiary Search</title>

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
 
<body class="mobile" onload="openOnloadUrbanRural();">

<div class="beneficiary-main-wrap">
  <div class="row flex-nowrap">
	<%@ include file="leftColumn.jsp" %> 
  
    <main class="col">
      <nav>
      <%@ include file="header.jsp" %>          
      </nav>
  
      <div class="beneficiary-section">
        <main>
          <section class="SECC-data">
            <form:form  action=""  method="POST"  commandName="command">
            <div class="container">
              <div class="px-1 px-md-3">
                <div class="col-12">
                  <h2><span>PMJAY - </span>SECC Beneficiaries </h2>
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
                          <input class="form-check-input" type="radio" id="rural" checked name="urFlag" value="R"   onclick="openUrbanRural()">
                          <label class="form-check-label" for="rural">
                            Rural
                          </label>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade  " id="Urban-home" role="tabpanel" aria-labelledby="Urban-home-tab">
                      <div class="row" id="urbanID">
                        <div class="col-12 col-md-3" id="urbanID1">
                          <form:select path="stateCodeUrban" id="stateCodeUrban" tabindex="5"  class="form-select" aria-label="Default select example" onchange="getStateDataUrban('D')">
                            <option value="">Select State</option>
                          </form:select>
                        </div>

                        <div class="col-12 col-md-3" id="urbanID3">
                          <form:select path="districtCodeUrban" id="districtCodeUrban" tabindex="6" class="form-select" aria-label="Default select example" onchange="getStateDataUrban('T')">
                            <option value="">Select District</option>                          
                          </form:select>
                        </div>

                        <div class="col-12 col-md-3" id="urbanID2">
                          <form:select path="townCodeUrban" id="townCodeUrban" tabindex="7" class="form-select" aria-label="Default select example" onchange="getStateDataUrban('W')">
                            <option value="">Select Town</option>                          
                          </form:select>
                        </div>

                        <div class="col-12 col-md-3" id="urbanID4">
                          <form:select path="WardCodeUrban" id="WardCodeUrban" tabindex="8" class="form-select" aria-label="Default select example" onchange="submitSearch()">
                            
                            <option value="">Select Ward</option>                            
                          </form:select>
                        </div>
                      </div>
                    </div>
                    <div class="tab-pane fade show active" id="Rural-profile" role="tabpanel" aria-labelledby="Rural-profile-tab">
                      <div class="row"  id="ruralID">
                        <div class="col-12 col-md-3"  id="ruralID1">
                          <form:select path="stateCodeRural" id="stateCodeRural"  tabindex="1" class="form-select" aria-label="Default select example" onchange="getStateData('D')">
                            <option value="">Select State</option>
                          </form:select>
                        </div>

                        <div class="col-12 col-md-3" id="ruralID3">
                          <form:select path="districtCodeRural" id="districtCodeRural"  tabindex="2" class="form-select" aria-label="Default select example" onchange="getStateData('B')">
                            <option value="">Select District</option>                             
                          </form:select>
                        </div>

                        <div class="col-12 col-md-3" id="ruralID2">
                          <form:select path="blockCodeRural" id="blockCodeRural" tabindex="3" class="form-select" aria-label="Default select example" onchange="getStateData('V')">
                            <option value="">Select Block</option>                   
                           
                          </form:select>
                        </div>

                        <div class="col-12 col-md-3" id="ruralID4">
                          <form:select path="villageCodeRural" id="villageCodeRural" tabindex="4"  class="form-select" aria-label="Default select example" onchange="submitSearch()">
                            <option value="">Select Village</option>                            
                          </form:select>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div id="datahide" class="card mt-4 beneficiary-section-table" style="display:none">
                    <div class="card-header">SECC beneficiaries details</div>
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

                </div>
              </div>
            </div>
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
            <form:hidden path="card_status" id="card_status" />
            <form:hidden path="uidtoken" id="uidtoken" /> 
            <form:hidden path="stateCode" id="stateCode"/> 
            <form:hidden path="blockName" id="blockName" /> 
            
            <form:hidden path="wardName" id="wardName" /> 
            <div id="overlay" class="overlay_full" onclick="off()">
              <div class="fancy-spinner">
				  <div class="ring"></div>
				  <div class="ring"></div>
				  <div class="dot"></div>
				  <span>Loading...</span>
				</div>
     </div>
    
    <div id="overlay_search" class="overlay_full" onclick="offSearch()">
              <div class="fancy-spinner">
			<div class="ring"></div>
			<div class="ring"></div>
			<div class="dot"></div>
			<span>Searching...</span>
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
  $(function () {
    $("#example1").DataTable({
          "responsive": false, "lengthChange": false, "autoWidth": false,
          "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
        }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');   
  });
  
  </script>
  
  <script type="text/javascript">
  
  
  
  function submitSearch(){
    
  onSearch();
    var requestUri='<%=requestUri%>';
    var urFlag= document.getElementById("ruralUrbanFlag").value;
    if(urFlag=="R"){
    var stateCodeRural=document.getElementById("stateCodeRural").value;	
    
    var districtCodeRural=document.getElementById("districtCodeRural").value;	
    
    var blockCodeRural =document.getElementById("blockCodeRural").value;	
    
    var villageCodeRural=document.getElementById("villageCodeRural").value;	
    
    var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
    
    $.ajax({
          type: "POST",
          url: "/"+requestUri+"/beneficiarySearch",
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
      
      var stateCodeUrban=document.getElementById("stateCodeUrban").value;	
      
      var districtCodeUrban=document.getElementById("districtCodeUrban").value;	
      
      var townCodeUrban=document.getElementById("townCodeUrban").value;	
      
      var WardCodeUrban=document.getElementById("WardCodeUrban").value;	
      
      var ruralUrbanFlag=document.getElementById("ruralUrbanFlag").value;	
      
    onSearch();
    $.ajax({
          type: "POST",
         url: "/"+requestUri+"/beneficiarySearch",
        //    url: "/beneficiarySearch",
          data: {"stateCodeUrban": stateCodeUrban,
              "districtCodeUrban":districtCodeUrban,
              "townCodeUrban" : townCodeUrban,
                "WardCodeUrban" :WardCodeUrban,
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
        $("#blockCodeRural option").remove();
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
        var stateCode= document.getElementById("stateCodeUrban").value;
        var districtCode= document.getElementById("districtCodeUrban").value;
        
        
        
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
        $("#townCodeUrban option").remove();
              var obj = jQuery.parseJSON( data );
             var count=0;
           for (var key in obj) {
  
               var values =  obj[key];
               var x = document.getElementById("townCodeUrban");
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
      }else if(type=="W"){
        
        var stateCode= document.getElementById("stateCodeUrban").value;
        var districtCode= document.getElementById("districtCodeUrban").value;
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
  
  function openUrbanRural(){
  
      var saveKyc = '<%=saveKyc%>'; 
      if(saveKyc=="Y"){
        alert("Beneficiary kyc has been successfully updated.");
        }else if(saveKyc=="N"){
        alert("Beneficiary kyc updatation failed.");
        }
  
    if (document.getElementById("rural").checked) {
      
      document.getElementById("ruralUrbanFlag").value=document.getElementById("rural").value;
        
      document.getElementById("urbanID1").style.display = "none";
      document.getElementById("urbanID2").style.display = "none";
      document.getElementById("urbanID3").style.display = "none";
      document.getElementById("urbanID4").style.display = "none";
      
      
      document.getElementById("ruralID1").style.display = "block";
      document.getElementById("ruralID2").style.display = "block";
      document.getElementById("ruralID3").style.display = "block";
      document.getElementById("ruralID4").style.display = "block";
      
      
      getStateData("S");
      document.getElementById("districtCodeRural").value="";	
      document.getElementById("blockCodeRural").value="";	
      document.getElementById("villageCodeRural").value="";
        
    }
    else if (document.getElementById("urban").checked) {
      document.getElementById("ruralUrbanFlag").value=document.getElementById("urban").value;
        
      document.getElementById("urbanID1").style.display = "block";
      document.getElementById("urbanID2").style.display = "block";
      document.getElementById("urbanID3").style.display = "block";
      document.getElementById("urbanID4").style.display = "block";
      
      document.getElementById("ruralID1").style.display = "none";
      document.getElementById("ruralID2").style.display = "none";
      document.getElementById("ruralID3").style.display = "none";
      document.getElementById("ruralID4").style.display = "none";
      
      
      getStateDataUrban('S');
      document.getElementById("districtCodeUrban").value="";	
      document.getElementById("townCodeUrban").value="";	
      document.getElementById("WardCodeUrban").value="";
      
    }
    
  }
  
  
  function openOnloadUrbanRural(){
  	 $('#secc-collapse').addClass('show');
	 $('#nfsa-collapse').removeClass('show');
	 $('#adacli').addClass('active');
	 $('#aassli').removeClass('active');

      var viewAction = '<%=viewAction%>'; 
  
      if(viewAction=='Y'){
      
      document.getElementById("datahide").style.display = "block";
    var chekedRUFlag='<%=beneficiarySeccForm.getRuralUrbanFlag()%>';
        
      if(chekedRUFlag=='R'){
      
      document.getElementById("rural").checked = true;
        document.getElementById("ruralUrbanFlag").value="R";
        document.getElementById("urbanID1").style.display = "none";
      document.getElementById("urbanID2").style.display = "none";
      document.getElementById("urbanID3").style.display = "none";
      document.getElementById("urbanID4").style.display = "none";
      
      
      document.getElementById("ruralID1").style.display = "block";
      document.getElementById("ruralID2").style.display = "block";
      document.getElementById("ruralID3").style.display = "block";
      document.getElementById("ruralID4").style.display = "block";
      
      getStateDataView("S",'<%=beneficiarySeccForm.getStateCodeRural()%>');
      getStateDataView("D",'<%=beneficiarySeccForm.getStateCodeRural()%>','<%=beneficiarySeccForm.getDistrictCodeRural()%>');
      getStateDataView("B",'<%=beneficiarySeccForm.getStateCodeRural()%>','<%=beneficiarySeccForm.getDistrictCodeRural()%>','<%=beneficiarySeccForm.getBlockCodeRural()%>');
      getStateDataView("V",'<%=beneficiarySeccForm.getStateCodeRural()%>','<%=beneficiarySeccForm.getDistrictCodeRural()%>',
                 '<%=beneficiarySeccForm.getBlockCodeRural()%>','<%=beneficiarySeccForm.getVillageCodeRural()%>');		
        
      submitSearchRuralView('<%=beneficiarySeccForm.getRuralUrbanFlag()%>','<%=beneficiarySeccForm.getStateCodeRural()%>','<%=beneficiarySeccForm.getDistrictCodeRural()%>',
                 '<%=beneficiarySeccForm.getBlockCodeRural()%>','<%=beneficiarySeccForm.getVillageCodeRural()%>');	
  
        }else if(chekedRUFlag=='U'){
        
          document.getElementById("urban").checked = true;
          document.getElementById("ruralUrbanFlag").value="U";
          document.getElementById("urbanID1").style.display = "block";
        document.getElementById("urbanID2").style.display = "block";
        document.getElementById("urbanID3").style.display = "block";
        document.getElementById("urbanID4").style.display = "block";
      
      document.getElementById("ruralID1").style.display = "none";
      document.getElementById("ruralID2").style.display = "none";
      document.getElementById("ruralID3").style.display = "none";
      document.getElementById("ruralID4").style.display = "none";
     
      $('#Rural-profile').removeClass('disabled');
      $('#Urban-home').addClass('show');
      $('#Urban-home').addClass('active');
      $('#Rural-profile-tab').removeClass('active');
      $('#Urban-home-tab').addClass('active');
      
      getStateDataUrbanView("S",'<%=beneficiarySeccForm.getStateCodeUrban()%>');
      getStateDataUrbanView("D",'<%=beneficiarySeccForm.getStateCodeUrban()%>','<%=beneficiarySeccForm.getDistrictCodeUrban()%>');
      getStateDataUrbanView("T",'<%=beneficiarySeccForm.getStateCodeUrban()%>','<%=beneficiarySeccForm.getDistrictCodeUrban()%>'
      ,'<%=beneficiarySeccForm.getTownCodeUrban()%>');
      getStateDataUrbanView("W",'<%=beneficiarySeccForm.getStateCodeUrban()%>','<%=beneficiarySeccForm.getDistrictCodeUrban()%>',
                 '<%=beneficiarySeccForm.getTownCodeUrban()%>','<%=beneficiarySeccForm.getWardCodeUrban()%>');		
        
      submitSearchUrbanView('<%=beneficiarySeccForm.getRuralUrbanFlag()%>','<%=beneficiarySeccForm.getStateCodeUrban()%>','<%=beneficiarySeccForm.getDistrictCodeUrban()%>',
                 '<%=beneficiarySeccForm.getTownCodeUrban()%>','<%=beneficiarySeccForm.getWardCodeUrban()%>');	
  
  
      
        }
      
    }else{
  
      if (document.getElementById("rural").checked) {
      
      document.getElementById("ruralUrbanFlag").value=document.getElementById("rural").value;
        
      document.getElementById("urbanID1").style.display = "none";
      document.getElementById("urbanID2").style.display = "none";
      document.getElementById("urbanID3").style.display = "none";
      document.getElementById("urbanID4").style.display = "none";
      
      
      document.getElementById("ruralID1").style.display = "block";
      document.getElementById("ruralID2").style.display = "block";
      document.getElementById("ruralID3").style.display = "block";
      document.getElementById("ruralID4").style.display = "block";
      
      
      getStateData("S");
        
    }
    else if (document.getElementById("urban").checked) {
      document.getElementById("ruralUrbanFlag").value=document.getElementById("urban").value;
        
      document.getElementById("urbanID1").style.display = "block";
      document.getElementById("urbanID2").style.display = "block";
      document.getElementById("urbanID3").style.display = "block";
      document.getElementById("urbanID4").style.display = "block";
      
      document.getElementById("ruralID1").style.display = "none";
      document.getElementById("ruralID2").style.display = "none";
      document.getElementById("ruralID3").style.display = "none";
      document.getElementById("ruralID4").style.display = "none";
      $('#Rural-profile').removeClass('disabled');
      $('#Urban-home').addClass('show');
      $('#Urban-home').addClass('active');
      $('#Rural-profile-tab').removeClass('active');
      $('#Urban-home-tab').addClass('active');
      getStateDataUrban('S');
      
    }
    }
  }
  
  
  
  function editDetail(value) {
  
  var requestUri='<%=requestUri%>';
  var stateCode;
  on();
    if(document.getElementById("ruralUrbanFlag").value=='R'){
       document.getElementById("stateName").value  =$("#stateCodeRural option:selected").text();
       document.getElementById("districtName").value =$("#districtCodeRural option:selected").text();
       document.getElementById("villageName").value =$("#villageCodeRural option:selected").text();
       document.getElementById("blockName").value =$("#blockCodeRural option:selected").text();
       stateCode=document.getElementById("stateCodeRural").value;
    }else if(document.getElementById("ruralUrbanFlag").value=='U'){
        document.getElementById("stateName").value  =$("#stateCodeUrban option:selected").text();
       document.getElementById("districtName").value =$("#districtCodeUrban option:selected").text();
       document.getElementById("townName").value =$("#townCodeUrban option:selected").text();
       document.getElementById("wardName").value =$("#wardCodeUrban option:selected").text();
       stateCode=document.getElementById("stateCodeUrban").value;
    }	
  
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
        document.forms[0].action="/"+requestUri+"/beneficiaryCardDonwload";
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
        document.forms[0].action="/"+requestUri+"/beneficiaryCardDonwload";
        document.forms[0].method="post";
        document.forms[0].submit();
            }else if(obj['status'] == "D"){
        document.forms[0].action="/"+requestUri+"/beneficiaryDemoKycView";
        document.forms[0].method="post";
        document.forms[0].submit();
             }else{
        document.forms[0].action="/"+requestUri+"/beneficiaryView";
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
  
  
  
  function getStateDataView(type,stateCode,districtCode,blockCode,villageCode) {
  
    var requestUri='<%=requestUri%>';
    
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
      document.getElementById("stateCodeRural").value=stateCode;  
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
          document.getElementById("districtCodeRural").value=districtCode;   
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
        $("#blockCodeRural option").remove();
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
         document.getElementById("blockCodeRural").value=blockCode;   
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
          document.getElementById("villageCodeRural").value=villageCode; 
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
  
  
  
  function getStateDataUrbanView(type,stateCode,districtCode,townCode,wardCode) {
  
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
        document.getElementById("stateCodeUrban").value=stateCode;  
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
          document.getElementById("districtCodeUrban").value=districtCode;
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
        $("#townCodeUrban option").remove();
              var obj = jQuery.parseJSON( data );
             var count=0;
           for (var key in obj) {
  
               var values =  obj[key];
               var x = document.getElementById("townCodeUrban");
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
          document.getElementById("townCodeUrban").value=townCode;
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
           document.getElementById("WardCodeUrban").value=wardCode;
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
  
  </script>
    
  <script>
  
  function submitSearchRuralView(ruralUrbanFlag,stateCodeRural,districtCodeRural,blockCodeRural,villageCodeRural){
     onSearch();
    var requestUri='<%=requestUri%>';
    $.ajax({
          type: "POST",
          url: "/"+requestUri+"/beneficiarySearch",
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
              
              
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
        
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
      
  function submitSearchUrbanView(ruralUrbanFlag,stateCodeUrban,districtCodeUrban,townCodeUrban,WardCodeUrban){
      var requestUri='<%=requestUri%>';
   onSearch();
    $.ajax({
          type: "POST",
         url: "/"+requestUri+"/beneficiarySearch",
        //    url: "/beneficiarySearch",
          data: {"stateCodeUrban": stateCodeUrban,
              "districtCodeUrban":districtCodeUrban,
              "townCodeUrban" : townCodeUrban,
                "WardCodeUrban" :WardCodeUrban,
                "ruralUrbanFlag" :ruralUrbanFlag,
              },
          success: function(data){
           newData = data;
              console.log(newData);
        var data1 = jQuery.parseJSON( newData );
            //  var data1 =eval(newData.villageList);
            var sno 
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
    
  
  </script>
  
  <script>
  
  function on() {
    document.getElementById("overlay").style.display = "flex";
  }
  
  function off() {
    document.getElementById("overlay").style.display = "none";
  }
  
  function onSearch() {
    document.getElementById("overlay_search").style.display = "flex";
  }
  
  function offSearch() {
    document.getElementById("overlay_search").style.display = "none";
  }
  
  
  function formatDate(date) {
    if(date.length>4){
      year=date.substring(0,4);
    }else{
    year=date;
    }	
      return year;
  }
  
  </script>
  
</body>

</html>