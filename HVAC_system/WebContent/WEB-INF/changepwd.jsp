<%--Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637 --%>
<jsp:include page="template_top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div id = nav>
	 	<ul>
	       <li><a href="myschedule.do" >My schedule</a></li>
	       <li><a href="myroominfo.do">My room</a></li>
	       <li><a href="changepwd.do" id = onlick>Change Password</a></li>
	    </ul>
   	</div>
	<h2 align="center"> Enter your new password </h2>
	<form method="POST" action="changepwd.do">
		<table >
			<tr> <td colspan="3"><hr/></td></tr>
			<tr>
				<td> New Password: </td>
				<td><input type="password" id ="focustext" name="newPassword" value=""/></td>
			</tr>
			<tr>
				<td> Confirm New Password: </td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2" align="center">
					<button type="submit" name="action" value="Change Password" style ="width:250px;"
					 onload="focus()"> Change Password </button>
				</td>
			</tr>
			<tr><td colspan="3"><hr/></td></tr>
		</table>
	</form>
	  <c:forEach var="error" items="${errors}">
				<p align="center" style="color:red"> ${error} </p>
	</c:forEach>
	<p align="center" style="font-size:medium"> ${message}</p>
<jsp:include page="template_bottom.jsp" />