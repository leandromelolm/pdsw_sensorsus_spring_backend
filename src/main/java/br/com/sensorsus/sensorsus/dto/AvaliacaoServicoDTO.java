package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.sensorsus.sensorsus.model.AvaliacaoServico;
import br.com.sensorsus.sensorsus.model.Servico;

public class AvaliacaoServicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String estabelecimento;
	
	private List<AvaliacaoServico> avaliacoesServico = new ArrayList<>();
	
	
	public AvaliacaoServicoDTO() {
		
	}
	
	public AvaliacaoServicoDTO(Servico obj) {
		id = obj.getId();
		nome = obj.getNome();
		setEstabelecimento(obj.getEstabelecimento().getNome());
		avaliacoesServico = obj.getAvaliacoesServico();
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

	public List<AvaliacaoServico> getAvaliacoesServico() {
		return avaliacoesServico;
	}

	public void setAvaliacoesServico(List<AvaliacaoServico> avaliacoesServico) {
		this.avaliacoesServico = avaliacoesServico;
	}
	
	
}
