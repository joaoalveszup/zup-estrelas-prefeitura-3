package br.com.zup.estrela.prefeitura.service;

import java.util.List;

import br.com.zup.estrela.prefeitura.dto.MensagemDTO;
import br.com.zup.estrela.prefeitura.entity.Funcionario;

public interface IFuncionarioService {

	public MensagemDTO adicionarFuncionario (Funcionario funcionario);
	
	public Funcionario buscarFuncionario (Long idFuncionario);
	
	public MensagemDTO alterarFuncionario (Long idFuncionario);
	
	public MensagemDTO removerFuncionario (Long idFuncionario);
	
	public List<Funcionario> listarFuncionarios();
		
}