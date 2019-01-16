package br.gov.serpro.despo.academia.banco24h.bancobeta.operacao;

import static br.gov.serpro.despo.academia.banco24h.banco.Movimentacao.Tipo.CREDITO;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;

public class RecebimentoDeTransferencia extends Movimentacao {

    public RecebimentoDeTransferencia(LocalDate data, BigDecimal valor) {
        super(data, valor);
    }

    @Override
    public Tipo getTipo() {
        return CREDITO;
    }

    @Override
    public String getDescricao() {
        return "Recebimento de transferência";
    }
    
}
