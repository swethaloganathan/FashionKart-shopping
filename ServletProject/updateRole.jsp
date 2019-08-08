<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ideas2it.onlineshopping.model.Role" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>ROLE MODULE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
    <body class="background">
       <div class="container">
             <h3 align="center"> UPDATE ROLE: </h3>
             <form class="form-horizontal" action="update-role" method="GET">
                   <%
                    Role role = (Role) request.getAttribute("role");
                    %>

               <div class="form-group">
                  <label class="control-label col-sm-3" for=rolename">Name:</label>
                  <div class="col-sm-6">
                     <input type="text" class="form-control" id="rolename" placeholder="Enter name" name="name" value="<%=role.getName()%>">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="description">Description:</label>
                  <div class="col-sm-6">          
                     <input type="text" class="form-control" id="description" placeholder="Enter password" name="description" value="<%=role.getDescription()%>">
                  </div>
               </div>
               <input type="hidden" name="id" value="<%=role.getId()%>"/>
               <input type="hidden" name="status" value="<%=role.getIsActive()%>"/>
               <input type="hidden" name="createdDate" value="<%=role.getDateTime()%>"/>
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
