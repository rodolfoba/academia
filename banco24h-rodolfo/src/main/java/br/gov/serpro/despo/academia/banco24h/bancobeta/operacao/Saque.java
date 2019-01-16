package br.gov.serpro.despo.academia.banco24h.bancobeta.operacao;

import static br.gov.serpro.despo.academia.banco24h.banco.Movimentacao.Tipo.DEBITO;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;

public abstract class Saque extends Movimentacao implements Operacao<Void> {

    private static final BigDecimal TAXA_VALOR = BigDecimal.ONE;
    private static final BigDecimal TAXA_PERCENTUAL = new BigDecimal("0.005"); // 0,5%
    
    public Saque(LocalDate data, BigDecimal valor) {
        super(data, adicionaTaxas(valor));
    }
    
    private static BigDecimal adicionaTaxas(BigDecimal original) {
        BigDecimal taxaEmPercentual = original.multiply(TAXA_PERCENTUAL);
        return original.add(taxaEmPercentual).add(TAXA_VALOR).negate();
    }

    @Override
    public Tipo getTipo() {
        return DEBITO;
    }

    @Override
    public String getDescricao() {
        return "Saque";
    }

}
