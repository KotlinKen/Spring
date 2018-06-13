<jsp:include page ="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Hello Spring!" name="pageTitle"/>
</jsp:include>

	<img src="${pageContext.request.contextPath }/resources/images/logo-spring.png" id="center-image" alt="스프링로고" />
	<c:forEach var="i" begin="1" end="6" varStatus="status">
		<h${i }> 김률민 페이지 ${i }</h${i }>
		<c:if test="${status.last}">
		
		6번째는 
		
		</c:if>
	</c:forEach>
	${rootPath }
<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
