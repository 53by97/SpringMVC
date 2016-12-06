package com.kvvssut.springmvc.service;

import javax.ws.rs.core.Response;

import com.kvvssut.springmvc.integration.dto.RestDemoRequestResource;

public interface ClientRestService {
	
	public Response doRestDemoCall(String userName, RestDemoRequestResource restDemoRequestResource);

	public Response doRestDemoCall(String userName);
	
}
