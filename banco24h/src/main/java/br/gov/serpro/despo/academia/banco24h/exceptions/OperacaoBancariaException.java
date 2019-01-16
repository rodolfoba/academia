package br.gov.serpro.despo.academia.banco24h.exceptions;

public class OperacaoBancariaException extends Exception {

    private static final long serialVersionUID = 1L;

    public OperacaoBancariaException(String mensagem){
        super(mensagem);
    }

}
