package br.gov.serpro.despo.academia.banco24h.caixa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import br.gov.serpro.despo.academia.banco24h.caixa.BancoMock.TipoOperacao;
import br.gov.serpro.despo.academia.banco24h.exceptions.BancoNaoEncontradoException;
import br.gov.serpro.despo.academia.banco24h.interfaces.BancoOperavel;

public class Caixa24hTest {

    @Test(expected = BancoNaoEncontradoException.class)
    public void deveLancarExcecaoAoTentarCriarCaixaSemBancos() throws Exception {
        new Caixa24h(new ArrayList<>());
    }

    @Test(expected = BancoNaoEncontradoException.class)
    public void deveLancarExcecaoAoTentarCriarCaixaListaBancosNula() throws Exception {
        new Caixa24h(null);
    }

    @Test
    public void deveCriarCaixa() throws Exception {
        BancoOperavel banco = new BancoMock();
        assertNotNull(new Caixa24h(Arrays.asList(banco)));
    }

    @Test(expected = BancoNaoEncontradoException.class)
    public void deveLancarExcecaoAoTentarEncontrarBancoInexistente() throws Exception {
        BancoOperavel bancoUm = new BancoMock("Um");
        BancoOperavel bancoDois = new BancoMock("Dois");

        Caixa24h caixa = new Caixa24h(Arrays.asList(bancoUm, bancoDois));
        caixa.consultarSaldo("Tres", null);
    }

    @Test
    public void deveInvocarSacarBancoDois() throws Exception {
        BancoOperavel bancoUm = new BancoMock("Um");
        BancoOperavel bancoDois = new BancoMock("Dois");

        Caixa24h caixa = new Caixa24h(Arrays.asList(bancoUm, bancoDois));

        caixa.sacar("Dois", null, BigDecimal.ONE);

        // Verifica se não invocou nada errado no Banco Um
        assertNull(((BancoMock) bancoUm).getOperacaoInvocada());
        assertNull(((BancoMock) bancoUm).getValorRecebidoParametro());

        // Verifica invocação no Banco Dois
        assertEquals(TipoOperacao.SACAR, ((BancoMock) bancoDois).getOperacaoInvocada());
        assertEquals(BigDecimal.ONE, ((BancoMock) bancoDois).getValorRecebidoParametro());
    }

    @Test
    public void deveInvocarDepositarBancoDois() throws Exception {
        BancoOperavel bancoUm = new BancoMock("Um");
        BancoOperavel bancoDois = new BancoMock("Dois");

        Caixa24h caixa = new Caixa24h(Arrays.asList(bancoUm, bancoDois));

        caixa.depositar("Dois", null, BigDecimal.TEN);

        // Verifica se não invocou nada errado no Banco Um
        assertNull(((BancoMock) bancoUm).getOperacaoInvocada());
        assertNull(((BancoMock) bancoUm).getValorRecebidoParametro());

        // Verifica invocação no Banco Dois
        assertEquals(TipoOperacao.DEPOSITAR, ((BancoMock) bancoDois).getOperacaoInvocada());
        assertEquals(BigDecimal.TEN, ((BancoMock) bancoDois).getValorRecebidoParametro());
    }

    @Test
    public void deveInvocarTransferirBancoDois() throws Exception {
        BancoOperavel bancoUm = new BancoMock("Um");
        BancoOperavel bancoDois = new BancoMock("Dois");

        Caixa24h caixa = new Caixa24h(Arrays.asList(bancoUm, bancoDois));

        caixa.transferir("Dois", null, null, BigDecimal.valueOf(2L));

        // Verifica se não invocou nada errado no Banco Um
        assertNull(((BancoMock) bancoUm).getOperacaoInvocada());
        assertNull(((BancoMock) bancoUm).getValorRecebidoParametro());

        // Verifica invocação no Banco Dois
        assertEquals(TipoOperacao.TRANSFERIR, ((BancoMock) bancoDois).getOperacaoInvocada());
        assertEquals(BigDecimal.valueOf(2L), ((BancoMock) bancoDois).getValorRecebidoParametro());
    }

    @Test
    public void deveInvocarConsultarSaldoBancoDois() throws Exception {
        BancoOperavel bancoUm = new BancoMock("Um");
        BancoOperavel bancoDois = new BancoMock("Dois");

        Caixa24h caixa = new Caixa24h(Arrays.asList(bancoUm, bancoDois));

        caixa.consultarSaldo("Dois", null);

        // Verifica se não invocou nada errado no Banco Um
        assertNull(((BancoMock) bancoUm).getOperacaoInvocada());
        assertNull(((BancoMock) bancoUm).getValorRecebidoParametro());

        // Verifica invocação no Banco Dois
        assertEquals(TipoOperacao.CONSULTAR_SALDO, ((BancoMock) bancoDois).getOperacaoInvocada());
        assertNull(((BancoMock) bancoDois).getValorRecebidoParametro());
    }

    @Test
    public void deveInvocarConsultarExtratoBancoDois() throws Exception {
        BancoOperavel bancoUm = new BancoMock("Um");
        BancoOperavel bancoDois = new BancoMock("Dois");

        Caixa24h caixa = new Caixa24h(Arrays.asList(bancoUm, bancoDois));

        caixa.consultarExtrato("Dois", null, null, null);

        // Verifica se não invocou nada errado no Banco Um
        assertNull(((BancoMock) bancoUm).getOperacaoInvocada());
        assertNull(((BancoMock) bancoUm).getValorRecebidoParametro());

        // Verifica invocação no Banco Dois
        assertEquals(TipoOperacao.CONSULTAR_EXTRATO, ((BancoMock) bancoDois).getOperacaoInvocada());
        assertNull(((BancoMock) bancoDois).getValorRecebidoParametro());
    }
}
