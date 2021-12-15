package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.Date;

public class AvaliacaoEstabelecimentoDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private Date dataCriacao;
	private String descricao;
	private Double classificacao;
	
	private Integer usuarioId;
	private Integer estabelecimentoId;
	
	public AvaliacaoEstabelecimentoDTO() {		
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
}
