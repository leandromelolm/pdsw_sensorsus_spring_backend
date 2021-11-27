package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.sensorsus.sensorsus.model.Endereco;
import br.com.sensorsus.sensorsus.model.Estabelecimento;

public class EstabelecimentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Integer codCnes;
	private Set<String> telefones = new HashSet<>();
	
	private Endereco endereco;
	
	public EstabelecimentoDTO() {		
	}
	
	public EstabelecimentoDTO(Estabelecimento obj) {
		id = obj.getId();
		nome = obj.getNome();
		codCnes = obj.getCodCnes();
		endereco = obj.getEndereco();
		telefones = obj.getTelefones();
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	

}
