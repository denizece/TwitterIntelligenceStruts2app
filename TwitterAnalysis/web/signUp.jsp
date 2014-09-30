<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Sign Up Page</title>
</head>
<body>
<s:form action="getSignUpResult">
<s:textfield key="name" label="UserName"></s:textfield>
<s:textfield key="email" label="E-mail"></s:textfield>
<s:password key="password" label="Password"></s:password>
<s:select label="UserType"
		key="userType.idUserType" 
		headerKey="-1" headerValue="Select User Type"
		list="userTypes" 
		listValue="typeName"
		listKey="idUserType"
		name="selectedType" />
<s:submit ></s:submit>
</s:form>

</body>
</html>