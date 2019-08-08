<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ideas2it.onlineshopping.model.Role" %>
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
           <h3 align="center"> ADD USER: </h3>
              <form class="form-horizontal" action="save-user" method="post">
                 <div class="form-group">
                    <label class="control-label col-sm-3" for=uname">UserName:</label>
                    <div class="col-sm-6">
                       <input type="text" placeholder="Enter Username" name="username" class="form-control" id="uname" required>
                    </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="password">Password:</label>
                   <div class="col-sm-6">          
                       <input type="password" placeholder="Enter password" name="password" class="form-control" id="password" required>
                   </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="name">Name:</label>
                   <div class="col-sm-6">          
                       <input type="text" placeholder="Enter Name" name="name" class="form-control" id="name" required>
                   </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="email">Email:</label>
                   <div class="col-sm-6">       
                       <input type="email" placeholder="Enter Email" name="email" class="form-control" id="email" required>
                   </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="address">Address:</label>
                   <div class="col-sm-6">
                       <textarea rows = "5" cols = "50" name = "address" class="form-control" id="address" required></textarea>         
                   </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="phone">Phone Number:</label>
                   <div class="col-sm-6">      
                       <input type="number" maxlength="10" placeholder="Enter Phone number" name="phoneNumber" class="form-control" id="phone" required>
                   </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="phone">Select Roles:</label>
                   <div class="col-sm-6">
                        <%
                           List<Role> roles = (List<Role>) request.getAttribute("roles");
                           for (Role role : roles) {
                        %>
                      <div class="checkbox">
                          <label><input type="checkbox" name="roles" value="<%=role.getId()%>"><%=role.getName()%></label>
                      </div><br>
                      <%}%>
                   </div>
                </div>
                <div class="form-group">        
                   <div class="col-sm-offset-3 col-sm-5">
                      <input type="submit" class="btn btn-success" value="Save"/>
                   </div>
                   <div class="col-sm-offset-1">
                      <a class="btn btn-success" onclick="history.back()" role="button">Cancel</a>
                   </div>
               </div>
            </form>
        </div>
    </body>
</html>
