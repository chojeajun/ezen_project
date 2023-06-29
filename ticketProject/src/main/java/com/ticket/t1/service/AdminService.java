package com.ticket.t1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IAdminDao;

@Service
public class AdminService {

	@Autowired
	IAdminDao idao;
	
}
