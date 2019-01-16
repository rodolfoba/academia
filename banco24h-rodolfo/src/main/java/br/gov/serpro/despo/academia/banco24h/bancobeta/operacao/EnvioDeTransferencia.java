package br.gov.serpro.despo.academia.banco24h.bancobeta.operacao;

import static br.gov.serpro.despo.academia.banco24h.banco.Movimentacao.Tipo.DEBITO;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;

public class EnvioDeTransferencia extends Movimentacao {

    private static final BigDecimal TAXA_VALOR = BigDecimal.ONE;

    public EnvioDeTransferencia(LocalDate data, BigDecimal valor) {
        super(data, adicionaTaxas(valor));
    }

    private static BigDecimal adicionaTaxas(BigDecimal original) {
        return original.add(TAXA_VALOR);
    }

    @Override
    public Tipo getTipo() {
        return DEBITO;
    }

    @Override
    public String getDescricao() {
        return "Envio de transferência";
    }

}
