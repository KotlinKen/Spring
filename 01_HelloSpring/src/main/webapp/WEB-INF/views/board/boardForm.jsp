<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="게시글 작성" name="pageTitle"/></jsp:include>

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
		<input type="text" name="boardTitle" id="boardTitle" placeholder="제목" class="form-control" />
		<input type="text" name="boardWriter" id="boardWriter" readonly required class="form-control" value="${ memberLoggedIn.userId}"/>
		<br />
		<div class="input-group mb-3" style="padding:0px; ">
		  <div class="input-group-prepend">
		    <span class="input-group-text ">첨부파일</span>
		  </div>
		  <div class="custom-file">
		    <input type="file" class="custom-file-input" name="upFile" id="upFile1">
		    <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
		  </div>
		</div>
		<div class="input-group mb-3" style="padding:0px; ">
		  <div class="input-group-prepend">
		    <span class="input-group-text ">첨부파일</span>
		  </div>
		  <div class="custom-file">
		    <input type="file" class="custom-file-input" name="upFile" id="upFile2">
		    <label class="custom-file-label" for="upFile2">파일을 선택하세요</label>
		  </div>
		</div>
		<textarea name="boardContent" id="" cols="30" rows="10" placeholder="내용" required class="form-control"></textarea>
		<br />
		<input type="submit" value="저장" class="btn btn-outline-success" value="저장" />
	</form>
</div>







<script>
function validate(){
	return true;
}
$(function(){
		$("[name=upFile]").on("change", function(){
			//var fileName = $(this).val();
			var fileName = $(this).prop("files")[0].name;
			$(this).next(".custom-file-label").html(fileName);
		});
		
});
</script>


<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
