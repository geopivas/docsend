package br.gov.arsesp.assinador;

import javax.print.attribute.standard.Media;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;

import br.gov.arsesp.assinador.dominio.DocumentoCreateExternoRequest;
import br.gov.arsesp.assinador.dominio.Remetente;

public class AssinadorEnvioDocumentoClient extends ClientServico {
	
	public static final String STR_URI_CRIAR_DOCUMENTO_EXTERNO = "/Api/Documento/CreateExterno";
	
	public boolean enviarDocumento(DocumentoCreateExternoRequest documentoParaAssinar, Remetente documentoRemetente) {	
		
		Client build = getClientBuilder().register(MultiPartWriter.class).build();
		WebTarget webTarget = getTarget(build).path(STR_URI_CRIAR_DOCUMENTO_EXTERNO);
		Builder invocationBuilder = getInvocationBuilder(webTarget);
		defineParametrosDeProxyBacen();
				
		FormDataMultiPart formMulti = getFormMultiComFields(documentoParaAssinar, documentoRemetente);		
		
		
		FileDataBodyPart arquivo = new FileDataBodyPart("bytes", documentoParaAssinar.getArquivoLocal(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
		formMulti.bodyPart(arquivo);
		

		Entity<FormDataMultiPart> entityDocumentoPost = Entity.entity(formMulti, formMulti.getMediaType());		
		Response resposta = invocationBuilder.post(entityDocumentoPost);
		return resposta.getStatus() == 200;
	}

	private FormDataMultiPart getFormMultiComFields(DocumentoCreateExternoRequest documentoParaAssinar, Remetente documentoRemetente) {
		FormDataMultiPart formMulti = new FormDataMultiPart();
		
		formMulti.field("nome", documentoParaAssinar.getNome());
		formMulti.field("arquivoNome", documentoParaAssinar.getArquivoNome());
		formMulti.field("tipoId", String.valueOf(documentoParaAssinar.getTipoId()));
		
		Gson jsonRemetente = new Gson();
		String json = jsonRemetente.toJson(documentoRemetente);
		
		
		formMulti.field("remetente", json, MediaType.APPLICATION_JSON_TYPE);
		
		return formMulti;
	}

	@Override
	protected Builder getInvocationBuilder(WebTarget targetLogin) {
		Invocation.Builder builder = targetLogin.request();
		builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);		
		builder.accept(MediaType.TEXT_HTML, MediaType.APPLICATION_XHTML_XML, MediaType.APPLICATION_XML);		
		return builder;
	}
	
}
