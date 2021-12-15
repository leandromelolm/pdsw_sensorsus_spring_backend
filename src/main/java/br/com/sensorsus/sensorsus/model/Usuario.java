package br.com.sensorsus.sensorsus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.com.sensorsus.sensorsus.model.enums.Perfil;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@NotNull
	@NotBlank	
	private String nomeCompleto;
	
	
	@NotBlank
	@Column(unique=true)
	private String nickname; //apelido
	
	@JsonIgnore
	@Column(unique=true)
	private String email;
	
	@JsonIgnore
	private String senha;
	
	@JsonIgnore
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
		
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<AvaliacaoEstabelecimento> avaliacoesE = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<AvaliacaoServico> avaliacoesServico = new ArrayList<>();
	
	public Usuario()  {
		addPerfil(Perfil.STANDARD);
	}
	
	public Usuario(Integer id, String nomeCompleto, String nickname, String email, String senha) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.nickname = nickname;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.STANDARD);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public List<AvaliacaoEstabelecimento> getAvaliacoesE() {
		return avaliacoesE;
	}

	public void setAvaliacoesE(List<AvaliacaoEstabelecimento> avaliacoesE) {
		this.avaliacoesE = avaliacoesE;
	}
	
	public List<AvaliacaoServico> getAvaliacoesServico() {
		return avaliacoesServico;
	}

	public void setAvaliacoesServico(List<AvaliacaoServico> avaliacoesServico) {
		this.avaliacoesServico = avaliacoesServico;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nomeCompleto=" + nomeCompleto + ", nickname=" + nickname + ", senha=" + senha
				+ ", email=" + email + "]";
	}
	
	
}
