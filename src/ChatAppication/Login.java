package ChatAppication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet which loops with the <a href="Login.jsp">Login.jsp</a> Login.jsp file to display the login page
 * <br>and processes the login requests
 */
@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

    /**
     *
     * Handles the <i>post</i> method
     * processes the login requests and checks that the names are not empty
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        // if the user is logged in, go to the chat page
        if (session != null && session.getAttribute("name") != null)
            response.sendRedirect("chat");
        else { // user not logged in or a new user (irrelevant)
            response.setContentType("text/html");
            String name = request.getParameter("name");
            if (!name.trim().equals("")) { // name field is not empty
                request.getSession().setAttribute("name",name);
                response.sendRedirect("chat");
            } else { // name field is empty
                request.setAttribute("error", "true");
                request.getRequestDispatcher("Login.jsp").forward(request,response);
            }
        }
    }

    /**
     * Handles the <i>get</i> request
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        // if the user is logged in, go to the chat page
        if (session != null && session.getAttribute("name") != null)
            response.sendRedirect("chat");
        else { // user not logged in or a new user (irrelevant)
            request.setAttribute("error", "false");
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        }
    }
}
