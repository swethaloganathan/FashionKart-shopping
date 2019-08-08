<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ideas2it.onlineshopping.model.Cart" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CART MODULE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
    <body class="background">
       <div class="container">
             <h3 align="center"> UPDATE CART: </h3>
             <form class="form-horizontal" action="update-cart" method="GET">
                <%
                   Cart cart = (Cart) request.getAttribute("cart");
                %>
               <div class="form-group">
                  <label class="control-label col-sm-3" for=quantity">Quantity:</label>
                  <div class="col-sm-6">
                     <input type="number" name="quantity" value="<%=cart.getQuantity()%>" class="form-control" id="quantity">
                  </div>
               </div>
               <input type="hidden" name="id" value="<%=cart.getId()%>"/>
               <input type="hidden" name="createdDate" value="<%=cart.getDateTime()%>"/>
               <input type="hidden" name="productId" value="<%=cart.getProduct.getId()"/>
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
