import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/signUpUser")
public class SignUpUser extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		DAO dao = new DAO();
		User user = new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		if (dao.verifySignUp(user)) {
			dao.addUser(user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			System.out.println("Sign Up Succeeded!");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/signUp.jsp");
			System.out.println("Sign Up Failed!");
		}
		
		dao.close();
	}
}