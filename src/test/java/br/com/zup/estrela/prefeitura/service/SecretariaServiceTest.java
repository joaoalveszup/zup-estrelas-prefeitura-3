package br.com.zup.estrela.prefeitura.service;

import org.junit.Test;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.zup.estrela.prefeitura.dto.MensagemDTO;
import br.com.zup.estrela.prefeitura.entity.Secretaria;
import br.com.zup.estrela.prefeitura.repository.SecretariaRepository;

@RunWith(MockitoJUnitRunner.class)
public class SecretariaServiceTest {
		
	@Mock
	SecretariaRepository secretariaRepository;
	
	@InjectMocks
	SecretariaService secretariaService;
	
	@Test
	public void deveAdicionarSecretaria () {
		
		Secretaria secretaria = new Secretaria();
		
		Secretaria novaSecretaria = new Secretaria();
		
		secretaria.setIdSecretaria(3L);
		
		Mockito.when(secretariaRepository.existsById(3L)).thenReturn(false);
		
		Mockito.when(secretariaRepository.save(Mockito.any())).thenReturn(novaSecretaria);
		
		MensagemDTO retorno = this.secretariaService.adicionarSecretaria(secretaria);
		
		MensagemDTO mensagemEsperada = new MensagemDTO("Secretaria cadastrada com Sucesso.");
		
		Assert.assertEquals("Secretaria cadastrada com sucesso", mensagemEsperada, retorno);
		
	}
}
