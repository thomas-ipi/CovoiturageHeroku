package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.UserBean;

public class UserDao  {

	private final static String JPA_UNIT_NAME = "Covoiturage";
	private static EntityManager em;

	public static EntityManager getEm() {
		if (em == null) {
			em = Persistence.createEntityManagerFactory(JPA_UNIT_NAME).createEntityManager();
			return em;
		} else {
			return em;
		}
	}

	public UserBean userEstValide(String email, String pswd) {
		UserBean login = new UserBean(email, pswd);
		for (UserBean userExistant : getUserList()) {
			if (login.equals(userExistant)) {
				return userExistant;
			}
		}
		return null;
	}

	public void enregistrerUser(UserBean user) {
		getEm().getTransaction().begin();
		if (user.getId() == 0L) {
			getEm().persist(user);
		}
		else {
			getEm().merge(user);			
		}
		getEm().getTransaction().commit();
	}


	public List<UserBean> getUserList() {
		Query q = getEm().createQuery("select p from UserBean p");
		return q.getResultList();
	}

	public boolean userExiste(String email, String id) {

		boolean result = false;
		for (UserBean userExistant : getUserList())
			if (email.equals(userExistant.getEmail()) && (!id.equals(""+userExistant.getId()))) {
				
				result = true;
			}

		return result;

	}
	
}
