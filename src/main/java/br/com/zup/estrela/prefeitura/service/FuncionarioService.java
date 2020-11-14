package br.com.zup.estrela.prefeitura.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrela.prefeitura.dto.AlteraFuncionarioDTO;
import br.com.zup.estrela.prefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrela.prefeitura.dto.MensagemDTO;
import br.com.zup.estrela.prefeitura.entity.Funcionario;
import br.com.zup.estrela.prefeitura.entity.Secretaria;
import br.com.zup.estrela.prefeitura.repository.FuncionarioRepository;

@Service
public class FuncionarioService implements IFuncionarioService {

	private static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO = "funcionario cadastrado com Sucesso.";
	private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO = "funcionario alterado com Sucesso.";
	private static final String FUNCIONARIO_NAO_ALTERADO = "funcionario não pode ser alterado.";
	private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO = "funcionario removida com Sucesso.";
	private static final String FUNCIONARIO_JA_CADASTRADO = "O cadastro não ocorreu, funcionario já cadastrado na Prefeitura!";
	private static final String FUNCIONARIO_INEXISTENTE = "A funcionario não existe.";
	private static final String FUNCIONARIO_NAO_CADASTRADO = "O funcionario não foi cadastrado";

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	ISecretariaService secretariaService;

	public MensagemDTO adicionarFuncionario(Funcionario funcionario) {

		try {

			if (funcionario.getIdFuncionario() != null) {
				return new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);
			}

			alteraOrcamento(funcionario.getSalario(), funcionario.getSecretaria());

			funcionarioRepository.save(funcionario);

		} catch (NullPointerException e) {
			e.printStackTrace();
			return new MensagemDTO(FUNCIONARIO_NAO_CADASTRADO);
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			return new MensagemDTO(FUNCIONARIO_NAO_CADASTRADO);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new MensagemDTO(FUNCIONARIO_NAO_CADASTRADO);
		}

		return new MensagemDTO(FUNCIONARIO_CADASTRADO_COM_SUCESSO);

	}

	private void alteraOrcamento(Double salario, Secretaria secretaria) {
		
		secretaria = secretariaService.buscarSecretaria(secretaria.getIdSecretaria());
		
		if (salario <= secretaria.getOrcamentoFolha()) {
			ajustaOrcamento(secretaria, -salario);
		}

	}

	@Override
	public Funcionario buscarFuncionario(Long idFuncionario) {
		return funcionarioRepository.findById(idFuncionario).orElse(null);
	}

	@Override
	public MensagemDTO alterarFuncionario(Long idFuncionario, AlteraFuncionarioDTO funcionarioNovo) {

		Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

		if (funcionarioConsultado.isPresent()) {

			Funcionario funcionarioAtual = funcionarioConsultado.get();

			if (funcionarioNovo.getSalario() < funcionarioAtual.getSalario()) {
				return new MensagemDTO(FUNCIONARIO_NAO_ALTERADO);
			}

			if (funcionarioAtual.getSecretaria().getIdSecretaria() != funcionarioNovo.getIdSecretaria()) {

				if (!mudancaDeSecretaria(funcionarioNovo, funcionarioAtual)) {
					return new MensagemDTO(FUNCIONARIO_NAO_ALTERADO);
				}

			} else {

				if (!mudancaDeSalario(funcionarioNovo, funcionarioAtual)) {
					return new MensagemDTO(FUNCIONARIO_NAO_ALTERADO);
				}
			}
			Secretaria secretaria = secretariaService.buscarSecretaria(funcionarioNovo.getIdSecretaria());
			funcionarioAtual.setSalario(funcionarioNovo.getSalario());
			funcionarioAtual.setSecretaria(secretaria);
			funcionarioAtual.setFuncao(funcionarioNovo.getFuncao());
			funcionarioAtual.setConcursado(funcionarioNovo.getConcursado());
			funcionarioAtual.setDataAdmissao(funcionarioNovo.getDataAdmissao());

			funcionarioRepository.save(funcionarioAtual);
			return new MensagemDTO(FUNCIONARIO_ALTERADO_COM_SUCESSO);
		}

		return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
	}

	private boolean mudancaDeSalario(AlteraFuncionarioDTO funcionarioNovo, Funcionario funcionarioAtual) {
		Secretaria secretaria = secretariaService.buscarSecretaria(funcionarioNovo.getIdSecretaria());

		if (funcionarioNovo.getSalario() <= secretaria.getOrcamentoFolha()) {

			ajustaOrcamento(secretaria, -(funcionarioNovo.getSalario() - funcionarioAtual.getSalario()));

		} else {
			return false;
		}

		return true;
	}

	private boolean mudancaDeSecretaria(AlteraFuncionarioDTO funcionarioNovo, Funcionario funcionarioAtual) {
		// verificar se o local que ele sera transferido tem orcamento em folha

		Secretaria secretariaNova = secretariaService.buscarSecretaria(funcionarioNovo.getIdSecretaria());

		if (funcionarioNovo.getSalario() <= secretariaNova.getOrcamentoFolha()) {

			Secretaria secretariaAtual = funcionarioAtual.getSecretaria();
			// alterar o valor da folha atual - soma = salario funcionario atual
			ajustaOrcamento(secretariaAtual, funcionarioAtual.getSalario());

			// alterar o valor da folha nova - subritarir = salario do funcionario novo
			ajustaOrcamento(secretariaNova, -funcionarioNovo.getSalario());

		} else {
			return false;
		}

		return true;
	}

	private void ajustaOrcamento(Secretaria secretaria, Double valor) {
		AlteraSecretariaDTO alteraSecretariaDTO = new AlteraSecretariaDTO();

		alteraSecretariaDTO.setArea(secretaria.getArea());
		//alteraSecretariaDTO.setOrcamentoProjetos(secretaria.getOrcamentoProjetos());
		alteraSecretariaDTO.setOrcamentoFolha(secretaria.getOrcamentoFolha() + valor);
		alteraSecretariaDTO.setTelefone(secretaria.getTelefone());
		alteraSecretariaDTO.setEndereco(secretaria.getEndereco());
		alteraSecretariaDTO.setSite(secretaria.getSite());
		alteraSecretariaDTO.setEmail(secretaria.getEmail());

		secretariaService.alterarSecretaria(secretaria.getIdSecretaria(), alteraSecretariaDTO);

	}

	@Override
	public MensagemDTO removerFuncionario(Long idFuncionario) {
		Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);
		
		if (funcionarioConsultado.isPresent()) {

			Funcionario funcionarioAtual = funcionarioConsultado.get();
			
			Secretaria secretariaAtual = funcionarioAtual.getSecretaria();
			
			ajustaOrcamento(secretariaAtual, funcionarioAtual.getSalario());
			
			funcionarioRepository.delete(funcionarioAtual);
			
		}
		
		return new MensagemDTO (FUNCIONARIO_REMOVIDO_COM_SUCESSO);
	}

	@Override
	public List<Funcionario> listarFuncionarios() {
		return (List<Funcionario>) funcionarioRepository.findAll();
	}

}
