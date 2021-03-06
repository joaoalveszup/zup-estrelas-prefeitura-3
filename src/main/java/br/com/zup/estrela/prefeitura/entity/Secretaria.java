package br.com.zup.estrela.prefeitura.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.zup.estrela.prefeitura.enums.Area;

@Entity
@Table(name = "secreataria")
public class Secretaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_secretaria")
	private Long idSecretaria;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Area area;

	//@Column(name = "orcamento_projeto", nullable = false)
	//private Double orcamentoProjeto;
	
	@Column(name = "orcamento_folha", nullable = false)
	private Double orcamentoFolha;
	
	@Column(nullable =  false)
	private String telefone;
	
	@Column(nullable =  false)
	private String endereco;
	
	@Column(nullable = false)
	private String site;
	
	@Column(nullable =  false)
	private String email;
	
	@OneToMany
	private List<Funcionario> funcionarios;
	
	//List<Projeto> projetos;
	
	
	public Long getIdSecretaria() {
		return idSecretaria;
	}

	public void setIdSecretaria(Long idSecretaria) {
		this.idSecretaria = idSecretaria;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

//	public Double getOrcamentoProjetos() {
//		return orcamentoProjeto;
//	}
//
//	public void setOrcamentoProjetos(Double orcamentoProjetos) {
//		this.orcamentoProjeto = orcamentoProjetos;
//	}

	public Double getOrcamentoFolha() {
		return orcamentoFolha;
	}

	public void setOrcamentoFolha(Double orcamentoFolha) {
		this.orcamentoFolha = orcamentoFolha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
