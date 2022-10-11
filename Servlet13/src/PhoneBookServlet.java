import javax.servlet.ServletConfig;
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
        phoneBook.readFile();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            String uri = request.getRequestURI();
            PrintWriter out = response.getWriter();
            if(uri.equals("/Servlet13/servlet/PhoneBook")){
                out.println("<html>\n<body>\n");
                out.println(getMainPage());
                out.println("</body>\n</html>");
            }
            else if(uri.equals("/Servlet13/servlet/PhoneBook/addition")){
                out.println("<html>\n<body>\n");
                out.println(getAdditionPage());
                out.println("</body>\n</html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getMainPage() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"/Servlet13/servlet/PhoneBook/addition\">New</a>");

        HashMap<String, ArrayList<String>> tmp = phoneBook.getPhoneBook();
        for (HashMap.Entry<String, ArrayList<String>> hashMap : tmp.entrySet()) {
            sb.append("<p>").append(hashMap.getKey()).append(": ");
            String prefix = "";
            for (String p : hashMap.getValue()) {
                sb.append(prefix).append(p);
                prefix = ", ";
            }
            sb.append("</p>");
        }

        return sb.toString();
    }

    private String getAdditionPage() throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append("<form method=\"POST\" action=\"/Servlet13/servlet/PhoneBook/addition/add\">\n")
                .append("Name: <input type=\"text\" name=\"name\"><br><br>\n")
                .append("Phone: <input type=\"text\" name=\"phone\"><br><br>\n")
                .append("<input type=\"submit\" value=\"add\">\n")
                .append("</form>")
                .append("<a href=\"/Servlet13/servlet/PhoneBook\">GetBack</a>");


        return sb.toString();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            out.println("<html>\n<body>\n");
            if (request.getParameterMap().size() != 0) {
                String phone = "phone";
                if (isDigitsOnly(request.getParameter(phone))) {
                    String name = "name";
                    if (!phoneBook.containName(request.getParameter(name))) {
                      if (!(request.getParameter(name).equals("")))
                            phoneBook.addPhoneNumber(request.getParameter(name), request.getParameter(phone));
                    }
                    else {
                        if (!(phoneBook.containPhone(request.getParameter(name), request.getParameter(phone)))) {
                        if (!(request.getParameter(phone).equals("")))
                            phoneBook.addPhoneNumber(request.getParameter(name), request.getParameter(phone));

                        } else
                            out.println("<h2>This phone was already add in the list</h2>\n");
                    }
                } else
                    out.println("<h2>The phone must contain only numbers</h2>\n");
            }
            out.println("<a href=\"/Servlet13/servlet/PhoneBook\">GetBack</a>");
            out.println("</body>\n</html>");
            phoneBook.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isDigitsOnly(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) < '0') || (str.charAt(i) > '9')) {
                return false;
            }
        }
        return true;
    }
}
