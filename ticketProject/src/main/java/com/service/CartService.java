package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ICartDao;

@Service
public class CartService {

	@Autowired
	ICartDao cdao;
	
}
