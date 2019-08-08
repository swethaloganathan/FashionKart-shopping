<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>
       Error
    </title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  </head>
    <body class="background">
      <center>
         <h2>Error Message: ${error}</h2>
         <input type="submit" class="btn btn-success" value="Back" onclick="history.back()"/>
      </center>
    </body>
</html>
