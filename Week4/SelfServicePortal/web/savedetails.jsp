<%@page import="java.sql.*" %>
<%@page import="portal.service.*" %>
<%
    boolean success = false;
    
    String email = (String) session.getAttribute("email");
    
    if(email==null) request.getRequestDispatcher("index.jsp").forward(request, response);
    
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String addr = request.getParameter("addr");
    
    try{
        success = UserDao.updateDetails(fname, lname, addr, email);
    }catch(Exception e){
        session.setAttribute("email", null);
        session.invalidate();
        response.sendRedirect("index.jsp");
    }finally{
        request.setAttribute("update_status", success?"Detail(s) updated!":"Failed to update detail(s)");
        request.getRequestDispatcher("editdetails.jsp").forward(request, response);
    }
%>