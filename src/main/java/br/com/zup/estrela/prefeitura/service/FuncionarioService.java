package br.com.zup.estrela.prefeitura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrela.prefeitura.dto.MensagemDTO;
import br.com.zup.estrela.prefeitura.entity.Funcionario;
import br.com.zup.estrela.prefeitura.repository.FuncionarioRepository;

@Service
public class FuncionarioService implements IFuncionarioService {
	
	private static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO = "funcionario cadastrado com Sucesso.";
	private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO = "funcionario alterada com Sucesso.";
	private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO = "funcionario removida com Sucesso.";
	private static final String FUNCIONARIO_JA_CADASTRADO = "O cadastro não ocorreu, funcionario já cadastrado na Prefeitura!";
	private static final String FUNCIONARIO_INEXISTENTE = "A funcionario não existe.";
	
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	public MensagemDTO adicionarFuncionario (Funcionario funcionario) {
		
		if (funcionarioRepository.existsById(funcionario.getIdFuncionario())) {
			return new MensagemDTO
		}
		
	}
	
	
	
}
