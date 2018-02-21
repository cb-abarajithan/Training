<%@page import="java.sql.*" %>
<%@page import="portal.service.*" %>
<%
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    
    String email = request.getParameter("email");
    String cemail = request.getParameter("c_email");
    
    String pass = request.getParameter("pass");
    String cpass = request.getParameter("c_pass");

    boolean valid = false;
    String message = "";
    
    if(email.contentEquals(cemail) && pass.contentEquals(cpass)){
        try{
            valid = UserDao.register(fname, lname, email, pass);
        }catch (Exception e) {
            message = "Server Database error!";
        }
    }else if(!email.contentEquals(cemail)){
        message = "Email address doesn't match!";
    }else if(!pass.contentEquals(cpass)){
        message = "Password doesn't match!";
    }else{
        message = "Email and password doesn't match";
    }

    if(valid){
        response.sendRedirect("index.jsp");
    }else{
        request.setAttribute("message",message );
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
%>