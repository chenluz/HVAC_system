<jsp:include page="template_top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="roominfo.css" rel="stylesheet" type="text/css">
	<div id = nav>
	 	<ul>
	       <li><a href="myschedule.do" >My schedule</a></li>
	       <li><a href="myroominfo.do"  id = onlick>My room</a></li>
	       <li><a href="changepwd.do">Change Password</a></li>
	    </ul>
   	</div>
		<h2 align="center"> HVAC Information</h2>	
            <table id = roominfo>
               	<tr>
               	<td>Light</td>
               	<td>HVAC</td>
               	<td>Occupancy</td>
               	<td>Temperature</td>
                </tr>
                <tr>
                <c:forEach var="room" items="${roominfo}">
					<td>
						<c:choose>			 	 
		 				<c:when test="${room.isLightStatus()}"> 
		 					<img src="lighton.jpg" width="100" height="100"/>
		 				</c:when>
		 				<c:otherwise>
		 					  <img src="lightoff.png" width="100" height="100"/> 
		 				</c:otherwise>
		 				</c:choose>
					</td>
					<td>
						<c:choose>			 	 
		 				<c:when test="${room.isHvacStatus()}"> 
		 					<img src="hvacon.jpg" width="130" height="100"/>
		 				</c:when>
		 				<c:otherwise>
		 					  <img src="hvacoff.jpeg" width="130" height="100"/> 
		 				</c:otherwise>
		 				</c:choose>
					</td>
					<td>
						<c:choose>			 	 
		 				<c:when test="${room.isOccupancyStatus()}"> 
		 					<img src="hasperson.jpg" width="100" height="100"/>
		 				</c:when>
		 				<c:otherwise>
		 					  <img src="noperson.jpeg" width="100" height="100"/> 
		 				</c:otherwise>
		 				</c:choose>
					</td>
					<td id = house>
					<div>
					${room.getTemperature()}°F
					</div>
					</td>
				 </c:forEach>
				</tr>
            </table>

<jsp:include page="template_bottom.jsp" />
