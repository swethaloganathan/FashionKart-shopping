<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
           <%
                int productId = (Integer) request.getAttribute("productId");
           %>
        <div class="container">
           <h3 align="center"> ADD CART: </h3>
              <form class="form-horizontal" action="save-cart" method="post">
                 <div class="form-group">
                    <label class="control-label col-sm-3" for=productId">Product ID:</label>
                    <div class="col-sm-6">
                       <input type="number" name="productId" value="<%=productId%>" class="form-control" id="productId" required>
                    </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="quantity">Quantity:</label>
                   <div class="col-sm-6">          
                       <input type="number" placeholder="Enter quantity" name="quantity" class="form-control" id="quantity" required>
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
