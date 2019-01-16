package br.gov.serpro.despo.academia.banco24h.banco;

import java.math.BigDecimal;
import java.util.Set;

public interface BancoOperavel {

    String getNome();

    /**
     * Realiza saque em conta
     * @param identificadorDeConta
     * @param valor
     * @throws SaldoInsuficienteException
     * @throws ContaNaoExistenteException
     * @throws OperacaoBancariaException falha ao realizar operação bancaria
     */
    void sacar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
            throws SaldoInsuficienteException, ContaNaoExistenteException, OperacaoBancariaException;

    /**
     * Realiza depósito em conta
     * @param identificadorDeConta
     * @param valor
     * @throws ContaNaoExistenteException
     * @throws OperacaoBancariaException falha ao realizar operação bancaria
     */
    void depositar(IdentificadorDeConta identificadorDeConta, BigDecimal valor)
            throws ContaNaoExistenteException, OperacaoBancariaException;

    /**
     * Realiza transferência entre contas do mesmo banco
     * @param origem
     * @param destino
     * @param valor
     * @throws SaldoInsuficienteException
     * @throws ContaNaoExistenteException 
     * @throws OperacaoBancariaException falha ao realizar operação bancaria
     */
    void transferir(IdentificadorDeConta origem, IdentificadorDeConta destino, BigDecimal valor)
            throws SaldoInsuficienteException, ContaNaoExistenteException, OperacaoBancariaException;

    /**
     * Consulta saldo de conta
     * @param identificadorDeConta
     * @return saldo
     * @throws ContaNaoExistenteException 
     * @throws OperacaoBancariaException falha ao realizar operação bancaria
     */
    BigDecimal consultarSaldo(IdentificadorDeConta identificadorDeConta)
            throws ContaNaoExistenteException, OperacaoBancariaException;

    /**
     * Consulta extrato de conta
     * @param identificadorDeConta
     * @return extrato
     * @throws ContaNaoExistenteException 
     * @throws OperacaoBancariaException falha ao realizar operação bancaria
     */
    Set<Movimentacao> consultarExtrato(IdentificadorDeConta identificadorDeConta)
            throws ContaNaoExistenteException, OperacaoBancariaException;

}
