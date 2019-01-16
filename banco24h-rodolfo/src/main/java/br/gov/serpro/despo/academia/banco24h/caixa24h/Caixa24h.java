package br.gov.serpro.despo.academia.banco24h.caixa24h;

import java.math.BigDecimal;
import java.util.Set;

import br.gov.serpro.despo.academia.banco24h.banco.BancoOperavel;
import br.gov.serpro.despo.academia.banco24h.banco.ContaNaoExistenteException;
import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.banco.Movimentacao;
import br.gov.serpro.despo.academia.banco24h.banco.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.banco.SaldoInsuficienteException;

public class Caixa24h {

    private final BancoOperavel banco;
    
    public Caixa24h(BancoOperavel banco) {
        if (null == banco) {
            throw new IllegalArgumentException("Banco inválido");
        }
        
        this.banco = banco;
    }

    public void sacar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
            throws SaldoInsuficienteException, ContaNaoExistenteException, OperacaoBancariaException {
        validarIdentificadorDeConta(identificadorDeConta);
        
        if (null == valor) {
            throw new IllegalArgumentException("Valor para saque inválido");
        }
        
        banco.sacar(identificadorDeConta, valor);
    }

    public void depositar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
            throws ContaNaoExistenteException, OperacaoBancariaException {
        validarIdentificadorDeConta(identificadorDeConta);
        banco.depositar(identificadorDeConta, valor);
    }

    public void transferir(IdentificadorDeConta origem, IdentificadorDeConta destino, BigDecimal valor)
            throws SaldoInsuficienteException, ContaNaoExistenteException, OperacaoBancariaException {
        validarIdentificadorDeConta(origem);
        validarIdentificadorDeConta(destino);
        banco.transferir(origem, destino, valor);
    }

    public BigDecimal consultarSaldo(IdentificadorDeConta identificadorDeConta)
            throws ContaNaoExistenteException, OperacaoBancariaException {
        validarIdentificadorDeConta(identificadorDeConta);
        return banco.consultarSaldo(identificadorDeConta);
    }

    public Set<Movimentacao> consultarExtrato(IdentificadorDeConta identificadorDeConta)
            throws ContaNaoExistenteException, OperacaoBancariaException {
        validarIdentificadorDeConta(identificadorDeConta);
        return banco.consultarExtrato(identificadorDeConta);
    }
    
    private void validarIdentificadorDeConta(IdentificadorDeConta identificadorDeConta) {
        if (null == identificadorDeConta) {
            throw new IllegalArgumentException("Identificador de conta ausente");
        }
    }
}
