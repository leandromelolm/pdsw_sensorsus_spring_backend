package br.com.sensorsus.sensorsus.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name="tb_avaliacao_servico") 
public class AvaliacaoServico extends Avaliacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="servico_id")
	private Servico servico;
	
	public AvaliacaoServico() {	
	}

	public AvaliacaoServico(Integer idAvaliacao, Date dataCriacao, String descricao, Double classificacao,
			Usuario usuario, Servico servico) {
		super(idAvaliacao, dataCriacao, descricao, classificacao, usuario);		
		this.servico = servico;
	}
	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}
