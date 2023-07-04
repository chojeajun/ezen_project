package com.ticket.t1.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.RegisterTimeVO;

@Mapper
public interface IRegisterDao {

	ArrayList<RegisterTimeVO> getMyRegister(int mseq);
	void insertRegisterTime(RegisterTimeVO rtvo);

	
}
