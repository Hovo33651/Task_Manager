<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Task Manager</title>
  <link rel="stylesheet" href="cssFiles/index.css">
</head>
<body>

<div id="container">

  <div id="header">
    <h2>Welcome To Your Task Manager</h2>
  </div>

  <div id="login">
    <form id="log" action="/login" method="post">
      <label id="user_email">Email:</label><br>
      <label for="email"></label><input type="email" id="email" name="email"><br>
      <label id="user_password">Password:</label><br>
      <label for="password"></label><input type="password" id="password" name="password"><br>
      <input type="submit" value="Log In">
    </form>
  </div>
</div>
</body>
</html>