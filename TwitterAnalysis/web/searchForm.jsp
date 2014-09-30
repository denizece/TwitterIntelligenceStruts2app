<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Search</title>
</head>
<body>
<s:form action="getQueryCreate">
<s:hidden name="idCampaign" value="%{idCampaign}"/>
<s:textfield key="newQuery.queText" label="enter the phrase you want to search for"></s:textfield>
<!-- <s:textfield key="newQuery.idQuery" label="QueryId"></s:textfield> -->
<s:submit></s:submit>
</s:form>
</body>
</html>