<% 
    if(session.getAttribute("email") !=null){
        response.sendRedirect("details.jsp");
    }
%>
<html>
  <head>
    <title>Login - Self Service Portal</title>
  </head>

  <h1>Login - Self Service Portal</h1>
  
  <form action="dologin.jsp" method="post">
    Email: <input type="text" name="email"><br>
    Password: <input type="password" name="pass"><br>
    ${message}<br>
    <input type="submit" value="Submit">
    <a href="register.jsp">New here?</a>
  </form>

</html>