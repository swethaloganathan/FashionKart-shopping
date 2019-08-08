<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ideas2it.onlineshopping.model.User" %>
<%@ page import="com.ideas2it.onlineshopping.model.Role" %>  
<html>
  <head>
    <title>
       FashionKart Shopping
    </title>
     <link rel="stylesheet" href="style.css">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
     <meta charset="utf-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  </head>
   <body class="main-menu">
    <%
       String roleName = (String) session.getAttribute("role");
       User user = (User) session.getAttribute("user");
       if (session != null) { %>
          <h4>
             <p align="right">Hello, welcome <%=user.getUsername()%></p>
          </h4>
    <% } %>
    <div class="container">
        <div id="mySidenav" class="sidenav">
             <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
             <form method="GET">
               <a href="display-product">Product</a>
             </form>
             <form method="GET">
               <a href="display-cart">Cart</a>
             </form>
             <form method="GET">
               <a href="display-order">Order</a>
             </form>
             <% if (roleName.equals("ROLE_ADMIN")) { %>
               <form method="GET">
                  <a href="display-role">Role</a>
               </form>
             <% } %>
             <% if (roleName.equals("ROLE_ADMIN")) { %>
               <form method="GET">
                  <a href="display-user">User</a>
               </form>
             <% } %>
               <form method="GET">
                  <a href="logout">LogOut</a>
               </form>
           </div>
           <div id="main">
              <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;Home Menu</span>
           </div>
           <script>
              function openNav() {
                  document.getElementById("mySidenav").style.width = "250px";
                  document.getElementById("main").style.marginLeft = "250px";
              }
              function closeNav() {
                  document.getElementById("mySidenav").style.width = "0";
                  document.getElementById("main").style.marginLeft= "0";
              }
           </script>
           <center>
              <h2>WELCOME TO FASHIONKART SHOPPING</h2>
           </center>
           <div id="myCarousel" class="carousel slide" data-ride="carousel">
              <!-- Indicators -->
              <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
              </ol>
              <!-- Wrapper for slides -->
              <div class="carousel-inner">
                <div class="item active">
                  <center>
                    <img src="img/login1.jpeg" alt="Los Angeles" width="850" height="350">
                  </center>
                </div>
              <div class="item">
                 <center>
                   <img src="img/login3.jpeg" alt="Chicago" width="850" height="350">
                 </center>
              </div>
              <div class="item">
                 <center>
                   <img src="img/homelogin4.jpg" alt="New york" width="850" height="350">
                 </center>
              </div>
           </div>
           <!-- Left and right controls -->
           <a class="left carousel-control" href="#myCarousel" data-slide="prev">
             <span class="glyphicon glyphicon-chevron-left"></span>
             <span class="sr-only">Previous</span>
           </a>
           <a class="right carousel-control" href="#myCarousel" data-slide="next">
             <span class="glyphicon glyphicon-chevron-right"></span>
             <span class="sr-only">Next</span>
           </a>
       </div>
    </div>
  </div>
 </body>
</html>
