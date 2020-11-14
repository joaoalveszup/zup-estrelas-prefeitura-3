package br.com.zup.estrela.prefeitura.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrela.prefeitura.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository <Funcionario, Long> {

}
