package br.gov.serpro.despo.academia.banco24h.banco;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaqueFake extends Movimentacao {

    public static final String DESCRICAO = "Saque Fake";
    
    public SaqueFake(LocalDate data, BigDecimal valor) {
        super(data, valor);
    }

    @Override
    public Tipo getTipo() {
        return Tipo.DEBITO;
    }

    @Override
    public String getDescricao() {
        return DESCRICAO;
    }

}
