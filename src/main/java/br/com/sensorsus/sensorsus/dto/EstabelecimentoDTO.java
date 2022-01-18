package br.com.sensorsus.sensorsus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.sensorsus.sensorsus.model.Endereco;
import br.com.sensorsus.sensorsus.model.Estabelecimento;
import br.com.sensorsus.sensorsus.model.Servico;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Integer codCnes;
	private String descricao;
	private String orgaoGestor;
	private String naturezaJuridica;
	private Endereco endereco;
	private List<Servico> servicos = new ArrayList<>();
	private Set<String> telefones = new HashSet<>();
	
	private Double score;
	private Integer count;
	
	public EstabelecimentoDTO(Estabelecimento obj) {
		id = obj.getId();
		nome = obj.getNome();
		codCnes = obj.getCodCnes();
		descricao = obj.getDescricao();
		orgaoGestor = obj.getOrgaoGestor();
		naturezaJuridica = obj.getNaturezaJuridica();
		endereco = obj.getEndereco();
		servicos = obj.getServicos();
		telefones = obj.getTelefones();
		score = obj.getScore();
		count = obj.getCount();
		
	}	
}
