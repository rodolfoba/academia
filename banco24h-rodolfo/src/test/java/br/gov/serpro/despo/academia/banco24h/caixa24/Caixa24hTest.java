package br.gov.serpro.despo.academia.banco24h.caixa24;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import br.gov.serpro.despo.academia.banco24h.banco.BancoOperavel;
import br.gov.serpro.despo.academia.banco24h.banco.ContaNaoExistenteException;
import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeContaFake;
import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;
import br.gov.serpro.despo.academia.banco24h.banco.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.banco.SaldoInsuficienteException;
import br.gov.serpro.despo.academia.banco24h.banco.SaqueFake;
import br.gov.serpro.despo.academia.banco24h.caixa24h.Caixa24h;

public class Caixa24hTest {

    Caixa24h caixa24h;
    BancoOperavel banco;

    IdentificadorDeConta identificador;
    BigDecimal valor;
    
    @BeforeEach
    public void beforeEach() {
        banco = mock(BancoOperavel.class);
        caixa24h = new Caixa24h(banco);
    }

    @Test
    public void criacaoComBancoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Caixa24h(null));
    }

    @Test
    public void interacaoComBancoAoSacar() throws Exception {
        // given
        identificador = new IdentificadorDeContaFake();
        valor = BigDecimal.ONE;

        // when
        caixa24h.sacar(identificador, valor);

        // then
        verify(banco).sacar(identificador, valor);
        verifyNoMoreInteractions(banco);
    }
    
    @Test
    public void interacaoComBancoAoSacarQuandoSaldoInsuficiente() throws Exception {
        // given
        identificador = new IdentificadorDeContaFake();
        valor = BigDecimal.ONE;
        
        // when
        doThrow(new SaldoInsuficienteException("Saldo insuficiente")).when(banco).sacar(identificador, valor);
        Executable execucao = () -> caixa24h.sacar(identificador, valor);
        
        // then
        assertThrows(SaldoInsuficienteException.class, execucao);
    }
    
    @Test
    public void interacaoComBancoAoSacarQuandoContaNaoExistente() throws Exception {
        // given
        identificador = new IdentificadorDeContaFake();
        valor = BigDecimal.ONE;
        
        // when
        doThrow(new ContaNaoExistenteException("Conta não existente")).when(banco).sacar(identificador, valor);
        Executable execucao = () -> caixa24h.sacar(identificador, valor);
        
        // then
        assertThrows(ContaNaoExistenteException.class, execucao);
    }
    
    @Test
    public void interacaoComBancoAoSacarQuandoOperacaoFalhar() throws Exception {
        // given
        identificador = new IdentificadorDeContaFake();
        valor = BigDecimal.ONE;
        
        // when
        doThrow(new OperacaoBancariaException("Falha")).when(banco).sacar(identificador, valor);
        Executable execucao = () -> caixa24h.sacar(identificador, valor);
        
        // then
        assertThrows(OperacaoBancariaException.class, execucao);
    }

    @Test
    public void interacaoComBancoAoDepositar() throws Exception {
        // given
        identificador = new IdentificadorDeContaFake();
        valor = BigDecimal.ONE;
        
        // when
        caixa24h.depositar(identificador, valor);

        // then
        verify(banco).depositar(identificador, valor);
        verifyNoMoreInteractions(banco);
    }

    @Test
    public void interacaoComBancoAoTransferir() throws Exception {
        // given
        IdentificadorDeConta origem = new IdentificadorDeContaFake();
        IdentificadorDeConta destino = new IdentificadorDeContaFake();
        valor = BigDecimal.ONE;
        
        // when
        caixa24h.transferir(origem, destino, valor);

        // then
        verify(banco).transferir(origem, destino, valor);
        verifyNoMoreInteractions(banco);
    }

    @Test
    public void interacaoComBancoAoConsultarSaldo() throws Exception {
        // given
        identificador = new IdentificadorDeContaFake();
        BigDecimal saldoEsperado = BigDecimal.ZERO;
        
        // when
        when(banco.consultarSaldo(identificador)).thenReturn(saldoEsperado);
        BigDecimal saldoAtual = caixa24h.consultarSaldo(identificador);

        // then
        verify(banco).consultarSaldo(identificador);
        verifyNoMoreInteractions(banco);
        assertEquals(saldoEsperado, saldoAtual);
    }

    @Test
    public void interacaoComBancoAoConsultarExtrato() throws Exception {
        // given
        identificador = new IdentificadorDeContaFake();
        Movimentacao movimentacaoEsperada = new SaqueFake(LocalDate.now(), BigDecimal.ONE);
        Set<Movimentacao> extratoEsperado = new HashSet<>(asList(movimentacaoEsperada));
        
        // when
        when(banco.consultarExtrato(identificador)).thenReturn(extratoEsperado);
        Set<Movimentacao> extratoAtual = caixa24h.consultarExtrato(identificador);

        // then
        verify(banco).consultarExtrato(identificador);
        verifyNoMoreInteractions(banco);
        assertEquals(1, extratoAtual.size());
        Movimentacao movimentacaoAtual = extratoAtual.iterator().next();
        assertEquals(movimentacaoEsperada.getData(), movimentacaoAtual.getData());
        assertEquals(movimentacaoEsperada.getValor(), movimentacaoAtual.getValor());
        assertEquals(movimentacaoEsperada.getTipo(), movimentacaoAtual.getTipo());
    }
    
    @Test
    public void saqueComIdentificadorDeContaInvalido() throws Exception {
        valor = BigDecimal.ONE;
        assertThrows(IllegalArgumentException.class, () -> caixa24h.sacar(null, valor));
    }
    
    @Test
    public void saqueComValorInvalido() throws Exception {
        identificador = new IdentificadorDeContaFake();
        assertThrows(IllegalArgumentException.class, () -> caixa24h.sacar(identificador, null));
    }

}
