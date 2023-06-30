package com.ticket.t1.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.RegisterTimeVO;

@Mapper
public interface IRegisterDao {

	void insertRegisterTime(int mseq, String string, String string2, String string3);
	ArrayList<RegisterTimeVO> getMyRegister(MemberVO membervo);

	
}
