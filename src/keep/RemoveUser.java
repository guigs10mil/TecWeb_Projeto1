package keep;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/removeUser")
public class RemoveUser extends HttpServlet {
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
		HttpSession session = request.getSession(true);
		User user = new User();
		user.setName((String)session.getAttribute("username"));
		user.setPassword(request.getParameter("password"));
		if (dao.verifyLogin(user)) {
			dao.removeUser((Integer)session.getAttribute("idUser"));
		}
        dao.close();
        
        request.getRequestDispatcher("./index.jsp").forward(request, response);
	}
}
