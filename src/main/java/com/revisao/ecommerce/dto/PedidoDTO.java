package com.revisao.ecommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.revisao.ecommerce.entities.ItemDoPedido;
import com.revisao.ecommerce.entities.Pedido;
import com.revisao.ecommerce.entities.StatusDoPedido;

public class PedidoDTO {

	private Long id;
	private StatusDoPedido Status;
	private Instant momento; 
	private Long clienteId;
	
	private List<ItemDoPedidoDTO> items = new ArrayList<>();
	
	public PedidoDTO() {
	}

	public PedidoDTO(Long id, StatusDoPedido status, Instant momento, Long clienteId) {
		this.id = id;
		Status = status;
		this.momento = momento;
		this.clienteId = clienteId;
	}
	public PedidoDTO(Pedido ped) {
		id = ped.getId();
		Status = ped.getStatus();
		momento = ped.getMomento();
		clienteId = ped.getCliente().getId();
		for (ItemDoPedido item : ped.getItems()) {
			ItemDoPedidoDTO itemDto = new ItemDoPedidoDTO(item);
			items.add(itemDto);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusDoPedido getStatus() {
		return Status;
	}

	public void setStatus(StatusDoPedido status) {
		Status = status;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<ItemDoPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDoPedidoDTO> items) {
		this.items = items;
	}
	
}