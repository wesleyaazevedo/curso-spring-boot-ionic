package com.cursomc.uml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomc.uml.domain.Cliente;

@Repository 	
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
