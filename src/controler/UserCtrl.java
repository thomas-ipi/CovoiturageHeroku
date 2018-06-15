package controler;

import java.io.Serializable;
import java.util.List;

import dao.UserDao;
import model.UserBean;

public class UserCtrl implements Serializable{
	
	private List<UserBean> memberList;
	private UserBean newUser = new UserBean();
	
	private static transient UserDao pDao = new UserDao();
	
	public String createUser(){
		pDao.enregistrerUser(newUser);
		newUser = new UserBean();
		return "KO";	
		}

	public List<UserBean> getMemberList() {
		return memberList=pDao.getUserList();
	}

	public void setMemberList(List<UserBean> memberList) {
		this.memberList = memberList;
	}

	public UserBean getNewUser() {
		if(newUser==null){
			newUser = new UserBean();
		}
		return newUser;
	}

	public void setNewUser(UserBean newUser) {
		this.newUser = newUser;
	}

}
