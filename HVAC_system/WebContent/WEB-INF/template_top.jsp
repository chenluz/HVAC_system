<%--Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html charset=US-ASCII">
	<title>Item list </title>
	<link href="all.css" rel="stylesheet" type="text/css">
</head>

<body onLoad="document.getElementById('focustext').focus()">
	<div class = "top_div">
		<c:choose>	
			<%-- login,register, home(without login) and detail page has header: ref longin,register and home--%> 		 	 
		 	<c:when test="${ (empty user)}">
		 	 	<a href="roomlist.do">Home</a>
		 	 	<a href="login.do">Login</a>
		 	 	<a href="register.do">Register</a>	 	
		    </c:when>
		     <%-- Myhome, home(with login) and detailpage has header: ref register,changepassword and home--%>	 
		     <c:otherwise>
		     	<a href="roomlist.do">Home</a>
		 	 	<a href="register.do">Register</a>
		 	 	<a href="logout.do">Logout</a>
		 	 	<a href="myhome.do">My home</a>
		 	 	<span class=span>Current User: ${user.getEmailAddr()}</span>     		 	 
		    </c:otherwise>
		 </c:choose>		
	   </div>

