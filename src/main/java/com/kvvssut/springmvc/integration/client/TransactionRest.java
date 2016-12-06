package com.kvvssut.springmvc.integration.client;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientResponse;

import com.kvvssut.springmvc.integration.dto.RestDemoRequestResource;

@Stateless
@Path("")
public interface TransactionRest {
	
	@PUT
	@Path("/method/{param}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ClientResponse<String> putToRestEasyDemo(RestDemoRequestResource restDemoRequestResource, @PathParam("param") String userName);

	@GET
	@Path("/method/{username}/balance")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientResponse<String> getBalanceByUsername(@PathParam("username") String userName);
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientResponse<String> getBalanceByUsername();
}
