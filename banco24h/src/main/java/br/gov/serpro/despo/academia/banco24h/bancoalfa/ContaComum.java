package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import java.math.BigDecimal;

import br.gov.serpro.despo.academia.banco24h.exceptions.IdentificadorDeContaInvalidoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public class ContaComum extends ContaBancoAlfa {

    public ContaComum(IdentificadorDeContaBancoAlfa identificadorDeConta)
            throws IdentificadorDeContaInvalidoException, SaldoInsuficienteException {
        this(identificadorDeConta, BigDecimal.ZERO);
    }

    public ContaComum(IdentificadorDeContaBancoAlfa identificadorDeConta, BigDecimal saldo)
            throws IdentificadorDeContaInvalidoException, SaldoInsuficienteException {
        super(identificadorDeConta, saldo);
    }

    @Override
    public BigDecimal getLimite() {
        return BigDecimal.ZERO;
    }

    @Override
    protected int getLimiteDeOperacoesPorDia() {
        return 3;
    }

}
