package br.gov.serpro.despo.academia.banco24h.exceptions;

public class ContaNaoEncontradaException extends OperacaoBancariaException {

    private static final long serialVersionUID = 1L;

    public ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
