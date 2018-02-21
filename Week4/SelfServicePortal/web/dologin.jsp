<%@page import="java.sql.*" %>
<%@page import="portal.service.*" %>
<%
    String email = request.getParameter("email");
    String pass = request.getParameter("pass");

    boolean valid = false;
    String message = "Invalid email or password!";

    try{
        valid = UserDao.isValid(email, pass);
    }catch (Exception e) {
        message = "Server Database error!";
    }

    if(valid){
        session.setAttribute("email", email);
        response.sendRedirect("details.jsp");
    }else{
        request.setAttribute("message", message);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>