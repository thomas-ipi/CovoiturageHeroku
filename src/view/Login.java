package view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.UserBean;
import model.UserManager;

/**
 * Servlet implementation class Register
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW_PAGES_URL = "/WEB-INF/UserManager/index.jsp";
	private static final String UrlItineraire = "/WEB-INF/Itineraires/itineraire.jsp";
	
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PWD = "pwd";
    private HttpSession session;
    private UserDao userDao;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        String email = request.getParameter(FIELD_EMAIL);
        String pwd = request.getParameter(FIELD_PWD);
        String result =null;
        Boolean isOk = true;

        Map<String, String> errors = new HashMap<String, String>();
        Map<String, String> forms = new HashMap<String, String>();
        
        result = validateEmail(email);
         if (result==null){
            forms.put(FIELD_EMAIL, email);
           }
         else {
           	errors.put(FIELD_EMAIL, result);
           	isOk = false;
         }
         
         result = validatePwd(pwd);
          if (result==null){
              forms.put(FIELD_PWD, pwd);
             }
           else {
             	errors.put(FIELD_PWD, result);
             	isOk = false;
           }
          
          RequestDispatcher dispat;
          
          if (isOk){
        	  
        	result="Succes";
        	UserBean userBean = userDao.userEstValide(request.getParameter(FIELD_EMAIL),request.getParameter(FIELD_PWD));
      		
    		if (userBean==null) {
    			errors.put(FIELD_EMAIL, "l'utilisateur n'existe pas et/ou le mot de passe n'est pas correct");
    			request.setAttribute("errors", errors);
    			request.setAttribute("forms", forms);
    			dispat = request.getRequestDispatcher(VIEW_PAGES_URL);
    		}
    		else
    		{
				session = request.getSession();
				session.setAttribute("user", userBean);
    			dispat = request.getRequestDispatcher(UrlItineraire);
    		}
          }
          else{
        	  request.setAttribute("errors", errors);
        	  request.setAttribute("forms", forms);
  			  dispat = request.getRequestDispatcher(VIEW_PAGES_URL);
          }
          dispat.include(request, response);
	}

	
	private String validateEmail( String email ) {
		String result = null; 
		if ( email != null && email.trim().length() != 0 ) {
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				result = "Veuillez saisir une adresse mail valide" ;
			}
		}
		else {
			result = "L'adresse mail est obligatoire" ;
		}		
		return result; 
	}
	
	private String validatePwd(String pwd) {
		String result = null;
		if ((pwd==null) || (pwd=="")){
			result =  "Le mot de passe est obligatoire" ;
		}
		return result;
	}
	
}
