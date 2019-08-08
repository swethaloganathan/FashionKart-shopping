<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ideas2it.onlineshopping.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>USER MODULE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
    <body class="background">
       <div class="container">
             <h3 align="center"> UPDATE USER: </h3>
             <form class="form-horizontal" action="update-user" method="GET">
               <%
                   User user = (User) request.getAttribute("user");
                   session.setAttribute("roles", user.getRoles());
               %>
               <div class="form-group">
                  <label class="control-label col-sm-3" for=uname">User Name:</label>
                  <div class="col-sm-6">
                     <input type="text" name="username" value="<%=user.getUsername()%>" class="form-control" id="uname">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="password">Password:</label>
                  <div class="col-sm-6">          
                     <input type="text" name="password" value="<%=user.getPassword()%>" class="form-control" id="password">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="name">Name:</label>
                  <div class="col-sm-6">          
                     <input type="text" name="name" value="<%=user.getName()%>" class="form-control" id="name">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="email">Email ID:</label>
                  <div class="col-sm-6">          
                     <input type="email" name="email" value="<%=user.getEmail()%>" class="form-control" id="email">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="phone">Phone Number:</label>
                  <div class="col-sm-6">          
                     <input type="number" maxlength="10" name="phoneNumber" value="<%=user.getPhone()%>" class="form-control" id="phone">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="address">Address:</label>
                  <div class="col-sm-6">          
                     <input type="text" name="address" value="<%=user.getAddress()%>" class="form-control" id="address">
                  </div>
               </div>
               <input type="hidden" name="id" value="<%=user.getId()%>"/>
               <input type="hidden" name="status" value="<%=user.getIsActive()%>"/>
               <input type="hidden" name="createdDate" value="<%=user.getDateTime()%>"/>
               <div class="form-group">        
                 <div class="col-sm-offset-3 col-sm-5">
                    <input type="submit" class="btn btn-success" value="Update"/>
                 </div>
                 <div class="col-sm-offset-1">
                    <a class="btn btn-success" onclick="history.back()" role="button">Cancel</a>
                 </div>
               </div>
             </form>
        </div>
    </body>
</html>
