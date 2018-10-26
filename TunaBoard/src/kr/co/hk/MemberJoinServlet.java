package kr.co.hk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbpkg.MemberDAO;
import dbpkg.MemberVO;

@WebServlet("/memberJoin")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO vo = MemberDAO.getMaxCustNo();  //가입날짜와 회원번호 가져오기
		
		request.setAttribute("vo", vo);
		
		Utils.dispatcher("memberJoin", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO();
		vo.setCustno(Integer.parseInt(request.getParameter("custno")));
		vo.setCustname(request.getParameter("custname"));
		vo.setPhone(request.getParameter("phone"));
		vo.setJoindate(request.getParameter("joindate"));
		vo.setAddress(request.getParameter("address"));
		vo.setGrade(request.getParameter("grade"));
		vo.setCity(request.getParameter("city"));
		
		MemberDAO.insertMember(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("msg", "회원가입이 완료되었습니다!");
		Utils.dispatcher("memberJoin", request, response);
		
		/*alert띄우기
		 * response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원등록이 완료되었습니다!');"
				+ " location.href='memberJoin';</script>");
		out.flush();*/
		
	}

}
