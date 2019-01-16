package br.gov.serpro.despo.academia.banco24h.exceptions;

public class BancoNaoEncontradoException extends OperacaoBancariaException {
	
	private static final long serialVersionUID = 1L;

	public BancoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
