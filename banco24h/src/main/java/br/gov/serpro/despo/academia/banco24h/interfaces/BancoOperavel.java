package br.gov.serpro.despo.academia.banco24h.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.gov.serpro.despo.academia.banco24h.domain.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.domain.Transacao;
import br.gov.serpro.despo.academia.banco24h.exceptions.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public interface BancoOperavel {

    String getIdentificacao();
    
    String getNome();

    void sacar(IdentificadorDeConta identificadorDeConta, BigDecimal valor) throws SaldoInsuficienteException, OperacaoBancariaException;

    void depositar(IdentificadorDeConta identificadorDeConta, BigDecimal valor) throws OperacaoBancariaException;
    
    void transferir(IdentificadorDeConta origem, IdentificadorDeConta destino, BigDecimal valor) throws SaldoInsuficienteException, OperacaoBancariaException;
    
    BigDecimal consultarSaldo(IdentificadorDeConta identificadorDeConta) throws OperacaoBancariaException;

    List<Transacao> consultarExtrato(IdentificadorDeConta identificadorDeConta, LocalDate dataInicial, LocalDate dataFinal) throws OperacaoBancariaException; 

}


