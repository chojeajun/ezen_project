package com.ticket.t1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.ICartDao;

@Service
public class CartService {

	@Autowired
	ICartDao cdao;
	
}