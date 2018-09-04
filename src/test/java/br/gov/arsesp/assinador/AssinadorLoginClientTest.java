package br.gov.arsesp.assinador;

import br.gov.arsesp.assinador.dominio.Login;
import junit.framework.TestCase;

public class AssinadorLoginClientTest extends TestCase {

	public void testGetLoginTokenComSucesso() {

		Login dadosLogin = new Login("esilva@sp.gov.br", "@r&3sp2018");
		assertNotNull(new AssinadorLoginClient().getLoginToken(dadosLogin));
	}

	public void testGetLoginTokenComSenhaInvalida() {
		Login dadosLogin = new Login("esilva@sp.gov.br", "123456");
		assertNull(new AssinadorLoginClient().getLoginToken(dadosLogin));
	}
	
	public void testGetLoginComSenhaInvalida() {
		Login dadosLogin = new Login("esilva@sp.gov.br", "123456");
		int loginSemToken = new AssinadorLoginClient().getLoginSemToken(dadosLogin);
		assertNotNull(loginSemToken);
		assertFalse(loginSemToken == 200);
	}

	public void testGetLoginSemComSucesso() {
		Login dadosLogin = new Login("esilva@sp.gov.br", "@r&3sp2018");
		int retorno = new AssinadorLoginClient().getLoginSemToken(dadosLogin);
		assertNotNull(retorno);
		assertEquals(retorno, 200);
	}
	
}
