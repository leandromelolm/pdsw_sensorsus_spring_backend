package br.com.sensorsus.sensorsus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estabelecimento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer codCnes;
	private String descricao;
	private String orgaoGestor;
	private String naturezaJuridica;
	
	@JsonIgnore
	@OneToMany(mappedBy="estabelecimento")
	private List<AvaliacaoEstabelecimento> avaliacao = new ArrayList<>();
	
	public Estabelecimento() {
		
	}	
	
	public Estabelecimento(Integer id, String nome, Integer codCnes, String descricao, String orgaoGestor,
			String naturezaJuridica) {
		super();
		this.id = id;
		this.nome = nome;
		this.codCnes = codCnes;
		this.descricao = descricao;
		this.orgaoGestor = orgaoGestor;
		this.naturezaJuridica = naturezaJuridica;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOrgaoGestor() {
		return orgaoGestor;
	}

	public void setOrgaoGestor(String orgaoGestor) {
		this.orgaoGestor = orgaoGestor;
	}

	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}

	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}
	
	public List<AvaliacaoEstabelecimento> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(List<AvaliacaoEstabelecimento> avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naturezaJuridica == null) ? 0 : naturezaJuridica.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estabelecimento other = (Estabelecimento) obj;
		if (naturezaJuridica == null) {
			if (other.naturezaJuridica != null)
				return false;
		} else if (!naturezaJuridica.equals(other.naturezaJuridica))
			return false;
		return true;
	}
	
}