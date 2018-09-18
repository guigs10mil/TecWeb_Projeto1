package keep;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/verifyLogin")
public class VerifyLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		DAO dao = new DAO();
		if (dao.verifyLogin(user)) {
			int idUser = dao.getIdUser(user);
			HttpSession session = request.getSession(true);
			session.setAttribute("idUser", idUser);
			request.getRequestDispatcher("./notes.jsp").forward(request, response);
			System.out.println("Sign In Succeeded!");
		} else {
			response.sendRedirect(request.getContextPath() + "/signIn.jsp");
			System.out.println("Sign In Failed!");
		}

		dao.close();

	}
}
