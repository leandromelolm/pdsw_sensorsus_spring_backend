package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.sensorsus.sensorsus.model.AvaliacaoServico;

public class AvaliacaoServicoTesteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idAvaliacao;
	private String nomeServico;
	private String nomeEstabelecimento;
	
	private Date dataCriacao;
	private String descricao;
	private Double classificacao;
	private String nickname;
	
	public AvaliacaoServicoTesteDTO() {
		
	}
	
	public AvaliacaoServicoTesteDTO(AvaliacaoServico obj) {
		idAvaliacao = obj.getIdAvaliacao();
		nomeServico = obj.getServico().getNome();
		nomeEstabelecimento = obj.getServico().getEstabelecimento().getNome();
		dataCriacao = obj.getDataCriacao();
		descricao = obj.getDescricao();
		classificacao = obj.getClassificacao();
		nickname = obj.getUsuario().getNickname();
	}

	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
