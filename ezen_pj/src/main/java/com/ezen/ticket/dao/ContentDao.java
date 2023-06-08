package com.ezen.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.ticket.dto.ContentVO;
import com.ezen.ticket.util.Dbman;

public class ContentDao {

	private ContentDao() {
	};

	private static ContentDao itc = new ContentDao();

	public static ContentDao getInstance() {
		return itc;
	}

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ArrayList<ContentVO> getBestContent() {

		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		String sql = "select * from content where bestyn=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "Y");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ContentVO cvo = new ContentVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setTitle(rs.getString("title"));
				cvo.setLocationNum(rs.getInt("locationnum"));
				cvo.setArtist(rs.getString("artist"));
				cvo.setImage(rs.getString("image"));
				cvo.setContent(rs.getString("content"));
				cvo.setCategory(rs.getInt("category"));
				cvo.setAge(rs.getString("age"));
				cvo.setBestyn(rs.getString("bestyn").charAt(0));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return list;
	}

	public ArrayList<ContentVO> selectContent(int category) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		con = Dbman.getConnection();
		String sql;
		ContentVO cvo = null;
		try {
			if (category == 0) {
				sql = "select * from content";
				pstmt = con.prepareStatement(sql);
			} else {
				sql = "select * from content where category=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, category);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setCategory(rs.getInt("category"));
				cvo.setTitle(rs.getString("title"));
				cvo.setArtist(rs.getString("artist"));
				cvo.setLocationNum(rs.getInt("locationNum"));
				cvo.setContent(rs.getString("content"));
				cvo.setImage(rs.getString("image"));
				cvo.setAge(rs.getString("age"));
				cvo.setBestyn(rs.getString("bestyn").charAt(0));
				list.add(cvo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}

		return list;
	}

	public ArrayList<ContentVO> selectContentsByCategory(int category) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		ContentVO cvo = null;
		con = Dbman.getConnection();
		String sql = "select cseq, category, title, image, locationnum from content where category=?";
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, category);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setTitle(rs.getString("title"));
				cvo.setCategory(rs.getInt("category"));
				cvo.setImage(rs.getString("image"));
				cvo.setLocationNum(rs.getInt("locationnum"));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return list;
	}

	public ArrayList<ContentVO> selectFromContentByTitle(int cseq) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		ContentVO cvo = null;
		con = Dbman.getConnection();
		String sql = "select * from content where cseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setCategory(rs.getInt("category"));
				cvo.setTitle(rs.getString("title"));
				cvo.setArtist(rs.getString("artist"));
				cvo.setLocationNum(rs.getInt("locationNum"));
				System.out.println("제대로 말해라 " + rs.getInt("locationNum"));
				cvo.setContent(rs.getString("content"));
				cvo.setImage(rs.getString("image"));
				cvo.setAge(rs.getString("age"));
				cvo.setBestyn(rs.getString("bestyn").charAt(0));
				cvo.settDateTime(rs.getString("tDateTime"));
				list.add(cvo);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ContentVO> selectFromContentTimeByTitle(int cseq) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		ContentVO cvo = null;
		con = Dbman.getConnection();
		String sql = "select * from content where cseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(cseq);
				cvo.setCategory(rs.getInt("category"));
				cvo.setLocationNum(rs.getInt("locationNum"));
				System.out.println("cseq=" + cvo.getCseq());
				System.out.println("category=" + cvo.getCategory());
				System.out.println("locationNum=" + cvo.getLocationNum());
				list.add(cvo);
			}
			sql = "select distinct contentDate, cseq from contentTime where cseq=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(cseq);
				cvo.setContentDate(rs.getTimestamp("contentDate"));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ContentVO> selectFromLocationViewByTitle(int cseq) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		ContentVO cvo = null;
		con = Dbman.getConnection();
		String sql = "select * from content_loc_seat_view where cseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new ContentVO();
				cvo.setLocationName(rs.getString("locationName"));
				cvo.setAreaImage(rs.getString("areaImage"));
				list.add(cvo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ContentVO> selectFromContentAreaByTitle(int locationNum) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		ContentVO cvo = null;
		con = Dbman.getConnection();
		String sql = "select * from seat where locationNum=? order by price desc";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, locationNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cvo = new ContentVO();
				cvo.setArea(rs.getString("area"));
				cvo.setPrice(rs.getInt("price"));
				System.out.println("area" + rs.getString("area"));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ContentVO> selectTimeByDate(int cseq, String contentDate) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		con = Dbman.getConnection();
		String sql = "select distinct contentTime from  content_time_view where cseq=? and contentDate=to_date(?,'yyyy-mm-dd') order by contentTime";
		ContentVO cvo = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.setString(2, contentDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cvo = new ContentVO();
				cvo.setContentTime(rs.getString("contentTime"));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}

		return list;
	}

	public ArrayList<ContentVO> selectAll() {
		ArrayList<ContentVO> content = new ArrayList<ContentVO>();
		ContentVO cvo = null;
		con = Dbman.getConnection();
		String sql = "select * from content order by category asc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setTitle(rs.getString("title"));
				cvo.setLocationNum(rs.getInt("locationnum"));
				cvo.setArtist(rs.getString("artist"));
				cvo.setImage(rs.getString("image"));
				cvo.setContent(rs.getString("content"));
				cvo.setCategory(rs.getInt("category"));
				cvo.setAge(rs.getString("age"));
				cvo.setBestyn(rs.getString("bestyn").charAt(0));
				content.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return content;
	}

	public ArrayList<ContentVO> selectAreaPrice(int cseq, String area) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		con = Dbman.getConnection();
		ContentVO cvo = null;
		String sql = "select cseq, locationName, area,price, areaImage from content_loc_seat_view where cseq=? and area=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.setString(2, area);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setLocationName(rs.getString("locationName"));
				cvo.setArea(rs.getString("area"));
				cvo.setPrice(rs.getInt("price"));
				cvo.setAreaImage(rs.getString("areaImage"));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}

		return list;
	}

	public ArrayList<ContentVO> selectContentByCseq(int cseq) {
		ArrayList<ContentVO> content = new ArrayList<ContentVO>();
		ContentVO cvo = null;
		String sql = "select * from content where cseq = ?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setTitle(rs.getString("title"));
				cvo.setLocationNum(rs.getInt("locationnum"));
				cvo.setArtist(rs.getString("artist"));
				cvo.setImage(rs.getString("image"));
				cvo.setContent(rs.getString("content"));
				cvo.setCategory(rs.getInt("category"));
				cvo.setAge(rs.getString("age"));
				cvo.setBestyn(rs.getString("bestyn").charAt(0));
				cvo.settDateTime(rs.getString("tdatetime"));
				content.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return content;
	}

	public ArrayList<ContentVO> contentSearch(String key) {
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		ContentVO cvo = null;
		String sql = "select * from content where title like '%" + key + "%'";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new ContentVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setTitle(rs.getString("title"));
				cvo.setLocationNum(rs.getInt("locationnum"));
				cvo.setArtist(rs.getString("artist"));
				cvo.setImage(rs.getString("image"));
				cvo.setContent(rs.getString("content"));
				cvo.setCategory(rs.getInt("category"));
				cvo.setAge(rs.getString("age"));
				cvo.setBestyn(rs.getString("bestyn").charAt(0));
				cvo.settDateTime(rs.getString("tdatetime"));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}

		return list;
	}

}
