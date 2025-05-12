package com.revisao.ecommerce.dto;

import com.revisao.ecommerce.entities.ItemDoPedido;

public class ItemDoPedidoDTO {

	private Long produtoId;
	private String nome;
	private Double preco;
	private Integer quantidade;
	private String imgUrl;
	
	public ItemDoPedidoDTO(){
		
	}
	
	public ItemDoPedidoDTO(Long produtoId, String nome, Double preco, Integer quantidade, String imgUrl) {
		this.produtoId = produtoId;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.imgUrl = imgUrl;
	}
	
	public ItemDoPedidoDTO(ItemDoPedido entity) {
		this.produtoId = entity.getProduto().getId();
		this.nome = entity.getProduto().getNome();
		this.preco = entity.getPreco();
		this.quantidade = entity.getQuantidade();
		this.imgUrl = entity.getProduto().getImgUrl();
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Double getSubTotal() {
		return quantidade * preco;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}