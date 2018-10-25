<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="common.css">
<title>회원 등록 화면</title>
</head>
<script type="text/javascript">
	function clkSumit() {
		var frm = document.frm;
		
		if(frm.custname.value == ""){
			alert("회원성명이 입력되지 않았습니다.");
			return false;
		}else if(frm.phone.value == ""){
			alert("회원전화가 입력되지 않았습니다.");
			return false;
		}else if(frm.address.value == ""){
			alert("회원주소가 입력되지 않았습니다.");
			return false;
		}else if(frm.joindate.value == ""){
			alert("가입일자가 입력되지 않았습니다.");
			return false;
		}else if(frm.grade.value == ""){
			alert("고객등급이 입력되지 않았습니다.");
			return false;
		}else if(frm.city.value == ""){
			alert("도시코드가 입력되지 않았습니다.");
			return false;
		}
		return true;
		
	}
</script>
<body>
	<jsp:include page="header.jsp" />
		<section>
			<div class="member">
				<form name="frm" action="memberJoin" method="post" onsubmit="return clkSumit();">
					<h3>홈쇼핑 회원 등록</h3>
					<div class="tbl_join">
						<table>
							<tr>
								<th>회원번호(자동발생)</th>
								<td><input type="text" name="custno" value="${vo.custno}" readonly></td>
							</tr>
							<tr>
								<th>회원성명</th>
								<td><input type="text" name="custname" value="테스트"></td>
							</tr>
							<tr>
								<th>회원전화</th>
								<td><input type="text" name="phone" value="010-3333-4444"></td>
							</tr>
							<tr>
								<th>회원주소</th>
								<td><input type="text" name="address" value="대구시 달서구"></td>
							</tr>
							<tr>
								<th>가입일자</th>
								<td><input type="text" name="joindate" value="${vo.joindate}"></td>
							</tr>
							<tr>
								<th>고객등급[A:VIP,B:일반,C:직원]</th>
								<td><input type="text" name="grade" value="A"></td>
							</tr>
							<tr>
								<th>도시코드</th>
								<td><input type="text" name="city" value="60"></td>
							</tr>
							<tr>
								<th colspan="2">
									<input type="submit" value="등록">
									<input type="button" value="조회" onclick="location.href='memberFind'">
								</th>
							<tr>
						</table>
					</div>
				</form>
			</div>
		</section>
	<jsp:include page="footer.jsp" />
</body>
</html>