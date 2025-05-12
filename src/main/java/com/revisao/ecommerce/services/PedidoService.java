package com.revisao.ecommerce.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revisao.ecommerce.dto.ItemDoPedidoDTO;
import com.revisao.ecommerce.dto.PedidoDTO;
import com.revisao.ecommerce.entities.ItemDoPedido;
import com.revisao.ecommerce.entities.Pedido;
import com.revisao.ecommerce.entities.Produto;
import com.revisao.ecommerce.entities.StatusDoPedido;
import com.revisao.ecommerce.entities.Usuario;
import com.revisao.ecommerce.repositories.ItemDoPedidoRepository;
import com.revisao.ecommerce.repositories.PedidoRepository;
import com.revisao.ecommerce.repositories.ProdutoRepository;
import com.revisao.ecommerce.repositories.UsuarioRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ItemDoPedidoRepository itemPedidoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public PedidoDTO inserir(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setMomento(Instant.now());
        pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
        
        Usuario cliente = usuarioRepository.getReferenceById(dto.getClienteId());
        pedido.setCliente(cliente);
        
        for(ItemDoPedidoDTO itemDto : dto.getItems()) {
            Produto produto = produtoRepository.getReferenceById(itemDto.getProdutoId());
            ItemDoPedido item = new ItemDoPedido(pedido, produto, itemDto.getQuantidade(), itemDto.getPreco());
            pedido.getItems().add(item);
        }
        
        pedido = pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItems());
        return new PedidoDTO(pedido);
    }

    public List<PedidoDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                      .map(PedidoDTO::new)
                      .collect(Collectors.toList());
    }

    public PedidoDTO findById(Long id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        Pedido pedido = obj.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        try {
            Pedido pedido = pedidoRepository.getReferenceById(id);
            pedido.setStatus(dto.getStatus());
            pedido = pedidoRepository.save(pedido);
            return new PedidoDTO(pedido);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o pedido", e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            pedidoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o pedido", e);
        }
    }
    
    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        // Este método parece duplicado com o método 'inserir'
        // Pode ser removido ou unificado com o método 'inserir'
        Pedido entity = new Pedido();
        entity.setMomento(Instant.now());
        entity.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
        
        for (ItemDoPedidoDTO ipDto : dto.getItems()) {
            Produto produto = produtoRepository.getReferenceById(ipDto.getProdutoId());
            ItemDoPedido item = new ItemDoPedido(entity, produto, ipDto.getQuantidade(), produto.getPreco());
            entity.getItems().add(item);
        }

        pedidoRepository.save(entity);
        itemPedidoRepository.saveAll(entity.getItems());
        return new PedidoDTO(entity);
    }
}