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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form method='post'>");
        out.println("ID: <input type='number' name='id'><br>");
        out.println("Novo Texto: <input type='text' name='texto'><br>");
        out.println("Novo Label: <input type='text' name='label' step='0.01'><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("<body><html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Note note = new Note();
        note.setId(Integer.valueOf(request.getParameter("id")));
        note.setText(request.getParameter("texto"));
        note.setLabel(request.getParameter("label"));
        dao.updateNote(note);
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("atualizado");
        out.println("</body></html>");

        dao.close();
    }
}