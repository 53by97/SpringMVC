package com.kvvssut.springmvc.service.impl;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvvssut.springmvc.integration.dto.RestDemoRequestResource;
import com.kvvssut.springmvc.integration.service.RestDemoIntgService;
import com.kvvssut.springmvc.service.ClientRestService;

@Service
public class ClientRestServiceImpl implements ClientRestService {

	private static Logger LOGGER = LoggerFactory.getLogger(ClientRestServiceImpl.class);

	@Autowired
	private RestDemoIntgService restDemoIntgService;

	private final static String HOST_PATH = "http://localhost:8080";
	private final static String PATH_REST_CLIENT_APPLICATION = "/restapplicationclient/kvvssut/client/";
	private final static String PATH_GET_BALANCE_BY_NAME = "%s/balance";

	@Override
	public Response doRestDemoCall(String userName, RestDemoRequestResource restDemoRequestResource) {
		Object[] statuses = new Object[2];
		try {
			statuses = restDemoIntgService.callRestEasyDemoApplication(restDemoRequestResource,
					HOST_PATH + PATH_REST_CLIENT_APPLICATION, String.format(PATH_GET_BALANCE_BY_NAME, userName));
			LOGGER.info("HTTP status is : {}. Message is : {}.", (Integer) statuses[0], statuses[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status((Integer) statuses[0]).entity(statuses[1]).build();
	}

	public Response doRestDemoCall(String userName) {
		Object[] statuses = new Object[2];
		try {
			statuses = restDemoIntgService.callRestEasyDemoApplication(HOST_PATH + PATH_REST_CLIENT_APPLICATION,
					userName);
			LOGGER.info("HTTP status is : {}.", (Integer) statuses[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status((Integer) statuses[0]).entity(statuses[1]).build();
	}

}
