package com.kvvssut.springmvc.mvc.service.impl;

import javax.ejb.Local;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvvssut.springmvc.mvc.service.RequestService;
import com.kvvssut.springmvc.service.ClientRestService;

@Local
@Service
public class RequestServiceImpl implements RequestService {
	
	//private static Logger logger = Logger.getLogger(RequestServiceImpl.class);

	@Autowired
	private ClientRestService clientRestService;

	public String getBalance(String username) {
		Response response =	this.clientRestService.doRestDemoCall(username);

		System.out.println(response.getEntity().toString());

		if(response.getStatus() == 200){
			return "success";
		} else {
			return "failure";
		}
	}
	
}
