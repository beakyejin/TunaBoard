<%@page import="dbpkg.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<MemberVO> list = (List<MemberVO>)request.getAttribute("list"); %>
<!DOCTYPE html>
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
						<% if(list != null && list.size() >0){ 
								for(MemberVO vo : list){%>
						<tr>
							<td><%=vo.getCustno() %></td>
							<td><%=vo.getCustname() %></td>
							<td><%=vo.getGrade() %></td>
							<td><%=vo.getPrice() %></td>
						</tr>
						<% }
						}%>
					</table>
				</div>
			</div>
		</section>
	<jsp:include page="footer.jsp" />
</body>
</html>