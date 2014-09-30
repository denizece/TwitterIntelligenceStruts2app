<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Employee Home</title>

</head>
<h2>Welcome, <s:property value="%{user.name}" />!</h2>

<body>
<s:form action="getCampaignSelected">
<s:hidden name="idUser" value="%{idUser}"/>
<s:select label=" Select from your Campaigns "
		key="selectedCampaign" 
		headerKey="-1" headerValue="Select Campaign"
		list="campaigns" 
		listValue="name"
		listKey="idCampaign"
		name="selectedCampaign" />
<s:url id="newCamp" namespace="/" action="preAddEmployerCampaign">
<s:param name="idUser" value="idUser"/>
<s:param name="idCampaign" value="selectedCampaign" />
</s:url>
<s:a href="%{newCamp}">Add new Employer Campaign</s:a>
<s:submit></s:submit>
</s:form>
</body>
</html>