package com.kvvssut.springmvc.mvc.service.impl;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvvssut.springmvc.mvc.service.RequestService;
import com.kvvssut.springmvc.service.ClientRestService;

@Local
@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private ClientRestService clientRestService;

	public BigDecimal getBalance(String username) {
		Response response = this.clientRestService.doRestDemoCall(username);

		return new BigDecimal(response.getEntity().toString());
	}

}
