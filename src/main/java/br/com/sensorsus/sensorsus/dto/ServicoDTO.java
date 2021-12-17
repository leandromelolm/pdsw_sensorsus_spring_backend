package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;

import br.com.sensorsus.sensorsus.model.Servico;

public class ServicoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idServico;
	private String nomeServico;
	
	private String nomeEstabelecimento;
		
	public ServicoDTO() {		
	}
	
	public ServicoDTO(Servico obj) {
		idServico = obj.getId();
		nomeServico = obj.getNome();
		setNomeEstabelecimento(obj.getEstabelecimento().getNome());
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer id) {
		this.idServico = id;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nome) {
		this.nomeServico = nome;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String estabelecimento) {
		this.nomeEstabelecimento = estabelecimento;
	}	
}
