package br.gov.serpro.despo.academia.banco24h.banco;

public class IdentificadorDeContaFake implements IdentificadorDeConta {

    @Override
    public boolean ehIgual(IdentificadorDeConta outroIdentificador) {
        return equals(outroIdentificador);
    }

}
