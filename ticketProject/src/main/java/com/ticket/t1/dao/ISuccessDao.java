package com.ticket.t1.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.SuccessVO;
import com.ticket.t1.util.Paging;

@Mapper
public interface ISuccessDao {
	
	void getSuccessListBySucseq(HashMap<String, Object> paramMap);

	void readCountOne(HashMap<String, Object> paramMap);

	void getReplyList(HashMap<String, Object> paramMap);

	void getReplyMember(HashMap<String, Object> paramMap);

	void replyDelete(int srseq);

	void insertReply(int seq, String reply, String nickname);

	List<SuccessVO> listSuccess(Paging paging);

	int getAllCount(String string);
	
}
