package com.ezen.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.ticket.dto.AdminVO;
import com.ezen.ticket.dto.ContentVO;
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
				System.out.println("@@@@@@@@@@@@@ " + tablename);
				System.out.println("!!!!!!!!!!!!! " + fieldname);
				String sql =" select count(*) cnt from ( "
						+ " select * from ( "
						+ " select rownum as rn, c.* from ( "
						+ "  (select cseq, title, artist, locationName, category, bestyn from product_all_content_view where title like '%'||?||'%'"
						+ " group by cseq, title, artist, locationName, category, bestyn order by cseq desc) c ))) ";
//				String sql ="select count(*) as cnt from " + tablename 
//						+ " where " +  fieldname  + " like '%'||?||'%' ";
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
		
		
		// 상품 리스트
				public ArrayList<ContentVO> adminProductList(Paging paging, String key) {
					ArrayList<ContentVO> list = new ArrayList<ContentVO>();
					con = Dbman.getConnection();
					String sql =" select * from ( "
							+ " select * from ( "
							+ " select rownum as rn, c.* from ( "
							+ "  (select cseq, title, artist, locationName, category, bestyn from product_all_content_view where title like '%'||?||'%'"
							+ " group by cseq, title, artist, locationName, category, bestyn order by cseq desc) c ) "
							+ " ) where rn >= ?"
							+ " ) where rn <= ?";
					System.out.println("@@@@@  sql ==== " + sql);
					try {
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, key);
						pstmt.setInt(2, paging.getStartNum());
						pstmt.setInt(3, paging.getEndNum());
						rs = pstmt.executeQuery();
						while(rs.next()) {
							ContentVO cvo = new ContentVO();
							cvo.setCseq(rs.getInt("cseq"));
							cvo.setTitle(rs.getString("title"));
							cvo.setArtist(rs.getString("artist"));
							cvo.setLocationName(rs.getString("locationName"));
							//System.out.println(rs.getString("locationName")); 대문자였음
							cvo.setCategory(rs.getInt("category"));
							cvo.setBestyn(rs.getString("bestyn").charAt(0));
							list.add(cvo);
						}
						
					} catch (SQLException e) {e.printStackTrace();
					} finally {Dbman.close(con, pstmt, rs);
					}
					return list;
				}
		
//		// 상품 리스트
//		public ArrayList<ContentVO> adminProductList2(Paging paging, String key) {
//			ArrayList<ContentVO> list = new ArrayList<ContentVO>();
//			con = Dbman.getConnection();
//			String sql ="select * from ( "
//					+ " select * from ( "
//					+ " select rownum as rn, c.* from ( "
//					+ "  (select * from content where title like '%'||?||'%' order by cseq desc) c ) "
//					+ " ) where rn >= ? "
//					+ " ) where rn <= ? ";
//			
//			try {
//				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, key);
//				pstmt.setInt(2, paging.getStartNum());
//				pstmt.setInt(3, paging.getEndNum());
//				rs = pstmt.executeQuery();
//				while(rs.next()) {
//					ContentVO cvo = new ContentVO();
//					cvo.setCategory(rs.getInt("category"));
//					cvo.setBestyn(rs.getString("bestyn").charAt(0));
//					list.add(cvo);
//				}
//				
//			} catch (SQLException e) {e.printStackTrace();
//			} finally {Dbman.close(con, pstmt, rs);
//			}
//			return list;
//		}
		public ContentVO getAdminContent(int cseq) {
			
			ContentVO cvo=new ContentVO();
			con=Dbman.getConnection();
			String sql="select * from content where cseq=?";
				try {
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, cseq);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						cvo=new ContentVO();
						cvo.setCseq(rs.getInt("cseq")); // adminproductDetail 에서 보기편하게 순서조정
						cvo.setTitle(rs.getString("title"));
						cvo.setArtist(rs.getString("artist"));
						cvo.setImage(rs.getString("image"));
						cvo.setCategory(rs.getInt("category"));
						cvo.setLocationNum(rs.getInt("locationNum")); // adminproductDetail에서는 사용하지 않음
						cvo.setContent(rs.getString("content"));
						cvo.setAge(rs.getString("age"));
						cvo.setBestyn(rs.getString("bestyn").charAt(0));
						cvo.settDateTime(rs.getString("tDateTime"));
						
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return cvo;
		}
		public ArrayList<ContentVO> getAdminContentLoc(int cseq) {
			ArrayList<ContentVO> list=new ArrayList<ContentVO>();
			ContentVO cvo=null;
			con=Dbman.getConnection();
			String sql="select * from content_loc_seat_view where cseq=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, cseq);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					cvo=new ContentVO();
					cvo.setLocationName(rs.getString("locationName")); // adminProductDetail 에서도 사용
					System.out.println( "공연장소 뱉어어어어어어억!!!!" +   rs.getString("locationName"));
					cvo.setArea(rs.getString("area"));
					cvo.setPrice(rs.getInt("price"));
					cvo.setAreaImage(rs.getString("areaImage"));
					list.add(cvo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {Dbman.close(con, pstmt, rs);}
			return list;
		}
		
		public ArrayList<ContentVO> getAdminContentTime(int cseq) {
			ArrayList<ContentVO> list=new ArrayList<ContentVO>();
			ContentVO cvo=null;
			con=Dbman.getConnection();
			String sql="select * from content_time_view where cseq=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, cseq);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					cvo=new ContentVO();
					cvo.setContentDate(rs.getTimestamp("contentDate"));
					cvo.setContentTime(rs.getString("contentTime"));
					list.add(cvo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {Dbman.close(con, pstmt, rs);}
			return list;
		}
		public ContentVO getAdminProduct(int cseq) {
			
			
			
			
			return null;
		}
		
		public int adminDeleteContent(int cseq) {
			int result=0;
			con=Dbman.getConnection();
			String sql="delete from content where cseq=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, cseq);
				result=pstmt.executeUpdate();
				
				sql="delete from contentTime where cseq=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, cseq);
				result=pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {Dbman.close(con, pstmt, rs);}
			return result;
		}
		
		public void updateContent(ContentVO cvo) {
			
			String sql ="update content set title=?, content=?, image=? where cseq=?";
			con = Dbman.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, cvo.getTitle());
				pstmt.setString(2, cvo.getContent());
				pstmt.setString(3, cvo.getImage());
				pstmt.setInt(4, cvo.getCseq());
				pstmt.executeUpdate();

			} catch (SQLException e) {e.printStackTrace();
			} finally { Dbman.close(con, pstmt, rs);
			}
			
			
		}
		
		
		
}