/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.service;

import portal.service.db.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cb-abarajithan
 */
public class DeactivateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        boolean success = false;
    
        String email = (String) session.getAttribute("email");

        try{
            success = UserDao.deactivateUser(email);
        }catch(Exception e){
            request.getRequestDispatcher("details.jsp").forward(request, response);
        }
        if(success){
            session.setAttribute("email", null);
            session.invalidate();
            response.sendRedirect("index.jsp");
        }else{
            request.getRequestDispatcher("details.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
