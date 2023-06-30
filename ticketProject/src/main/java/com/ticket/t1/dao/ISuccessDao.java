package com.ticket.t1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISuccessDao {
	
	void getSuccessListBySucseq(HashMap<String, Object> paramMap);

	void readCountOne(HashMap<String, Object> paramMap);

	void getReplyList(HashMap<String, Object> paramMap);

	void getReplyMember(HashMap<String, Object> paramMap);

	void replyDelete(int srseq);
	
}
