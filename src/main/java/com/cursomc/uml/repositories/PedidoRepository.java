package com.cursomc.uml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomc.uml.domain.Pedido;

@Repository 	
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
