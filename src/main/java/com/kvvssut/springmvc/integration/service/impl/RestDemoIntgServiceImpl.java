package com.kvvssut.springmvc.integration.service.impl;

import org.jboss.resteasy.client.ClientResponse;
import org.springframework.stereotype.Service;

import com.kvvssut.springmvc.integration.client.BaseRestClient;
import com.kvvssut.springmvc.integration.client.TransactionRest;
import com.kvvssut.springmvc.integration.dto.RestDemoRequestResource;
import com.kvvssut.springmvc.integration.service.RestDemoIntgService;

@Service
public class RestDemoIntgServiceImpl extends BaseRestClient implements RestDemoIntgService {
	
//	private static Logger logger = Logger.getLogger(RestDemoIntgServiceImpl.class);

	@Override
	public Object [] callRestEasyDemoApplication(RestDemoRequestResource restDemoRequestResource, String callURL, String param) throws Exception {
		TransactionRest restEasyDemoIntgClient;
		ClientResponse<String> clientResponse = null;
		Object [] statuses = new Object[2];
		
		try {
			System.out.println(callURL+param);
			restEasyDemoIntgClient = getClient(TransactionRest.class, callURL);
			clientResponse = restEasyDemoIntgClient.putToRestEasyDemo(restDemoRequestResource, param);
			if(clientResponse != null){
				statuses[0] = clientResponse.getStatus();
				statuses[1] = clientResponse.getEntity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseConnection(clientResponse);
		}

		return statuses;
	}
	
	@Override
	public Object[] callRestEasyDemoApplication(String requestPath, String userName) throws Exception {
		TransactionRest restEasyDemoIntgClient;
		ClientResponse<String> clientResponse = null;
		Object [] statuses = new Object[2];
		
		try {
			System.out.println("Calling link: " + "URL " + requestPath);
			restEasyDemoIntgClient = getClient(TransactionRest.class, requestPath);
			System.out.println("client info " + restEasyDemoIntgClient);
			clientResponse = restEasyDemoIntgClient.getBalanceByUsername(userName);
			//clientResponse = restEasyDemoIntgClient.getBalanceByUsername();
			if(clientResponse != null){
				statuses[0] = clientResponse.getStatus();
				statuses[1] = clientResponse.getEntity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseConnection(clientResponse);
		}

		return statuses;
	}

}
