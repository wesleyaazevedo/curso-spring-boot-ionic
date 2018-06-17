package com.cursomc.uml.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cursomc.uml.domain.Categoria;
import com.cursomc.uml.repositories.CategoriaRepository;
import com.cursomc.uml.resources.dto.CategoriaDTO;
import com.cursomc.uml.services.exceptions.DataIntegrityException;
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
	
	//Deletar Categoria
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria com produtos cadastrados.");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
}
