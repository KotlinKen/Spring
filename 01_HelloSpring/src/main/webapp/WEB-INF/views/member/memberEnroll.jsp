<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="" name="pageTitle"/></jsp:include>




<style>
#enroll-container{
width:500px; 
margin:0 auto;
text-align:center;
}

/*

*/
div#userId-container{position:relative; padding: 0px; }
div#userId-container .guide{
	display:none; font-size:12px; position:absolute; top:12px; right:10px; 
}
div#userId-container .ok{color:green;}
div#userId-container .error{color:red;}


</style>

<div id="enroll-container">
	<form action="${rootPath}/member/memberEnrollEnd.do" method="POST">
		<div id="userId-container">
			<input type="text" class="form-control" name="userId" id="userId_" placeholder="아이디" required />
			<span class="guide ok">이 아이디는 사용 가능합니다.</span>
			<span class="guide error">이 아이디는 사용이 불가능 합니다.</span>
			<input type="hidden" id="idDuplicateCheck" value="0" />
		</div>
		<br />
		<input type="password" class="form-control" name="password" id="password_" placeholder="패스워드" required />
		<br />
		<input type="password" class="form-control" name="" id="password2" placeholder="패스워드확인"  required/>
		<br />
      	<input type="text" class="form-control" name="userName" id="userName" placeholder="이름" required/>
		<br />
		<input type="number" class="form-control" name="age" id="userAge" placeholder="나이" />
		<br />
		<input type="email" class="form-control" name="email" id="email" placeholder="이메일"  />
		<br />
		<input type="text" class="form-control" name="phone" id="phone" maxlength ="11" placeholder="전화번호" required />
		<br />
		<input type="text" class="form-control" name="address" id="address" placeholder="주소" />
		<br />
		<select name="gender" id="gender" class="form-control" required>
			<option value="" disabled selected>성별</option>
			<option value="M" selected>남</option>
			<option value="F">여</option>
		</select>	
		<div class="form-check-inline form-check">
			취미 : &nbsp;
			<!-- (input:checkbox#hobby$+label[for=hobby$])*5 -->
			<input type="checkbox" value="운동" class="form-check-input" name="hobby" id="hobby1" />
			<label for="hobby1" class="form-check-label" >운동</label>
			&nbsp;
			<input type="checkbox" value="등산" class="form-check-input" name="hobby" id="hobby2" />
			<label for="hobby2" class="form-check-label" >등산</label>
			&nbsp;
			<input type="checkbox" value="독서" class="form-check-input" name="hobby" id="hobby3" />
			<label for="hobby3" class="form-check-label" >독서</label>
			&nbsp;
			<input type="checkbox" value="게임" class="form-check-input" name="hobby" id="hobby4" />
			<label for="hobby4" class="form-check-label" >게임</label>
			&nbsp;
			<input type="checkbox" value="여행" class="form-check-input" name="hobby" id="hobby5" />
			<label for="hobby5" class="form-check-label" >여행</label>
		</div>
		<br />
		<input type="submit" value="가입" class="btn btn-outline-success" />
	
	</form>
</div>


<script>
/* $(function(){
	$("#password2").blur(function(){
		var p1 = $("#passowrd_").val();
		var p2 = $(this).val();
		if(p1 != p2) 
			alert("패스워드가 일치하지 않습니다.");
		$("#password_").focus();
		
	})
	
});

function validate(){
	var userId = $("#userId_");
	if(userId.val().trim().length<4){
		alert("아이디는 최소 4자리 이상이어야 합니다.");
		userId.focus();
		return false;
		
	}
	return true;
} */
</script>
<script>

$("#userId_").on("keyup",function(){
    var userId = $(this).val().trim();
    if(userId.length<4) return;
    
    $.ajax({
       url: "checkIdDuplicate.do",
       data: {userId: userId},
       dataType : "json",
       success: function(data){
          console.log(data);//true혹은 false값으로 받을 것임.
          console.log(data.isUsable);//true혹은 false값으로 받을 것임.
          if(data.isUsable==true){
             $(".guide.error").hide();
             $(".guide.ok").show();
             $("#idDuplicateCheck").val(1);
          }else{
             $(".guide.error").show();
             $(".guide.ok").hide();
             $("#idDuplicateCheck").val(0);
          }
       },
       error: function(jqxhr, textStatus, errorThrown){
          console.log("ajax실패",jqxhr, textStatus, errorThrown);
       }
       
    });
 });
 
 
 


</script>
<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
