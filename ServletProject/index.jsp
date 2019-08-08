<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
     <title>FashionKart Shopping</title>
     <link rel="stylesheet" href="./style.css">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
  </head>  
  <body class="index-background">
    <div class="container">
      <h2 class="index">USER LOGIN DETAILS:</h2>
        <form style="width:300px" action="user-login" method="post">
          <div class="form-group">
            <label for="uname" class="index">Username:</label>
            <input type="text" class="form-control" placeholder="Enter Username" id="uname" name="username" required>
          </div>
          <div class="form-group">
            <label for="pass" class="index">Password:</label>
            <input type="password" class="form-control" placeholder="Enter Password" id="pass" name="password" required>
          </div>
          <button type="submit" class="btn btn-default">Login</button>
        </form>
    </div>
  </body>
</html>
