package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;

public class AvaliacaoEstabelecimentoNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer AvaliacaoId;
	
	private Date dataCriacao;
	private String descricao;
	private Double classificacao;
	
	private Integer usuarioId;
	private Integer estabelecimentoId;
	
	private String usuarioEmail;
	
	public AvaliacaoEstabelecimentoNewDTO() {		
	}
	
	public AvaliacaoEstabelecimentoNewDTO(AvaliacaoEstabelecimento obj) {
		AvaliacaoId = obj.getIdAvaliacao();
		estabelecimentoId = obj.getEstabelecimento().getId();
		estabelecimentoId = obj.getEstabelecimento().getId();
		dataCriacao = obj.getDataCriacao();
		descricao = obj.getDescricao();
		classificacao = obj.getClassificacao();
		usuarioId = obj.getUsuario().getId();
		usuarioEmail = obj.getUsuario().getEmail();
	}
	
	public Integer getAvaliacaoId() {
		return AvaliacaoId;
	}

	public void setAvaliacaoId(Integer avaliacaoId) {
		AvaliacaoId = avaliacaoId;
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

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
	
}
