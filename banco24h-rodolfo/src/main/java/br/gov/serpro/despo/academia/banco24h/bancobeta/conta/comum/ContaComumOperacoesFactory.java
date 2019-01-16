package br.gov.serpro.despo.academia.banco24h.bancobeta.conta.comum;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.despo.academia.banco24h.bancobeta.OperacoesFactory;
import br.gov.serpro.despo.academia.banco24h.bancobeta.operacao.Saque;

public class ContaComumOperacoesFactory implements OperacoesFactory {

    @Override
    public Saque criarSaque(LocalDate data, BigDecimal valor) {
        return new SaqueContaComum(data, valor);
    }

}
