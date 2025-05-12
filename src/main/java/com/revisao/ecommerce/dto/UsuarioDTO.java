package com.revisao.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.revisao.ecommerce.entities.Pedido;
import com.revisao.ecommerce.entities.Usuario;

import jakarta.persistence.Column;

public class UsuarioDTO {
	
	private Long id;
	private String nome;
	@Column(unique = true)
	private String email;
	private String telefone;
	private String senha;
	
	private List<PedidoDTO> pedidos = new ArrayList<>();
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Long id, String nome, String email, String telefone, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
	}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		telefone = entity.getTelefone();
		senha = entity.getSenha();
		for(Pedido ped : entity.getPedidos()) {
			pedidos.add(new PedidoDTO(ped));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
		
}