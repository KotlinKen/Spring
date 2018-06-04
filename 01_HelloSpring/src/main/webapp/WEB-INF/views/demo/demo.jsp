<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="Demo" name="pageTitle"/></jsp:include>
<style>
div#demo-container{
	width : 500px;
	padding:15px; 
	margin: 0 auto;
	brder:1px solid #ececec;
	border-radius:10px; 

}
.list-group button{
background:#333; color:#fff; display:block; padding:10px; width:200px; float:left; border:none;

}

</style>

<div id="demo-container">
	<form id="devFrm">
		<!-- 이름 -->
		<div class="form-group row">
			<label for="devName" class="col-sm-2 col-form-label">이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="devName"
					name="devName">
			</div>
		</div>
		<!-- 나이 -->
		<div class="form-group row">
			<label for="devAge" class="col-sm-2 col-form-label">나이</label>
			<div class="col-sm-10">
				<input type="number" class="form-control" id="devAge"
					name="devAge">
			</div>
		</div>

		<!-- 이메일 -->
		<div class="form-group row">
			<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="devEmail"
					name="devEmail">
			</div>
		</div>

		<!-- 개발언어 -->
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">개발언어</label>
			<div class="col-sm-10">
				<div class="form-check form-check-inline">
					<input class="form-check-input" name="devLang" type="checkbox" id="Java" value="Java"> 
						
						<label class="form-check-label" for="inlineCheckbox1">Java</label>
				</div>
				
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="Oracle" value="Oracle"> 
					<label class="form-check-label" for="inlineCheckbox2">Oracle</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox"  name="devLang" id="Javascript" value="Javascript"> 
					<label class="form-check-label" for="inlineCheckbox3">Javascript</label>
				</div>

			</div>
		</div>
	</form>
	<!-- 전송버튼 -->
	<div class="list-group">
		<button type="button" onclick="demo1();" class="list-group-item-action">파라미터 핸들링 - HttpServletRequest</button>
		<button type="button" onclick="demo2();" class="list-group-item-action">파라미터 핸들링 - @RequestParam</button>
		<button type="button" onclick="demo3();" class="list-group-item-action">파라미터 핸들링 - 커맨드객체 VO </button>
		<button type="button" onclick="insertDev();" class="list-group-item-action"> insertDev() </button>
	</div>
</div>



<%  %>
<script>
	function demo1(){
		$("#devFrm").attr("action", "${rootPath }/demo/demo1.do");
		$("#devFrm").submit();
	}
	
	function demo2(){
		$("#devFrm").attr("action", "${rootPath }/demo/demo2.do");
		$("#devFrm").submit();
	}
	function demo3(){
		$("#devFrm").attr("action", "${rootPath }/demo/demo3.do");
		$("#devFrm").submit();
	}
	function insertDev(){
		$("#devFrm").attr("action", "${rootPath }/demo/insertDev.do");
		$("#devFrm").attr("method", "post");
		$("#devFrm").submit();
	}

</script>






<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
