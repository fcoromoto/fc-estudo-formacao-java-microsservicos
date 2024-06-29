package br.com.alurafood.pagamento.controller;

import br.com.alurafood.pagamento.dto.PagamentoDto;
import br.com.alurafood.pagamento.service.PagamentoService;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

  private final PagamentoService pagamentoService;

  @GetMapping
  public ResponseEntity<Page<PagamentoDto>> obterTodosOsPagamentos(@PageableDefault(size = 10) Pageable pageable) {
    return ResponseEntity.ok(pagamentoService.obterTodosOsPagamentos(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PagamentoDto> obterPorId(@PathVariable Long id) {
    return ResponseEntity.ok(pagamentoService.obterPorId(id));
  }

  @PostMapping
  public ResponseEntity<PagamentoDto> criarPagamento(@RequestBody PagamentoDto pagamentoDto, UriComponentsBuilder uriBuilder) {
    var pagamento = pagamentoService.criarPagamento(pagamentoDto);
    var endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
    return ResponseEntity.created(endereco).body(pagamento);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PagamentoDto> atualizarPagamento(@PathVariable Long id, @RequestBody PagamentoDto pagamentoDto) {
    return ResponseEntity.ok(pagamentoService.atualizarPagamento(id, pagamentoDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
    pagamentoService.deletarPagamento(id);
    return ResponseEntity.noContent().build();
  }
}
