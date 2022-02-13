package br.com.sensorsus.sensorsus.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name="tb_avaliacao_estabelecimento") 
public class AvaliacaoEstabelecimento extends Avaliacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="estabelecimento_id")
	private Estabelecimento estabelecimento;	
	
	public AvaliacaoEstabelecimento() {
	}

	public AvaliacaoEstabelecimento(Integer idAvaliacao, Date dataCriacao, String descricao, Double classificacao,
			Usuario usuario, Estabelecimento estabelecimento) {
		super(idAvaliacao, dataCriacao, descricao, classificacao, usuario);
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
}
