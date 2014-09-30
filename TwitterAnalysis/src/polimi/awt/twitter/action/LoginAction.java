package polimi.awt.twitter.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import polimi.awt.twitter.dao.UserDAO;
import polimi.awt.twitter.entity.Campaign;
import polimi.awt.twitter.entity.User;
import polimi.awt.twitter.entity.UserType;
import polimi.awt.twitter.service.UserTypeChecker;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
	
	private User user=new User();
/*	private Set<Campaign> campaigns=new HashSet<Campaign>();
	
	public Set<Campaign> getCampaigns() {
		return campaigns;
		}

	public void setCampaigns(Set<Campaign> campaigns) {
		this.campaigns = campaigns;
	}*/
	
	public LoginAction(){
		System.out.println("in the constructor of Loginaction");

	}

	public void validate() {
			if (user.getName()!=null&&user.getName().isEmpty()) {
				// userNameBlank
				addFieldError("name", "user name can not be blank");
				System.out.println("uname empty");
			}
			if (user.getPassword()!=null&&user.getPassword().isEmpty()) {
				// passwordBlank
				System.out.println("passempty");
				addFieldError("password", "password can not be blank");
			}
	}

	public String execute() {
		UserDAO udao=new UserDAO();
		System.out.println("LOGIN EXECUTE");
		//UserTypeChecker utc=new UserTypeChecker();
		
		User registeredUser=udao.getRegisteredUser(user.getName(), user.getPassword());
		if(registeredUser!=null){
				setUser(registeredUser);
				return user.getUserType().getTypeName();
		}
		else
			return LOGIN;
			/*if (user.getName().equals("deniz") && user.getPassword().equals("defne"))
				return "manager";//utc.checkType(user);
			else
				return LOGIN;*/
	}
	
	public String getHome(){
		UserTypeChecker utc= new UserTypeChecker();
		return utc.checkType(user);
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}
}
