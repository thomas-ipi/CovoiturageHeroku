package view;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.UserBean;
import dao.UserDao;



/**
 * Servlet implementation class Create
 */
@WebServlet("/Create")
public class Create extends HttpServlet {

	private static final String Url = "/WEB-INF/UserManager/create.jsp";
	private static final String UrlItineraire = "/WEB-INF/Itineraires/itineraire.jsp";

    private static final String DATA_DIRECTORY = "imgs";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

	private static final long serialVersionUID = 1L;
	private static final String FIELD_ID = "id";
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PROFIL = "profil";
	private static final String FIELD_NOM = "nom";
	private static final String FIELD_PRENOM = "prenom";
	private static final String FIELD_ADR1 = "adresse1";
	private static final String FIELD_ADR2 = "adresse2";
	private static final String FIELD_CODEPOSTAL = "codepostal";
	private static final String FIELD_VILLE = "ville";
	private static final String FIELD_GENRE = "genre";
	private static final String FIELD_FUMEUR = "fumeur";
	private static final String FIELD_TELFIXE = "telFixe";
	private static final String FIELD_TELPORTABLE = "telPortale";
	private static final String FIELD_PWD1 = "pwd1";
	private static final String FIELD_PWD2 = "pwd2";
	private static final String FIELD_LATLONG = "latlong";
	private static final String FIELD_AVATAR = "avatar";
	private static final String FIELD_AVATAR_FILENAME = "avatarFilename";
	
	private HttpSession session;
	
	private UserDao userDao;
	
	private Map errors = new HashMap();
	private Map forms = new HashMap();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Create() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxx : " + request.getRequestURL());
		
		RequestDispatcher dispat;
		session = request.getSession();

