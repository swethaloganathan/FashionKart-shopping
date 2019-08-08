<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ideas2it.onlineshopping.model.User" %>
<%@ page import="com.ideas2it.onlineshopping.model.Cart" %>
<html>
<head>
    <title>Cart</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
  <body class="background">
           <%
              User user = (User) session.getAttribute("user");
              if (session != null) { %>
                  <h4>
                     <p align="right">Hello, welcome <%=user.getUsername()%></p>
                  </h4>
           <% } %>
      <center>
         <h2>Cart items</h2>
      </center>
        <form action="create-order" method="GET">
               <%
                 List<Cart> carts = (List<Cart>) request.getAttribute("cartDetails");
                 for (Cart cart : carts) {
               %>

           <div align="center">
             <table class="table" style="width:70%">
              <tbody>
                <tr>
                   <td>Select:</td><td><input type="checkbox" name="carts" value="<%=cart.getId()%>"/></td>
                </tr>
                <tr>
                   <td>Cart id:</td><td><%=cart.getId()%></td>
                </tr>
                <tr>
                   <td>Quantity</td><td><%=cart.getQuantity()%></td>
                </tr>
                <tr>
                   <td>Created date:</td><td><%=cart.getDateTime()%></td>
                </tr>
                <tr>
                   <td>Modified date:</td><td><%=cart.getUpdatedDateTime()%></td>
                </tr><form></form>
                <tr>
                   <td>
                     <div>
                       <form action="edit-cart" method="GET">
                           <input type="hidden" name="id" value="<%=cart.getId()%>"/>
                               <input type="submit" class="btn btn-success" value="Edit"/>
                       </form>
                      </div>
                    </td>
                    <td>
                      <div>
                          <form action="delete-cart" method="GET">
                             <input type="hidden" name="id" value="<%=cart.getId()%>" />
                             <input type="submit" class="btn btn-success" value="Delete"/>
                          </form>
                       </div>
                    </td>
                </tr>
            </tbody>
         </table>
      </div>
      <% } %>
         <center>
             <input type="submit" class="btn btn-success" value="Move to Order"/>
         </center>
   </form>
   <form align="right" action="Mainmenu.jsp">
        <input type="submit" class="btn btn-success" value="Back to Mainmenu"/>
   </form>
 </body>
</html>
