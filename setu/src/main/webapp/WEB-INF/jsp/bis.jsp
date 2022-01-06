<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>National Health Authority</title>

  <link rel="icon" type="image/ico" sizes="16x16" href="images/favicon.ico">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Sora:wght@100;200;300;400;500;600;700;800&display=swap"
    rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  <link rel="stylesheet" href="sass/setu-custom.css">
  <link rel="stylesheet" type="text/css" href="https://kenwheeler.github.io/slick/slick/slick.css" />
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
        <li><a href="javascript:void;" class="searchbar"><img src="images/search.png" alt=""></a></li>
        <li>
          <button class="btn btn-yellow" onclick="window.location.href='index'">Register/Sign in</button>
        </li>
      </ul>
    </div>
  </nav>
 
  <div class="main-wrapper">
    <main>

      <section class="home-slider">
        <div class="container-fluid home-slider-wrap">
          <div class="home-slider-content">
            <h1>National Health Authority</h1>
            <p>National Health Authority (NHA) is the apex body responsible for implementing India&#39;s flagship public health insurance/assurance scheme called &#34;Ayushman Bharat Pradhan Mantri Jan Arogya Yojana&#34; & has been entrusted with the role of designing strategy, building technological infrastructure and implementation of &#34;National Digital Health Mission&#34; to create a National Digital Health Eco-system. NHA is leading the implementation for national Digital Health Mission (NDHM) in coordination with different ministries/departments of the Government of India, State Governments, and private sector/civil society organizations.</p>
            <!-- <button class="btn btn-dark">Book an Appointment</button> -->
          </div>
          <div class="home-slider-img">
            <div class="fade0">
              <div class="home-sliderimg-wrap">
                <div class="slider-content">
                  <h2>आयुष्मान भारत में अपना नाम जानना और कार्ड बनवाना हुआ अब और भी आसान</h2>
                  <ul>
                    <li><i class="fa fa-star" aria-hidden="true"></i>&#8377; 5 लाख का मुफ्त उपचार, प्रति वर्ष, प्रति परिवार </li>
                    <li><i class="fa fa-star" aria-hidden="true"></i>देश के किसी भी सूचीबद्ध सरकारी या प्राइवेट अस्पताल में इलाज </li>
                    <li><i class="fa fa-star" aria-hidden="true"></i>परिवार के सदस्यो की संख्या और कोई उम्र पर कोई सीमा नहीं </li>
                  </ul>
                  <div class="callus-wrap">
                      <img src="images/PMJAY-logo.png" alt="">
                      <p>अधिक जानकारी के लिए <b>14555</b> पर कॉल करे </p>
                  </div>
                </div>
                <div class="slider-figure">
                  <img src="images/PM-Pic01.png" alt="">
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    

      <section class="ayushman-wrap">
        <div class="container">
          <h2>How To Get Ayushman Card</h2>
          <div class="ayushman-card-wrap">
            <div class="ayushman-card">
              <img src="images/registered.png" alt=""> 
              <span>Register Yourself & Search Beneficiary</span>
              <button class="btn btn-dark" onclick="window.location.href='https://users.nha.gov.in/UserManagement/verifyEkyc.htm'">Register</button>
            </div>

            <div class="ayushman-card">
              <img src="images/approved.png" alt="">
              <span>Do Your eKYC & wait for Approval</span>
              <button class="btn btn-dark" onclick="window.location.href='index'">Do Your KYC</button>
            </div>

            <div class="ayushman-card">
              <img src="images/download.png" alt="">
              <span>Download Your Ayushman Card</span>
              <button class="btn btn-dark" onclick="window.location.href='index'">Download Card</button>
            </div>
          </div>
        </div>
      </section>

      <section class="faq-wrap">
        <div class="container">
          <h2>Frequently Asked Questions</h2>
          <div class="faq-card-wrap">
            <!-- <div class="left-faq">
              <div class="left-faq-content">
                <h3>Sub Heading</h3>
                <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Labore ipsum fugiat esse dolores omnis!
                  Dolorem eligendi molestias exercitationem architecto voluptates adipisicing elit. Labore ipsum fugiat
                  esse dolores omnis! Dolorem eligendi molestias exercitationem architecto voluptates a reprehenderit
                  veritatis, commodi nam. Modi quod amet illo provident.</p>
                <button class="btn btn-dark">View More</button>
              </div>
            </div> -->
            <div class="right-faq">
              <div class="accordion_container">
                <div class="accordion_head">How to get Ayushman card if i do not have aadhaar?<span
                    class="plusminus">+</span></div>
                <div class="accordion_body" style="display: none;">
                  <p>While the new BIS has been enabled to facilitate the users to generate their Ayushman card, the old
                    version of BIS is still running on https://bis.pmjay.gov.in. You can get yourself registered for
                    Ayushman Card at your nearest CSC center or at any empanelled PMJAY hospital.</p>
                </div>
                <div class="accordion_head">Who all can register and generate the Ayushman card in the new BIS ?<span
                    class="plusminus">+</span></div>
                <div class="accordion_body" style="display: none;">
                  <p>The new BIS allows any user who wants to register himself for generating Ayushman card for
                    himself/herself or for any known beneficiary. Also exisiting users of BIS can login to the new BIS
                    for generating Ayushman cards.</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>      
    </main>
	<%@ include file="footer.jsp" %>    

  </div>

  <!-- JS File  -->
  <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="https://kenwheeler.github.io/slick/slick/slick.min.js"></script>


  <script type="text/javascript" src="js/setu-custom.js"></script>

</body>

</html>