package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;

public class UsuarioNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String nomeCompleto;
	private String username;
	private String senha;
	private String email;
	private Integer tipoUsuario;
	
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

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}	
	
}
