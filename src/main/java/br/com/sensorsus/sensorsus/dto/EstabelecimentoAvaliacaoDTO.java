package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;
import br.com.sensorsus.sensorsus.model.Estabelecimento;

public class EstabelecimentoAvaliacaoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Integer codCnes;
	
	private List<AvaliacaoEstabelecimento> avaliacoes = new ArrayList<>();
	
	public EstabelecimentoAvaliacaoDTO() {		
	}
	
	public EstabelecimentoAvaliacaoDTO(Estabelecimento obj) {
		id = obj.getId();
		nome = obj.getNome();
		codCnes = obj.getCodCnes();
		setAvaliacoes(obj.getAvaliacoes());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodCnes() {
		return codCnes;
	}

	public void setCodCnes(Integer codCnes) {
		this.codCnes = codCnes;
	}

	public List<AvaliacaoEstabelecimento> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoEstabelecimento> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	

}
