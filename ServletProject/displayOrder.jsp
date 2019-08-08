<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ideas2it.onlineshopping.model.User" %>
<%@ page import="com.ideas2it.onlineshopping.model.Product" %>
<%@ page import="com.ideas2it.onlineshopping.model.Order" %>
<%@ page import="com.ideas2it.onlineshopping.model.OrderDetail" %>
<html>
 <head>
    <title>Order</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
 </head>
  <body class="background">
     <form align="right" action="Mainmenu.jsp">
        <input type="submit" class="btn btn-success" value="Back to Mainmenu"/>
     </form>
           <%
              User user = (User) session.getAttribute("user");
              if (session != null) { %>
                  <h4>
                     Hello, welcome <%=user.getUsername()%>
                  </h4>
           <% } %>
      <center>
         <h2>List of orders</h2>
      </center>
              <%
                List<Order> orders = (List<Order>) request.getAttribute("orderDetails");
                for (Order order : orders) {
              %>
        <div>
              <h3 align="center">Order Id: <%=order.getId()%></h3>
              <% for (OrderDetail orderDetail : order.getOrderDetails()) {
                 Product product = orderDetail.getProduct();
              %>
             <div align="center">
                 <table class="table" style="width:70%">
                    <tr>
                       <td>Product Name:</td><td><%=product.getProductName()%></td>
                    </tr>
                    <tr>
                       <td>Description:</td><td><%=product.getDescription()%></td>
                    </tr>
                    <tr>
                       <td>Price:</td><td><%=product.getPrice()%></td>
                    </tr>
                 </table>
             </div>
             <% } %>
           <center>
              <h4>Total Price: <%=order.getTotalPrice()%></h4>
              <h4>Status: <%=order.getIsActive()%></h4>
              <h4>Created date: <%=order.getDateTime()%></h4>
              <h4>Modified date: <%=order.getUpdatedDateTime()%></h4>
           </center>
          <div align="center">
             <form action="delete-order" method="GET">
                <input type="hidden" name="id" value="<%=order.getId()%>" />
                <input type="submit" class="btn btn-success" value="Cancel Order"/>
             </form>
          </div>
        </div>
      <% } %>
  </body>
</html>
