package br.gov.serpro.despo.academia.banco24h.banco;

public class SaldoInsuficienteException extends OperacaoBancariaException {

    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException(String mensagem){
        super(mensagem);
    }

}
