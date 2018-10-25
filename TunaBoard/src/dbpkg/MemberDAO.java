package dbpkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
	public static MemberVO getMaxCustNo(){
		System.out.println("getMaxCustNo [Start]");
		
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
		System.out.println("getMaxCustNo [End]");
		
		return vo;
	}

	public static void insertMember(MemberVO vo) {
		System.out.println("insertMember [Start]");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO MEMBER_TBL_02 " 
				+ " (CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, GRADE, CITY) "
				+ " VALUES "
				+ " ( (SELECT NVL(MAX(CUSTNO), 100000)+1 from MEMBER_TBL_02) "
				+ " , ?, ?, ?, "
				+ " ( select to_char(sysdate, 'yyyymmdd') from dual )"
				+ " , ?, ?) ";
		
		try {
			con = DBconn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getCustname());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddress());
			ps.setString(4, vo.getGrade());
			ps.setString(5, vo.getCity());
			
			ps.executeQuery();
			
		} catch (Exception e) {
			System.out.println("insertMember 에러");
			e.printStackTrace();
		}finally{
			DBconn.close(con, ps, null);
		}
		
		System.out.println("insertMember [End]");
	}
}
