package br.gov.serpro.despo.academia.banco24h.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import static org.junit.Assert.*;

import br.gov.serpro.despo.academia.banco24h.exceptions.IdentificadorDeContaInvalidoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public class ContaTest {
	
    @Test
    public void deveConstruirCorretamente() throws Exception {
        IdentificadorDeContaFake identificador = new IdentificadorDeContaFake(1);
        BigDecimal saldo = BigDecimal.ZERO;
        ContaFake conta = new ContaFake(identificador, saldo);
        assertEquals(identificador, conta.getIdentificadorDeConta());
        assertEquals(saldo, conta.getSaldo());
    }
    
	@Test(expected=IdentificadorDeContaInvalidoException.class)
	public void deveLancarIdentificadorDeContaInvalidoException() throws Exception {
		IdentificadorDeConta identificadorDeConta = new IdentificadorDeContaFake(-1L);
		new ContaFake(identificadorDeConta, BigDecimal.ZERO);
	}
	
	@Test(expected=SaldoInsuficienteException.class)
	public void deveLancarSaldoInsuficienteException() throws Exception {
		IdentificadorDeConta identificadorDeConta = new IdentificadorDeContaFake(1);
		new ContaFake(identificadorDeConta, BigDecimal.valueOf(-101));
	}
	
	@Test
	public void deveAdicionarOperacaoCredito() throws Exception {
		IdentificadorDeConta identificadorDeConta = new IdentificadorDeContaFake(1);
		Conta conta = new ContaFake(identificadorDeConta, BigDecimal.valueOf(100));
		Transacao transacao = new Transacao(BigDecimal.valueOf(100L), LocalDate.now(), "Depósito Teste");
		
		conta.addTransacao(transacao);
		
		assertEquals(BigDecimal.valueOf(200L), conta.getSaldo());
		assertEquals(1, conta.getTransacoes().size());
	}
	
	@Test
	public void deveAdicionarOperacaoDebito() throws Exception {
		IdentificadorDeConta identificadorDeConta = new IdentificadorDeContaFake(1);
		Conta conta = new ContaFake(identificadorDeConta, BigDecimal.valueOf(100));
		Transacao transacao = new Transacao(BigDecimal.valueOf(-150L), LocalDate.now(), "Débito Teste");
		
		conta.addTransacao(transacao);
		
		assertEquals(BigDecimal.valueOf(-50), conta.getSaldo());
		assertEquals(1, conta.getTransacoes().size());
	}
	
	@Test(expected=SaldoInsuficienteException.class)
	public void deveLancarSaldoInsuficienteExceptionQuandoExcederLimite() throws Exception {
		IdentificadorDeConta identificadorDeConta = new IdentificadorDeContaFake(1);
		Conta conta = new ContaFake(identificadorDeConta, BigDecimal.valueOf(50));
		Transacao transacao = new Transacao(BigDecimal.valueOf(-200), LocalDate.now(), "Débito Teste");
		
		conta.addTransacao(transacao);
		
		assertEquals(BigDecimal.valueOf(200L), conta.getSaldo());
		assertEquals(1, conta.getTransacoes().size());
	}
	
	@Test
	public void deveAdicionarOperacoes() throws Exception {
		IdentificadorDeConta identificadorDeConta = new IdentificadorDeContaFake(1);
		Conta conta = new ContaFake(identificadorDeConta, BigDecimal.valueOf(100));
		
		conta.addTransacao(new Transacao(BigDecimal.valueOf(100), LocalDate.now(), "Depósito Teste"));
		conta.addTransacao(new Transacao(BigDecimal.valueOf(50), LocalDate.now(), "Depósito Teste 2"));
		
		assertEquals(BigDecimal.valueOf(250L), conta.getSaldo());
		assertEquals(2, conta.getTransacoes().size());
	}
}
