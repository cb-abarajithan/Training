/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cb-abarajithan
 */
public class EditDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        
        boolean success = false;
        String message = "Failed to update detail(s)";
    
        String email = (String) session.getAttribute("email");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String addr = request.getParameter("addr");

        try{
            success = UserDao.updateDetails(fname, lname, addr, email);
        }catch(Exception e){
            session.setAttribute("email", null);
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
        
        if(success){
            message = "Detail(s) updated!";
        }
        response.getWriter().write(message);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
