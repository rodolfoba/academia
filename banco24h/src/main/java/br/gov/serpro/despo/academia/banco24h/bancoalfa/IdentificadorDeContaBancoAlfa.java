package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import java.util.Objects;

import br.gov.serpro.despo.academia.banco24h.domain.IdentificadorDeConta;

public final class IdentificadorDeContaBancoAlfa extends IdentificadorDeConta {

    private final int numero;

    public IdentificadorDeContaBancoAlfa(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Número de identificação de conta inválido");
        }

        this.numero = numero;
    }

    @Override
    public boolean isValid() {
        return true;
    }
    
    public int getNumero() {
        return numero;
    }

    @Override
    protected boolean ehIgual(IdentificadorDeConta outro) {
        if (this == outro) {
            return true;
        }
        
        if (getClass() != outro.getClass()) {
            return false;
        }
        
        IdentificadorDeContaBancoAlfa other = (IdentificadorDeContaBancoAlfa) outro;
        return numero == other.numero;
    }

    @Override
    protected int calcularHashCode() {
        return Objects.hash(numero);
    }

}
