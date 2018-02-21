<%@page import="java.sql.*" %>
<%@page import="portal.service.*" %>
<%
    boolean success = false;
    
    String email = (String) session.getAttribute("email");
    
    if(email==null) request.getRequestDispatcher("index.jsp").forward(request, response);
    
    try{
        success = UserDao.deactivateUser(email);
    }catch(Exception e){}
    if(success){
        session.setAttribute("email", null);
        session.invalidate();
        request.setAttribute("message", "Account deactivated successfully!");
        response.sendRedirect("index.jsp");
    }else{
        request.getRequestDispatcher("details.jsp").forward(request, response);
    }
%>
