<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ideas2it.onlineshopping.model.Role" %>
<%@ page import="com.ideas2it.onlineshopping.model.User" %>
<html>
    <head>
        <title>Roles</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body class="background">
       <%
           User user = (User) session.getAttribute("user");
           if (session != null) { %>
              <h4>
                 <p align="right">Hello, welcome <%=user.getUsername()%></p>
              </h4>
       <% } %>
       <div class="container">
          <h2 align="center">List of Roles</h2>
          <table class="table">
            <thead>
            <tr>
               <td>
                  <form action="save-role" method="POST">
                     <input type="submit" class="btn btn-success" value="Add role"/>
                  </form>
               </td>
               <td colspan="7">
                   <form align="right" action="Mainmenu.jsp">
                      <input type="submit" class="btn btn-success" value="Back to Mainmenu"/>
                   </form>
               </td>
            </tr>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Created Date</th>
                <th>Updated Date</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>
            <tbody>
               <tr>
                 <%
                    List<Role> roles = (List<Role>) request.getAttribute("roleDetails");
                    for (Role role : roles) {
                 %>
                 <td><%=role.getId()%></td>
                 <td><%=role.getName()%></td>
                 <td><%=role.getDescription()%></td>
                 <td><%=role.getIsActive()%></td>
                 <td><%=role.getDateTime()%></td>
                 <td><%=role.getUpdatedDateTime()%></td>
                 <td><form action="edit-role" method="GET">
                         <input type="hidden" name="id" value="<%=role.getId()%>" />
                         <input type="submit" class="btn btn-success" value="Edit"/>
                      </form>
                 </td>
                 <td>
                     <form action="delete-role" method="GET">
                         <input type="hidden" name="id" value="<%=role.getId()%>" />
                         <input type="submit" class="btn btn-success" value="Delete"/>
                     </form>
                 </td>
               </tr>
             <%}%>
           </tbody>
       </table>
     </div>
  </body>
</html>
