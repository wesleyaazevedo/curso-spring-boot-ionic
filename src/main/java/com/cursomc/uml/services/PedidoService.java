package com.cursomc.uml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.uml.domain.Pedido;
import com.cursomc.uml.repositories.PedidoRepository;
import com.cursomc.uml.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional <Pedido> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id 
				+", Tipo: "+ Pedido.class.getName()));
	}

}
