<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Query Execute Result</title>
</head>
<body>
<table class="tweetTable" border="1">
    <tr class="even"> 
        <td><b>Username</b></td>        
        <td><b>Post</b></td>
        <td><b>Geolocation</b></td>        
        <td><b>Date</b></td>       
        <td><b>Day</b></td>        
        <td><b>Hour</b></td>
        <td><b>Minute</b></td> 
        <td><b>Year</b></td>
        <td><b>Month</b></td>
    </tr>
    <s:iterator value="tweets" status="tweetStatus">
            <tr
            	class="<s:if test="#tweetStatus.odd == true ">odd</s:if><s:else>even</s:else>">
                <td><s:property value="author" /></td>
                <td><s:property value="tweetText" /></td>
                <td><s:property value="geoLocation" /></td>
                <td><s:property value="date" /></td>
                <td><s:property value="day" /></td>
                <td><s:property value="hour" /></td>
                <td><s:property value="minute" /></td>           
                <td><s:property value="year" /></td>
                <td><s:property value="month" /></td>     
            </tr>
    </s:iterator>
</table>
</body>
</html>