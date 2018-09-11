import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form method='post'>");
        out.println("ID: <input type='number' name='id'><br>");
        out.println("Nome: <input type='text' name='name'><br>");
        out.println("Password: <input type='text' name='password' step='0.01'><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("<body><html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        User user = new User();
        user.setId(Integer.valueOf(request.getParameter("id")));
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        dao.updateUser(user);
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("atualizado" + user.getName());
        out.println("</body></html>");

        dao.close();
    }
}