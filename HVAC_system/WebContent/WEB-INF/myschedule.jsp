<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template_top.jsp" />
<link href="schedule.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="setTemperature.js"> </script>
<div id = nav>
	 	<ul>
	       <li><a href="myschedule.do"  id = onlick >My schedule</a></li>
	       <li><a href="myroominfo.do" >My room</a></li>
	       <li><a href="changepwd.do">Change Password</a></li>
	    </ul>
</div>
	 <h2 align="center">HVAC Schedule</h2>
	 	<table id = "textsample">
	 	<tr>
  			<td>53°C/127°F</td>
  			<c:forEach var="i" begin="1" end="105" >
		 		<td></td>
     	    </c:forEach>
     	    <td>18°C/64°F</td>
  			<c:forEach var="i" begin="1" end="104" >
		 		<td></td>
     	    </c:forEach>
			<td>-18°C/0°F</td>
			<td></td><td></td><td></td><td></td>
     	</tr> 
      	</table>
	 	<table id = "colorsample">
     	 <tr>		
			<c:forEach var="i" begin="1" end="255" >
		 		<td  style = "background-color:rgb(${(255-i).intValue()},0,${i});"> </td>
     	    </c:forEach>
     	    </tr> 
      
	 	</table>
	 <form action="update.do" method="post">
       	<table id = "schedule"> 
        	 <colgroup>
	    		<col span="1" class="head" > 
	    		<col span="24">
  			</colgroup>
  			<tr>
  			<td>MONDAY</td>
			<c:forEach var="scheduleMo" items="${scheduleMo}">
		 		<td class= temperature onclick = "setTemperature(${scheduleMo.getTemperature()},${scheduleMo.id},this);" 
		 		style = "background-color:rgb(${scheduleMo.getTemperature().intValue()*2},0,${255-scheduleMo.getTemperature().intValue()*2});"> </td>
     	    </c:forEach>
     	    </tr> 
     	    <tr>
  			<td>TUESDAY</td>
			<c:forEach var="scheduleTu" items="${scheduleTu}">
		 		<td class= temperature onclick = "setTemperature(${scheduleTu.getTemperature()},${scheduleTu.id},this);" 
		 		style = "background-color:rgb(${scheduleTu.getTemperature().intValue()*2},0,${255-scheduleTu.getTemperature().intValue()*2});"> </td>
     	    </c:forEach>
     	    </tr> 
     	    <tr>
  			<td>WENDESDAY</td>
			<c:forEach var="scheduleWe" items="${scheduleWe}">
		 		<td class= temperature onclick = "setTemperature(${scheduleWe.getTemperature()},${scheduleWe.id},this);" 
		 		style = "background-color:rgb(${scheduleWe.getTemperature().intValue()*2},0,${255-scheduleWe.getTemperature().intValue()*2});"> </td>
     	    </c:forEach>
     	    </tr> 
     	    <tr>
  			<td>THURSDAY</td>
			<c:forEach var="scheduleTh" items="${scheduleTh}">
		 		<td class= temperature onclick = "setTemperature(${scheduleTh.getTemperature()},${scheduleTh.id},this);" 
		 		style = "background-color:rgb(${scheduleTh.getTemperature().intValue()*2},0,${255-scheduleTh.getTemperature().intValue()*2});"> </td>
     	    </c:forEach>
     	    </tr> 
     	    <tr>
  			<td>FRIDAY</td>
			<c:forEach var="scheduleFr" items="${scheduleFr}">
		 		<td class= temperature onclick = "setTemperature(${scheduleFr.getTemperature()},${scheduleFr.id},this);" 
		 		style = "background-color:rgb(${scheduleFr.getTemperature().intValue()*2},0,${255-scheduleFr.getTemperature().intValue()*2});"> </td>
     	    </c:forEach>
     	    </tr> 
     	    <tr>
  			<td>SATURDAY</td>
			<c:forEach var="scheduleSa" items="${scheduleSa}">
		 		<td class= temperature onclick = "setTemperature(${scheduleSa.getTemperature()},${scheduleSa.id},this);" 
		 		 style = "background-color:rgb(${scheduleSa.getTemperature().intValue()*2},0,${255-scheduleSa.getTemperature().intValue()*2});"> </td>
     	    </c:forEach>
     	    </tr> 
     	    <tr>
  			<td>SUNDAY</td>
			<c:forEach var="scheduleSu" items="${scheduleSu}">
		 		<td class= temperature onclick = "setTemperature(${scheduleSu.getTemperature()},${scheduleSu.id},this);" 
		 		style = "background-color:rgb(${scheduleSu.getTemperature().intValue()*2},0,${255-scheduleSu.getTemperature().intValue()*2});"> </td>
     	    </c:forEach>
     	    </tr>    	    
     	    <tr>
     	    <td></td>
     	    <c:forEach var="i" begin="0" end="11" step="1"> 
       		 	<td>${i}A</td>
    		</c:forEach>
       		 <td>noon</td>
    		<c:forEach var="i" begin="1" end="11" step="1"> 
        		<td>${i}P</td>
    		</c:forEach>
     	    </tr>  	 
		</table>
		</form>
	 <button type ="submit" id = "data" onclick ="showData(${temperaturelist});" style = "width:120px;float:right">Show Data</button>
   	<c:forEach var="error" items="${errors}">
			<p align="center"  style="color:red"> ${error} </p>
	</c:forEach>
	<p align="center" style="font-size:large"> ${message}</p>
<jsp:include page="template_bottom.jsp" />