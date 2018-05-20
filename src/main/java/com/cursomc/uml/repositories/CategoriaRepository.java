package com.cursomc.uml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomc.uml.domain.Categoria;

@Repository 	
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
