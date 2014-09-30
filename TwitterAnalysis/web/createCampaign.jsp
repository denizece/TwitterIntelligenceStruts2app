<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create a Campaign</title>
</head>
<body>
<s:form action="getCampaignCreate">
<s:hidden name="idUser" value="%{idUser}"/>
<s:textfield key="name" label="CampaignName"></s:textfield>
<s:submit label="submitbut1"></s:submit>
</s:form>
</body>
</html>