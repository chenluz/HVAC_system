<%--Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN http://www.w3.org/TR/html4/loose.dtd>
<jsp:include page="template_top.jsp" />
	    <h2 align="center"> Register </h2>
        <form action="register.do" method="POST">
            <table class ="register_table">
                <tr>
                    <td>User Name:</td>
                    <td>
                        <input type="text" id ="focustext" name="userName" value= "${registerform.userName}"/>
                    </td>
                </tr>
                <tr>
                    <td>E-mail Address:</td>
                    <td>
                    	<input type="text" name="email" value="${registerform.email}"/>
                    </td>
                </tr> 
                <tr>
                    <td>Room Number:</td>
                    <td>
                    	<input type="text" name="roomNum" value="${registerform.roomNum}"/>
                    </td>
                </tr>    
                <tr>
                    <td>First Name:</td>
                    <td>
                    	<input type="text" name="firstname" value="${registerform.firstname}" />
                    </td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td>
                    	<input type="text" name="lastname" value="${registerform.lastname}" />
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td><input type="password" name="passwordConfirm" /></td>
                </tr>
                <tr>  
                	<td></td>
                    <td >
                        <button type ="submit" name = "action" value ="Register" style ="width:250px; margin-top:2%;">Register</button>	
                    </td>
                </tr>
            </table>
        </form>
        <c:forEach var="error" items="${errors}">
				<p align="center" style="color:red"> ${error} </p>
		</c:forEach>
<jsp:include page="template_bottom.jsp" />