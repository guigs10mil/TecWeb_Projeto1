import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verifyLogin")
public class VerifyLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		
		User user = new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		DAO dao = new DAO();
		if (dao.verifyLogin(user)) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			System.out.println("Sign In Succeeded!");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/signIn.jsp");
			System.out.println("Sign In Failed!");
		}

		dao.close();

	}
}
