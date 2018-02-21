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
    <head><title>Welcome - Self Service Portal</title></head>
    <body>
        <h1>Self Service Portal</h1>
        <a href="logout.jsp">Logout</a><br><br><br>
        <form action="editdetails.jsp" method="POST">
            <div>
                Name: <%=(user==null)?"Error!":user.getName() %><br>
                Email: <%=(user==null)?"Error!":user.getEmail() %><br>
                Address: <%=(user==null)?"Error!": ((user.getAddress()==null)?"-- NA --":user.getAddress()) %><br>
                <input type="submit" value="Edit details"/><br>
                <a href="deactivate.jsp">Deactivate account</a>
            </div>
        </form>
    </body>
</html>