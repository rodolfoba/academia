package br.gov.serpro.despo.academia.banco24h.bancobeta.conta.premium;

import java.math.BigDecimal;

import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.ContaBancoBeta;

public class ContaPremium extends ContaBancoBeta {

    public ContaPremium(IdentificadorDeConta identificador) {
        super(identificador);
    }

    @Override
    protected BigDecimal getLimiteNegativo() {
        return BigDecimal.valueOf(5000);
    }

}
