package br.gov.arsesp.assinador.dominio;

public class LoginToken {
	
	private String token;
	private String loginURL;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLoginURL() {
		return loginURL;
	}
	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}
	
}
