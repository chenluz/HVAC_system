<%--Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template_top.jsp" />
		<h2 align="center"> Login </h2> 
			<form action="login.do" method="post">
	           <div class="login_div">
	            <table>
	                <tr>
	                    <td>E-mail Address:</td>
	                </tr>
	                <tr>
	                    <td colspan="2">
	                   		 <input type="text" id ="focustext"  name="email"  value= "${loginform.email}"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td>Password:</td>
	                </tr>
	                 <tr>
	                    <td colspan="2">
	                    <input type="password" name="password" />
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="2" align="center">
			                <button type ="submit" name = "action" value ="Login" style ="width:250px; ">Login</button>
	                    </td>
	                </tr>
	            </table>
	           <c:forEach var="error" items="${errors}">
				<p align="center" style="color:red"> ${error} </p> 			                                                                 
			  </c:forEach>
	           </div>
	        </form>
<jsp:include page="template_bottom.jsp" />