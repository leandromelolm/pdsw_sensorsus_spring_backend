//package br.com.sensorsus.sensorsus.dto;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;
//import br.com.sensorsus.sensorsus.model.Usuario;
//
//
//public class AvaliacaoEstabelecimentoDTO implements Serializable {
//	
//	private static final long serialVersionUID = 1L;
//	
//	private Integer idAvaliacao;
//	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
//	private Date dataCriacao;
//	private String descricao;
//	private Double classificacao;
//	
//	private String usuarioNome;
//
//	public AvaliacaoEstabelecimentoDTO() {
//		
//	}
//	
//	public AvaliacaoEstabelecimentoDTO(AvaliacaoEstabelecimento obj, Usuario objUser) {
//		idAvaliacao = obj.getIdAvaliacao();
//		
//		classificacao = obj.getClassificacao();
//		usuarioNome = objUser.getUsername();
//	}
//
//	public Integer getIdAvaliacao() {
//		return idAvaliacao;
//	}
//
//	public void setIdAvaliacao(Integer idAvaliacao) {
//		this.idAvaliacao = idAvaliacao;
//	}
//
//	public Date getDataCriacao() {
//		return dataCriacao;
//	}
//
//	public void setDataCriacao(Date dataCriacao) {
//		this.dataCriacao = dataCriacao;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public Double getClassificacao() {
//		return classificacao;
//	}
//
//	public void setClassificacao(Double classificacao) {
//		this.classificacao = classificacao;
//	}
//
//	public String getUsuarioNome() {
//		return usuarioNome;
//	}
//
//	public void setUsuarioNome(String usuarioNome) {
//		this.usuarioNome = usuarioNome;
//	}
//	
//	
//	
//
//}
