<!DOCTYPE html>
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
          
          <ul class="list-unstyled mt-2 ps-0 left-nav-custom">
            <li class="mb-1">
              <button class="btn btn-toggle align-items-center rounded" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">
                Help
              </button>
              <div class="collapse" id="home-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> By Name or any other column i.e Father's name, YOB, gender etc.</a></li>
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> By Name and Father's name ( keep space between Name and Father's name).</a></li>
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> By Any two consecutive columns with space.</a></li>
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> By Name or any other column i.e Father's name, YOB, gender etc.</a></li>
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> By Father's name and view family data with card status.</a></li>
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> CSV Format</a></li>
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> Excel Format</a></li>
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> PDF Format</a></li>
                  <li><a href="#" class="link-dark rounded"><i class="fa fa-info-circle"></i> Privacy Policy</a></li>
                </ul>
              </div>
            </li>            
          </ul>

        </div>
      </div>
    </div>
  
    <main class="col">
      <nav>
        <div class="container-fluid nav-wrapper">
          <div class="logo">
            <a href="javascript:void(0);" data-bs-target="#sidebar" data-bs-toggle="collapse" class="me-2 text-decoration-none humberger"><img src="images/humberger-white.png" alt=""></a>
            <a href="https://setu.pmjay.gov.in/setu/" class="text-white ms-1">
              <!-- <img src="images/national-health-authority-logo1.png" alt="National Health Authority"> -->
              Home
            </a>
          </div>          
          <div class="dropdown custom-dropdown">
            <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                <img class="me-2" src="images/user-profile.jpg" alt="" width="24" height="24"> 9999651332
            </button>
            <div class="dropdown-menu dropdown-menu-end dropdown-profile-wrap" aria-labelledby="dropdownMenuButton1">
              <div class="dropdown-profile">                          
                <strong>abc@gmail.com</strong>
              </div>
              <div class="dropdown-profile">
                <span>CSC_ID :</span>
                <strong>8976564120558</strong>
              </div>
              <div class="dropdown-profile">
                <span>Email :</span>
                <strong>fullemailid@gmail.com</strong>
              </div>
              <div class="dropdown-profile">
                <span>State Code :</span>
                <strong>UP</strong>
              </div>
              <div class="dropdown-profile">
                <span>District Code</span>
                <strong>JOP</strong>
              </div>
              <div class="dropdown-profile">
                <a class="btn btn-dark custom-btn-sm" href="javascript:void(0);">Logout</a>
              </div>                        
            </div>              
          </div> 
        </div>
      </nav>
  
      <div class="beneficiary-section">
        <main>
          <section class="SECC-data mb-4">
            <div class="container">
              <div class="px-1 px-md-3">
                <div class="col-12">
                  <h2>Link Your AADHAAR </h2>
                  <div class="beneficiary-view-tabs mb-2">
                    <ul class="nav nav-pills" id="pills-tab" role="tablist">
                      <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="pills-nfsa-tab" data-bs-toggle="pill" data-bs-target="#pills-nfsa" type="button" role="tab" aria-controls="pills-nfsa" aria-selected="true">NFSA DETAILS</button>
                      </li>
                      <li class="nav-item" role="presentation">
                        <button class="nav-link" id="pills-declare-tab" data-bs-toggle="pill" data-bs-target="#pills-declare" type="button" role="tab" aria-controls="pills-declare" aria-selected="false">DECLARE</button>
                      </li>
                      <li class="nav-item" role="presentation">
                        <button class="nav-link" id="pills-ekyc-tab" data-bs-toggle="pill" data-bs-target="#pills-ekyc" type="button" role="tab" aria-controls="pills-ekyc" aria-selected="false">eKYC DETAILS</button>
                      </li>
                      <li class="nav-item" role="presentation">
                        <button class="nav-link" id="pills-preview-tab" data-bs-toggle="pill" data-bs-target="#pills-preview" type="button" role="tab" aria-controls="pills-preview" aria-selected="false">PREVIEW</button>
                      </li>
                    </ul>
                    <div class="tab-content tab-content-view" id="pills-tabContent">
                      <div class="tab-pane fade show active" id="pills-nfsa" role="tabpanel" aria-labelledby="pills-nfsa-tab">
                        <div class="tab-card-in">
                          
                          <table id="example" class="table table-striped dataTable" style="width:100%">
                            <div class="tab-pane fade" id="pills-familymember" role="tabpanel" aria-labelledby="pills-familymember-tab">
                              
                            <thead>
                              <div class="row my-3">
                              <div class=" col-md-4 col-sm-6 mb-2">
                                <div class="custom-form-group form-group">
                                  <label for="">State</label>
                                  <select class="form-select">
                                    <option value="">Delhi</option>
                                    <option value="">Gujrat</option>
                                    <option value="">Maharastra</option>
                                    <option value="">Himachal</option>
                                    <option value="">Punjab</option>
                                    <option value="">Haryana</option>
                                  </select>
                                </div> 
                              </div>
                              <div class=" col-sm-6 col-md-4 ">
                                <div class="custom-form-group form-group">
                                  <label for="">Ration card Number</label>
                                  <input type="text" class="form-control">
                                </div>
                              </div>

                              <div class="col-md-4">
                                <button class="btn btn-sm btn-dark" onclick="window.location.href='#'">Submit</button> 

                              </div>
                              </div>
                                <tr>
                                    <th>S.No.</th>
                                    <th>Ref ID</th>
                                    <th>Family Member Name</th>
                                    <th>Yod</th>
                                    <th>Gender</th>
                                    <th>Mother Name</th>
                                    <th>Relation</th>
                                    <th> Card Status</th>
                                    <th>View</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                  <td>1</td>
                                  <td>205781751</td>
                                  <td>PRITESH P PARAMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>HEAD</td>
                                  <td>Card not made</td>
                                  <td><a href="#">View</a></td>
                                </tr> 
                                <tr>
                                  <td>2</td> 
                                  <td>12893178</td>
                                  <td>PRITESH P PARMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>HEAD</td>
                                  <td>Card not made</td>
                                  <td><a href="#">View</a></td>
                                </tr> 
                                <tr>
                                  <td>3</td>
                                  <td>13467982</td>
                                  <td>PRITESH P PARMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>HEAD</td>
                                  <td>Card not made</td>
                                  <td><a href="#">View</a></td>
                                </tr> 
                                <tr>
                                  <td>4</td>
                                  <td>35795135</td>
                                  <td>PRITESH P PARMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>HEAD</td>
                                  <td>Card not made</td>
                                  <td><a href="#">View</a></td>
                                </tr> 
                                <tr>
                                  <td>5</td>
                                  <td>15975315</td>
                                  <td>PRITESH P PARMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>HEAD</td>
                                  <td>Card not made</td>
                                  <td><a href="#">View</a></td>
                                </tr>   
                                <tr>
                                  <td>6</td>
                                  <td>87458965</td>
                                  <td>PRITESH P PARMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>HEAD</td>
                                  <td>Card not</td>
                                  <td><a href="#">View</a></td>
                                </tr> 
                                <tr>
                                  <td>7</td>
                                  <td>89562356</td>
                                  <td>PRITESH P PARMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>HEAD</td>
                                  <td>Card not made</td>
                                  <td><a href="#">View</a></td>
                                </tr> 
                                <tr>
                                  <td>8</td>
                                  <td>56325461</td>
                                  <td>PRITESH P PARMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>HEAD</td>
                                  <td>Card not made</td>
                                  <td><a href="#">View</a></td>
                                </tr> 
                                <tr>
                                  <td>9</td>
                                  <td>45698712</td>
                                  <td>PRITESH P PARMAR</td>
                                  <td>1930</td>
                                  <td>Male</td>
                                  <td>MAIALDINA FERNANDES</td>
                                  <td>Head</td>
                                  <td>Card not made</td>
                                  <td><a href="#">View</a></td>
                                </tr> 
                                                 
                            </tbody>                          
                        </table>
                            <div class="text-center mt-3">
                              <button class="btn btn-sm btn-white" onclick="window.location.href='#'">Cancel</button>
                              <button class="btn btn-sm btn-dark" onclick="window.location.href='#'">Next</button>
                            </div>
                          </div>
                      </div>
                      <div class="tab-pane fade" id="pills-declare" role="tabpanel" aria-labelledby="pills-declare-tab">
                        <div class="tab-card-in">
                          <h3>Enter Your Details (As Per Aadhaar)</h3>
                            <div class="row mt-4">
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Name</label>
                                  <input type="text" class="form-control">
                                </div>                                
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Guardian's Name</label>
                                  <input type="text" class="form-control">
                                </div> 
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Gender</label>
                                  <select class="form-select">
                                    <option value="">Male</option>
                                    <option value="">Female</option>
                                  </select>
                                </div> 
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">DOB</label>
                                  <input type="date" class="form-control">
                                </div> 
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Mobile Number</label>
                                  <input type="text" class="form-control">
                                </div> 
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Pincode</label>
                                  <input type="text" class="form-control">
                                </div> 
                              </div>
                              <div class="col-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">Enter Aadhaar for Verification</label>
                                  <input type="text" class="form-control">
                                </div>
                              </div>
                              <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <div class="custom-form-group form-group">
                                  <label for="">How Would You like to Authenticate</label>

                                    <div class="nav mb-1 custom-radio-otp justify-content-between radio-group" id="pills-tab" role="tablist">                                       
                                      <div class="form-radio" role="presentation" id="demographic-home-tab" data-bs-toggle="pill" data-bs-target="#demographic-home" type="button" role="tab" aria-controls="demographic-home" aria-selected="true">
                                        <input type="radio" id="demographic" name="fav_language"><label for="demographic">Demographic</label>
                                      </div>
                                      <div class="form-radio" role="presentation" id="otp-home-tab" data-bs-toggle="pill" data-bs-target="#otp-home" type="button" role="tab" aria-controls="otp-home" aria-selected="false">
                                        <input type="radio" id="otp" name="fav_language"><label for="otp">OTP</label>
                                      </div>
                                      <div class="form-radio" role="presentation" id="finger-home-tab" data-bs-toggle="pill" data-bs-target="#finger-home" type="button" role="tab" aria-controls="finger-home" aria-selected="false">
                                        <input type="radio" id="finger" name="fav_language"><label for="finger">Finger</label>
                                      </div>
                                      <div class="form-radio" role="presentation" id="iris-home-tab" data-bs-toggle="pill" data-bs-target="#iris-home" type="button" role="tab" aria-controls="iris-home" aria-selected="false">
                                        <input type="radio" id="iris" name="fav_language"><label for="iris">IRIS</label>
                                      </div>
                                    </div>
                                    <div class="tab-content mt-4" id="pills-tabContent">
                                      <div class="tab-pane fade show active" id="demographic-home" role="tabpanel" aria-labelledby="demographic-home-tab">
                                        <button class="btn custom-btn-sm btn-dark">Demographic Auth</button>
                                      </div>
                                      <div class="tab-pane fade" id="otp-home" role="tabpanel" aria-labelledby="otp-home-tab">
                                        <button class="btn custom-btn-sm btn-dark">Get OTP</button>
                                      </div>
                                      <div class="tab-pane fade" id="finger-home" role="tabpanel" aria-labelledby="finger-home-tab">
                                        <button class="btn custom-btn-sm btn-dark">Scan Your Finger</button>
                                      </div>
                                      <div class="tab-pane fade" id="iris-home" role="tabpanel" aria-labelledby="iris-home-tab">
                                        <button class="btn custom-btn-sm btn-dark">Capture Your Biometric</button>
                                      </div>
                                    </div>

                                </div>
                              </div>
                            </div>

                            <div class="text-center mt-3">
                              <button class="btn btn-sm btn-white" onclick="window.location.href='#'">Previous</button>
                              <button class="btn btn-sm btn-dark" onclick="window.location.href='#'">Next</button>
                            </div>
                          </div>
                      </div>
                      <div class="tab-pane fade" id="pills-ekyc" role="tabpanel" aria-labelledby="pills-ekyc-tab">
                        <div class="tab-card-in">
                          <h3>eKYC Details </h3>
                            <div class="row mt-2">
                              <div class="col-12 col-sm-4 col-md-3 col-lg-2">
                                <img src="images/user-profile-l.jpg" class="img-thumbnail rounded mx-auto d-block" alt="">
                              </div>
                              <div class="col-12 col-sm-8 col-md-9 col-lg-10">
                                <div class="ekyc-details-table">
                                  <span>Name:</span>
                                  <strong>Aarianna Joseph</strong>
                                </div>
                                <div class="ekyc-details-table">
                                  <span>Guardian's Name:</span>
                                  <strong>Aarielle Aarianna Joseph</strong>
                                </div>
                                <div class="ekyc-details-table">
                                  <span>Gender:</span>
                                  <strong>Female</strong>
                                </div>
                                <div class="ekyc-details-table">
                                  <span>DOB:</span>
                                  <strong>1981</strong>
                                </div>
                                <div class="ekyc-details-table">
                                  <span>Address:</span>
                                  <strong>AB-587/24, Village: Amone, District: NORTH GOA, State: GOA</strong>
                                </div>
                              </div>
                                                           
                            </div>

                            <div class="text-center mt-3">
                              <button class="btn btn-sm btn-white" onclick="window.location.href='#'">Previous</button>
                              <button class="btn btn-sm btn-dark" onclick="window.location.href='#'">Next</button>
                            </div>
                          </div>
                      </div>
                      <div class="tab-pane fade" id="pills-preview" role="tabpanel" aria-labelledby="pills-preview-tab">
                        <div class="tab-card-in">
                          <h3>Declared Details by Beneficiary </h3>
                          <div class="row mx-0 mb-4">
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Name: </span>
                              <strong>DADAN RAGHUNATH PRASAD</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Guardian's Name: </span>
                              <strong>DADAN PRASAD</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Mother's Name:</span>
                              <strong>Nirmala Devi</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Gender: </span>
                              <strong>Male</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>YOB: </span>
                              <strong>1981</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>State Name: </span>
                              <strong>GOA</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>District: </span>
                              <strong>NORTH GOA</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Village/Town: </span>
                              <strong>Amone</strong>
                            </div>
                          </div>

                          <h3>Declared Details by Beneficiary </h3>
                          <div class="row mx-0 mb-4">
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Name: </span>
                              <strong>DADAN RAGHUNATH PRASAD</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Guardian's Name: </span>
                              <strong>DADAN PRASAD</strong>
                            </div>                           
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Gender: </span>
                              <strong>Male</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>DOB: </span>
                              <strong>1981</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Mobile Number: </span>
                              <strong>GOA</strong>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4 col-lg-3 tab-card-in-seccdata">
                              <span>Pincode: </span>
                              <strong>403503</strong>
                            </div>
                          </div>

                          <h3>eKYC Details as in Aadhaar</h3>
                          <div class="row mb-4">
                            <div class="col-12 col-sm-4 col-md-3 col-lg-2">
                              <img src="images/user-profile-l.jpg" class="img-thumbnail rounded mx-auto d-block" alt="">
                            </div>
                            <div class="col-12 col-sm-8 col-md-9 col-lg-10">
                              <div class="ekyc-details-table">
                                <span>Name:</span>
                                <strong>Aarianna Joseph</strong>
                              </div>
                              <div class="ekyc-details-table">
                                <span>Guardian's Name:</span>
                                <strong>Aarielle Aarianna Joseph</strong>
                              </div>
                              <div class="ekyc-details-table">
                                <span>Gender:</span>
                                <strong>Female</strong>
                              </div>
                              <div class="ekyc-details-table">
                                <span>DOB:</span>
                                <strong>1981</strong>
                              </div>
                              <div class="ekyc-details-table">
                                <span>Address:</span>
                                <strong>AB-587/24, Village: Amone, District: NORTH GOA, State: GOA</strong>
                              </div>
                            </div>
                                                         
                          </div>

                          <h3>Auth/eKYC Completed By</h3>
                          <div class="row mx-0">
                            <div class="col-6 col-sm-3 col-md-3 col-lg-3 tab-card-in-seccdata auth-tabs">
                              <span>Demographic </span>
                              <strong><i class="fas fa-times text-danger"></i></strong>
                            </div>
                            <div class="col-6 col-sm-3 col-md-3 col-lg-3 tab-card-in-seccdata auth-tabs">
                              <span>OTP </span>
                              <strong><i class="fas fa-check text-success"></i></strong>
                            </div>
                            <div class="col-6 col-sm-3 col-md-3 col-lg-3 tab-card-in-seccdata auth-tabs">
                              <span>Biometric (FingerPrint) </span>
                              <strong><i class="fas fa-times text-danger"></i></strong>
                            </div>
                            <div class="col-6 col-sm-3 col-md-3 col-lg-3 tab-card-in-seccdata auth-tabs">
                              <span>Biometric (Iris)</span>
                              <strong><i class="fas fa-times text-danger"></i></strong>
                            </div>
                          </div>


                            <div class="text-center mt-4">
                              <button class="btn btn-sm btn-white" onclick="window.location.href='#'">Previous</button>
                              <button class="btn btn-sm btn-white" onclick="window.location.href='#'">Finish</button>
                              <button class="btn btn-sm btn-dark" onclick="window.location.href='#'">Cancel</button>
                            </div>
                          </div>
                      </div>
                    </div>  
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
    </main>

  </div>
</div>

  <!-- JS File  -->
  <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript" src="js/setu-custom.js"></script>


</body>

</html>