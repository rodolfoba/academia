package br.gov.serpro.despo.academia.banco24h.domain;

public class IdentificadorDeContaInvalido extends IdentificadorDeConta {

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    protected boolean ehIgual(IdentificadorDeConta outroIdentificador) {
        throw new UnsupportedOperationException();
        
    }

    @Override
    protected int calcularHashCode() {
        throw new UnsupportedOperationException();
    }

}
