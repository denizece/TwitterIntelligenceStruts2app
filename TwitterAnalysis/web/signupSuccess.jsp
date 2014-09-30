<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Sign-Up Success!</title>
</head>
<body>
	<h1>Sign up Successful</h1>

	<h4>
		User Name :
		<s:property value="user.name" />
		<p>
			Email :
			<s:property value="user.email" />
		</p>
		<p>
			userType:
			<s:property value="user.userType.typeName" />
		</p>

	</h4>
</body>
</html>