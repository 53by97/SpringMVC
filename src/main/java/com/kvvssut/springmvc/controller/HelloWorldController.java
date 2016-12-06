package com.kvvssut.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kvvssut.springmvc.mvc.service.RequestService;

@Controller
public class HelloWorldController {

//	private static Logger logger = Logger.getLogger(HelloWorldController.class);

	@Autowired
	private RequestService requestService;


	@RequestMapping("/")
	public String hello() {
		return "index";
	}

	@RequestMapping(value = "/userbalance", method = RequestMethod.GET)
	public String getBalance(@RequestParam("name") String name, Model model) {
		System.out.println("start");
		String message = "Hi " + name + "!";
		System.out.println(requestService.getBalance(name));
		model.addAttribute("message", message);
		System.out.println("end");
		return "hi";
	}

}