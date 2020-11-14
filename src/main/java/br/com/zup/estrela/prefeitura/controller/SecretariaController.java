package br.com.zup.estrela.prefeitura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrela.prefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrela.prefeitura.dto.MensagemDTO;
import br.com.zup.estrela.prefeitura.entity.Secretaria;
import br.com.zup.estrela.prefeitura.service.ISecretariaService;


@RestController
@RequestMapping("/secretarias")
public class SecretariaController {

	@Autowired
	ISecretariaService secretariaService; 
	
	@PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO adicionaSecretaria(@RequestBody Secretaria secretaria) {
		return secretariaService.adicionarSecretaria(secretaria);
	}
	
	@GetMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Secretaria buscarSecretaria(@PathVariable Long idSecretaria) {
        return secretariaService.buscarSecretaria(idSecretaria);
	}
	
	@PutMapping(path = "/{idSecretaria}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO alterarSecretaria(@PathVariable Long idSecretaria, @RequestBody AlteraSecretariaDTO secretaria) {
		return secretariaService.alterarSecretaria(idSecretaria, secretaria); 
	}


	@DeleteMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO removerSecretaria(@PathVariable Long idSecretaria) {
		return secretariaService.removerSecretaria(idSecretaria);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Secretaria> listarSecretarias() {
        return secretariaService.listarSecretarias();
    }
	
}
