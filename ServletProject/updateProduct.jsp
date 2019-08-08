<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ideas2it.onlineshopping.model.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>PRODUCT MODULE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
    <body class="background">
       <div class="container">
             <h3 align="center"> UPDATE PRODUCT: </h3>
             <form class="form-horizontal" action="update-product" method="GET">
                <%
                  Product product = (Product) request.getAttribute("product");
                %>
               <div class="form-group">
                  <label class="control-label col-sm-3" for=pname">Product Name:</label>
                  <div class="col-sm-6">
                     <input type="text" name="productName" value="<%=product.getProductName()%>" class="form-control" id="pname">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="description">Description:</label>
                  <div class="col-sm-6">          
                     <input type="text" name="description" value="<%=product.getDescription()%>" class="form-control" id="description">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="price">Price:</label>
                  <div class="col-sm-6">          
                     <input type="number" name="price" value="<%=product.getPrice()%>" class="form-control" id="price">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="sellprice">Selling Price:</label>
                  <div class="col-sm-6">          
                     <input type="number" name="sellingPrice" value="<%=product.getSellingPrice()%>" class="form-control" id="sellprice">
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-sm-3" for="mrp">MRP:</label>
                  <div class="col-sm-6">          
                     <input type="number" name="mrp" value="<%=product.getMrp()%>" class="form-control" id="mrp">
                  </div>
               </div>
               <input type="hidden" name="id" value="<%=product.getId()%>"/>
               <input type="hidden" name="sku" value="<%=product.getSku()%>"/>
               <input type="hidden" name="status" value="<%=product.getIsActive()%>"/>
               <input type="hidden" name="createdDate" value="<%=product.getDateTime()%>"/>
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
