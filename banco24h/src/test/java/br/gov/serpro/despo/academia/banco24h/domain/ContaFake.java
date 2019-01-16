package br.gov.serpro.despo.academia.banco24h.domain;

import java.math.BigDecimal;

import br.gov.serpro.despo.academia.banco24h.exceptions.IdentificadorDeContaInvalidoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public class ContaFake extends Conta {
	
	private static final BigDecimal LIMITE = BigDecimal.valueOf(100);

	public ContaFake(IdentificadorDeConta identificadorDeConta, BigDecimal saldo)
			throws IdentificadorDeContaInvalidoException,
			SaldoInsuficienteException {
		super(identificadorDeConta, saldo);
	}

	@Override
	public BigDecimal getLimite() {
		return LIMITE;
	}

}
