package com.kvvssut.springmvc.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kvvssut.springmvc.mvc.service.RequestService;

@Controller
public class HelloWorldController {

	private static Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

	@Autowired
	private RequestService requestService;

	@RequestMapping("/request_page")
	public String hello() {
		return "index";
	}

	@RequestMapping(value = "/userbalance", method = RequestMethod.GET)
	public String getBalance(@RequestParam("name") String name, Model model) {
		BigDecimal balance = requestService.getBalance(name);
		String message = new StringBuilder("Hi ").append(name).append("! ").append("Your current balance is : ")
				.append(balance).toString();
		model.addAttribute("message", message);
		LOGGER.info("User : {}, balance is : {}", name, balance);
		return "user_balance";
	}

}