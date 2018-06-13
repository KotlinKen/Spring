<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="" name="pageTitle"/></jsp:include>
<%@ page language="java"  isErrorPage="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<c:if test="${not empty exception.getMessage()}">
</c:if>


   <%-- <c:out value="${exception.getMessage()}"/> --%>


<%-- <c:out value="${requestScope['javax.servlet.error.message']}"/> --%>

<h2><%=	exception.getMessage() %></h2>
<h1><a href="${rootPath}">시작페이지로 돌아가기</a></h1>
<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
