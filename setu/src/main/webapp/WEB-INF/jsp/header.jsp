<div class="container-fluid nav-wrapper">
          <div class="logo">
            <a href="javascript:void(0);" data-bs-target="#sidebar" data-bs-toggle="collapse" class="me-2 text-decoration-none humberger"><img src="images/humberger-white.png" alt=""></a>
            <a href="/<%=requestUri%>/home" class="text-white ms-1">
              Home
            </a>
          </div>          
          <div class="dropdown custom-dropdown">
            <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
              <%if(ObjectUtils.isEmpty(sessionUser.getPht())){ %>  
              <img class="me-2" src="images/user-profile.jpg" alt="" width="24" height="24"><%=userId %>
              <%} else{ %>
                <img class="me-2" src="data:image/jpeg;base64,<%=sessionUser.getPht()%>" alt="" width="24" height="24"><%=userId %>
                <%}%>
            </button>
            <div class="dropdown-menu dropdown-menu-end dropdown-profile-wrap" aria-labelledby="dropdownMenuButton1">
               <div class="dropdown-profile">                          
                 <strong><%=sessionUser.getName() %></strong>
              </div>
              <div class="dropdown-profile">
				<span>CSC_ID :</span> <strong><%=userId %></strong>
				</div> 
              <div class="dropdown-profile">
                <span>Dob :</span>
                <strong><%=sessionUser.getDob() %></strong>
              </div>
              <div class="dropdown-profile">
                 <span>Gender :</span>
                <strong><%=sessionUser.getGender() %></strong> 
              </div>
              
             <div class="dropdown-profile">
              <span>Address :</span>
             <strong><%=sessionUser.getAdr() %>,<%=sessionUser.getDistrict() %>,
             <%=sessionUser.getState() %>-<%=sessionUser.getPin() %></strong> 
           </div>          
             
              <div class="dropdown-profile" style="text-align:center;">
                <a class="btn btn-dark custom-btn-sm" href="/<%=requestUri%>/logout">Logout</a>
              </div>                        
            </div>              
          </div> 
        </div>