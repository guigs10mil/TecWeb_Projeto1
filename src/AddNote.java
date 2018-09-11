import java.io.*;
import java.util.Calendar;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addNote")
public class AddNote extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Text: <input type='text' name='text'><br>");
		out.println("Label: <input type='text' name='label'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		DAO dao = new DAO();
		Note note = new Note();
		note.setColor("#ffffff");
		note.setDateCreated(Calendar.getInstance());
		note.setText(request.getParameter("text"));
		note.setIdUser(6);
		note.setLabel(request.getParameter("label"));
		dao.addNote(note);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("Note added.");
		out.println("</body></html>");
		dao.close();
	}
}