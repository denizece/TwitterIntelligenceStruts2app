<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Employee Add Employer Campaign Page</title>
</head>
<body>
<h2>Welcome, <s:property value="%{user.name}" />!</h2>

<s:form action="getEmployerCampaignSelected">
<s:hidden name="idUser" value="%{idUser}"/>
<s:select label=" Your Campaigns "
		key="selectedCampaign" 
		headerKey="-1" headerValue="Select Campaign"
		list="listCampaigns" 
		listValue="name"
		listKey="idCampaign"
		name="selectedCampaign" />
<s:submit></s:submit>
</s:form>
</body>
</html>