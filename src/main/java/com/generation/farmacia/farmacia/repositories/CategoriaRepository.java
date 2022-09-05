package com.generation.farmacia.farmacia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.farmacia.farmacia.models.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
	List<CategoriaModel> findAllByDescricaoContainingIgnoreCase(String descricao);
}
