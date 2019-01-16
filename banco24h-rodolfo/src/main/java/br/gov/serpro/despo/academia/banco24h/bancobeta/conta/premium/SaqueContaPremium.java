package br.gov.serpro.despo.academia.banco24h.bancobeta.conta.premium;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.despo.academia.banco24h.banco.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.ContaBancoBeta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.operacao.Saque;

public class SaqueContaPremium extends Saque {

    public SaqueContaPremium(LocalDate data, BigDecimal valor) {
        super(data, valor);
    }

    @Override
    public Void executar(ContaBancoBeta conta) throws OperacaoBancariaException {
        conta.movimentar(this);
        return null;
    }

}
