package polimi.awt.twitter.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import polimi.awt.twitter.dao.CampaignDAO;
import polimi.awt.twitter.dao.UserDAO;
import polimi.awt.twitter.entity.Campaign;
import polimi.awt.twitter.entity.User;
import polimi.awt.twitter.entity.UserType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CampaignAction extends ActionSupport implements ModelDriven<Campaign> {
	
	private User user=new User();
	private String name;
	private String idUser;
	private int selectedCampaign;
	private Campaign campaign=new Campaign();
	private Set<Campaign> campaigns=new HashSet<Campaign>();
	private Set<Campaign> campaigns_1=new HashSet<Campaign>();


	private List<Campaign> listCampaigns=new ArrayList<Campaign>();


	
	public String loadCreate(){
		System.out.println("LOAD CREATE idUSER="+getIdUser());
		UserDAO udao=new UserDAO();
		int id=Integer.parseInt(idUser);
		User tuser= udao.findById(id);
		setUser(tuser);
		return SUCCESS;
	}
public String loadEmpCampaignAdd(){
	System.out.println("LOADEmpCampaignAdd idUSER="+getIdUser());
	UserDAO udao=new UserDAO();
	int id=Integer.parseInt(idUser);
	User tuser= udao.findById(id);
	setUser(tuser);
	CampaignDAO cdao=new CampaignDAO();
	setListCampaigns(cdao.listCampaign());
	return SUCCESS;
	}
	//IT SHould take the campaigns here from DB..!
	public CampaignAction(){
		
	}
	//--end	
	
	public String execute(){
		System.out.println("CAMPAIGN ACTION EXECUTE");		

			if (selectedCampaign>0)
				{
				UserDAO udao=new UserDAO();
				int id=Integer.parseInt(idUser);
				User tuser= udao.findById(id);
				setUser(tuser);
				System.out.println("campaign action execute success");
				System.out.println("selectedCampaign id : "+ selectedCampaign);
				System.out.println("RETURNING  : "+ user.getUserType().getTypeName()+"_success");
				return user.getUserType().getTypeName()+"_success";
				}
			else{
				System.out.println("campaign action execute error");
				return ERROR;}		
	}
	public String employer_add(){
		System.out.println("CAMPAIGN ACTION employer_add");		

		if (selectedCampaign>0)
			{
			
			System.out.println("campaign action employer_add success");
			System.out.println("selectedCampaign id : "+ selectedCampaign);
			System.out.println("USER id : "+ idUser);
			
			UserDAO udao=new UserDAO();
			int id=Integer.parseInt(idUser);
			User tuser= udao.findById(id);
			setUser(tuser);
			//int idc=Integer.parseInt(selectedCampaign);
			CampaignDAO campDAO = new CampaignDAO();
			Campaign camp = campDAO.findById(selectedCampaign);
			Set<User> usrs=camp.getUsers();
			usrs.add(user);
			campDAO.merge(camp);
			//campaigns_1=user.getCampaigns_1();
			//campaigns_1.add(camp);
			//user.setCampaigns_1(campaigns_1);
			
			
			return SUCCESS;
			}
		else{
			System.out.println("campaign action execute error");
			return ERROR;}	
	}
	public String select(){
		
		UserDAO udao=new UserDAO();
		int id=Integer.parseInt(idUser);
		User tuser= udao.findById(id);
		setUser(tuser);
		setCampaigns(user.getCampaigns());
		
		System.out.println("selectedCampaign id : "+ selectedCampaign);
		System.out.println("campaign action SELECT success");
		System.out.println("CAMPAIGN ACTION SELECT");
		System.out.println("User== "+user.getName());

		return SUCCESS;
	}
	
	
	public String emp_select(){
		UserDAO udao=new UserDAO();
		int id=Integer.parseInt(idUser);
		User tuser= udao.findById(id);
		setUser(tuser);
		setCampaigns(user.getCampaigns_1());
		
		System.out.println("selectedCampaign id : "+ selectedCampaign);
		System.out.println("campaign action emp_SELECT success");
		System.out.println("CAMPAIGN ACTION emp_SELECT");
		System.out.println("User== "+user.getName());

		return SUCCESS;
	}
	public String create(){
		System.out.println("campaign action CREATE success");
		System.out.println("new campaign Name : "+ campaign.getName());
		
		UserDAO udao=new UserDAO();
		int id=Integer.parseInt(idUser);
		User tuser= udao.findById(id);
	
		campaigns=user.getCampaigns();
		campaigns.add(campaign);
		CampaignDAO cDAO=new CampaignDAO();
		
		campaign.setUser(tuser);
		cDAO.add(campaign);
		System.out.println("campaign saved");
		user.setCampaigns(campaigns);

		return SUCCESS;
	}
	
	

	@Override
	public Campaign getModel() {
		// TODO Auto-generated method stub
		return campaign;
	}
	public String getIdUser() {
		System.out.println("GETIDUSER----"+idUser);
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	public Set<Campaign> getCampaigns() {
		return campaigns;
	}
	public void setCampaigns(Set<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	public int getSelectedCampaign() {
		return selectedCampaign;
	}
	public void setSelectedCampaign(int selectedCampaign) {
		this.selectedCampaign = selectedCampaign;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Campaign> getListCampaigns() {
		return listCampaigns;
	}
	public void setListCampaigns(List<Campaign> listCampaigns) {
		this.listCampaigns = listCampaigns;
	}
	public Set<Campaign> getCampaigns_1() {
		return campaigns_1;
	}
	public void setCampaigns_1(Set<Campaign> campaigns_1) {
		this.campaigns_1 = campaigns_1;
	}
}
