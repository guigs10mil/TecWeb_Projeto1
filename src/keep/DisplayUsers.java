package keep;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayUsers")
public class DisplayUsers extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DisplayUsers() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		service(request, response);
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		DAO dao = new DAO();
		List<User> users = dao.getUsers();
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>id_user</td><td>name</td>" +
				"<td>password</td></tr>");
		for (User user : users) {
			out.println("<tr><td>" + user.getId() + "</td>");
			out.println("<td>" + user.getName() + "</td>");
			out.println("<td>" + user.getPassword() + "</td></tr>");
		}
		out.println("</table></body></html>");

		dao.close();

	}
}
