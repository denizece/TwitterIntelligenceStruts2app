<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Manager Page</title>
</head>
<body>
<h2>Welcome, <s:property value="name" />!</h2>

<s:form action="getCampaignSelected">
<s:hidden name="idUser" value="%{idUser}" /> 
<s:select label=" Your Campaigns "
		key="selectedCampaign" 
		headerKey="-1" headerValue="Select Campaign"
		list="campaigns" 
		listValue="name"
		listKey="idCampaign"
		name="selectedCampaign" />

<s:url id="newCamp" namespace="/" action="preCampaignCreate">
<s:param name="idUser" value="idUser"/>
<s:param name="idCampaign" value="selectedCampaign" />
</s:url>
<s:a href="%{newCamp}">Create New Campaign</s:a>
<s:submit></s:submit>
</s:form>
</body>
</html>