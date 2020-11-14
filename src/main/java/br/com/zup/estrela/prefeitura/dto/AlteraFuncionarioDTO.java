package br.com.zup.estrela.prefeitura.dto;

import java.time.LocalDate;

public class AlteraFuncionarioDTO {
	
	private Double salario;
	
	private Long idSecretaria;
	
	private String funcao;
	
	private Boolean concursado;
	
	private LocalDate dataAdmissao;

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Long getIdSecretaria() {
		return idSecretaria;
	}

	public void setIdSecretaria(Long idSecretaria) {
		this.idSecretaria = idSecretaria;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Boolean getConcursado() {
		return concursado;
	}

	public void setConcursado(Boolean concursado) {
		this.concursado = concursado;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

}
