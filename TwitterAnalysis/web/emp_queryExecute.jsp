<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Select Query</title>
</head>
<body>
<h2>Welcome, <s:property value="%{campaign.name}" />!</h2>
<s:form action="getQuerySelected">
<s:select label=" Your Queries"
		key="selectedQuery" 
		headerKey="-1" headerValue="Select query"
		list="queries" 
		listValue="queText"
		listKey="idQue"
		name="selectedQuery" />

<s:submit></s:submit>
</s:form> 

<s:form action="getQueryGeoLocMap">
<s:select label=" See Query GeoLocation Map"
		key="selectedQuery" 
		headerKey="-1" headerValue="Select query"
		list="queries" 
		listValue="queText"
		listKey="idQue"
		name="selectedQuery" />
<s:submit></s:submit>
</s:form> 

</body>
</html>