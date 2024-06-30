package br.com.alurafood.pagamento.service;

import br.com.alurafood.pagamento.client.PedidoClient;
import br.com.alurafood.pagamento.dto.PagamentoDto;
import br.com.alurafood.pagamento.model.Pagamento;
import br.com.alurafood.pagamento.model.Status;
import br.com.alurafood.pagamento.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PagamentoService {

  private final PagamentoRepository repository;
  private final ModelMapper modelMapper;
  private final PedidoClient pedido;

  public Page<PagamentoDto> obterTodosOsPagamentos(Pageable paginacao) {
    return repository.findAll(paginacao)
        .map(pagamento -> modelMapper.map(pagamento, PagamentoDto.class));
  }

  public PagamentoDto obterPorId(Long id) {
    return repository.findById(id).map(pagamento -> modelMapper.map(pagamento, PagamentoDto.class))
        .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
  }

  public PagamentoDto criarPagamento(PagamentoDto pagamentoDto) {
    var pagamento = modelMapper.map(pagamentoDto, Pagamento.class);
    pagamento.setStatus(Status.CRIADO);
    repository.save(pagamento);
    return modelMapper.map(pagamento, PagamentoDto.class);
  }

  public PagamentoDto atualizarPagamento(Long id, PagamentoDto pagamentoDto) {
    var pagamento = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

    var currentId = pagamento.getId();
    var currentStatus = pagamento.getStatus();

    modelMapper.map(pagamentoDto, pagamento);

    pagamento.setId(currentId);
    pagamento.setStatus(currentStatus);

    repository.save(pagamento);

    return modelMapper.map(pagamento, PagamentoDto.class);
  }

  public void deletarPagamento(Long id) {
    repository.deleteById(id);
  }

  public void confirmarPagamento(Long id) {
    var pagamento = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

    pagamento.setStatus(Status.CONFIRMADO);
    repository.save(pagamento);
    pedido.atualizaPagamento(pagamento.getPedidoId());
  }
}
