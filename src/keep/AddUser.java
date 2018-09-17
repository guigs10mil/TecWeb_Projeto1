package keep;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Username: <input type='text' name='name'><br>");
		out.println("Password: <input type='text' name='password'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		DAO dao = new DAO();
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		dao.addUser(user);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("User " + user.getName() + " added.");
		out.println("</body></html>");
		dao.close();
	}
}