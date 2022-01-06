<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>AdminLTE 3 | Legacy User Menu</title>

    <!-- Google Font: Source Sans Pro -->
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"
    />
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css" />
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/adminlte.min.css" />

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
      .tabsnav .nav-item {
        text-align: center;
        flex: 1 1 10%;
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
      .table-striped tbody tr:nth-of-type(even) {
        background-color: #dedeff;
      }
      .gap-1 {
        gap: 1rem;
      }

      .tabsnav {
        background: #ffffff !important;
      }
    </style>
  </head>
  <body class="hold-transition sidebar-mini">
    <!-- Site wrapper -->
    <div class="wrapper">
      <!-- Navbar -->
      <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button"
              ><i class="fas fa-bars"></i
            ></a>
          </li>
          <li class="nav-item d-none d-sm-inline-block">
            <a href="#" class="nav-link">Home</a>
          </li>
          <li class="nav-item d-none d-sm-inline-block">
            <a href="#" class="nav-link">Contact</a>
          </li>
        </ul>

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
          <!-- Navbar Search -->
          <li class="nav-item">
            <a
              class="nav-link"
              data-widget="navbar-search"
              href="#"
              role="button"
            >
              <i class=""></i>
            </a>
            <div class="navbar-search-block">
              <form class="form-inline">
                <div class="input-group input-group-sm">
                  <input
                    class="form-control form-control-navbar"
                    type="search"
                    placeholder="Search"
                    aria-label="Search"
                  />
                  <div class="input-group-append">
                    <button class="btn btn-navbar" type="submit">
                      <i class="fas fa-search"></i>
                    </button>
                    <button
                      class="btn btn-navbar"
                      type="button"
                      data-widget="navbar-search"
                    >
                      <i class="fas fa-times"></i>
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </li>

          <!-- Messages Dropdown Menu -->
          <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="#">
              <i class=""></i>
              <span class="badge badge-danger navbar-badge"></span>
            </a>
            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
              <a href="#" class="dropdown-item">
                <!-- Message Start -->
                <div class="media">
                  <img
                    src="../../dist/img/user1-128x128.jpg"
                    alt="User Avatar"
                    class="img-size-50 mr-3 img-circle"
                  />
                  <div class="media-body">
                    <h3 class="dropdown-item-title">
                      Brad Diesel
                      <span class="float-right text-sm text-danger"
                        ><i class="fas fa-star"></i
                      ></span>
                    </h3>
                    <p class="text-sm">Call me whenever you can...</p>
                    <p class="text-sm text-muted">
                      <i class="far fa-clock mr-1"></i> 4 Hours Ago
                    </p>
                  </div>
                </div>
                <!-- Message End -->
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item">
                <!-- Message Start -->
                <div class="media">
                  <img
                    src="../../dist/img/user8-128x128.jpg"
                    alt="User Avatar"
                    class="img-size-50 img-circle mr-3"
                  />
                  <div class="media-body">
                    <h3 class="dropdown-item-title">
                      John Pierce
                      <span class="float-right text-sm text-muted"
                        ><i class="fas fa-star"></i
                      ></span>
                    </h3>
                    <p class="text-sm">I got your message bro</p>
                    <p class="text-sm text-muted">
                      <i class="far fa-clock mr-1"></i> 4 Hours Ago
                    </p>
                  </div>
                </div>
                <!-- Message End -->
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item">
                <!-- Message Start -->
                <div class="media">
                  <img
                    src="../../dist/img/user3-128x128.jpg"
                    alt="User Avatar"
                    class="img-size-50 img-circle mr-3"
                  />
                  <div class="media-body">
                    <h3 class="dropdown-item-title">
                      Nora Silvester
                      <span class="float-right text-sm text-warning"
                        ><i class="fas fa-star"></i
                      ></span>
                    </h3>
                    <p class="text-sm">The subject goes here</p>
                    <p class="text-sm text-muted">
                      <i class="far fa-clock mr-1"></i> 4 Hours Ago
                    </p>
                  </div>
                </div>
                <!-- Message End -->
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item dropdown-footer"
                >See All Messages</a
              >
            </div>
          </li>
          <!-- Notifications Dropdown Menu -->
          <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="#">
              <i class=""></i>
              <span class="badge badge-warning navbar-badge"></span>
            </a>
            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
              <span class="dropdown-item dropdown-header"
                >15 Notifications</span
              >
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item">
                <i class="fas fa-envelope mr-2"></i> 4 new messages
                <span class="float-right text-muted text-sm">3 mins</span>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item">
                <i class="fas fa-users mr-2"></i> 8 friend requests
                <span class="float-right text-muted text-sm">12 hours</span>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item">
                <i class="fas fa-file mr-2"></i> 3 new reports
                <span class="float-right text-muted text-sm">2 days</span>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item dropdown-footer"
                >See All Notifications</a
              >
            </div>
          </li>
          <li class="nav-item dropdown user-menu">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
              <img
                src="../../dist/img/user2-160x160.jpg"
                class="user-image img-circle elevation-2"
                alt="User Image"
              />
              <span class="d-none d-md-inline">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
              <!-- User image -->
              <li class="user-header bg-primary">
                <img
                  src="../../dist/img/user2-160x160.jpg"
                  class="img-circle elevation-2"
                  alt="User Image"
                />

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-4 text-center">
                    <a href="#">Followers</a>
                  </div>
                  <div class="col-4 text-center">
                    <a href="#">Sales</a>
                  </div>
                  <div class="col-4 text-center">
                    <a href="#">Friends</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <a href="#" class="btn btn-default btn-flat">Profile</a>
                <a href="#" class="btn btn-default btn-flat float-right"
                  >Sign out</a
                >
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-widget="fullscreen" href="#" role="button">
              <i class="fas fa-expand-arrows-alt"></i>
            </a>
          </li>
          <li class="nav-item">
            <a
              class="nav-link"
              data-widget="control-sidebar"
              data-slide="true"
              href="#"
              role="button"
            >
              <i class="fas fa-th-large"></i>
            </a>
          </li>
        </ul>
      </nav>
      <!-- /.navbar -->

      <!-- Main Sidebar Container -->
      <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="#" class="brand-link">
          <img
            src="dist/img/AdminLTELogo.png"
            alt="AdminLTE Logo"
            class="brand-image img-circle elevation-3"
            style="opacity: 0.8"
          />
          <span class="brand-text font-weight-light">AdminLTE 3</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
          <!-- SidebarSearch Form -->
          <div class="form-inline mt-2">
            <div class="input-group" data-widget="sidebar-search">
              <input
                class="form-control form-control-sidebar"
                type="search"
                placeholder="Search"
                aria-label="Search"
              />
              <div class="input-group-append">
                <button class="btn btn-sidebar">
                  <i class="fas fa-search fa-fw"></i>
                </button>
              </div>
            </div>
          </div>

          <!-- Sidebar Menu -->

          <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
      </aside>
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->

        <section>
          <div class="container">
            <div class="row">
              <div class="card col-md-12">
                <div class="card-header">
                  <div class="steps">
                    <div class="step active">
                      <span class="btn btn-primary"> Family details </span>
                    </div>
                    <div class="step">
                      <span class="btn btn-primary"
                        >Details of the New Member</span
                      >
                    </div>
                    <div class="step">
                      <span class="btn btn-primary">
                        Exisiting PMJAY beneficiary
                      </span>
                    </div>
                    <div class="step">
                      <span class="btn btn-primary">Preview</span>
                    </div>
                  </div>
                </div>
                <div id="cardBody" class="card-body">
                  <div class="tabs">
                    <div id="first" class="tab w-100">
                      <div class="table-responsive">
                        <table class="table table-striped">
                          <thead>
                            <tr>
                              <th scope="col">#</th>
                              <th scope="col">Name of the family member</th>
                              <th scope="col">Source (SECC/Added Member)</th>
                              <th scope="col">Current status</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <th scope="row">1</th>
                              <td>Mark</td>
                              <td>Otto</td>
                              <td>
                                <span class="badge badge-secondary"
                                  >Not Enrolled</span
                                >
                              </td>
                            </tr>
                            <tr>
                              <th scope="row">2</th>
                              <td>Jacob</td>
                              <td>Thornton</td>
                              <td>
                                <span class="badge badge-warning"
                                  >Under Process</span
                                >
                              </td>
                            </tr>
                            <tr>
                              <th scope="row">3</th>
                              <td>Larry</td>
                              <td>the Bird</td>
                              <td>
                                <span class="badge badge-success"
                                  >Under Process</span
                                >
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                    <div id="second" class="tab w-100">
                      <div class="row justify-content-between">
                        <div class="col-md-6 d-flex gap-1">
                          <p>
                            I undersigned want to add myself to the SECC
                            database as PMJAY beneficiary because of
                          </p>
                        </div>
                        <div class="col-md-3">
                          <select class="custom-select">
                            <option selected>Marriage/birth</option>
                            <option value="1">Marriage</option>
                            <option value="2">Birth</option>
                          </select>
                        </div>
                      </div>

                      <div class="row my-3">
                        <div class="form-group col-md-4">
                          <label for="">Name</label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Guardian Name </label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Date of Birth </label>

                          <input
                            type="date"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Gender </label>
                          <select class="custom-select">
                            <option selected>Select gender</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                          </select>
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Pin code </label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Mobile</label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>
                      </div>
                      <div class="row align-items-end">
                        <div class="col-md-5">
                          <label for="inputgender" class="form-label"
                            >Enter Your Aadhaar Card</label
                          >
                          <div class="d-flex flex-column gap-3 mt-2">
                            <input
                              type="text"
                              class="form-control"
                              placeholder="Enter your Aadhaar card number"
                            />
                          </div>
                        </div>

                        <div class="col-md-3">
                          <button class="btn btn-primary mt-4">preview</button>
                        </div>
                      </div>
                      <div class="col-md-9 mt-3">
                        <div class="form-check form-check-inline">
                          <input
                            class="form-check-input"
                            type="radio"
                            id="radio1"
                            name="ekyc-new"
                            value="option3"
                          />
                          <label class="form-check-label" for="radio"
                            >OTP
                          </label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input
                            class="form-check-input"
                            type="radio"
                            id="radio1"
                            name="ekyc-new"
                            value="option3"
                          />
                          <label class="form-check-label" for="radio"
                            >Fingerprint</label
                          >
                        </div>
                        <div class="form-check form-check-inline">
                          <input
                            class="form-check-input"
                            type="radio"
                            id="radio1"
                            name="ekyc-new"
                            value="option3"
                          />
                          <label class="form-check-label" for="radio"
                            >IRIS</label
                          >
                        </div>
                      </div>
                    </div>
                    <div id="third" class="tab w-100">
                      <div class="row my-3">
                        <div class="form-group col-md-4">
                          <label for="">Name</label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Guardian Name </label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">HHID </label>

                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Gender </label>
                          <select class="custom-select">
                            <option selected>Select gender</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                          </select>
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Pin code </label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-4">
                          <label for="">Address</label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="form-group col-md-3">
                          <label for="">Mr x </label>
                          <select class="custom-select">
                            <option selected>Select gender</option>
                            <option value="1">Male</option>
                            <option value="2">Female</option>
                          </select>
                        </div>
                        <div class="form-group col-md-3">
                          <label for="">of Mr y </label>
                          <div class="input-group">
                            <input
                              type="file"
                              class="form-control"
                              id="inputGroupFile04"
                              aria-describedby="inputGroupFileAddon04"
                              aria-label="Upload"
                            />
                          </div>
                        </div>
                        <div class="form-group col-md-3">
                          <label for="">Date of marriage/birth</label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>
                        <div class="form-group col-md-3">
                          <label for="">Document name</label>
                          <input
                            type="text"
                            name=""
                            class="form-control"
                            id=""
                          />
                        </div>

                        <div class="col-md-12">
                          <div class="form-check">
                            <input
                              class="form-check-input"
                              type="checkbox"
                              value=""
                              id="flexCheckDefault"
                            />
                            <label
                              class="form-check-label"
                              for="flexCheckDefault"
                            >
                              I hereby declare that I agree to add another
                              member to my household ID for availing the PMJAY
                              benefits
                            </label>
                          </div>
                        </div>
                      </div>
                      <div class="row align-items-end">
                        <div class="col-md-5">
                          <label for="inputgender" class="form-label"
                            >Enter Your Aadhaar Card</label
                          >
                          <div class="d-flex flex-column gap-3 mt-2">
                            <input
                              type="text"
                              class="form-control"
                              placeholder="Enter your Aadhaar card number"
                            />
                          </div>
                        </div>
                      </div>

                      <div class="row my-3 justify-content-between">
                        <div class="col-md-7">
                          <div class="form-check form-check-inline">
                            <input
                              class="form-check-input"
                              type="radio"
                              id="radio1"
                              value="option1"
                            />
                            <label class="form-check-label" for="radio"
                              >OTP</label
                            >
                          </div>
                          <div class="form-check form-check-inline">
                            <input
                              class="form-check-input"
                              type="radio"
                              id="radio1"
                              name="ekyc-new"
                              value="option2"
                            />
                            <label class="form-check-label" for="radio">
                              Finger Print</label
                            >
                          </div>
                          <div class="form-check form-check-inline">
                            <input
                              class="form-check-input"
                              type="radio"
                              id="radio1"
                              name="ekyc-new"
                              value="option3"
                            />
                            <label class="form-check-label" for="radio"
                              >IRIS
                            </label>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div id="fourth" class="tab w-100">Tab 4</div>
                  </div>
                </div>
                <div class="card-footer d-flex justify-content-end gap-1">
                  <button
                    id="prevBtn"
                    class="btn btn-outline-primary"
                    onclick="next(-1)"
                  >
                    Previous
                  </button>
                  <button
                    id="nextBtn"
                    class="btn btn-primary"
                    onclick="next(1)"
                  >
                    Next
                  </button>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!-- Main content -->

        <!-- /.content -->
      </div>
      <!-- Content Wrapper. Contains page content -->

      <!-- /.content-wrapper -->

      <footer class="main-footer">
        <div class="float-right d-none d-sm-block"><b>Version</b> 3.1.0</div>
        <strong
          >Copyright &copy; 2014-2021
          <a href="https://adminlte.io">AdminLTE.io</a>.</strong
        >
        All rights reserved.
      </footer>

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
    <script src="dist/js/demo.js"></script>
    <script src="dist/js/otp.js"></script>

    <script>
      var currentTab = 0;

      showTab(currentTab);

      function showTab(n) {
        var x = document.getElementsByClassName("tab");
        x[n].style.display = "block";

        if (n == 0) {
          document.getElementById("prevBtn").style.display = "none";
        } else {
          document.getElementById("prevBtn").style.display = "inline";
        }
        if (n == x.length - 1) {
          document.getElementById("nextBtn").innerHTML = "Submit";
        } else {
          document.getElementById("nextBtn").innerHTML = "Next";
        }
        fixStepIndicator(n);
      }

      function next(n) {
        var x = document.getElementsByClassName("tab");

        x[currentTab].style.display = "none";

        currentTab = currentTab + n;

        if (currentTab >= x.length) {
          return false;
        }

        showTab(currentTab);
      }

      function fixStepIndicator(n) {
        var i,
          x = document.getElementsByClassName("step");
        for (i = 0; i < x.length; i++) {
          x[i].className = x[i].className.replace(" active", "");
        }

        x[n].className += " active";
      }
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
  </body>
</html>
