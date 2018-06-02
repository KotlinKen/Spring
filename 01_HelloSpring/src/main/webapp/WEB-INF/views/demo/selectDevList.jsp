<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="" name="pageTitle"/></jsp:include>






		<c:forEach var="v" items="${list}" varStatus="vs">
		${v.devName} ${v.devAge} ${v.devEmail } 
			<c:forEach var="l" items="${v.devLang}" varStatus="ls">
				${l}
				<c:if test="${!ls.last}">
				,
				</c:if>
				
			</c:forEach>
			<c:if test="${!vs.last}">
			<br />
			</c:if>
		</c:forEach>


<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
