<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Log-in</title>
</head>
<body>
<s:form action="getLogin">
<s:textfield key="name" label="UserName"></s:textfield>
<s:password key="password" label="Password"></s:password>
<s:url id="signup" namespace="/" action="getSignUp" />
<s:a href="%{signup}">Signup?</s:a>

<s:submit>
<s:param name="idUser" value="%{#user.idUser}" />
</s:submit>
<!--<s:submit label="submitbut1"></s:submit>!-->
</s:form>
</body>
</html>