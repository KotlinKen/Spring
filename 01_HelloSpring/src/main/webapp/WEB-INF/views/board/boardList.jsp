<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="" name="pageTitle"/></jsp:include>



<style>
input#btn-add{float:right; margin:0 0 15px;}
</style>

총 ${count }건의 게시물이 있습니다.
<input type="button" value="글쓰기"  class="btn btn-bd-download d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3"  id="btn-add" onclick="location.href='boardForm.do'" />
<table class="table table-striped table-hover">
<tr>
	<th scope="col">번호</th>
	<th scope="col">제목</th>
	<th scope="col">작성자</th>
	<th scope="col">작성일</th>
	<th scope="col">첨부파일</th>
	<th scope="col">조회수</th>
</tr>
<c:forEach var="item" items="${list}" varStatus="status">
<tr onclick="fn_boardView('${item.BOARDNO }')">
<td>    ${item.BOARDNO}</td>
<td>    ${item.BOARDTITLE}</td>
<td>    ${item.BOARDWRITER}</td>
<td>    ${item.BOARDDATE}</td>
<td>    <c:if test="${ item.FILECOUNT > 0  }"> <img src="${rootPath }/resources/images/file.png" alt="" /></c:if></td>
<td>    ${item.BOARDREADCOUNT}</td>



</tr>
</c:forEach>
</table>


<%
	int totalContents = Integer.parseInt(String.valueOf(request.getAttribute("count")));
	int numPerPage = Integer.parseInt(String.valueOf(request.getAttribute("numPerPage")));
	int cPage =1;
	try{
		cPage = Integer.parseInt(String.valueOf(request.getParameter("cPage")));
	}catch(NumberFormatException e){
		
	}
	
%>

  <%= com.kh.spring.common.util.Utils.getPageBar(totalContents, cPage, numPerPage, "boardList.do") %>
<script>
	function fn_boardView(no){
		location.href="${rootPath}/board/boardView.do?boardNo="+no;
	}
</script>


<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
