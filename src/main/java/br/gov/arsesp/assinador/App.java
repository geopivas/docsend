package br.gov.arsesp.assinador;

import br.gov.arsesp.assinador.dominio.Login;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
		AssinadorLoginClient client = new AssinadorLoginClient();
		String loginToken = client.getLoginToken(new Login("esilva@sp.gov.br", "@r&3sp2018"));
		System.out.println("TokenObtido: " + loginToken);
	}

	
}
