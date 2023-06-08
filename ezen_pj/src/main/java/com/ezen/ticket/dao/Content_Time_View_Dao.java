package com.ezen.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.ticket.dto.Content_Time_View_VO;
import com.ezen.ticket.util.Dbman;

public class Content_Time_View_Dao {
	private Content_Time_View_Dao() {}
	private static Content_Time_View_Dao itc = new Content_Time_View_Dao();
	public static Content_Time_View_Dao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public ArrayList<Content_Time_View_VO> hoonSelectCartDetail(int cseq) {
		ArrayList<Content_Time_View_VO> list = new ArrayList<Content_Time_View_VO>();
		Content_Time_View_VO ctvv = null;
		String sql = "select * from content_time_view where cseq=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ctvv = new Content_Time_View_VO();
				ctvv.setCseq(rs.getInt("cseq"));
				ctvv.setTitle(rs.getString("title"));
				ctvv.setContentdate(rs.getString("contentdate").substring(0,10));
				ctvv.setContenttime(rs.getString("contenttime"));
				list.add(ctvv);
			}
		} catch (SQLException e) { e.printStackTrace();
		}
		return list;
	}
	
}
