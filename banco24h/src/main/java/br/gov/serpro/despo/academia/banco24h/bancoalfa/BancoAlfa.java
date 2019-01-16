package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.gov.serpro.despo.academia.banco24h.domain.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.domain.Transacao;
import br.gov.serpro.despo.academia.banco24h.exceptions.ContaNaoEncontradaException;
import br.gov.serpro.despo.academia.banco24h.exceptions.IdentificadorDeContaInvalidoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;
import br.gov.serpro.despo.academia.banco24h.interfaces.BancoOperavel;

public class BancoAlfa implements BancoOperavel {

    private final Collection<ContaBancoAlfa> contas;
    
    public BancoAlfa() {
        this(Collections.emptySet());
    }
    
    public BancoAlfa(Set<ContaBancoAlfa> contas) {
        this.contas = new HashSet<>(contas);
    }
    
    @Override
    public String getIdentificacao() {
        return "BANCO-ALFA";
    }

    @Override
    public String getNome() {
        return "Banco Alfa";
    }

    @Override
    public void sacar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
            throws SaldoInsuficienteException, OperacaoBancariaException {
        ContaBancoAlfa conta = obterContaParaRealizarOperacao(identificadorDeConta);
        Transacao transacao = new Transacao(valor.negate(), LocalDate.now(), "SAQUE");
        conta.addTransacao(transacao);
        conta.registraOperacaoRealizada(LocalDate.now());
    }

    @Override
    public void depositar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
            throws OperacaoBancariaException {

        throw new OperacaoBancariaNaoSuportadaException("Depósito não suportado");
    }

    @Override
    public void transferir(IdentificadorDeConta identificadorOrigem, IdentificadorDeConta identificadorDestino, BigDecimal valor)
            throws SaldoInsuficienteException, OperacaoBancariaException {
        
        ContaBancoAlfa contaOrigem = obterContaParaRealizarOperacao(identificadorOrigem);
        ContaBancoAlfa contaDestino = obterConta(identificadorDestino);
        
        final LocalDate hoje = LocalDate.now();
        Transacao transacaoOrigem = new Transacao(valor.negate(), hoje, "TRANSFERENCIA_ENVIO");
        Transacao transacaoDestino = new Transacao(valor, hoje, "TRANSFERENCIA_RECEBIMENTO");
        contaOrigem.addTransacao(transacaoOrigem);
        contaDestino.addTransacao(transacaoDestino);
        
        contaOrigem.registraOperacaoRealizada(hoje);
    }

    @Override
    public BigDecimal consultarSaldo(IdentificadorDeConta identificadorDeConta) throws OperacaoBancariaException {
        ContaBancoAlfa conta = obterContaParaRealizarOperacao(identificadorDeConta);
        BigDecimal saldo = conta.getSaldo();
        conta.registraOperacaoRealizada(LocalDate.now());
        return saldo;
    }

    @Override
    public List<Transacao> consultarExtrato(IdentificadorDeConta identificadorDeConta, LocalDate dataInicial,
            LocalDate dataFinal) throws OperacaoBancariaException {
        ContaBancoAlfa conta = obterContaParaRealizarOperacao(identificadorDeConta);
        List<Transacao> transacoes = conta.getTransacoes();
        conta.registraOperacaoRealizada(LocalDate.now());
        return transacoes;
    }

    private ContaBancoAlfa obterContaParaRealizarOperacao(IdentificadorDeConta identificadorDeConta)
            throws IdentificadorDeContaInvalidoException, ContaNaoEncontradaException, LimiteDeOperacoesPorDiaExcedidoException {

        ContaBancoAlfa conta = obterConta(identificadorDeConta);
        if (conta.isLimiteDeOperacoesPorDiaAlcancado(LocalDate.now())) {
            throw new LimiteDeOperacoesPorDiaExcedidoException("Limite de operações por dia excedido");
        }
        return conta;
    }
    
    private ContaBancoAlfa obterConta(IdentificadorDeConta identificadorDeConta) throws IdentificadorDeContaInvalidoException, ContaNaoEncontradaException {
        validarIdentificador(identificadorDeConta);
        
        return contas.stream().filter((c) -> c.getIdentificadorDeConta().ehIgual(identificadorDeConta)).findFirst()
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }
    
    private void validarIdentificador(IdentificadorDeConta identificadorDeConta) throws IdentificadorDeContaInvalidoException {
        if (null == identificadorDeConta || !identificadorDeConta.isValid()
                || !(identificadorDeConta instanceof IdentificadorDeContaBancoAlfa)) {
            throw new IdentificadorDeContaInvalidoException("Identificador de conta inválido");
        }
    }
    
}
