package com.ezen.ticket.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.dto.RegisterTimeVO;
import com.ezen.ticket.util.Dbman;

public class RegisterTimeDao {
	
	private RegisterTimeDao() {}
	private static RegisterTimeDao itc = new RegisterTimeDao();
	public static RegisterTimeDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
   public ArrayList<RegisterTimeVO> getMyRegister(MemberVO mvo) {
	      
	      ArrayList<RegisterTimeVO> registerTime = new ArrayList<RegisterTimeVO>();
	      RegisterTimeVO rgtVO = null;
	      con = Dbman.getConnection();
	      String sql = "select * from registertime where mseq = ?";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, mvo.getMseq());
         rs = pstmt.executeQuery();
         while(rs.next()) {
            rgtVO = new RegisterTimeVO();
            rgtVO.setRtseq(rs.getInt("rtseq"));
            rgtVO.setMseq(rs.getInt("mseq"));
            rgtVO.setRegisterdate(rs.getString("registerdate"));
            rgtVO.setStarttime(rs.getString("starttime"));
            rgtVO.setEndtime(rs.getString("endtime"));
	            registerTime.add(rgtVO);
	         }
	      } catch (SQLException e) { e.printStackTrace();
	      } finally { Dbman.close(con, pstmt, rs); }
	      return registerTime;
   }

	public void insertRegisterTime(int mseq, String registerDate, String startTime, String endTime) {
		
		con = Dbman.getConnection();
		String sql = "insert into registerTime(rtseq, mseq, registerDate, startTime, endTime) "
				+ "values( registerTime_rtseq.nextVal, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mseq);
			pstmt.setString(2, registerDate);
			pstmt.setString(3, startTime);
			pstmt.setString(4, endTime);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		
	}

	public ArrayList<RegisterTimeVO> getCommissioner(int mseq, String tDate, int tTime) {
		ArrayList<RegisterTimeVO> list=new ArrayList<RegisterTimeVO>();
		//등록한 대리인 정보와 등록 날짜, 시간 불러오는 메소드
		RegisterTimeVO rtvo=null;
		con=Dbman.getConnection();
		String sql="select * from commissioner_view where registerDate=?"; //티켓팅 날짜와 같은 대리인 select
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tDate);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String starttime=rs.getString("starttime").replace(":", ""); //시간 형식 0000으로 바꿈
				String endtime=rs.getString("endtime").replace(":", "");
				int startTime=Integer.parseInt(starttime); //String에서 숫자로 전환해서 비교할거임
				int endTime=Integer.parseInt(endtime);
				System.out.println("startTime: "+startTime);
				System.out.println("endTime: "+endTime);
				
				sql="select * from commissioner_view where registerDate=? and ?<=? and ?<=? and mseq!=? order by success desc";
				//티켓팅 날짜에 시간 되는 대리인 중 시간대도 되는 대리인 고름. 그중에서 본인은 제외함
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, tDate);
				pstmt.setInt(2, startTime);
				pstmt.setInt(3, tTime);
				pstmt.setInt(4, tTime);
				pstmt.setInt(5, endTime);
				pstmt.setInt(6, mseq);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					rtvo=new RegisterTimeVO();
					rtvo.setMseq(rs.getInt("mseq"));
					rtvo.setCid(rs.getString("cid"));
					rtvo.setCnickname(rs.getString("cnickname"));
					rtvo.setGrade(rs.getInt("grade"));
					rtvo.setGname(rs.getString("gname"));
					rtvo.setSuccess(rs.getInt("success"));
					try {
						SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
						SimpleDateFormat sdfReal= new SimpleDateFormat("yyyy-MM-dd");
						Date formatDate;
						formatDate = sdf.parse(rs.getString("registerDate"));
						String registerDate= sdfReal.format(formatDate);
						rtvo.setRegisterdate(registerDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					rtvo.setStarttime(rs.getString("startTime"));
					rtvo.setEndtime(rs.getString("endTime"));
					rtvo.setCom_price(rs.getInt("com_price"));
					list.add(rtvo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {Dbman.close(con, pstmt, rs);}
		
		return list;
	}
	
	
}
