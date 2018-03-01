/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phone.book;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phone.book.db.PersonDAO;

/**
 *
 * @author cb-abarajithan
 */
public class GetById extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contact = "";
        
        int id = -1;
                
        try{
            id = Integer.parseInt(req.getParameter("id"));
        }catch(NumberFormatException e){
        }
        
        if(id!=-1){
            try{
                contact = PersonDAO.getById(id).toJSONString();
            }catch(SQLException e){
            }
            
        }
        
        resp.setContentType("application/json");
        resp.getWriter().write(contact);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
