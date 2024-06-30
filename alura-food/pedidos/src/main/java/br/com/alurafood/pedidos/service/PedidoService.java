package br.com.alurafood.pedidos.service;

import br.com.alurafood.pedidos.dto.PedidoDto;
import br.com.alurafood.pedidos.model.Pedido;
import br.com.alurafood.pedidos.model.StatusPedido;
import br.com.alurafood.pedidos.repository.PedidoRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PedidoService {

  private final PedidoRepository repository;
  private final ModelMapper modelMapper;

  public PedidoDto criarPedido(PedidoDto pedidoDto) {
    Pedido pedido = modelMapper.map(pedidoDto, Pedido.class);
    pedido.setDataHora(LocalDateTime.now());
    pedido.setStatus(StatusPedido.REALIZADO);
    pedido.getItemPedidos().forEach(item -> item.setPedido(pedido));

    Pedido pedidoSalvo = repository.save(pedido);
    return modelMapper.map(pedidoSalvo, PedidoDto.class);
  }

  public Page<PedidoDto> obterTodosPedidos(Pageable paginacao) {
    return repository.findAll(paginacao)
        .map(pedido -> modelMapper.map(pedido, PedidoDto.class));
  }

  public PedidoDto obterPedidoPorId(Long id) {
    Pedido pedido = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    return modelMapper.map(pedido, PedidoDto.class);
  }
}
