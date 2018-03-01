package phone.book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phone.book.db.Person;
import phone.book.db.PersonDAO;

/**
 *
 * @author cb-abarajithan
 */
public class GetAllContacts extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String listOfUsersHTML;
        
        try{
            listOfUsersHTML = PersonDAO.getAll().toJSONString();
        }catch(SQLException e){
            listOfUsersHTML = "Fetching failed!";
        }
        resp.setContentType("application/json");
        resp.getWriter().write(listOfUsersHTML);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
