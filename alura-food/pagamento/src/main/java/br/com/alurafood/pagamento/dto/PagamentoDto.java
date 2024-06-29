package br.com.alurafood.pagamento.dto;

import br.com.alurafood.pagamento.model.Status;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoDto {

  private Long id;
  private BigDecimal valor;
  private String nome;
  private String numero;
  private String expiracao;
  private String cvc;
  private Status status;
  private Long formaPagamentoId;
  private Long pedidoId;
}
