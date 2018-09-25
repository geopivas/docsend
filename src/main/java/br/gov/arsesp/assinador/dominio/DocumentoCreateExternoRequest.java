package br.gov.arsesp.assinador.dominio;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class DocumentoCreateExternoRequest {

	private String nome;
	private String arquivonome;
	private int tipoid;
	private InputStream conteudoInputStream;
	private File arquivoLocal;
	private Byte[] bytes;
	private File arquivo;
	private String stringBase64;
	
	
	public DocumentoCreateExternoRequest(String nome, String arquivoNome, int idTipoDocumento, InputStream conteudo, Byte[] bytes) {
		this.nome = nome;
		this.arquivonome = arquivoNome;
		this.tipoid = idTipoDocumento;
		this.conteudoInputStream = conteudo;
		this.bytes = bytes;
	}

	public DocumentoCreateExternoRequest() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeDocumento) {
		this.nome = nomeDocumento;
	}

	public String getArquivoNome() {
		return arquivonome;
	}

	public void setArquivoNome(String arquivoNome) {
		this.arquivonome = arquivoNome;
	}

	public InputStream getConteudoInputStream() {
		return conteudoInputStream;
	}

	public void setConteudoInputStream(InputStream conteudoInputStream) {
		this.conteudoInputStream = conteudoInputStream;
	}

	public int getTipoId() {
		return tipoid;
	}

	public void setTipoId(int tipoId) {
		this.tipoid = tipoId;
	}

	public File getArquivoLocal() {
		return arquivoLocal;
	}

	public void setArquivoLocal(File arquivoLocal) {
		this.arquivoLocal = arquivoLocal;
	}

	public Byte[] getBytes() {
		return bytes;
	}

	public void setBytes(Byte[] bytes) {
		this.bytes = bytes;
	}

	public String getStringBase64() {
		return stringBase64;
	}

	public void setStringBase64(String stringBase64) {
		this.stringBase64 = stringBase64;
	}	

}
