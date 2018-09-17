package keep;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateNote")
public class UpdateNote extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Note note = new Note();
        note.setId(Integer.valueOf(request.getParameter("idNote")));
        note.setText(request.getParameter("text"));
        note.setLabel(request.getParameter("label"));
        dao.updateNote(note);

        dao.close();
        
        request.setAttribute("idUser", Integer.valueOf(request.getParameter("idUser")));
        request.getRequestDispatcher("./notes.jsp").forward(request, response);
    }
}