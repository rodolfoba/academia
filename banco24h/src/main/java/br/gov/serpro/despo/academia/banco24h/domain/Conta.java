package br.gov.serpro.despo.academia.banco24h.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.gov.serpro.despo.academia.banco24h.exceptions.IdentificadorDeContaInvalidoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public abstract class Conta {
	
	private BigDecimal saldo;
	private List<Transacao> transacoes = new ArrayList<>();
	private IdentificadorDeConta identificadorDeConta;
	
	public Conta(IdentificadorDeConta identificadorDeConta, BigDecimal saldo) throws IdentificadorDeContaInvalidoException, SaldoInsuficienteException{
		if (!identificadorDeConta.isValid()) {
			throw new IdentificadorDeContaInvalidoException("Identificador de conta inválido: " + identificadorDeConta);
		}
		if (saldo.compareTo(getLimite().negate()) < 0) {
			throw new SaldoInsuficienteException("Não é possível criar conta com saldo abaixo do limite. Limite = " + getLimite() );
		}
		
		this.identificadorDeConta = identificadorDeConta;
		this.saldo = saldo;
	}
	
	public void addTransacao(Transacao transacao) throws SaldoInsuficienteException {
		BigDecimal saldoProjetado = saldo.add(transacao.getValor());
		if (saldoProjetado.compareTo(getLimite().negate()) < 0 ) {
			throw new SaldoInsuficienteException("Saldo Insuficiente para essa operação. Saldo="+saldo+" Limite=" + getLimite() );
		}
		saldo = saldoProjetado;
		transacoes.add(transacao);
	}
		
	public IdentificadorDeConta getIdentificadorDeConta() {
		return identificadorDeConta;
	}
	
	public List<Transacao> getTransacoes(){
		// Cria uma nova lista com as mesmas transações (imutáveis), para evitar que as transacoes da conta sejam manipuladas diretamente
		return new ArrayList<Transacao>(transacoes);
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}

	public abstract BigDecimal getLimite();
	
}
