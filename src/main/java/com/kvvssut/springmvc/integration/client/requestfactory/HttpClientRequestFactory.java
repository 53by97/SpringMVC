package com.kvvssut.springmvc.integration.client.requestfactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;

@ApplicationScoped
public class HttpClientRequestFactory {

	private HttpClient httpClient;
	
	@PostConstruct
	private void init(){
		HttpParams params = new BasicHttpParams();
		
	    ConnManagerParams.setMaxTotalConnections(params, 250);
	    ConnManagerParams.setTimeout(params, 1000);
	    ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRoute() {
			
			@Override
			public int getMaxForRoute(HttpRoute arg0) {
				// TODO Auto-generated method stub
				return 100;
			}
		});


	    SchemeRegistry schemeRegistry = new SchemeRegistry();
	    schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

	    ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);

	    this.httpClient = new DefaultHttpClient(cm, params);
	}
	
	public ClientRequest createClientRequest(String url) {
		ClientExecutor clientExecutor = new ApacheHttpClient4Executor(this.httpClient);
		ClientRequest clientRequest = new ClientRequest(url, clientExecutor);
		clientRequest.followRedirects(false);
		return clientRequest;
	}
	
	public HttpClient getHttpClient() {
		return this.httpClient;
	}
	
	
	@PreDestroy
	private void destroy() {
		this.httpClient.getConnectionManager().shutdown();
	}
}
