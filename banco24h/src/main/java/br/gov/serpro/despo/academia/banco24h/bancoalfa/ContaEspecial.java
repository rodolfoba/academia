package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import java.math.BigDecimal;

import br.gov.serpro.despo.academia.banco24h.exceptions.IdentificadorDeContaInvalidoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public class ContaEspecial extends ContaBancoAlfa {

    public ContaEspecial(IdentificadorDeContaBancoAlfa identificadorDeConta)
            throws IdentificadorDeContaInvalidoException, SaldoInsuficienteException {
        this(identificadorDeConta, BigDecimal.ZERO);
    }

    public ContaEspecial(IdentificadorDeContaBancoAlfa identificadorDeConta, BigDecimal saldo)
            throws IdentificadorDeContaInvalidoException, SaldoInsuficienteException {
        super(identificadorDeConta, saldo);
    }

    @Override
    public BigDecimal getLimite() {
        return BigDecimal.valueOf(1000);
    }

    @Override
    protected int getLimiteDeOperacoesPorDia() {
        return 0;
    }

}
