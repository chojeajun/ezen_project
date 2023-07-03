package com.ticket.t1.dao;


import java.util.HashMap;


import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.MemberVO;

@Mapper
public interface IMemberDao {


	void getMember(HashMap<String, Object> paramMap);
	void joinKakao(HashMap<String, Object> paramMap);
	void insertMember(HashMap<String, Object> paramMap);
	void getMyRegister(HashMap<String, Object> paramMap);
	void updateMember(HashMap<String, Object> paramMap);
	void updateMember2(MemberVO membervo);

	

}
