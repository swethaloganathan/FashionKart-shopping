<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ideas2it.onlineshopping.model.User" %>
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
           <h3 align="center"> ADD PRODUCT: </h3>
              <form class="form-horizontal" action="save-product" method="post">
                 <div class="form-group">
                    <label class="control-label col-sm-3" for=pname">Product Name:</label>
                    <div class="col-sm-6">
                       <input type="text" placeholder="Enter product name" name="productName" class="form-control" id="pname" required>
                    </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="description">Description:</label>
                   <div class="col-sm-6">          
                       <input type="text" placeholder="Enter description" name="description" class="form-control" id="description" required>
                   </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="price">Price:</label>
                   <div class="col-sm-6">          
                       <input type="number" placeholder="Enter price" name="price" class="form-control" id="price" required>
                   </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="sellprice">Selling Price:</label>
                   <div class="col-sm-6">       
                       <input type="number" placeholder="Enter selling price" name="sellingPrice" class="form-control" id="sellprice" required>
                   </div>
                </div>
                <div class="form-group">
                   <label class="control-label col-sm-3" for="mrp">MRP:</label>
                   <div class="col-sm-6">      
                       <input type="number" placeholder="Enter MRP" name="mrp" class="form-control" id="mrp" required>
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
