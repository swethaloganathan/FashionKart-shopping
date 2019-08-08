<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ideas2it.onlineshopping.model.User" %>
<html>
    <head>
        <title>Users</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body class="background">
         <%
              User userDetail = (User) session.getAttribute("user");
              if (session != null) { %>
                  <h4>
                     <p align="right">Hello, welcome <%=userDetail.getUsername()%></p>
                  </h4>
          <% } %>
       <div class="container">
          <h2 align="center">List of Users</h2>
          <table class="table">
            <thead>
            <tr>
               <td>
                  <form action="create-user" method="POST">
                     <input type="submit" class="btn btn-success" value="Add User"/>
                  </form>
               </td>
               <td colspan="10">
                   <form align="right" action="Mainmenu.jsp">
                      <input type="submit" class="btn btn-success" value="Back to Mainmenu"/>
                   </form>
               </td>
            </tr>
            <tr>
                <th>ID</th>
                <th>User Name</th>
                <th>Name</th>
                <th>Email ID</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Status</th>
                <th>Created Date</th>
                <th>Updated Date</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>
            <tbody>
               <tr>
                 <%
                    List<User> users = (List<User>) request.getAttribute("userDetails");
                    for (User user : users) {
                 %>
                 <td><%=user.getId()%></td>
                 <td><%=user.getUsername()%></td>
                 <td><%=user.getName()%></td>
                 <td><%=user.getEmail()%></td>
                 <td><%=user.getPhone()%></td>
                 <td><%=user.getAddress()%></td>
                 <td><%=user.getIsActive()%></td>
                 <td><%=user.getDateTime()%></td>
                 <td><%=user.getUpdatedDateTime()%></td>
                 <td><form action="edit-user" method="GET">
                        <input type="hidden" name="id" value="<%=user.getId()%>" />
                        <input type="submit" class="btn btn-success" value="Edit"/>
                     </form>
                 </td>
                 <td>
                     <form action="delete-user" method="GET">
                        <input type="hidden" name="id" value="<%=user.getId()%>" />
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
