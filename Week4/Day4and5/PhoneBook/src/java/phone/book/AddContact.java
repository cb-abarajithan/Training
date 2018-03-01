/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phone.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phone.book.db.PersonBuilder;
import phone.book.db.PersonDAO;

/**
 *
 * @author cb-abarajithan
 */
public class AddContact extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String message = "Cannot add contact!";
        
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        
        String line1 = req.getParameter("line1");
        String line2 = req.getParameter("line2");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        
        int zip = 0;
        try{
            zip = Integer.parseInt(req.getParameter("zip").isEmpty()?"0":req.getParameter("zip"));
        }catch(NumberFormatException e){
            message = "Invalid Zip code!";
            resp.getWriter().write(message);
            return;
        }
        
        String country = req.getParameter("country");
        
        long work = 0;
        long mobile = 0;
        long home = 0;
        
        try{
            work = Long.parseLong(req.getParameter("work").isEmpty()?"0":req.getParameter("work"));
        }catch(NumberFormatException e){
            message = "Invalid Work PhoneNumber!";
            resp.getWriter().write(message);
            return;
        }
        try{
            mobile = Long.parseLong(req.getParameter("mobile").isEmpty()?"0":req.getParameter("mobile"));
        }catch(NumberFormatException e){
            message = "Invalid Mobile PhoneNumber!";
            resp.getWriter().write(message);
            return;
        }
        try{
            home = Long.parseLong(req.getParameter("home").isEmpty()?"0":req.getParameter("home"));
        }catch(NumberFormatException e){
            message = "Invalid Home PhoneNumber!";
            resp.getWriter().write(message);
            return;
        }
        
        if(fname.isEmpty() || lname.isEmpty()){
            message = "Name required!";
            resp.getWriter().write(message);
            return;
        }
        
        if(work==0 && mobile==0 && home==0){
            message = "Atleast one phonenumber required!";
            resp.getWriter().write(message);
            return;
        }
        
        boolean success;
        
        try{
            success = PersonDAO.addPerson(
                    new PersonBuilder()
                            .addName(fname, lname)
                            .addAddressLines(line1, line2)
                            .addAddressDetails(city, state, zip, country)
                            .addWorkNumber(work)
                            .addHomeNumber(home)
                            .addMobileNumber(mobile)
                            .build()
            );
        }catch(SQLException e){
            message = e.getMessage();
            success = false;
        }
        
        if(success){
            message = "success";
        }
        resp.getWriter().write(message);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
