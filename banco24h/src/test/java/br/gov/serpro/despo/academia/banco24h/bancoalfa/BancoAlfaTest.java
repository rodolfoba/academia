package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import br.gov.serpro.despo.academia.banco24h.domain.IdentificadorDeContaFake;
import br.gov.serpro.despo.academia.banco24h.domain.IdentificadorDeContaInvalido;
import br.gov.serpro.despo.academia.banco24h.domain.Transacao;
import br.gov.serpro.despo.academia.banco24h.exceptions.ContaNaoEncontradaException;
import br.gov.serpro.despo.academia.banco24h.exceptions.IdentificadorDeContaInvalidoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public class BancoAlfaTest {

    BancoAlfa banco;
    IdentificadorDeContaBancoAlfa identificador;
    ContaBancoAlfa conta;
    BigDecimal valor;
    
    @Test(expected = OperacaoBancariaNaoSuportadaException.class)
    public void deposito() throws Exception {
        banco = new BancoAlfa();
        banco.depositar(null, null);
    }
    
    @Test()
    public void consultaSaldo() throws Exception {
        valor = BigDecimal.ZERO;
        identificador = new IdentificadorDeContaBancoAlfa(1);
        ContaBancoAlfa conta = new ContaBancoAlfa(identificador, valor) {
            
            @Override
            public BigDecimal getLimite() {
                return BigDecimal.ZERO;
            }

            @Override
            protected int getLimiteDeOperacoesPorDia() {
                return 0;
            }
        };
        Set<ContaBancoAlfa> contas = new HashSet<>();
        contas.add(conta);
        banco = new BancoAlfa(contas);
        
        BigDecimal saldo = banco.consultarSaldo(identificador);
        assertEquals(valor, saldo);
    }
    
    @Test(expected = IdentificadorDeContaInvalidoException.class)
    public void consultaSaldoComIdentificadorNulo() throws Exception {
        banco = new BancoAlfa();
        banco.consultarSaldo(null);
    }
    
    @Test(expected = IdentificadorDeContaInvalidoException.class)
    public void consultaSaldoComIdentificadorInvalido() throws Exception {
        banco = new BancoAlfa();
        banco.consultarSaldo(new IdentificadorDeContaInvalido());
    }
    
    @Test(expected = IdentificadorDeContaInvalidoException.class)
    public void consultaSaldoComIdentificadorDesconhecido() throws Exception {
        banco = new BancoAlfa();
        banco.consultarSaldo(new IdentificadorDeContaFake(1));
    }
    
    @Test(expected = ContaNaoEncontradaException.class)
    public void consultaSaldoDeContaInexistente() throws Exception {
        banco = new BancoAlfa();
        banco.consultarSaldo(new IdentificadorDeContaBancoAlfa(1));
    }
    
    @Test(expected = LimiteDeOperacoesPorDiaExcedidoException.class)
    public void consultaSaldoDeContaComumComLimiteDeOperacoesPorDiaExcedido() throws Exception {
        inicializaBancoComContaComumPadrao();
        banco.consultarSaldo(identificador);
        banco.consultarSaldo(identificador);
        banco.consultarSaldo(identificador);
        
        banco.consultarSaldo(identificador);
    }
    
    @Test
    public void consultaExtratoDeContaSemOperacoesRealizadas() throws Exception {
        inicializaBancoComContaComumPadrao();
        List<Transacao> extrato = banco.consultarExtrato(identificador, null, null);
        assertTrue(extrato.isEmpty());
    }
    
    @Test
    public void consultaExtratoDeContaComOperacaoRealizada() throws Exception {
        // Banco com uma conta com uma operacao realizada
        BigDecimal valor = BigDecimal.ONE;
        LocalDate data = LocalDate.now();
        String descricao = "SAQUE";
        Transacao transacao = new Transacao(valor, data, descricao);
        conta = new ContaComum(new IdentificadorDeContaBancoAlfa(1));
        conta.addTransacao(transacao);
        conta.registraOperacaoRealizada(data);
        inicializaBancoComConta(conta);
        
        List<Transacao> extrato = banco.consultarExtrato(identificador, null, null);
        
        assertEquals(1, extrato.size());
        Transacao extratoTransacao = extrato.get(0);
        assertEquals(valor, extratoTransacao.getValor());
        assertEquals(LocalDate.now(), extratoTransacao.getData());
        assertEquals(descricao, extratoTransacao.getDescricao());
    }
    
    @Test
    public void saqueDisponivel() throws Exception {
        identificador = new IdentificadorDeContaBancoAlfa(1);
        conta = new ContaComum(identificador, BigDecimal.ONE);
        inicializaBancoComConta(conta);
        
        banco.sacar(identificador, BigDecimal.ONE);
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
        assertEquals(1, conta.getTransacoes().size());
    }
    
    @Test(expected = SaldoInsuficienteException.class)
    public void saqueComSaldoInsuficiente() throws Exception {
        inicializaBancoComContaComumPadrao();
        banco.sacar(identificador, BigDecimal.ONE);
    }
    
    @Test(expected = LimiteDeOperacoesPorDiaExcedidoException.class)
    public void saqueComLimiteDeOperacoesExcedido() throws Exception {
        identificador = new IdentificadorDeContaBancoAlfa(1);
        conta = new ContaComum(identificador, BigDecimal.TEN);
        inicializaBancoComConta(conta);
        banco.sacar(identificador, BigDecimal.ONE);
        banco.sacar(identificador, BigDecimal.ONE);
        banco.sacar(identificador, BigDecimal.ONE);
        
        banco.sacar(identificador, BigDecimal.ONE);
    }
    
    @Test
    public void transferencia() throws Exception {
        ContaComum origem = new ContaComum(new IdentificadorDeContaBancoAlfa(1), BigDecimal.ONE);
        ContaComum destino = new ContaComum(new IdentificadorDeContaBancoAlfa(2), BigDecimal.ZERO);
        Set<ContaBancoAlfa> contas = new HashSet<>(asList(origem, destino));
        banco = new BancoAlfa(contas);
        
        banco.transferir(origem.getIdentificadorDeConta(), destino.getIdentificadorDeConta(), BigDecimal.ONE);
        
        assertEquals(BigDecimal.ZERO, origem.getSaldo());
        assertEquals(1, origem.getTransacoes().size());
        
        assertEquals(BigDecimal.ONE, destino.getSaldo());
        assertEquals(1, destino.getTransacoes().size());
    }
    
    private void inicializaBancoComContaComumPadrao() throws Exception {
        identificador = new IdentificadorDeContaBancoAlfa(1);
        conta = new ContaComum(identificador);
        inicializaBancoComConta(conta);
    }
    
    private void inicializaBancoComConta(ContaBancoAlfa conta) {
        identificador = conta.getIdentificadorDeConta();
        Set<ContaBancoAlfa> contas = new HashSet<>();
        contas.add(conta);
        banco = new BancoAlfa(contas);
    }
    
}
