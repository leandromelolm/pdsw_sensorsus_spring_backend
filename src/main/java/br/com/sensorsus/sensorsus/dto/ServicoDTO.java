package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;

import br.com.sensorsus.sensorsus.model.Servico;

public class ServicoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	private String estabelecimento;
		
	public ServicoDTO() {		
	}
	
	public ServicoDTO(Servico obj) {
		id = obj.getId();
		nome = obj.getNome();
		setEstabelecimento(obj.getEstabelecimento().getNome());
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

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}	
}
