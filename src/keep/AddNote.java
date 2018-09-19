package keep;
import java.io.*;
import java.util.Calendar;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addNote")
public class AddNote extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		DAO dao = new DAO();
		Note note = new Note();
		HttpSession session = request.getSession(true);
		note.setColor(request.getParameter("color"));
		note.setDateCreated(Calendar.getInstance());
		note.setText(request.getParameter("text"));
		note.setIdUser((Integer)session.getAttribute("idUser"));
		note.setLabel(request.getParameter("label"));
		if (note.getText().length()>0 && note.getLabel().length()>0){
			dao.addNote(note);
		}
		dao.close();
        request.getRequestDispatcher("./notes.jsp").forward(request, response);
	}
}