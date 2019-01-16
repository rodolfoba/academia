package br.gov.serpro.despo.academia.banco24h.exceptions;

public class ValorInvalidoException extends OperacaoBancariaException {
	
	private static final long serialVersionUID = 1L;

	public ValorInvalidoException(String mensagem){
        super(mensagem);
    }
}
