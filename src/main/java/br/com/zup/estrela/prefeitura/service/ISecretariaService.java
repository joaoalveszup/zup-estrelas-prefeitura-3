package br.com.zup.estrela.prefeitura.service;

import java.util.List;

import br.com.zup.estrela.prefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrela.prefeitura.dto.MensagemDTO;
import br.com.zup.estrela.prefeitura.entity.Secretaria;

public interface ISecretariaService {

	public MensagemDTO adicionarSecretaria (Secretaria secretaria);
	
	public Secretaria buscarSecretaria (Long idSecretaria);
	
	public MensagemDTO alterarSecretaria (Long idSecretaria, AlteraSecretariaDTO alteraSecretariaDTO );
	
	public MensagemDTO removerSecretaria (Long idSecretaria);
	
	public List<Secretaria> listarSecretarias();
	
}
