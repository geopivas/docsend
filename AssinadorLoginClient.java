package br.gov.arsesp.assinador;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.gov.arsesp.assinador.dominio.Login;
import br.gov.arsesp.assinador.dominio.LoginToken;

public class AssinadorLoginClient extends ClientServico {
	
	public static final String STR_URI_LOGIN = "/Api/Login/EmailSenhaSemCookie";
	public static final String STR_URI_LOGIN_SEM_TOKEN = "/Api/Login/EmailSenha";
	
	public String getLoginToken(Login dadosLogin) {
		WebTarget targetLogin = getTarget().path(STR_URI_LOGIN);
		Response resposta = enviarRquisicao(dadosLogin, targetLogin);
		if (resposta.getStatus() == 200) {
			String conteudoDaResposta = resposta.readEntity(String.class);
			LoginToken loginToken = new Gson().fromJson(conteudoDaResposta, LoginToken.class);
			return loginToken.getToken();
		}
		return null;
	}
	
	public int getLoginSemToken(Login dadosLogin) {
		WebTarget targetLogin = getTarget().path(STR_URI_LOGIN_SEM_TOKEN);
		Response resposta = enviarRquisicao(dadosLogin, targetLogin);
		return resposta.getStatus();
	}
	
	public Response getResponseLoginSemToken(Login dadosLogin) {
		WebTarget targetLogin = getTarget().path(STR_URI_LOGIN_SEM_TOKEN);
		Response resposta = enviarRquisicao(dadosLogin, targetLogin);
		return resposta;
	}

	private Response enviarRquisicao(Login dadosLogin, WebTarget targetLogin) {
		Invocation.Builder builder = getInvocationBuilder(targetLogin);
		defineParametrosDeProxyBacen();
	    Form form = getFormParams(dadosLogin);
	    Entity<Form> formEnviar = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED);
		Response resposta = builder.post(formEnviar);
		return resposta;
	}
	
	protected Invocation.Builder getInvocationBuilder(WebTarget targetLogin) {
		Invocation.Builder builder = targetLogin.request();
		builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
		builder.accept(MediaType.APPLICATION_JSON);
		return builder;
	}

	private Form getFormParams(Login dadosLogin) {
		Form form = new Form();
	    form.param("email", dadosLogin.getEmail());
	    form.param("senha", dadosLogin.getSenha());
		return form;
	}
	


}
