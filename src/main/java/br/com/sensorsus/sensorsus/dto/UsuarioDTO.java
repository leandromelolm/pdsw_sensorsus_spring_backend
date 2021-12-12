package br.com.sensorsus.sensorsus.dto;


import java.io.Serializable;

import br.com.sensorsus.sensorsus.model.Usuario;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nomeCompleto;
	private String username;
	private String email;
//	private String senha;
//	private TipoUsuario tipoUsuario;
	
	public UsuarioDTO() {
		
	}
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nomeCompleto = obj.getNomeCompleto();
		username = obj.getUsername();
		email = obj.getEmail();
//		tipoUsuario = obj.getTipo();
		
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public TipoUsuario getTipoUsuario() {
//		return tipoUsuario;
//	}
//	public void setTipoUsuario(TipoUsuario tipoUsuario) {
//		this.tipoUsuario = tipoUsuario;
//	}
}
