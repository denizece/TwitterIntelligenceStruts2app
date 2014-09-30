package polimi.awt.twitter.action;

import polimi.awt.twitter.service.QueryExecuter;

public class QueryCreateAction {
	private String qresult;
	private String query;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQresult() {
		return qresult;
	}
	public void setQresult(String qresult) {
		this.qresult = qresult;
	}
	public String execute(){
		QueryExecuter qe= new QueryExecuter();
		setQresult(qe.getQueriedTweets());
		System.out.println(getQuery());
		return "querySuccess";
	}

}
