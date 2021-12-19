package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;

public class AvaliacaoEstabelecimentoDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private Integer idAvaliacao;
	
	private Integer estabelecimentoId;
	private String nomeEstabelecimento;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataCriacao;
	private String descricao;
	private Double classificacao;	
	
	private Integer usuarioId;
	private String apelido;
	
	public AvaliacaoEstabelecimentoDTO(AvaliacaoEstabelecimento obj) {
		idAvaliacao = obj.getIdAvaliacao();
		estabelecimentoId = obj.getEstabelecimento().getId();
		nomeEstabelecimento = obj.getEstabelecimento().getNome();
		dataCriacao = obj.getDataCriacao();
		descricao = obj.getDescricao();
		classificacao = obj.getClassificacao();
		usuarioId = obj.getUsuario().getId();
		apelido = obj.getUsuario().getNickname();
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
