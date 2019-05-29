package ChatAppication;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet which processes the logout requests
 */
@WebServlet(name = "Logout", urlPatterns = "/logout")
public class Logout extends HttpServlet {

    /**
     * Handles the <i>post</i> method<br>
     * called the doGet() method
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    /**
     * Handles the <i>get</i> request<br>
     * Closes the session and redirects user to the login page
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();
        response.sendRedirect("login");
    }
}
