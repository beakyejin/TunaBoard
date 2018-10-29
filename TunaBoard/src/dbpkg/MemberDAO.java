package dbpkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	public static MemberVO getMaxCustNo(){
		System.out.println("----getMaxCustNo [Start]----");
		
		MemberVO vo = new MemberVO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT NVL(MAX(CUSTNO), 100000)+1 "
				+ " , TO_CHAR(SYSDATE, 'YYYYMMDD') "
				+ " FROM  MEMBER_TBL_02 ";
		
		try{
			con = DBconn.getConnection();
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				int maxCustNO = rs.getInt(1);
				String toDay = rs.getString(2);
				
				System.out.println("maxCustNO: " + maxCustNO);
				System.out.println("toDay: " + toDay);
				
				vo.setCustno(maxCustNO);
				vo.setJoindate(toDay);
			}
			
		}catch(Exception e){
			System.out.println("getMaxCustNo 에러");
			e.printStackTrace();
		}finally{
			DBconn.close(con, ps, rs);
		}
		System.out.println("----getMaxCustNo [End]----");
		
		return vo;
	}

	public static void insertMember(MemberVO vo) {
		System.out.println("----insertMember [Start]----");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO MEMBER_TBL_02 " 
				+ " (CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, GRADE, CITY) "
				+ " VALUES "
				+ " ( ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			con = DBconn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getCustno());
			ps.setString(2, vo.getCustname());
			ps.setString(3, vo.getPhone());
			ps.setString(4, vo.getAddress());
			ps.setString(5, vo.getJoindate());
			ps.setString(6, vo.getGrade());
			ps.setString(7, vo.getCity());
			
			ps.executeQuery();
			
		} catch (Exception e) {
			System.out.println("insertMember 에러");
			e.printStackTrace();
		} finally{
			DBconn.close(con, ps, null);
		}
		
		System.out.println("----insertMember [End]----");
	}

	public static ArrayList<MemberVO> getMemberList() {
		System.out.println("----getMemberList [Start]----");
		
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT "
				+ " CUSTNO, CUSTNAME, PHONE, ADDRESS, "
				+ " TO_CHAR(JOINDATE,'YYYY-MM-DD') AS JOINDATE "
				+ " , decode( grade, 'A', 'VIP' "
				+ "                , 'B', '일반' "
				+ "                , 'C', '직원') " 
                +" as grade"
				+ ", CITY "
				+ " FROM MEMBER_TBL_02 ";
		
		try {
			con = DBconn.getConnection();
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				MemberVO vo = new MemberVO();
				vo.setCustno(rs.getInt("CUSTNO"));
				vo.setCustname(rs.getString("CUSTNAME"));
				vo.setPhone(rs.getString("PHONE"));
				vo.setAddress(rs.getString("ADDRESS"));
				vo.setJoindate(rs.getString("JOINDATE"));
				
				/*고객 등급
				 * String grade = rs.getString("grade");
				if(grade.equals("A")){
					grade="VIP";
				}else if(grade.equals("B")){
					grade="일반";
				}else if(grade.equals("C")){
					grade="직원";
				}
				vo.setGrade(grade);*/
				
				vo.setGrade(rs.getString("GRADE"));
				vo.setCity(rs.getString("CITY"));
				list.add(vo);
			}
			
			for(MemberVO a : list){
				System.out.printf("%d %s %s %s %s %s %s\n"
						, a.getCustno(), a.getCustname(), a.getPhone(), a.getAddress()
						, a.getJoindate(), a.getGrade(), a.getCity());
			}
			
		} catch (Exception e) {
			System.out.println("getMemberList 에러");
			e.printStackTrace();
		} finally{
			DBconn.close(con, ps, rs);
		}
		
		System.out.println("----getMemberList [End]----");
		return list;
	}

	public static MemberVO getMember(int custno) {
		System.out.println("----getMember [Start]----");
		MemberVO vo = new MemberVO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT "
				+ " CUSTNO, CUSTNAME, PHONE, ADDRESS, "
				+ " TO_CHAR(JOINDATE, 'YYYY-MM-DD') AS JOINDATE,"
				+ " GRADE, CITY "
				+ " FROM MEMBER_TBL_02"
				+ " WHERE CUSTNO = ? ";
		
		try {
			con = DBconn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, custno);
			rs = ps.executeQuery();
			
			while(rs.next()){
				vo.setCustno(rs.getInt("CUSTNO"));
				vo.setCustname(rs.getString("CUSTNAME"));
				vo.setPhone(rs.getString("PHONE"));
				vo.setAddress(rs.getString("ADDRESS"));
				vo.setJoindate(rs.getString("JOINDATE"));
				vo.setGrade(rs.getString("GRADE"));
				vo.setCity(rs.getString("CITY"));
				
				System.out.printf("%d %s %s %s %s %s %s\n"
							, vo.getCustno(), vo.getCustname(), vo.getPhone(), 
							vo.getAddress(), vo.getJoindate(), vo.getGrade(), vo.getCity());
				
			}
			
			
		} catch (Exception e) {
			System.out.println("getMember 에러");
			e.printStackTrace();
		} finally {
			DBconn.close(con, ps, rs);
		}
		
		System.out.println("----getMember [End]----");
		return vo;
	}

	public static void updateMember(MemberVO vo) {
		System.out.println("----updateMember [Start]----");
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update member_tbl_02 "
				+ " set custname=?, phone=?, address=?, grade=?, city=? " 
				+ " where custno=? ";
		
		try {
			con = DBconn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getCustname());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddress());
			ps.setString(4, vo.getGrade());
			ps.setString(5, vo.getCity());
			ps.setInt(6, vo.getCustno());
			
			ps.executeQuery();
			
		} catch (Exception e) {
			System.out.println("updateMember 에러");
			e.printStackTrace();
		} finally{
			DBconn.close(con, ps, null);
		}
		System.out.println("----updateMember [End]----");
	}

	public static List<MemberVO> getSales() {
		System.out.println("----getSales [Start]----");
		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select a.custno as custno "
					+" , a.custname as custname "
					+" , decode(a.grade, 'A', 'VIP' " 
					+" , 'B', '일반' "
					+" , 'C', '직원') as grade "
					+" , nvl(sum(b.price), 0) as price "
					+" from member_tbl_02 a "
					+" join money_tbl_02 b "
					+" on a.custno = b.custno "
					+" group by a.custno, a.custname, a.grade "
					+" order by price desc ";
		
		try {
			con = DBconn.getConnection();
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				MemberVO vo = new MemberVO();
				vo.setCustno(rs.getInt("CUSTNO"));
				vo.setCustname(rs.getString("CUSTNAME"));
				vo.setGrade(rs.getString("GRADE"));
				vo.setPrice(rs.getString("PRICE"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("getSales 에러");
			e.printStackTrace();
		} finally {
			DBconn.close(con, ps, rs);
		}
		
		System.out.println("----getSales [End]----");
		
		return list;
	}
}
