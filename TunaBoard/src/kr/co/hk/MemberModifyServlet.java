package kr.co.hk;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbpkg.MemberDAO;
import dbpkg.MemberVO;

@WebServlet("/memberModify")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int custno = Integer.parseInt(request.getParameter("custno"));
		MemberVO vo = MemberDAO.getMember(custno);
		request.setAttribute("vo", vo);
		Utils.dispatcher("memberModify", request, response);
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
		
		/*System.out.printf("%d %s %s %s %s %s %s\n"
				, vo.getCustno(), vo.getCustname(), vo.getPhone(), 
				vo.getAddress(), vo.getJoindate(), vo.getGrade(), vo.getCity());*/
		
		MemberDAO.updateMember(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("msg", "회원 정보 수정이 완료되었습니다!");
		Utils.dispatcher("memberModify", request, response);
	}

}
