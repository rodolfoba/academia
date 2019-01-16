package br.gov.serpro.despo.academia.banco24h.banco;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Movimentacao {

    public enum Tipo {CREDITO, DEBITO}
    
    private final LocalDate data;
    private final BigDecimal valor;
    
    public Movimentacao(LocalDate data, BigDecimal valor) {
        super();
        this.data = data;
        this.valor = valor;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public BigDecimal getValor() {
        return valor;
    }
    
    public abstract Tipo getTipo();
    
    public abstract String getDescricao();
    
}
