package br.com.sensorsus.sensorsus.dto;


import java.io.Serializable;

import br.com.sensorsus.sensorsus.model.Usuario;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nomeCompleto;
	private String nickname;
	private String email;
//	private String senha;
	
	public UsuarioDTO() {		
	}
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nomeCompleto = obj.getNomeCompleto();
		nickname = obj.getNickname();
		email = obj.getEmail();		
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
	public void setNickname(String username) {
		this.nickname = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
