package br.gov.serpro.despo.academia.banco24h.exceptions;

public class IdentificadorDeContaInvalidoException extends OperacaoBancariaException {
	
	private static final long serialVersionUID = 1L;

	public IdentificadorDeContaInvalidoException(String mensagem){
        super(mensagem);
    }
}
