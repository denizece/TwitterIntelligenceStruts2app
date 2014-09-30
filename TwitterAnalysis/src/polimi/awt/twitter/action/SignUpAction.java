package polimi.awt.twitter.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.catalina.Session;
import org.hibernate.Query;

import polimi.awt.twitter.dao.UserDAO;
import polimi.awt.twitter.dao.UserTypeDAO;
import polimi.awt.twitter.dao.UserTypeHome;
import polimi.awt.twitter.entity.Campaign;
import polimi.awt.twitter.entity.User;
import polimi.awt.twitter.entity.UserType;
import polimi.awt.twitter.service.UserTypeChecker;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SignUpAction extends ActionSupport implements ModelDriven<User> {
	
	private int selectedType;
	private User user=new User();
	private UserType userType =new UserType();
	private UserTypeDAO utDAO=new UserTypeDAO();
	private List<UserType> userTypes=new ArrayList<UserType>();	
	private Set<Campaign> campaigns=new HashSet<Campaign>();
	

	public void validate() {
			if(user.getName()!=null&&user.getName().isEmpty()) {
				// userNameBlank
				addFieldError("name", "user name can not be blank");
				System.out.println("uname empty");
			}
			if (user.getPassword()!=null&&user.getPassword().isEmpty()) {
				// passwordBlank
				System.out.println("passempty");
				addFieldError("password", "password can not be blank");
			}
			if (user.getEmail()!=null&&user.getEmail().isEmpty()) {
				// passwordBlank
				System.out.println("mailempty");
				addFieldError("email", "mail can not be blank");
			}
			if(userTypes.size()>0){
		if (getSelectedType()<0)
			addFieldError("selectedType", "User Type Can Not Be Blank");
		else if (userTypes.get(getSelectedType())==null)
			addFieldError("selectedType", "User Type Can Not Be Blank");
		else
			user.setUserType(userTypes.get(getSelectedType()));}
		
	}
	
	public String execute(){
		System.out.println("i am in execute the user will be saved");
		UserTypeDAO utDAO=new UserTypeDAO();
		UserType ut=utDAO.findById(getSelectedType());
		user.setUserType(ut);
		UserDAO udao=new UserDAO();
		udao.add(user);
		System.out.println("user is saved");
		
		return SUCCESS;
	}
	
	public String display(){
		System.out.println("SIGN UP ACTION DISPLAY");
		userTypes=utDAO.listUserType();
		
		return SUCCESS;
	}
	
	public SignUpAction(){
		System.out.println("in the constructor of signupaction");
	}
	
	public int getSelectedType() {
		return selectedType;
	}
	public void setSelectedType(int selectedType) {
		this.selectedType = selectedType;
	}
	
	public Set<Campaign> getCampaigns() {
		return campaigns;
	}
	public void setCampaigns(Set<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	public List<UserType> getUserTypes() {
		return userTypes;
	}
	public void setUserTypes(List<UserType> userTypes) {
		this.userTypes = userTypes;
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
