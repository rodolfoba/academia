package br.gov.serpro.despo.academia.banco24h.bancobeta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.gov.serpro.despo.academia.banco24h.banco.BancoOperavel;
import br.gov.serpro.despo.academia.banco24h.banco.ContaNaoExistenteException;
import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;
import br.gov.serpro.despo.academia.banco24h.banco.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.banco.SaldoInsuficienteException;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.ContaBancoBeta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.IdentificadorDeContaBancoBeta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.operacao.Saque;

public class BancoBeta implements BancoOperavel {

    private static final String NOME = "Banco Beta";
    private final Set<ContaBancoBeta> contas;
    
    public BancoBeta(Collection<ContaBancoBeta> contas) {
        this.contas = new HashSet<>(contas);
    }
    
    @Override
    public String getNome() {
        return NOME;
    }

    @Override
    public void sacar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
            throws SaldoInsuficienteException, ContaNaoExistenteException, OperacaoBancariaException {
        
        ContaBancoBeta conta = obterConta(identificadorDeConta);
        OperacoesFactory factory = Factories.obterOperacoesFactory(conta);
        Saque saque = factory.criarSaque(LocalDate.now(), valor);
        saque.executar(conta);
    }

    @Override
    public void depositar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
            throws ContaNaoExistenteException, OperacaoBancariaException {
        
        // TODO Auto-generated method stub
    }

    @Override
    public void transferir(IdentificadorDeConta origem, IdentificadorDeConta destino, BigDecimal valor)
            throws SaldoInsuficienteException, ContaNaoExistenteException, OperacaoBancariaException {

        // TODO Auto-generated method stub
    }

    @Override
    public BigDecimal consultarSaldo(IdentificadorDeConta identificadorDeConta)
            throws ContaNaoExistenteException, OperacaoBancariaException {
        
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Movimentacao> consultarExtrato(IdentificadorDeConta identificadorDeConta)
            throws ContaNaoExistenteException, OperacaoBancariaException {
        
        // TODO Auto-generated method stub
        return null;
    }

    private ContaBancoBeta obterConta(IdentificadorDeConta identificadorDeConta) throws ContaNaoExistenteException {
        if (null == identificadorDeConta || !(identificadorDeConta instanceof IdentificadorDeContaBancoBeta)) {
            throw new IllegalArgumentException("Identificador de conta inválido");
        }
        
        return contas.stream().filter((c) -> c.getIdentificador().ehIgual(identificadorDeConta)).findFirst()
                .orElseThrow(() -> new ContaNaoExistenteException("Conta não existente"));
    }
}
