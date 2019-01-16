package br.gov.serpro.despo.academia.banco24h.bancobeta.conta;

import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeConta;

public final class IdentificadorDeContaBancoBeta implements IdentificadorDeConta {

    @Override
    public boolean ehIgual(IdentificadorDeConta outro) {
        return equals(outro);
    }

}
