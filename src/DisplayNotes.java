import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayNotes")
public class DisplayNotes extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		DAO dao = new DAO();
		List<Note> notes = dao.getNotes();
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>id_user</td><td>name</td>" +
				"<td>password</td></tr>");
		for (Note note : notes) {
			out.println("<tr><td>" + note.getId() + "</td>");
			out.println("<td>" + note.getColor() + "</td>");
			out.println("<td>" + note.getDateCreated().getTime() + "</td></tr>");
		}
		out.println("</table></body></html>");

		dao.close();

	}
}
