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
		note.setColor("#ffffff");
		note.setDateCreated(Calendar.getInstance());
		note.setText(request.getParameter("text"));
		note.setIdUser(Integer.valueOf(request.getParameter("idUser")));
		note.setLabel(request.getParameter("label"));
		dao.addNote(note);

		dao.close();
		
        request.setAttribute("idUser", note.getIdUser());
        request.getRequestDispatcher("./notes.jsp").forward(request, response);
	}
}