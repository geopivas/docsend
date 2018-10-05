package br.gov.arsesp.assinador;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;



public abstract class ClientServico {
	
	public static final String STR_URL = "https://utilize.assinasp.imprensaoficial.com.br";
	private WebTarget targetServico;
	private Client client;
		
	public ClientServico() {
		iniciaTargetServico();
	}

	private void iniciaTargetServico() {
		client = ClientBuilder.newBuilder().sslContext(getSSLContext()).hostnameVerifier(new HostnameVerifier() {
			
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		}).build();
		setTarget(client.target(STR_URL));
	}

	private void setTarget(WebTarget target) {
		this.targetServico = target;
		
	}

	public WebTarget getTarget() {
		if (targetServico == null) {
			iniciaTargetServico();
		}
		return targetServico;
	}
	
	protected abstract Invocation.Builder getInvocationBuilder(WebTarget targetLogin);

	protected void defineParametrosDeProxyBacen() {
		//Configuração do proxy do bacen
		System.setProperty("https.proxyHost", "cache");
	    System.setProperty("https.proxyPort", "8080");
	}

	public Client getClient() {
		return client;
	}
	
	public ClientBuilder getClientBuilder() {
		return ClientBuilder.newBuilder();
	}
	
	protected WebTarget getTarget(Client client) {
		return client.target(STR_URL);
	}
	
	
	public SSLContext getSSLContext() {

	    SSLContext context= null;
	    try {
	        context = SSLContext.getInstance("SSL");
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    try {
	        context.init(null,new TrustManager[]{new X509TrustManager(){
	            public void checkClientTrusted(      X509Certificate[] x509Certificates,      String authType)  {
	            }
	            public void checkServerTrusted(      X509Certificate[] x509Certificates,      String authType) {

	            }
	            public X509Certificate[] getAcceptedIssuers(){
	                return new X509Certificate[0];
	            }
	        }
	        },new SecureRandom());
	    } catch (KeyManagementException e) {
	        e.printStackTrace();
	    }

	    return  context;
	}
	
}
