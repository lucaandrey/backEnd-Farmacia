package com.generation.farmacia.farmacia.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.farmacia.models.ProdutoModel;
import com.generation.farmacia.farmacia.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/{nomeProduto}")
	public ResponseEntity<List<ProdutoModel>> getByNome(@PathVariable String nomeProduto){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> showAllProducts(ProdutoModel produtoModel){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
	}
	@PostMapping
	public ResponseEntity<ProdutoModel> postProducts(@Valid @RequestBody ProdutoModel produtoModel){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
	}
	@PutMapping
	private ResponseEntity <ProdutoModel> putProduto(@Valid @RequestBody ProdutoModel produtoModel){
		return produtoRepository.findById(produtoModel.getId())
				.map(resposta -> ResponseEntity.ok(produtoRepository.save(produtoModel)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		Optional<ProdutoModel> produtoModel = produtoRepository.findById(id);
		if(produtoModel.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);
	}
}
