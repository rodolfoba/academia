package br.gov.serpro.despo.academia.banco24h.banco;

public class ContaNaoExistenteException extends Exception {

    private static final long serialVersionUID = 1L;

    public ContaNaoExistenteException(String mensagem) {
        super(mensagem);
    }

}
