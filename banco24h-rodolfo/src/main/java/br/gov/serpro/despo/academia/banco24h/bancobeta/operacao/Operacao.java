package br.gov.serpro.despo.academia.banco24h.bancobeta.operacao;

import br.gov.serpro.despo.academia.banco24h.banco.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.ContaBancoBeta;

public interface Operacao<T> {

    T executar(ContaBancoBeta conta) throws OperacaoBancariaException;
    
}
