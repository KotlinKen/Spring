<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="" name="pageTitle"/></jsp:include>









<style>

#board-container{
	width:400px; 
	margin:0 auto;
	text-align:center;	
}
#board-container input{margin-bottom:15px;}
#board-container .input-group{margin:0px; }
.input-group-prepend{padding:0px;}
#board-container label.custom-file-label{text-align:left;}
</style>

<div id="board-container">
	<form action="boardFormEnd.do" name="boardFrm" method="post" enctype="multipart/form-data" onsubmit="return validate();">
		<input type="text" name="boardTitle" id="boardTitle" placeholder="제목" class="form-control"  value="${board[0].BOARDTITLE }"/>
		<input type="text" name="boardWriter" id="boardWriter" readonly required class="form-control" value="${board[0].BOARDWRITER }"/>
		<br />
		<textarea name="boardContent" id="" cols="30" rows="10" placeholder="내용" required class="form-control"></textarea>
		<br />
		


		      
		<input type="submit" value="저장" class="btn btn-outline-success" value="저장" />
	</form>
	 	<c:if test="${fn:length(board[0].ORIGINALFILENAME) > 0}">   
		<c:forEach var = "item" items="${board }" varStatus="vs">
			  <button class="btn btn-block btn-outline-success" onclick="fn_downLoad('${item.ORIGINALFILENAME}', '${item.RENAMEDFILENAME}')">첨부파일 ${vs.count} - ${item.ORIGINALFILENAME }</button>
		</c:forEach>
		</c:if>
</div>
<script>
function fn_downLoad(oName, rName){
	oName  = encodeURIComponent(oName);
	console.log(oName);
	console.log(rName);
 	location.href="${rootPath}/board/boardDownload.do?oName="+oName + "&rName="+ rName;
}

</script>

<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
