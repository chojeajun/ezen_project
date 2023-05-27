package com.ezen.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.ticket.dto.AddressVO;
import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.dto.ReviewVO;
import com.ezen.ticket.util.Dbman;



public class MemberDao {

private MemberDao() {}
	
	private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public MemberVO getMember(String id) {
		MemberVO mvo = null;
		String sql ="select * from member where id = ?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next() ) {
				mvo = new MemberVO();
				mvo.setMseq(rs.getInt("mseq"));
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));   
				mvo.setName(rs.getString("name"));
				mvo.setNickname(rs.getString("nickname"));
				mvo.setEmail(rs.getString("email"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setZip_num(rs.getString("zip_num"));
				mvo.setAddress1(rs.getString("address1"));
				mvo.setBirth(rs.getString("birth"));
				mvo.setAddress2(rs.getString("address2"));
				mvo.setGrade(rs.getString("grade"));
				mvo.setSuccess(rs.getInt("success"));
				mvo.setIndate(rs.getTimestamp("indate"));
				mvo.setUseyn(rs.getString("useyn").charAt(0));
				mvo.setGender(rs.getInt("gender"));
			} 
			
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);
		}
		
		return mvo;
	}

	public ArrayList<AddressVO> selectAddress(String dong) {
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		String sql ="select * from address where dong like '%'||?||'%' ";
		con = Dbman.getConnection();
		// 주소검색 address 테이블에서 dong 이라는 패턴이 포함된 주소 를 검색
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			
			while(rs.next() ) {
				AddressVO avo = new AddressVO();
				avo.setZip_num(rs.getString("zip_num"));
				avo.setSido(rs.getString("sido"));
				avo.setGugun(rs.getString("gugun"));
				avo.setDong(rs.getString("dong"));
				avo.setBunji(rs.getString("bunji"));
				avo.setZip_code(rs.getString("zip_code"));
				list.add(avo);
			} 
		} catch (SQLException e) { e.printStackTrace();
		}finally {Dbman.close(con, pstmt, rs);
		}
		return list;
	}

	public int insertMember(MemberVO mvo) {
		int result = 0;
		
		String sql ="insert into member(mseq ,id, pwd, name,  nickname,  gender, email, phone, birth, zip_num, address1, address2) "
				+ "values(member_mseq.nextVal, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mvo.getId());
			pstmt.setString(2, mvo.getPwd());
			pstmt.setString(3, mvo.getName());
			pstmt.setString(4, mvo.getNickname());
			pstmt.setInt(5, mvo.getGender());
			pstmt.setString(6, mvo.getEmail());
			pstmt.setString(7, mvo.getPhone());
			pstmt.setString(8, mvo.getBirth());
			pstmt.setString(9, mvo.getZip_num());
			pstmt.setString(10, mvo.getAddress1());
			pstmt.setString(11, mvo.getAddress2());
			result = pstmt.executeUpdate();

		} catch (SQLException e) { e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);
		}
		return result;
	}

	public int updateMember(MemberVO mvo) {
		int result = 0;
		con = Dbman.getConnection();
		String sql ="update member set pwd=?, name=?, nickname=?, email=?, phone=?, birth=?, zip_num=?, address1=? , address2=? where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mvo.getPwd());
			pstmt.setString(2, mvo.getName());
			pstmt.setString(3, mvo.getNickname());
			pstmt.setString(4, mvo.getEmail());
			pstmt.setString(5, mvo.getPhone());
			pstmt.setString(6, mvo.getBirth());
			pstmt.setString(7, mvo.getZip_num());
			pstmt.setString(8, mvo.getAddress1());
			pstmt.setString(9, mvo.getAddress2());
			pstmt.setString(10, mvo.getId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);
		}
		return result;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
