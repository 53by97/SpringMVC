package com.kvvssut.springmvc.integration.client;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.http.client.HttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.kvvssut.springmvc.integration.client.requestfactory.HttpClientRequestFactory;

@Stateless
public abstract class BaseRestClient {
	
	@Inject
	private HttpClientRequestFactory restClientRequestFactory;
	
	static {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}
	
	public <T> T getClient(Class<T> clazz, String base) {
		HttpClient httpClient = restClientRequestFactory.getHttpClient();
		ClientExecutor clientExecutor = new ApacheHttpClient4Executor(httpClient);
		return ProxyFactory.create(clazz, base, clientExecutor);
	}
	
	public <T> void releaseConnection(ClientResponse<T> clientResponse) {
		if (clientResponse != null) {
			clientResponse.releaseConnection();
		}
	}
	
	public Object mapResponse(ClientResponse<String> clientResponse, Object targetObjectType) { 
		Object object = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			object = objectMapper.readValue(clientResponse.getEntity(), targetObjectType.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public void setRestClientRequestFactory(
			HttpClientRequestFactory restClientRequestFactory) {
		this.restClientRequestFactory = restClientRequestFactory;
	}
}
