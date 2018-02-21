<% 
    if(session.getAttribute("email") != null){
        response.sendRedirect("details.jsp");
    }
%>
<html>
  <head>
    <title>Register - Self Service Portal</title>
  </head>

  <h1>Register to Self Service Portal</h1>
  
  <form action="doregister.jsp" method="post">
    <input type="text" name="fname" placeholder="First name"> <input type="text" name="lname" placeholder="Last name"><br>
    <input type="text" name="email" placeholder="Email"> <input type="text" name="c_email" placeholder="Confirm email"><br>
    <input type="password" name="pass" placeholder="Password"> <input type="password" name="c_pass" placeholder="Confirm password"><br>
    ${message}<br>
    <input type="submit" value="Register">
    <a href="index.jsp">Already have an account?</a>
  </form>

</html>