package br.gov.arsesp.assinador.dominio;

/**
 * Classe que representa os Signatários do documento
 * @author deinf.cella
 *
 */
public class UsuarioEtapaModel {

	private String id;
	private String pjId;
	private String uid;
	private String nome;
	private String email;
	private String cpf;
	private String titulo;
	private Integer etapa;
	private Boolean salvarContato;
	private Integer posicaoAssinatura;
	private Boolean emailVerificador;
	private String assunto;
	private String texto;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPjId() {
		return pjId;
	}
	public void setPjId(String pjId) {
		this.pjId = pjId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getEtapa() {
		return etapa;
	}
	public void setEtapa(Integer etapa) {
		this.etapa = etapa;
	}
	public Boolean getSalvarContato() {
		return salvarContato;
	}
	public void setSalvarContato(Boolean salvarContato) {
		this.salvarContato = salvarContato;
	}
	public Integer getPosicaoAssinatura() {
		return posicaoAssinatura;
	}
	public void setPosicaoAssinatura(Integer posicaoAssinatura) {
		this.posicaoAssinatura = posicaoAssinatura;
	}
	public Boolean getEmailVerificador() {
		return emailVerificador;
	}
	public void setEmailVerificador(Boolean emailVerificador) {
		this.emailVerificador = emailVerificador;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
}
