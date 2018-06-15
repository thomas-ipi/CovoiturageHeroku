package model;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

	private List<UserBean> userList;

	private static UserManager singleton;

	private UserManager() {
		userList = new ArrayList<UserBean>();
//		userList.add(new UserBean("conducteur", "eben@magnus.fr", "ben",
//				"eric", "19 chemin de fages", "", "31400", "toulouse", "masculin", "nonfumeur",
//				"05.00.00.00.00", "06.00.00.00.00", "eben", "43.5836919, 1.4636044000000084"));
//		
//		userList.add(new UserBean("conducteur", "dass@magnus.fr", "ass",
//				"domi", "128 avenue de lespinet", "", "31400", "toulouse", "masculin", "nonfumeur",
//				"05.00.00.00.00", "06.00.00.00.00", "dass", "43.5767382, 1.469967700000494"));
//		
//		userList.add(new UserBean("conducteur", "orou@magnus.fr", "orou",
//				"olivier", "27 avenue camille pujol", "", "31500", "toulouse", "masculin", "nonfumeur",
//				"05.00.00.00.00", "06.00.00.00.00", "dass", "43.6014841, 1.4605116"));
//		
//
//		userList.add(new UserBean("conducteur", "mou@magnus.fr", "mou",
//				"moudenc", "place du capitole", "", "31000", "toulouse", "masculin", "nonfumeur",
//				"05.00.00.00.00", "06.00.00.00.00", "mou", "43.6044328, 1.4434630"));
//
//		userList.add(new UserBean("conducteur", "toto@magnus.fr", "toto",
//				"toto", "Place de la loge", "", "66000", "Perpignan", "masculin", "nonfumeur",
//				"05.00.00.00.00", "06.00.00.00.00", "mou", "42.6996216,2.8946284"));

	}

	public static UserManager getInstance() {
		if (singleton == null) {
			singleton = new UserManager();
		}

		return singleton;
	}

	public UserBean userEstValide(String email, String pswd) {
		UserBean login = new UserBean(email, pswd);
		for (UserBean userExistant : userList) {
			if (login.equals(userExistant)) {
				return userExistant;
			}
		}
		return null;
	}

	public void creerUser(UserBean user) {
		userList.add(user);

	}

	public List<UserBean> getUserList() {
		return userList;

	}

	public boolean userExiste(String email) {

		boolean result = false;
		for (UserBean userExistant : userList)

			if (email.equals(userExistant.getEmail())) {
				result = true;
			}

		return result;

	}

}
