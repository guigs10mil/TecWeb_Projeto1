package keep;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		User user = new User();
		user.setName(request.getParameter("old_username"));
		user.setPassword(request.getParameter("old_password"));
		if (dao.verifyLogin(user)) {
			int idUser = dao.getIdUser(user);
			user.setName(request.getParameter("new_username"));
			user.setPassword(request.getParameter("new_password"));
			dao.updateUser(user);
			dao.close();
		}
		
        request.setAttribute("idUser", Integer.valueOf(request.getParameter("idUser")));
        request.getRequestDispatcher("./notes.jsp").forward(request, response);
	}
}