		UserBean u_courant = (UserBean) session.getAttribute("user");
		if  (u_courant!=null) {
			forms = new HashMap();
		
			forms.put(FIELD_ID, u_courant.getId());
			forms.put(FIELD_PROFIL, u_courant.getProfil());
			forms.put(FIELD_EMAIL, u_courant.getEmail());
			forms.put(FIELD_NOM, u_courant.getNom());
			forms.put(FIELD_PRENOM, u_courant.getPrenom());
			forms.put(FIELD_ADR1, u_courant.getAdresse1());
			forms.put(FIELD_ADR2, u_courant.getAdresse2());
			forms.put(FIELD_CODEPOSTAL, u_courant.getCodepostal());
			forms.put(FIELD_VILLE, u_courant.getVille());
			forms.put(FIELD_GENRE, u_courant.getGenre());
			forms.put(FIELD_FUMEUR, u_courant.getFumeur());
			forms.put(FIELD_TELFIXE, u_courant.getTelFixe());
			forms.put(FIELD_TELPORTABLE, u_courant.getTelPortale());
			forms.put(FIELD_PWD1, u_courant.getPassword());
			forms.put(FIELD_PWD2, u_courant.getPassword());
			forms.put(FIELD_LATLONG, u_courant.getLatlong());
			forms.put(FIELD_AVATAR, u_courant.getAvatar());
			forms.put(FIELD_AVATAR_FILENAME, u_courant.getAvatar());

			request.setAttribute("forms", forms);
		}
		dispat = request.getRequestDispatcher(Url);
		dispat.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	   DiskFileItemFactory factory = new DiskFileItemFactory();
		// Sets the size threshold beyond which files are written directly to disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);
		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		// constructs the folder where uploaded file will be stored
		String uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);

		long idUser;
		boolean isCreation = false;
		errors = new HashMap();
		forms = new HashMap();
		
		List<FileItem> items;
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	      for (FileItem item : items) {
	            if (item.isFormField()) {
	                String fieldname = item.getFieldName();
	                String fieldvalue = item.getString();
	                forms.put(fieldname, fieldvalue);
	            } else {
	                String fileName = new File(item.getName()).getName();
	                if ((fileName != null) && (!fileName.equals(""))) {
		                String filePath = uploadFolder + File.separator + fileName;
		                String fileNameAvatar = DATA_DIRECTORY + "/" + fileName;
		                forms.put(FIELD_AVATAR, fileNameAvatar); 
		                forms.put(FIELD_AVATAR_FILENAME, fileNameAvatar);
		                File uploadedFile = new File(filePath);
		                item.write(uploadedFile);
	                } else {
	                	forms.get(FIELD_AVATAR_FILENAME);
	                	forms.put(FIELD_AVATAR, (String) forms.get(FIELD_AVATAR_FILENAME));
	                }
	            }
	       }
			
			request.setAttribute("msg", " ");
			
			String id = (String) forms.get(FIELD_ID);
			String email = (String) forms.get(FIELD_EMAIL);
			
			if ((id==null) || (id.equals(""))){
				// creation du user
				idUser = 0L;
				isCreation = true;
				id="";
			}
			else {
				// Modification du user
				idUser = Long.parseLong(id) ;
				isCreation = false;
			}
		

			validateForm((String) forms.get(FIELD_PROFIL), 
					(String) forms.get(FIELD_EMAIL), 
					(String) forms.get(FIELD_NOM), 
					(String) forms.get(FIELD_PRENOM), 
					(String) forms.get(FIELD_ADR1), 
					(String) forms.get(FIELD_ADR2),
					(String) forms.get(FIELD_CODEPOSTAL), 
					(String) forms.get(FIELD_VILLE),
					(String) forms.get(FIELD_FUMEUR), 
					(String) forms.get(FIELD_TELFIXE), 
					(String) forms.get(FIELD_TELPORTABLE),
					(String) forms.get(FIELD_PWD1), 
					(String) forms.get(FIELD_GENRE)
					);
			
			validatePwd((String) forms.get(FIELD_PWD1), (String) forms.get(FIELD_PWD2));


			if (errors.size() == 0) {
//				avatar = uploadAvatar(request, response);
				if (userDao.userExiste(email, id)){
					
					errors.put(FIELD_EMAIL, "cet email est déjà enregistré");
					request.setAttribute("erreurs", errors);
					request.setAttribute("forms", forms);

					// Redirection vers la page de l'itinéraire
					RequestDispatcher dispat;
					dispat = request.getRequestDispatcher(Url);
					dispat.include(request, response);
					
				}
				else{
					
					UserBean u = new UserBean(idUser,
							(String) forms.get(FIELD_PROFIL), 
							(String) forms.get(FIELD_EMAIL), 
							(String) forms.get(FIELD_NOM), 
							(String) forms.get(FIELD_PRENOM), 
							(String) forms.get(FIELD_ADR1), 
							(String) forms.get(FIELD_ADR2),
							(String) forms.get(FIELD_CODEPOSTAL), 
							(String) forms.get(FIELD_VILLE),
							(String) forms.get(FIELD_GENRE),
							(String) forms.get(FIELD_FUMEUR), 
							(String) forms.get(FIELD_TELFIXE), 
							(String) forms.get(FIELD_TELPORTABLE),
							(String) forms.get(FIELD_PWD1), 
							(String) forms.get(FIELD_LATLONG), 
							(String) forms.get(FIELD_AVATAR) 
						);
											
					userDao.enregistrerUser(u);
					
					// Redirection vers la page de l'itinéraire
					RequestDispatcher dispat;
					session = request.getSession();
					session.setAttribute("user", u);
					dispat = request.getRequestDispatcher(UrlItineraire);
					dispat.include(request, response);
				}
			}
			else{
				request.setAttribute("erreurs", errors);
				request.setAttribute("forms", forms);

				// Redirection vers la page de l'itinéraire
				RequestDispatcher dispat;
				dispat = request.getRequestDispatcher(Url);
				dispat.include(request, response);
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println(e.getMessage());
		}

	}
	
	

	private void validateForm(String profil, String email, String nom,
			String prenom, String adresse1, String adresse2, String codepostal,
			String ville, String fumeur, String telFixe, String telPortale,
			String pwd1, String genre) {

		if (profil.isEmpty()) {
			errors.put(FIELD_PROFIL, "La sélection d'un profil est obligatoire");
		}
		if (fumeur == null) {
			errors.put(FIELD_FUMEUR, "Le choix du fumeur est obligatoire");
		}

		if (genre == null) {
			errors.put(FIELD_GENRE, "La sélection du genre est obligatoire");
		}
		if (email.isEmpty()) {
			errors.put(FIELD_EMAIL, "L'adresse email est obligatoire");
		} 
		if (nom.isEmpty()) {
			errors.put(FIELD_NOM, "Le nom est obligatoire");
		}

		if (prenom.isEmpty()) {
			errors.put(FIELD_PRENOM, "Le prénom est obligatoire");
		} 

		if (adresse1.isEmpty()) {
			errors.put(FIELD_ADR1, "L'adresse est obligatoire");
		} 
		if (codepostal.isEmpty()) {
			errors.put(FIELD_CODEPOSTAL, "Le code postal est obligatoire");
		} 
		if (ville.isEmpty()) {
			errors.put(FIELD_VILLE, "La ville est obligatoire");
		} 

		if (pwd1.isEmpty()) {
			errors.put(FIELD_PWD1, "Le mot de passe est obligatoire");
		} 

	}

	private void validatePwd(String pwd1, String pwd2) throws Exception {

		if (!pwd1.equals(pwd2)) {
			errors.put(FIELD_PWD2, "le mot de passe est incorrect");
		} else {
			forms.remove(FIELD_PWD2);
		}
	}
	
	

}
