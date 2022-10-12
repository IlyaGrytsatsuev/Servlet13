import javax.servlet.ServletConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBookServlet extends HttpServlet{

    private static PhoneBook phoneBook;

    public void init(ServletConfig config) {
        phoneBook = new PhoneBook();
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            phoneBook.readFile();
            HashMap<String, ArrayList<String>> tmp = phoneBook.getPhoneBook();
            request.setAttribute("phonelist", tmp);
            RequestDispatcher rd = request.getRequestDispatcher("PhoneBook.jsp");
            rd.forward(request, response);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
