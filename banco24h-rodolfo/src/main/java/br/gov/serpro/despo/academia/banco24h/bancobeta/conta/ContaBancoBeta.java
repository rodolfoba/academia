package br.gov.serpro.despo.academia.banco24h.bancobeta.conta;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;
import br.gov.serpro.despo.academia.banco24h.banco.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.banco.SaldoInsuficienteException;

public abstract class ContaBancoBeta {

    private final IdentificadorDeConta identificador;
    private final Set<Movimentacao> movimentacoes;
    private BigDecimal saldo;
    
    public ContaBancoBeta(IdentificadorDeConta identificador) {
        if (null == identificador) {
            throw new IllegalArgumentException("Identificador inválido");
        }
        
        this.identificador = identificador;
        this.movimentacoes = new LinkedHashSet<>();
        this.saldo = BigDecimal.ZERO;
    }
    
    public void movimentar(Movimentacao movimentacao) throws SaldoInsuficienteException, OperacaoBancariaException {
        BigDecimal saldoProjetado = saldo.add(movimentacao.getValor());
        if (saldoProjetado.compareTo(getLimiteNegativo().negate()) < 0 ) {
            throw new SaldoInsuficienteException("Saldo Insuficiente para essa operação. Saldo="+saldo+" Limite=" + getLimiteNegativo());
        }
        
        saldo = saldoProjetado;
        movimentacoes.add(movimentacao);
    }
    
    public Set<Movimentacao> getMovimentacoes() {
        return new LinkedHashSet<>(movimentacoes);
    }
    
    public BigDecimal getSaldo() {
        return saldo;
    }
    
    public IdentificadorDeConta getIdentificador() {
        return identificador;
    }
    
    protected abstract BigDecimal getLimiteNegativo();
    
}
