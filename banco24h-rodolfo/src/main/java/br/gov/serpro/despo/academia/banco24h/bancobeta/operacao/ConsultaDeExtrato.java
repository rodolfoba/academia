package br.gov.serpro.despo.academia.banco24h.bancobeta.operacao;

import static br.gov.serpro.despo.academia.banco24h.banco.Movimentacao.Tipo.DEBITO;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;

public class ConsultaDeExtrato extends Movimentacao {

    private static final BigDecimal TAXA_VALOR = new BigDecimal("0.5");
    
    public ConsultaDeExtrato(LocalDate data) {
        super(data, TAXA_VALOR);
    }
    
    @Override
    public Tipo getTipo() {
        return DEBITO;
    }

    @Override
    public String getDescricao() {
        return "Taxa de Consulta de Extrato";
    }
    
}
