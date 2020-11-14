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

import br.com.zup.estrela.prefeitura.dto.AlteraFuncionarioDTO;
import br.com.zup.estrela.prefeitura.dto.MensagemDTO;
import br.com.zup.estrela.prefeitura.entity.Funcionario;
import br.com.zup.estrela.prefeitura.service.IFuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	IFuncionarioService funcionarioService; 
	
	@PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO adicionaFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioService.adicionarFuncionario(funcionario);
	}
	
	@GetMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Funcionario buscarFuncionario(@PathVariable Long idFuncionario) {
        return funcionarioService.buscarFuncionario(idFuncionario);
	}
	
	@PutMapping(path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO alterarFuncionario(@PathVariable Long idFuncionario, @RequestBody AlteraFuncionarioDTO funcionario) {
		return funcionarioService.alterarFuncionario(idFuncionario, funcionario); 
	}

	@DeleteMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO removerFuncionario(@PathVariable Long idFuncionario) {
		return funcionarioService.removerFuncionario(idFuncionario);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }
	
}
