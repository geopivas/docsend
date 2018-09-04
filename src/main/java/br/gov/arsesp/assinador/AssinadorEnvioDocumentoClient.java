package br.gov.arsesp.assinador;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;

import br.gov.arsesp.assinador.dominio.DocumentoCreateExternoRequest;

public class AssinadorEnvioDocumentoClient extends ClientServico {
	
	public static final String STR_URI_CRIAR_DOCUMENTO_EXTERNO = "/Api/Documento/CreateExterno";
	
	public boolean enviarDocumento(DocumentoCreateExternoRequest documentoParaAssinar, String tokenSession) {
		Client build = getClientBuilder().register(MultiPartWriter.class).build();
		WebTarget webTarget = getTarget(build).path(STR_URI_CRIAR_DOCUMENTO_EXTERNO);
		Builder invocationBuilder = getInvocationBuilder(webTarget);
		defineParametrosDeProxyBacen();
		
				
		FormDataMultiPart formMulti = new FormDataMultiPart();
		formMulti.setContentDisposition(ContentDisposition.type("form-data").build());
		FormDataBodyPart arquivo = new FormDataBodyPart("bytes", documentoParaAssinar.getArquivoLocal(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
		formMulti.bodyPart(arquivo);
		
//		formMulti.getHeaders().add("Content-Type", "application/json");		

		Entity<FormDataMultiPart> entityDocumentoPost = Entity.json(formMulti);		
		Response resposta = invocationBuilder.post(entityDocumentoPost);
		return resposta.getStatus() == 200;
	}

	@Override
	protected Builder getInvocationBuilder(WebTarget targetLogin) {
		Invocation.Builder builder = targetLogin.request();
		/*builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);		
		builder.accept(MediaType.TEXT_HTML, MediaType.APPLICATION_XHTML_XML, MediaType.APPLICATION_XML);*/		
		return builder;
	}
	
}
