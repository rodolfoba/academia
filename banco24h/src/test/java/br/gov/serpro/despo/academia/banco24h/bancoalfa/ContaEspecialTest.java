package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.gov.serpro.despo.academia.banco24h.domain.Transacao;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public class ContaEspecialTest {

    ContaEspecial conta;
    IdentificadorDeContaBancoAlfa identificador;
    BigDecimal valor;
    LocalDate data = LocalDate.now();
    String descricao = "DESCRICAO";
    Transacao transacao;
    
    @Before
    public void before() {
        identificador = new IdentificadorDeContaBancoAlfa(1); 
    }
    
    @Test
    public void contaComSaldoPadrao() throws Exception {
        conta = new ContaEspecial(identificador);
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
    }
    
    @Test
    public void criacaoDeContaComSaldoInicial() throws Exception {
        BigDecimal saldo = BigDecimal.ONE;
        conta = new ContaEspecial(identificador, saldo);
        assertEquals(saldo, conta.getSaldo());
        
        saldo = new BigDecimal("1000.00").negate();
        conta = new ContaEspecial(identificador, saldo);
        assertEquals(saldo, conta.getSaldo());
        
        assertEquals(identificador, conta.getIdentificadorDeConta());
    }
    
    @Test(expected = SaldoInsuficienteException.class)
    public void criacaoDeContaComSaldoInicialInvalido() throws Exception {
        conta = new ContaEspecial(identificador, new BigDecimal("1000.01").negate());
    }
    
    @Test
    public void adicaoDeTransacao() throws Exception {
        conta = new ContaEspecial(identificador);
        valor = BigDecimal.ZERO;
        transacao = new Transacao(valor, data, descricao);
        
        conta.addTransacao(transacao);
        
        assertEquals(1, conta.getTransacoes().size());
        assertEquals(valor, transacao.getValor());
        assertEquals(data, transacao.getData());
        assertEquals(descricao, transacao.getDescricao());
    }
    
    @Test
    public void transacaoQueCreditaValor() throws Exception {
        conta = new ContaEspecial(identificador);
        valor = BigDecimal.ONE;
        transacao = new Transacao(valor, data, descricao);
        
        conta.addTransacao(transacao);
        assertEquals(1, conta.getTransacoes().size());
        assertEquals(valor, conta.getSaldo());
    }
    
    @Test
    public void debitoComValorDentroDoLimite() throws Exception {
        conta = new ContaEspecial(identificador, BigDecimal.ONE);
        valor = BigDecimal.ONE.negate();
        transacao = new Transacao(valor, data, descricao);
        
        conta.addTransacao(transacao);
        
        assertEquals(1, conta.getTransacoes().size());
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
    }
    
    @Test(expected = SaldoInsuficienteException.class)
    public void debitoComValorAcimaDoLimite() throws Exception {
        conta = new ContaEspecial(identificador);
        valor = new BigDecimal("1000.01").negate();
        transacao = new Transacao(valor, data, descricao);
        conta.addTransacao(transacao);
    }
    
    @Test
    public void limiteDeOperacoesIlimitado() throws Exception {
        conta = new ContaEspecial(identificador);
        assertEquals(0, conta.getLimiteDeOperacoesPorDia());
        assertFalse(conta.isLimiteDeOperacoesPorDiaAlcancado(data));
    }

}
