<%@page import="dbpkg.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="common.css">
<title>회원 조회 화면</title>
</head>
<body>
	<jsp:include page="header.jsp" />
		<section>
			<div class="member">
				<h3>회원 목록 조회/수정</h3>
				<div class="tbl_find">
					<table>
						<tr>
							<th>회원번호</th>
							<th>회원성명</th>
							<th>전화번호</th>
							<th>주소</th>
							<th>가입일자</th>
							<th>고객등급</th>
							<th>거주지역</th>
						</tr>
						<% if(list !=null && list.size() >0 ){
							for(MemberVO a : list){ %>
						<tr>
							<td>
								<a href="memberModify?custno=<%= a.getCustno() %>">
								<%= a.getCustno() %></a>
							</td>
							<td><%= a.getCustname() %></td>
							<td><%= a.getPhone() %></td>
							<td><%= a.getAddress() %></td>
							<td><%= a.getJoindate() %></td>
							<td><%= a.getGrade() %></td>
							<td><%= a.getCity() %></td>
						</tr>
						<%	}
						} %>
					</table>
				</div>
			</div>
		</section>
	<jsp:include page="footer.jsp" />
</body>
</html>