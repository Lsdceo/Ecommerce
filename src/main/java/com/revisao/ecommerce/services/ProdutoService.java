package com.revisao.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revisao.ecommerce.dto.CategoriaDTO;
import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.entities.Categoria;
import com.revisao.ecommerce.entities.Produto;
import com.revisao.ecommerce.repositories.CategoriaRepository;
import com.revisao.ecommerce.repositories.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repo;
    
    @Autowired
    CategoriaRepository repoCat;

    @Transactional
    public List<ProdutoDTO> findAll() {
        List<Produto> lista = repo.findAll();
        return lista.stream().map(x -> new ProdutoDTO(x)).toList();
    }

    public Page<ProdutoDTO> findPagina(Pageable pagina) {
        Page<Produto> busca = repo.findAll(pagina);
        return busca.map(x -> new ProdutoDTO(x));
    }

    public Optional<ProdutoDTO> findById(Long id) {
        Optional<Produto> produto = repo.findById(id);
        return produto.map(ProdutoDTO::new);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImgUrl(dto.getImgUrl());
        
        for (CategoriaDTO cDto : dto.getCategorias()) {
            Categoria cat = repoCat.getReferenceById(cDto.getId());
            entity.getCategorias().add(cat);
        }

        entity = repo.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        Optional<Produto> optionalProduto = repo.findById(id);
        
        if (optionalProduto.isPresent()) {
            Produto entity = optionalProduto.get();
            entity.setNome(dto.getNome());
            entity.setDescricao(dto.getDescricao());
            entity.setPreco(dto.getPreco());
            entity.setImgUrl(dto.getImgUrl());
            
            entity.getCategorias().clear();
            for (CategoriaDTO cDto : dto.getCategorias()) {
                Categoria cat = repoCat.getReferenceById(cDto.getId());
                entity.getCategorias().add(cat);
            }
            
            entity = repo.save(entity);
            return new ProdutoDTO(entity);
        }
        
        return null;
    }

    public boolean delete(Long id) {
        Optional<Produto> optionalProduto = repo.findById(id);
        
        if (optionalProduto.isPresent()) {
            repo.delete(optionalProduto.get());
            return true;
        }
        
        return false;
    }
}