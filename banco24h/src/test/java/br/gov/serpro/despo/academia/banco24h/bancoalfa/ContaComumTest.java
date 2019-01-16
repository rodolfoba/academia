package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.gov.serpro.despo.academia.banco24h.domain.Transacao;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public class ContaComumTest {

    ContaComum conta;
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
        conta = new ContaComum(identificador);
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
    }
    
    @Test
    public void criacaoDeContaComSaldoInicial() throws Exception {
        BigDecimal saldo = BigDecimal.ONE;
        conta = new ContaComum(identificador, saldo);
        assertEquals(saldo, conta.getSaldo());
        
        saldo = BigDecimal.ZERO;
        conta = new ContaComum(identificador, saldo);
        assertEquals(saldo, conta.getSaldo());
        
        assertEquals(identificador, conta.getIdentificadorDeConta());
    }
    
    @Test(expected = SaldoInsuficienteException.class)
    public void criacaoDeContaComSaldoInicialInvalido() throws Exception {
        conta = new ContaComum(identificador, new BigDecimal("0.01").negate());
    }
    
    @Test
    public void adicaoDeTransacao() throws Exception {
        conta = new ContaComum(identificador);
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
        conta = new ContaComum(identificador);
        valor = BigDecimal.ONE;
        transacao = new Transacao(valor, data, descricao);
        
        conta.addTransacao(transacao);
        assertEquals(1, conta.getTransacoes().size());
        assertEquals(valor, conta.getSaldo());
    }
    
    @Test
    public void debitoComValorDentroDoLimite() throws Exception {
        conta = new ContaComum(identificador, BigDecimal.ONE);
        valor = BigDecimal.ONE.negate();
        transacao = new Transacao(valor, data, descricao);
        
        conta.addTransacao(transacao);
        
        assertEquals(1, conta.getTransacoes().size());
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
    }
    
    @Test(expected = SaldoInsuficienteException.class)
    public void debitoComValorAcimaDoLimite() throws Exception {
        conta = new ContaComum(identificador);
        valor = BigDecimal.ONE.negate();
        transacao = new Transacao(valor, data, descricao);
        conta.addTransacao(transacao);
    }
    
    @Test
    public void limiteDeOperacoesPorDiaAlcancado() throws Exception {
        conta = new ContaComum(identificador);
        conta.registraOperacaoRealizada(data);
        conta.registraOperacaoRealizada(data);
        conta.registraOperacaoRealizada(data);
        assertTrue(conta.isLimiteDeOperacoesPorDiaAlcancado(data));
    }
    
    @Test
    public void limiteDeOperacoesPorDiaNaoAlcancado() throws Exception {
        conta = new ContaComum(identificador);
        conta.registraOperacaoRealizada(data);
        conta.registraOperacaoRealizada(data);
        assertFalse(conta.isLimiteDeOperacoesPorDiaAlcancado(data));
    }
}
