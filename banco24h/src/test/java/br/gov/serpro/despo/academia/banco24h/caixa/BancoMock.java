package br.gov.serpro.despo.academia.banco24h.caixa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.gov.serpro.despo.academia.banco24h.domain.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.domain.Transacao;
import br.gov.serpro.despo.academia.banco24h.exceptions.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;
import br.gov.serpro.despo.academia.banco24h.interfaces.BancoOperavel;

/**
 * Esse Banco Mock é usado apenas para testar o Caixa24h
 * 
 *
 */
public class BancoMock implements BancoOperavel {

	public enum TipoOperacao{
		SACAR,
		DEPOSITAR,
		TRANSFERIR,
		CONSULTAR_SALDO,
		CONSULTAR_EXTRATO
	}
	
	private String identificacao;
	
    private BigDecimal valorRecebidoParametro;
    private TipoOperacao operacaoInvocada;
    
	
	public BancoMock() {
	}
	
	public BancoMock(String identificacao) {
		this.identificacao = identificacao;
	}
	
	@Override
	public String getIdentificacao() {
		return identificacao;
	}

	@Override
	public String getNome() {
		return null;
	}

	@Override
	public void sacar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
			throws SaldoInsuficienteException, OperacaoBancariaException {
		this.operacaoInvocada = TipoOperacao.SACAR;
		this.valorRecebidoParametro = valor;
	}

	@Override
	public void depositar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
			throws OperacaoBancariaException {
		this.operacaoInvocada = TipoOperacao.DEPOSITAR;
		this.valorRecebidoParametro = valor;
	}

	@Override
	public void transferir(IdentificadorDeConta origem,
			IdentificadorDeConta destino, BigDecimal valor)
			throws SaldoInsuficienteException, OperacaoBancariaException {
		this.operacaoInvocada = TipoOperacao.TRANSFERIR;
		this.valorRecebidoParametro = valor;
	}

	/**
	 * Retorna sempre 1, apenas para testar a chamada
	 */
	@Override
	public BigDecimal consultarSaldo(IdentificadorDeConta identificadorDeConta)
			throws OperacaoBancariaException {
		this.operacaoInvocada = TipoOperacao.CONSULTAR_SALDO;
		
		return null;
	}

	@Override
	public List<Transacao> consultarExtrato(
			IdentificadorDeConta identificadorDeConta, LocalDate dataInicial, LocalDate dataFinal)
			throws OperacaoBancariaException {
		this.operacaoInvocada = TipoOperacao.CONSULTAR_EXTRATO;
		
		return null;
	}
	
	public TipoOperacao getOperacaoInvocada() {
		return operacaoInvocada;
	}

	public BigDecimal getValorRecebidoParametro() {
		return valorRecebidoParametro;
	}

}
