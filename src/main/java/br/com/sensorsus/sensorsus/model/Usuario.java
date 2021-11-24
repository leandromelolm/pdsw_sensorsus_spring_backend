package br.com.sensorsus.sensorsus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.com.sensorsus.sensorsus.model.enums.TipoUsuario;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@NotBlank	
	private String nomeCompleto;
	@NotNull
	@NotBlank	
	private String username;
	@NotNull
	@NotBlank
	private String senha;
	@NotNull
	@NotBlank
	private String email;
	
	private Integer tipoUsuario;
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<AvaliacaoEstabelecimento> avaliacoesE = new ArrayList<>();
	
	public Usuario()  {
		
	}
	
	public Usuario(Integer id, String nomeCompleto, String username, String senha, String email, TipoUsuario tipoUsuario) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.username = username;
		this.senha = senha;
		this.email = email;
		this.tipoUsuario = tipoUsuario.getCod();
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public TipoUsuario getTipo() {
		return TipoUsuario.toEnum(tipoUsuario);
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipoUsuario = tipo.getCod();
	}

	public List<AvaliacaoEstabelecimento> getAvaliacoesE() {
		return avaliacoesE;
	}

	public void setAvaliacoesE(List<AvaliacaoEstabelecimento> avaliacoesE) {
		this.avaliacoesE = avaliacoesE;
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
		return "Usuario [id=" + id + ", nomeCompleto=" + nomeCompleto + ", username=" + username + ", senha=" + senha
				+ ", email=" + email + "]";
	}
	
	
}
