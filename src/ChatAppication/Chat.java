package ChatAppication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * A servlet which loops with the <a href="Chat.jsp">Chat.jsp</a> file to display the chatroom page
 * <br>and processes the chat posts
 */
@WebServlet(name = "Chat", urlPatterns = "/chat")
public class Chat extends HttpServlet {

    @Override
    public void init() {
        this.getServletContext().setAttribute("messages", new ArrayList<String[]>());
    }

    /**
     * Handles the <i>post</i> method<br>
     * processes the chat posts and checks that they are not empty
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // if user is not logged in or has logged out, go to login page
        if (session == null || session.getAttribute("name") == null)//not a registar user, go to login
            response.sendRedirect("login");
        else { // user is not logged in
            // access list of messages from servlet.context
            ArrayList<String[]> messages = (ArrayList<String[]>) this.getServletContext().getAttribute("messages");
            String name = (String) session.getAttribute("name");
            String message = request.getParameter("message");
            request.setAttribute("error", "false");
            if (message != null) { // message input field is not empty
                if (!message.trim().equals("")) {
                    String[] pair = {name, message};
                    synchronized (this) {//lock the array from two servlets
                        messages.add(pair);
                    }
                } else // message input field is empty
                    request.setAttribute("error", "true");//empty message
            }
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("Chat.jsp?name=" + name).forward(request, response);
        }
    }

    /**
     * Handles the <i>get</i> request
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // if user is not logged in or has logged out, go to login page
        if(session == null || session.getAttribute("name") == null)
            response.sendRedirect("login");
        else // user is not logged in, call doPost() method
            doPost(request,response);
    }
}
