package br.gov.arsesp.assinador;

import java.util.Arrays;

import br.gov.arsesp.assinador.dominio.DocumentoCreateExternoRequest;
import br.gov.arsesp.assinador.dominio.Login;
import br.gov.arsesp.assinador.dominio.RemetenteModel;
import br.gov.arsesp.assinador.dominio.UsuarioEtapaModel;
import br.gov.arsesp.assinador.utils.FileUtils;
import junit.framework.TestCase;

public class AssinadorEnvioDocumentoClientTest extends TestCase{

	private static final String TESTEDOC_PDF = "testedoc.pdf";

	public void testEnviarDocumentoUsandoToken() throws Exception {
		
		Login dadosLogin = new Login("esilva@sp.gov.br", "@r&3sp2018");
		AssinadorLoginClient assinadorLoginClient = new AssinadorLoginClient();
//		String loginToken = assinadorLoginClient.getLoginToken(dadosLogin);
//		assertNotNull(loginToken);
		
		UsuarioEtapaModel usuarioEtapaModel = getSignatario("", dadosLogin);
		
		
		AssinadorEnvioDocumentoClient envioDeDocumento = new AssinadorEnvioDocumentoClient();
		DocumentoCreateExternoRequest documentoParaEnvio = getDocumentoParaEnvio();
		assertNotNull(documentoParaEnvio);
		assertTrue(envioDeDocumento.enviarDocumento(documentoParaEnvio, usuarioEtapaModel));
		
	}

	private UsuarioEtapaModel getSignatario(String loginToken, Login dadosLogin) {
		UsuarioEtapaModel usuarioEtapaModel = new UsuarioEtapaModel();
		
		usuarioEtapaModel.setNome("Geovani Teste");
		usuarioEtapaModel.setCpf("90763729507");
		usuarioEtapaModel.setEmail(dadosLogin.getEmail());
		usuarioEtapaModel.setUid(loginToken);
		usuarioEtapaModel.setTitulo("Sr.");
		
		return usuarioEtapaModel;
	}

	private DocumentoCreateExternoRequest getDocumentoParaEnvio() throws Exception {
		DocumentoCreateExternoRequest docTeste = new DocumentoCreateExternoRequest();
		docTeste.setArquivoNome(TESTEDOC_PDF);
		docTeste.setNome(TESTEDOC_PDF);
		docTeste.setTipoId("38");
		docTeste.setStringBase64(FileUtils.getBase64DoArquivo(TESTEDOC_PDF));
		return docTeste;
	}
	
	public void testEnviarDocumentoUsandoTokenEObjetoDeRequest() throws Exception {
		
		Login dadosLogin = new Login("esilva@sp.gov.br", "@r&3sp2018");
		UsuarioEtapaModel usuarioEtapaModel = getSignatario("", dadosLogin);
		RemetenteModel remetente = getRemetente(dadosLogin);
		AssinadorEnvioDocumentoClient envioDeDocumento = new AssinadorEnvioDocumentoClient();
		DocumentoCreateExternoRequest documentoParaEnvio = getDocumentoParaEnvio(usuarioEtapaModel, remetente);
		assertNotNull(documentoParaEnvio);
		assertTrue(envioDeDocumento.enviarJSonDoObjetoCreateExternoRequest(documentoParaEnvio));
		
	}

	private RemetenteModel getRemetente(Login dadosLogin) {
		RemetenteModel remetente = new RemetenteModel();
		remetente.setEmail(dadosLogin.getEmail());
		return remetente;
	}

	private DocumentoCreateExternoRequest getDocumentoParaEnvio(UsuarioEtapaModel usuarioEtapaModel, RemetenteModel remetente) throws Exception {
		DocumentoCreateExternoRequest documentoParaEnvio = getDocumentoParaEnvio();
		documentoParaEnvio.setSignatarios(Arrays.asList(usuarioEtapaModel));
		documentoParaEnvio.setRemetente(remetente);
		return documentoParaEnvio;
	}
	
}
