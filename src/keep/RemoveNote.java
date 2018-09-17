package keep;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removeNote")
public class RemoveNote extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        dao.removeNote(Integer.valueOf(request.getParameter("idNote")));
        System.out.println(request.getParameter("idUser"));
        dao.close();
        
        request.setAttribute("idUser", Integer.valueOf(request.getParameter("idUser")));
        request.getRequestDispatcher("./notes.jsp").forward(request, response);
    }
}