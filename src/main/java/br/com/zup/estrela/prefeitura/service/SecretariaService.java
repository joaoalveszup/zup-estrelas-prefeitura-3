package br.com.zup.estrela.prefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrela.prefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrela.prefeitura.dto.MensagemDTO;
import br.com.zup.estrela.prefeitura.entity.Secretaria;
import br.com.zup.estrela.prefeitura.repository.SecretariaRepository;

@Service
public class SecretariaService implements ISecretariaService {

	// mudar para um ennum
	private static final String SECRETARIA_CADASTRADA_COM_SUCESSO = "Secretaria cadastrada com Sucesso.";
	private static final String SECRETARIA_ALTERADA_COM_SUCESSO = "Secretaria alterada com Sucesso.";
	private static final String SECRETARIA_REMOVIDA_COM_SUCESSO = "Secretaria removida com Sucesso.";
	private static final String SECRETARIA_JA_CADASTRADA = "O cadastro não ocorreu, secretaria já cadastrada na Prefeitura!";
	private static final String SECRETARIA_INEXISTENTE = "A Secretaria não existe ou não foi cadastrada em nosso banco.";

	@Autowired
	SecretariaRepository secretariaRepository;

	public MensagemDTO adicionarSecretaria(Secretaria secretaria) {

		if (secretaria.getIdSecretaria() != null) {
			return new MensagemDTO(SECRETARIA_JA_CADASTRADA);
		}

		secretariaRepository.save(secretaria);
		return new MensagemDTO(SECRETARIA_CADASTRADA_COM_SUCESSO);

	}

	public Secretaria buscarSecretaria(Long idSecretaria) {
		return secretariaRepository.findById(idSecretaria).orElse(null);
	}

	public MensagemDTO alterarSecretaria(Long idSecretaria, AlteraSecretariaDTO alteraSecretariaDTO) {

		Optional<Secretaria> secretariaConsultada = secretariaRepository.findById(idSecretaria);

		if (secretariaConsultada.isPresent()) {

			Secretaria secretariaAlterada = secretariaConsultada.get();

			secretariaAlterada.setArea(alteraSecretariaDTO.getArea());
		//	secretariaAlterada.setOrcamentoProjetos(alteraSecretariaDTO.getOrcamentoProjetos());
			secretariaAlterada.setOrcamentoFolha(alteraSecretariaDTO.getOrcamentoFolha());
			secretariaAlterada.setTelefone(alteraSecretariaDTO.getTelefone());
			secretariaAlterada.setEndereco(alteraSecretariaDTO.getEndereco());
			secretariaAlterada.setSite(alteraSecretariaDTO.getSite());
			secretariaAlterada.setEmail(alteraSecretariaDTO.getEmail());

			secretariaRepository.save(secretariaAlterada);
			return new MensagemDTO(SECRETARIA_ALTERADA_COM_SUCESSO);
		}

		return new MensagemDTO(SECRETARIA_INEXISTENTE);
	}

	public MensagemDTO removerSecretaria(Long idSecretaria) {

		if (secretariaRepository.existsById(idSecretaria)) {
			secretariaRepository.deleteById(idSecretaria);
			return new MensagemDTO(SECRETARIA_REMOVIDA_COM_SUCESSO);
		}

		return new MensagemDTO(SECRETARIA_INEXISTENTE);
	}

	public List<Secretaria> listarSecretarias() {
		return (List<Secretaria>) secretariaRepository.findAll();
	}

}
