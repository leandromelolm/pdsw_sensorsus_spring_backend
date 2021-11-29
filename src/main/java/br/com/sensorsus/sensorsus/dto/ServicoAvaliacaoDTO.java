package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.sensorsus.sensorsus.model.AvaliacaoServico;
import br.com.sensorsus.sensorsus.model.Servico;

public class ServicoAvaliacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private Integer idServico;
	private String nomeServico;
	private String nomeEstabelecimento;
	
	private List<AvaliacaoServico> avaliacoesServico = new ArrayList<>();
	
	public ServicoAvaliacaoDTO() {
		
	}

	public ServicoAvaliacaoDTO(ServicoDTO obj) {		
		idServico = obj.getId();
		nomeServico = obj.getNome();
//		nomeEstabelecimento = obj.getEstabelecimento().getNome();
		nomeEstabelecimento = obj.getEstabelecimento();
	}

	public ServicoAvaliacaoDTO(Servico obj) {
		idServico = obj.getId();
		nomeServico = obj.getNome();
		avaliacoesServico = obj.getAvaliacoesServico();
		nomeEstabelecimento = obj.getEstabelecimento().getNome();
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
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

	public List<AvaliacaoServico> getAvaliacoesServico() {
		return avaliacoesServico;
	}

	public void setAvaliacoesServico(List<AvaliacaoServico> avaliacoesServico) {
		this.avaliacoesServico = avaliacoesServico;
	}	
	
}
