<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ideas2it.onlineshopping.model.User" %>
<%@ page import="com.ideas2it.onlineshopping.model.Product" %>
<!DOCTYPE html>
<html lang="en">
    <head>
      <title>Product</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="style.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
  <body class="product-body">
            <%
              User user = (User) session.getAttribute("user");
              if (session != null) { %>
                  <h4>
                     <p align="right">Hello, welcome <%=user.getUsername()%></p>
                  </h4>
           <% } %>
      <div class="container">
          <% String roleName = (String) session.getAttribute("role"); %>
           <center>
              <h2>List of Products</h2>
           </center>
                <% if (roleName.equals("ROLE_ADMIN")) { %>
                <form action="create-product" method="post">
                    <input type="submit" class="btn btn-success" value="Add Product"/>
                </form>
                <% } %>
                <form align="right" action="Mainmenu.jsp">
                    <input type="submit" class="btn btn-success" value="Back to Mainmenu"/>
                </form>
           <div class="row">
             <%
                List<Product> products = (List<Product>) request.getAttribute("productDetails");
                for (Product product : products) {
              %>
               <div class="col-md-4">
                   <div class="thumbnail">
                       <img src="img/main.jpg" alt="Lights" style="width:100%">
                        <br>
                        <table class="table">
                           <caption> <h2><%=product.getProductName()%></h2> </caption>
                              <tr>
                                <td>ProductId:</td><td><%=product.getId()%></td>
                              </tr>
                              <tr>
                                <td>SKU Id:</td><td><%=product.getSku()%></td>
                              </tr>
                              <tr>
                                <td>description:</td><td><%=product.getDescription()%></td>
                              </tr>
                              <tr>
                                <td>Price:</td><td><%=product.getPrice()%></td>
                              </tr>
                              <tr>
                                <td>Selling Price:</td><td><%=product.getSellingPrice()%></td>
                              </tr>
                              <tr>
                                <td>MRP:</td><td><%=product.getMrp()%></td>
                              </tr>
                              <tr>
                                <td>Status:</td><td><%=product.getIsActive()%></td>
                              </tr>
                              <tr>
                                <td>Created Date:</td><td><%=product.getDateTime()%></td>
                              </tr>
                              <tr>
                                <td>Modified Date:</td><td><%=product.getUpdatedDateTime()%></td>
                              </tr>
                              <tr>
                                <td>
                                   <% if (roleName.equals("ROLE_ADMIN")) { %>
                                   <form action="edit-product" method="GET">
                                      <input type="hidden" name="id" value="<%=product.getId()%>" />
                                      <input type="submit" class="btn btn-success" value="Edit"/>
                                   </form>
                                   <% } %>
                                </td>
                                <td>
                                   <% if (roleName.equals("ROLE_ADMIN")) { %>
                                   <form action="delete-product" method="GET">
                                       <input type="hidden" name="id" value="<%=product.getId()%>" />
                                       <input type="submit" class="btn btn-success" value="Delete"/>
                                   </form>
                                   <% } %>
                                </td>
                              </tr>
                              <tr>
                                 <td colspan="2">
                                    <form action="create-cart" method="post">
                                       <input type="hidden" name="id" value="<%=product.getId()%>" />
                                       <input type="submit" class="btn btn-success" value="Add to cart"/>
                                    </form>
                                 </td>
                              </tr>
                        </table>     
                    </div>
                </div>
             <% } %>
           </div>
        </div>
  </body>
</html>
