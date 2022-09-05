package com.generation.farmacia.farmacia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.farmacia.farmacia.models.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{
	
	public List<ProdutoModel> findAllByNomeProdutoContainingIgnoreCase(String nomeProduto);

}
