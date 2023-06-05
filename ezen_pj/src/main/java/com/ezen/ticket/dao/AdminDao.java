package com.ezen.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.ticket.dto.AdminVO;
import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.util.Dbman;
import com.ezen.ticket.util.Paging;

public class AdminDao {

	private AdminDao() {
	};

	private static AdminDao itc = new AdminDao();

	public static AdminDao getInstance() {
		return itc;
	}

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public AdminVO workerCheck(String workId) {
		AdminVO avo = new AdminVO();
		String sql = "select * from admin where id=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, workId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				avo.setId(rs.getString("id"));
				avo.setPwd(rs.getString("pwd"));
				avo.setName(rs.getString("name"));
				avo.setPhone(rs.getString("phone"));
				avo.setEmail(rs.getString("email"));
				avo.setAdminyn(rs.getString("adminyn").charAt(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}

		return avo;
	}
	// 일반 회원의 전체 리스트를 갯수를 조회
	// 회원리스트 
		public int getAllCount(String tablename, String fieldname, String key) {
			int count = 0;
			String sql ="select count(*) as cnt from " + tablename 
					+ " where " +  fieldname  + " like '%'||?||'%' ";
			con = Dbman.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);
				rs = pstmt.executeQuery();
				if(rs.next() ) {
					count = rs.getInt("cnt");
				}
				
			} catch (SQLException e) {e.printStackTrace();
			} finally {	Dbman.close(con, pstmt, rs);
			}
			return count;
		}

	
	// 일반 회원의 전체 리스트를 불러서 리스트로 리턴
		// 회원리스트
		public ArrayList<MemberVO> selectMember(Paging paging, String key) {
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			String sql ="select * from ( "
					+ " select * from ( "
					+ " select rownum as rn, m.* from ( "
					+ "  (select * from member where name like '%'||?||'%' order by indate desc) m ) "
					+ " ) where rn >= ? "
					+ " ) where rn <= ? ";
			con = Dbman.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);
				pstmt.setInt(2, paging.getStartNum());
				pstmt.setInt(3, paging.getEndNum());
				rs = pstmt.executeQuery();
				while(rs.next() ) {
					MemberVO mvo = new MemberVO();
					mvo.setId(rs.getString("id"));
					mvo.setPwd(rs.getString("pwd"));
					mvo.setName(rs.getString("name"));
					mvo.setNickname(rs.getString("nickname"));
					mvo.setGender(rs.getInt("gender"));
					mvo.setEmail(rs.getString("email"));
					mvo.setZip_num(rs.getString("zip_num"));
					mvo.setAddress1(rs.getString("address1"));
					mvo.setPhone(rs.getString("phone"));
					mvo.setUseyn(rs.getString("useyn").charAt(0));
					mvo.setIndate(rs.getTimestamp("indate"));
					list.add(mvo);
				} 
			} catch (SQLException e) {e.printStackTrace();
			} finally {Dbman.close(con, pstmt, rs);}
			return list;
		}
}