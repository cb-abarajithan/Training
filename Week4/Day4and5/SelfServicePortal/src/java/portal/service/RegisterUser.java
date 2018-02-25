/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.service;

import portal.service.db.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cb-abarajithan
 */
public class RegisterUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");

        String email = request.getParameter("email");
        String cemail = request.getParameter("c_email");

        String pass = request.getParameter("pass");
        String cpass = request.getParameter("c_pass");

        boolean valid = false;
        String message = "Registration failed!";

        if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || cemail.isEmpty() || pass.isEmpty() || cpass.isEmpty()){
            message = "Fill all detail(s)!";
        }else{
            if(!email.contentEquals(cemail) && !pass.contentEquals(cpass)){
                message = "Email and password doesn't match";
            }else if(!email.contentEquals(cemail)){
                message = "Email address doesn't match!";
            }else if(!pass.contentEquals(cpass)){
                message = "Password doesn't match!";
            }else{
                if(UserDao.isEmailValid(email)){
                    try{
                        valid = UserDao.register(fname, lname, email, pass);
                    }catch (Exception e) {
                        message = "Cannot register user!";
                    }
                }else{
                    message = "Enter a valid email address!";
                }
            }
        }

        if(valid){
            message = "SUCCESS";
        }
        response.getWriter().write(message);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    

}
