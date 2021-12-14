package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UsuarioNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nomeCompleto;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=20, message="O tamanho deve ser entre 5 e 20 caracteres")
	private String username;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	public UsuarioNewDTO() {		
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
}
