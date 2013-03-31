<%--Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template_top.jsp" />
	<h2 align ="center">Room List</h2> 
	<form action="search.do" method="POST">
            <table> 
                <tr>
                    <td >Search for:</td>
                    <td colspan="2"><input type="text" size="40" id ="focustext" name="search"
        	 			value ="${searchForm.getSearch()}"/>
        	 		</td>
                    <td > 
                    	<button type="submit" name="action" value ="search" style ="width:100px;">Search</button>
        	 		 <td/>
                </tr>
            </table>
      </form>
     <c:forEach var="error" items="${errors}">
			<h3 style="color:red"> ${error} </h3>
	</c:forEach>
	   <p align="center" style="font-size: large">The list now has ${ fn:length(items) } Rooms.</p>   
       <table>
    	    <c:set var="count" value="0" />
			<c:forEach var="item" items="${items}">
				<c:set var="count" value="${ count+1 }" />
        	    <tr>
			        <td valign="baseline" style="font-size:large;"> &nbsp; ${count}. &nbsp;</td>
			        <td valign="baseline">
			        <span style="font-size: large">
			        	<a href="itemdetail.do?id=${ item.id }">${ item.description }</a>
			        </span>
					</td>
			        <td valign="baseline">			        
			        <span style="font-size: large">	
			       		----$<c:out value="${ item.price }" />
			       	</span>
					</td>
        	    </tr>
        	</c:forEach>
        </table>
<jsp:include page="template_bottom.jsp" />