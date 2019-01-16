package br.gov.serpro.despo.academia.banco24h.bancobeta.conta.comum;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.IdentificadorDeContaBancoBeta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.premium.SaqueContaPremium;
import br.gov.serpro.despo.academia.banco24h.bancobeta.operacao.Deposito;
import br.gov.serpro.despo.academia.banco24h.bancobeta.operacao.Saque;

public class ContaComumTest {

    ContaComum conta;
    IdentificadorDeContaBancoBeta identificador;
    BigDecimal valor;
    LocalDate data;
    Movimentacao movimentacao;
    
    @BeforeEach
    public void before() {
        identificador = new IdentificadorDeContaBancoBeta();
        data = LocalDate.now();
    }
    
    @Test
    public void novaConta() throws Exception {
        conta = new ContaComum(identificador);
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
    }
    
    @Test
    public void novaContaComOperacoes() throws Exception {
        valor = new BigDecimal("100");
        conta = new ContaComum(identificador);
        new SaqueContaComum(data, valor).executar(conta);
        
        assertEquals(new BigDecimal("-101.500"), conta.getSaldo());
        assertEquals(identificador, conta.getIdentificador());
    }
    
    @Test
    public void criacaoDeContaInvalida() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> new ContaComum(null));
    }
    
    @Test
    public void credito() throws Exception {
        conta = new ContaComum(identificador);
        valor = BigDecimal.ONE;
        movimentacao = new Deposito(data, valor);
        conta.movimentar(movimentacao);
        
        assertEquals(BigDecimal.ONE, conta.getSaldo());
        assertEquals(1, conta.getMovimentacoes().size());
        Movimentacao atual = conta.getMovimentacoes().iterator().next();
        assertEquals(valor, atual.getValor());
        assertEquals(data, atual.getData());
        assertEquals(movimentacao.getTipo(), atual.getTipo());
    }
    
    @Test
    public void debito() throws Exception {
        ContaComum conta = new ContaComum(identificador);
        valor = new BigDecimal("100");
        SaqueContaPremium saque = new SaqueContaPremium(data, valor);
        saque.executar(conta);
        
        BigDecimal valorEsperado = new BigDecimal("-101.500");
        assertEquals(valorEsperado, conta.getSaldo());
        assertEquals(1, conta.getMovimentacoes().size());
        Movimentacao atual = conta.getMovimentacoes().iterator().next();
        assertEquals(valorEsperado, atual.getValor());
        assertEquals(data, atual.getData());
    }
    
    @Test
    public void debitoComValorAcimaDoLimite() throws Exception {
        conta = new ContaComum(identificador);
        valor = BigDecimal.valueOf(201);
//        movimentacao = new Saque(data, valor);
//        assertThrows(SaldoInsuficienteException.class, () -> conta.movimentar(movimentacao));
    }
    
    @Test
    public void limiteDeOperacoesPorDiaAlcancado() throws Exception {
        conta = new ContaComum(identificador);
//        conta.movimentar(new Deposito(data, BigDecimal.ONE));
//        conta.movimentar(new Deposito(data, BigDecimal.ONE));
//        assertThrows(LimiteDeOperacoesPorDiaExcedido.class, () -> conta.movimentar(new Deposito(data, BigDecimal.ONE)));
    }
    
}
