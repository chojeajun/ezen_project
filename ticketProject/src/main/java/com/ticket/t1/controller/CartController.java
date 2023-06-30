package com.ticket.t1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ticket.t1.service.CartService;


@Controller
public class CartController {

	@Autowired
	CartService cs;
	
	
}
