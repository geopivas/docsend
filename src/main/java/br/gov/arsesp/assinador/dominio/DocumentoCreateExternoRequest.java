package br.gov.arsesp.assinador.dominio;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class DocumentoCreateExternoRequest {

	private String nome;
	private String arquivoNome;
	private String tipoId;
	private String bytes;
	private List<UsuarioEtapaModel> signatarios;
	private RemetenteModel remetente;
	
	public DocumentoCreateExternoRequest(String nome, String arquivoNome, String idTipoDocumento, List<UsuarioEtapaModel> signatarios, RemetenteModel remetente) {
		this.nome = nome;
		this.arquivoNome = arquivoNome;
		this.tipoId = idTipoDocumento;
		this.signatarios = signatarios;
		this.remetente = remetente;
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
		return arquivoNome;
	}

	public void setArquivoNome(String arquivoNome) {
		this.arquivoNome = arquivoNome;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getBytes() {
		return bytes;
	}

	public void setBytes(String stringBase64) {
		this.bytes = stringBase64;
	}

	public List<UsuarioEtapaModel> getSignatarios() {
		return signatarios;
	}

	public void setSignatarios(List<UsuarioEtapaModel> signatarios) {
		this.signatarios = signatarios;
	}

	public RemetenteModel getRemetente() {
		return remetente;
	}

	public void setRemetente(RemetenteModel remetente) {
		this.remetente = remetente;
	}	

}
