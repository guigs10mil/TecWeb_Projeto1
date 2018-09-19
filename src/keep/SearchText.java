package keep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchText
 */
@WebServlet("/SearchText")
public class SearchText extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (request.getParameterValues("search") != null) {
			String search = request.getParameter("search");
			session.setAttribute("search", search);
			response.sendRedirect(request.getContextPath() + "/notes.jsp");
		}

	}
}
