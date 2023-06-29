package com.ticket.t1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ticket.t1.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService as;
	
	
}
