package controler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.Calcul;
import model.UserBean;
import model.UserManager;
import dao.UserDao;

/**
 * Servlet implementation class Itineraire
 */
@WebServlet("/itineraire")
public class Itineraire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String VIEW_PAGES_URL = "/WEB-INF/Itineraires/itineraire.jsp";
	private UserDao userDao = new UserDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Itineraire() {
		super();
		userDao = new UserDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("user", request.getAttribute("user"));
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL)
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Liste des étapes (format lat, long)
		String coordonnees = request.getParameter("coordlist");

		// Rayon d'action
		String rayon = request.getParameter("rayon");

		UserBean u_courant = (UserBean) request.getSession().getAttribute("user");

		// request.setAttribute("user", request.getAttribute("user"));

		Map usertrouves = new HashMap<String, UserBean>();

//		for (UserBean u : UserManager.getInstance().getUserList()) {
		for (UserBean u : userDao.getUserList()) {
			
			
			// Si pas l'utilisateur loggé
			if (!u.equals(u_courant)) {

				if (Calcul.trouverUser(coordonnees, u, Double.parseDouble(rayon))) {
					usertrouves.put(u.getEmail(), u);
				}
			}

		}

		// Rafraichit la page
		request.setAttribute("userstrouves", usertrouves);
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL) .include(request, response);

	}

}
