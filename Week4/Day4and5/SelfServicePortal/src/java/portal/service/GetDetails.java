/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.service;

import portal.service.db.UserDao;
import portal.service.db.User;
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
public class GetDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        User user = null;
        if(email != null){
            try{
                user = UserDao.getUserDetail(email);
            }catch(SQLException e){
                session.setAttribute("email", null);
                session.invalidate();
                resp.sendRedirect("index.jsp");
            }
        }
        
        if(user!=null)
            resp.getWriter().write(user.toString());
        else 
            resp.getWriter().write(",,,");
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
