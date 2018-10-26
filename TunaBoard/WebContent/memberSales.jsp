<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="common.css">
<title>회원 매출조회 화면</title>
</head>
<body>
	<jsp:include page="header.jsp" />
		<section>
			<div class="member">
				<h3>회원 매출 조회</h3>
				<div class="tbl_sales">
					<table>
						<tr>
							<th>회원번호</th>
							<th>회원성명</th>
							<th>고객등급</th>
							<th>매출</th>
						</tr>
						<tr>
						</tr>
					</table>
				</div>
			</div>
		</section>
	<jsp:include page="footer.jsp" />
</body>
</html>