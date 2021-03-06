package br.gov.arsesp.assinador;

import javax.ws.rs.client.Client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.gov.arsesp.assinador.dominio.DestinatarioModel;
import br.gov.arsesp.assinador.dominio.DocumentoCreateExternoRequest;
import br.gov.arsesp.assinador.dominio.Login;
import br.gov.arsesp.assinador.dominio.UsuarioEtapaModel;


public class AssinadorEnvioDocumentoClient extends ClientServico {
	
	public static final String STR_URI_CRIAR_DOCUMENTO_EXTERNO = "/Api/Documento/CreateExterno";
	
	public boolean enviarDocumento(DocumentoCreateExternoRequest documentoParaAssinar, UsuarioEtapaModel documentoRemetente) {
		
		Client build = getClient();
		WebTarget webTarget = getTarget(build).path(STR_URI_CRIAR_DOCUMENTO_EXTERNO);
		Builder invocationBuilder = getInvocationBuilder(webTarget);
		defineParametrosDeProxyBacen();
		

		Login dadosLogin = new Login("esilva@sp.gov.br", "@r&3sp2018");
		AssinadorLoginClient login = new AssinadorLoginClient();
		String tokenLogin = login.getLoginToken(dadosLogin);
		
		
		/*: F2537F811BD5C9B108C8AAA99CCFB7D7F42D176442262CE71C438C4
		ACS-Authorization-Token-UID: f816ab8f-3a16-4e12-b712-b85be210be2f
		ACS-Authorization-User-UID: a1be417e-0c70-42a1-8405-d96fb964d87b */
		invocationBuilder.header("ACS-Authorization-Token", tokenLogin);
		
//		JsonObject json = new JsonObject();
//		json.addProperty("bytes", documentoParaAssinar.getStringBase64());
		
//		FormDataMultiPart formMulti = getFormMultiComFields(documentoParaAssinar, documentoRemetente);
//		formMulti.bodyPart(json, MediaType.APPLICATION_JSON_TYPE);
//		Entity<FormDataMultiPart> entityDocumentoPost = Entity.entity(formMulti, formMulti.getMediaType());
		
		Form formularioRequisicao = getFormSimplesComFields(documentoParaAssinar, documentoRemetente);
		Entity<Form> entityDocumentoPost = Entity.entity(formularioRequisicao, MediaType.APPLICATION_JSON_TYPE);
		
		Gson jsonEntity = new Gson();
		StringBuilder json2 = new StringBuilder(jsonEntity.toJson(entityDocumentoPost.getEntity()));
		System.out.println("Req json:" + json2);
		json2.replace(0, "{\"parameters\":".length(), "");
		String enviar = json2.toString();
		System.out.println(enviar);
		
		Entity<String> jsonFromatado = Entity.entity(enviar, MediaType.APPLICATION_JSON);
		Response resposta = invocationBuilder.post(jsonFromatado);
		
		
		Gson respostaJson = new Gson();
		String jsonResposta = respostaJson.toJson(resposta.readEntity(String.class));
		System.out.println("Resposta velha: " +jsonResposta);
		return resposta.getStatus() == 200;
	}

	/*private FormDataMultiPart getFormMultiComFields(DocumentoCreateExternoRequest documentoParaAssinar, UsuarioEtapaModel documentoRemetente) {
		FormDataMultiPart formMulti = new FormDataMultiPart();
		
		formMulti.field("nome", documentoParaAssinar.getNome());
		formMulti.field("arquivoNome", documentoParaAssinar.getArquivoNome());
		formMulti.field("tipoId", String.valueOf(documentoParaAssinar.getTipoId()));
		formMulti.field("bytes", documentoParaAssinar.getStringBase64());
		
		Gson jsonRemetente = new Gson();
		String json = jsonRemetente.toJson(documentoRemetente);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("Signatarios", json);
		
		
		formMulti.field("Signatarios", json, MediaType.APPLICATION_JSON_TYPE);
		
		return formMulti;
	}*/
	
	private Form getFormSimplesComFields(DocumentoCreateExternoRequest documentoParaAssinar, UsuarioEtapaModel documentoRemetente) {
		Form formSimples = new Form();
		
		formSimples.param("nome", documentoParaAssinar.getNome());
		formSimples.param("arquivoNome", documentoParaAssinar.getArquivoNome());
		formSimples.param("tipoId", String.valueOf(documentoParaAssinar.getTipoId()));
		formSimples.param("bytes", documentoParaAssinar.getBytes());
		
		Gson jsonRemetente = new Gson();
		String json = jsonRemetente.toJson(documentoParaAssinar.getSignatarios());
		
		DestinatarioModel dest = new DestinatarioModel();
		dest.setEmail(documentoRemetente.getEmail() == null ? documentoParaAssinar.getSignatarios() == null ? "" : documentoParaAssinar.getSignatarios().get(0).getEmail() : "");
		Gson jsonDestinatario = new Gson();
		String jsonDest = jsonDestinatario.toJson(dest);
		formSimples.param("Destinatários", jsonDest);
		
		
		
		formSimples.param("Signatarios", json);
		
		return formSimples;
	}
	

	@Override
	protected Builder getInvocationBuilder(WebTarget targetLogin) {
		Invocation.Builder builder = targetLogin.request();
		builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE);		
		builder.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);		
		return builder;
	}

	public boolean enviarJSonDoObjetoCreateExternoRequest(DocumentoCreateExternoRequest documentoParaEnvio) {
		
		Client build = getClient();
		WebTarget webTarget = getTarget(build).path(STR_URI_CRIAR_DOCUMENTO_EXTERNO);
		Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		defineParametrosDeProxyBacen();
		

		Login dadosLogin = new Login("esilva@sp.gov.br", "@r&3sp2018");
		AssinadorLoginClient login = new AssinadorLoginClient();
		String tokenLogin = login.getLoginToken(dadosLogin);
		
		invocationBuilder.header("ACS-Authorization-Token", tokenLogin);
		
		Gson jsonRequisicao = new Gson();
		String enviarJSON = jsonRequisicao.toJson(documentoParaEnvio);
		System.out.println(enviarJSON);
		
		Entity<DocumentoCreateExternoRequest> jsonEnvio = Entity.entity(documentoParaEnvio, MediaType.APPLICATION_JSON_TYPE);
		Response resposta = invocationBuilder.post(jsonEnvio);
		
		Gson respostaJson = new Gson();
		String jsonResposta = respostaJson.toJson(resposta.readEntity(String.class));
		System.out.println("Resposta nova:" + jsonResposta);
		return resposta.getStatus() == 200;

	}
	
}
