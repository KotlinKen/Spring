<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="" name="pageTitle"/></jsp:include>


<table class="table" id="tbl-demo">
	<tr>
		<th scope="col">이름</th>
		<td> ${dev.devName} </td>
	</tr>
	<tr>
		<th scope="col">나이</th>
		<td> ${dev.devAge} </td>
	</tr>
	<tr>
		<th scope="col">이메일</th>
		<td> ${dev.devEmail} </td>
	</tr>
	<tr>
		<th scope="col">개발언어</th>
		<td>  
	
		<c:forEach var="v" items="${dev.devLang}" varStatus="vs">
		${v}
		<c:if test="${!vs.last}">
		,
		</c:if>
		</c:forEach>
		</td>
	</tr>

	
</table>



 


<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
