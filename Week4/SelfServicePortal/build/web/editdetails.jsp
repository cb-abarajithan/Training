<%@page import="java.sql.*" %>
<%@page import="portal.service.*" %>

<%
    User user = null;
    try{
        user = UserDao.getUserDetail((String)session.getAttribute("email"));
    }catch(Exception e){
        session.setAttribute("email", null);
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head><title>Edit Details - Self Service Portal</title></head>
    <body>
        <h1>Self Service Portal</h1>
        <a href="logout.jsp">Logout</a><br><br><br>
        <!-- TODO:Write save details-->
        <form action="savedetails.jsp" method="POST">
            <div>
                Name: <input type="text" name="fname" value="<%=(user==null)?"Error!":user.getFirstName()%>"> <input type="text" name="lname" value="<%=(user==null)?"Error!":user.getLastName()%>"><br>
                Email: <input type="text" disabled="true" value="<%=(user==null)?"Error!":user.getEmail()%>"><br>
                Address: <input type="text" name="addr" value="<%=(user==null)?"Error!": ((user.getAddress()==null)?"":user.getAddress()) %>"><br>
                <input type="submit" value="Save"/><a href="details.jsp">Discard</a>
            </div>
        </form>
        <h4>${update_status}</h4>
    </body>
</html>
