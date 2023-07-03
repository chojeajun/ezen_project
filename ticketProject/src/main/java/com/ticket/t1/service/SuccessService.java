package com.ticket.t1.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.ISuccessDao;

@Service
public class SuccessService {

	@Autowired
	ISuccessDao sucdao;
	
	public void getSuccessListBySucseq(HashMap<String, Object> paramMap) {
		sucdao.getSuccessListBySucseq( paramMap );
	}

	public void readCountOne(HashMap<String, Object> paramMap) {
		sucdao.readCountOne( paramMap );
	}

	public void getReplyList(HashMap<String, Object> paramMap) {
		sucdao.getReplyList( paramMap );
		
	}

	public void getReplyMember(HashMap<String, Object> paramMap) {
		sucdao.getReplyMember( paramMap );
	}

	public void replyDelete(int srseq) {
		sucdao.replyDelete(srseq);
		
	}

	public void insertReply(int seq, String reply, String nickname) {
		sucdao.insertReply(seq, reply, nickname);
		
	}
	
}
