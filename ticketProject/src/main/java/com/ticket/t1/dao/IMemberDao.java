package com.ticket.t1.dao;


import java.util.HashMap;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDao {


	void getMember(HashMap<String, Object> paramMap);



	void joinKakao(HashMap<String, Object> paramMap);

	

}
