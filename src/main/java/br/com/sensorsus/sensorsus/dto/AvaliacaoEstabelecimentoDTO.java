package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.Date;

public class AvaliacaoEstabelecimentoDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private Integer idAvaliacao;
	
	private Integer estabelecimentoId;
	private String nomeEstabelecimento;
	
	private Date dataCriacao;
	private String descricao;
	private Double classificacao;	
	
	private Integer usuarioId;
	private String apelido;
	
	public AvaliacaoEstabelecimentoDTO() {		
	}
	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Double classificacao) {
		this.classificacao = classificacao;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Integer getEstabelecimentoId() {
		return estabelecimentoId;
	}
	public void setEstabelecimentoId(Integer estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}
	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}
	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
}
