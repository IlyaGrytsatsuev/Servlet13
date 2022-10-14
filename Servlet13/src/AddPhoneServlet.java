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

public class AddPhoneServlet extends HttpServlet{

    private static PhoneBook phoneBook;

    public void init(ServletConfig config) {
        phoneBook = new PhoneBook();
    }

    
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
        
            phoneBook.readFile();
            ;
            PrintWriter out = response.getWriter();
            out.println("<html>\n<body>\n");
        
            String phone = "phone";
            String name = "name";
            String image = request.getParameter("Avatar");
            
            
       
    	    if (!(phoneBook.containPhone(request.getParameter(name), request.getParameter(phone)))) {
                phoneBook.addPhoneNumber(request.getParameter(name), request.getParameter(phone));
                phoneBook.addUsersAvatar(request.getParameter(name), image);
                phoneBook.writeFile();
                response.sendRedirect("PhoneBook");
		}		
            else{
                out.println("<h2>This phone was already add in the list</h2>\n");
                }
            out.println("<a href=\"PhoneBook\">GetBack</a>");
            out.println("</body>\n</html>");
           
                    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
