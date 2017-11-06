package br.com.wdcode.validacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wdcode.validacao.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long	> {

}
