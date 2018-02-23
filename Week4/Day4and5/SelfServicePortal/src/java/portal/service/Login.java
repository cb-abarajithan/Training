/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cb-abarajithan
 */
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        boolean valid = false;
        String message = "Invalid email or password!";

        if(email.length()==0 && pass.length()==0){
            message = "Enter Email and Password!";
        }else if(pass.length()==0){
            message = "Password cannot be empty!";
        }else if(email.length()==0){
            message = "Email cannot be empty!";
        }else{
            if(UserDao.isEmailValid(email)){
                try{
                    valid = UserDao.isValid(email, pass);
                }catch (Exception e) {
                    message = "Server error!";
                }
            }else{
                message = "Email is not valid!";
            }
        }
        
        if(valid){
            message = "SUCCESS";
            request.getSession().setAttribute("email", email);
        }
        
        response.getWriter().write(message);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
