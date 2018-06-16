package com.cursomc.uml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.uml.domain.Categoria;
import com.cursomc.uml.repositories.CategoriaRepository;
import com.cursomc.uml.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional <Categoria> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id 
				+", Tipo: "+ Categoria.class.getName()));
	}
	
	//Insere categoria se Id for nulo.
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	//Atualiza categoria quando id não for nulo. 
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}
