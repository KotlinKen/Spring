<jsp:include page ="/WEB-INF/views/common/header.jsp"><jsp:param value="" name="pageTitle"/></jsp:include>



<!-- Member [userId=powerman, password=$2a$10$TODK6Z450o9LYBFZ62csUuqmjQbyxR09YsHnjTl.pVpo41pMvfKSe, 
userName=김률민, gender=F, age=0, email=a@nate.com, phone=12341234 , address=addresss, 
hobby=[여행], enrollDate=2018-06-04] -->
<style>
#enroll-container{
width:500px; 
margin:0 auto;
text-align:center;
}
</style>
test subs
<div id="enroll-container">
	<form action="${rootPath}/member/memberUpdate.do" method="POST">
		<input type="text" class="form-control" name="userId" id="userId_" placeholder="아이디" value="${m.userId }" required readonly="readonly"/>
		<br />
      	<input type="text" class="form-control" name="userName" id="userName" placeholder="이름" value="${m.userName }" required/>
		<br />
		<input type="number" class="form-control" name="age" id="userAge" placeholder="나이" value="${m.age }" />
		<br />
		<input type="email" class="form-control" name="email" id="email" placeholder="이메일"  value="${m.email }" />
		<br />
		<input type="text" class="form-control" name="phone" id="phone" maxlength ="11" placeholder="전화번호" value="${m.phone }" required />
		<br />
		<input type="text" class="form-control" name="address" id="address" placeholder="주소" value="${m.address }"/>
		<br />
		<select name="gender" id="gender" class="form-control" required>
			<option value="" disabled selected>성별</option>
			<option value="M" <c:if test="${m.gender eq 'M' }">selected</c:if>  >남</option>
			<option value="F" <c:if test="${m.gender eq 'F' }">selected</c:if> >여</option>
		</select>	
		
		
		
		<div class="form-check-inline form-check">
			취미 : &nbsp;
			<!-- (input:checkbox#hobby$+label[for=hobby$])*5 -->
			<input type="checkbox" value="운동" class="form-check-input" name="hobby" id="hobby1" <c:if test="${fn:contains(m, '운동')}">checked</c:if>/>
			<label for="hobby1" class="form-check-label" >운동</label>
			&nbsp;
			<input type="checkbox" value="등산" class="form-check-input" name="hobby" id="hobby2" <c:if test="${fn:contains(m, '등산')}">checked</c:if>/>
			<label for="hobby2" class="form-check-label" >등산</label>
			&nbsp;
			<input type="checkbox" value="독서" class="form-check-input" name="hobby" id="hobby3" <c:if test="${fn:contains(m, '독서')}">checked</c:if>/>
			<label for="hobby3" class="form-check-label" >독서</label>
			&nbsp;
			<input type="checkbox" value="게임" class="form-check-input" name="hobby" id="hobby4" <c:if test="${fn:contains(m, '게임')}">checked</c:if>/>
			<label for="hobby4" class="form-check-label" >게임</label>
			&nbsp;
			<input type="checkbox" value="여행" class="form-check-input" name="hobby" id="hobby5" <c:if test="${fn:contains(m, '여행')}">checked</c:if> />
			<label for="hobby5" class="form-check-label" >여행</label>
		</div>
		<br />
		<input type="submit" value="가입" class="btn btn-outline-success" />
	
	</form>
</div>







<jsp:include page ="/WEB-INF/views/common/footer.jsp" />
