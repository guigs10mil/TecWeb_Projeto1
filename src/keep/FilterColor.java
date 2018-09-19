package keep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FilterColor
 */
@WebServlet("/FilterColor")
public class FilterColor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<String> filter = new ArrayList<String>();
		if (request.getParameterValues("color") != null) {
			String[] filterVector = request.getParameterValues("color");

			for (String color : filterVector) {
				filter.add(color);
			}
		}
		session.setAttribute("filter", filter);
		response.sendRedirect(request.getContextPath() + "/notes.jsp");
	}

}
