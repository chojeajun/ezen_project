package com.ezen.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.ticket.dto.CartVO;
import com.ezen.ticket.util.Dbman;

public class CartDao {
	
	private CartDao() {}
	private static CartDao itc = new CartDao();
	public static CartDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public ArrayList<CartVO> getMyCartList(int mseq) {
		ArrayList<CartVO> list = new ArrayList<CartVO>();
		CartVO cartVO = null;
		String sql = "select * from cart where mseq = ?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mseq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cartVO = new CartVO();
				cartVO.setMseq(rs.getInt("mseq"));
				cartVO.setCseq(rs.getInt("cseq"));
				cartVO.setContentDate(rs.getTimestamp("contentdate"));
				cartVO.setContentTime(rs.getString("contenttime"));
				cartVO.setLocationNum(rs.getInt("locationnum"));
				cartVO.setArea(rs.getString("area"));
				cartVO.setQuantity(rs.getInt("quantity"));
				cartVO.setIndate(rs.getTimestamp("indate"));
				cartVO.setBuyyn(rs.getString("buyyn").charAt(0));
				list.add(cartVO);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		
		return list;
	}
	
}
