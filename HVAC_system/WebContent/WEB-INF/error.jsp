<%--Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Error Page</title>
    </head>
    
	<body>
	
		<h2>Error</h2>

		<c:forEach var="error" items="${errors}">
			<h3 style="color:red"> ${error} </h3>
		</c:forEach>
		
		<c:choose>
			<c:when test="${ (empty user) }">
				Click <a href="search.do">here</a> to login.
			</c:when>
			<c:otherwise>
				Click <a href="myhome.do">here</a> to return to the My Home.
			</c:otherwise>
		</c:choose>

	</body>
</html>