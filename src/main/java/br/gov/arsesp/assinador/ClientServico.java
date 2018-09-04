package br.gov.arsesp.assinador;

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
		client = ClientBuilder.newClient();
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
	
}
