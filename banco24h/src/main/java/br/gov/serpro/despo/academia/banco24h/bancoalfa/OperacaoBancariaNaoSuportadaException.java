package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import br.gov.serpro.despo.academia.banco24h.exceptions.OperacaoBancariaException;

public class OperacaoBancariaNaoSuportadaException extends OperacaoBancariaException {

    private static final long serialVersionUID = 1L;

    public OperacaoBancariaNaoSuportadaException(String mensagem) {
        super(mensagem);
    }

}
