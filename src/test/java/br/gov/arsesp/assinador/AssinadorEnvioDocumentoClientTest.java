package br.gov.arsesp.assinador;

import br.gov.arsesp.assinador.dominio.DocumentoCreateExternoRequest;
import br.gov.arsesp.assinador.dominio.Login;
import br.gov.arsesp.assinador.dominio.Remetente;
import br.gov.arsesp.assinador.utils.FileUtils;
import junit.framework.TestCase;

public class AssinadorEnvioDocumentoClientTest extends TestCase{

	private static final String TESTEDOC_PDF = "testedoc.pdf";

	public void testEnviarDocumentoUsandoToken() throws Exception {
		
		Login dadosLogin = new Login("esilva@sp.gov.br", "@r&3sp2018");
		AssinadorLoginClient assinadorLoginClient = new AssinadorLoginClient();
		String loginToken = assinadorLoginClient.getLoginToken(dadosLogin);
		assertNotNull(loginToken);
		
		Remetente remetente = getRemetente(loginToken, dadosLogin);
		
		
		AssinadorEnvioDocumentoClient envioDeDocumento = new AssinadorEnvioDocumentoClient();
		DocumentoCreateExternoRequest documentoParaEnvio = getDocumentoParaEnvio();
		assertNotNull(documentoParaEnvio);
		assertNotNull(documentoParaEnvio.getConteudoInputStream());
		assertTrue(envioDeDocumento.enviarDocumento(documentoParaEnvio, remetente));
		
	}

	private Remetente getRemetente(String loginToken, Login dadosLogin) {
		Remetente remetente = new Remetente();
		
		remetente.setNome("Geovani Teste");
		remetente.setCpf("01234567890");
		remetente.setEmail(dadosLogin.getEmail());
		remetente.setUid(loginToken);
		
		return remetente;
	}

	private DocumentoCreateExternoRequest getDocumentoParaEnvio() throws Exception {
		DocumentoCreateExternoRequest docTeste = new DocumentoCreateExternoRequest();
		docTeste.setArquivoNome("meuTesteRest.pdf");;
		docTeste.setNome("DocumentoDeTesteRest.pdf");
		//TODO Descobrir o tipo de documento pelo formato
		docTeste.setTipoId(38);
		docTeste.setConteudoInputStream(FileUtils.getInputStreamDoDocumento(TESTEDOC_PDF));
		docTeste.setArquivoLocal(FileUtils.getArquivo(TESTEDOC_PDF));
		return docTeste;
	}
	
}
