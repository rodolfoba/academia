package br.gov.serpro.despo.academia.banco24h.bancobeta;

import java.util.HashMap;
import java.util.Map;

import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.ContaBancoBeta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.comum.ContaComum;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.comum.ContaComumOperacoesFactory;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.premium.ContaPremium;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.premium.ContaPremiumOperacoesFactory;

public class Factories {

    private static final Map<Class<? extends ContaBancoBeta>, OperacoesFactory> operacoes = new HashMap<>();

    static {
        inicializaOperacoes();
    }

    private Factories() {}
    
    private static void inicializaOperacoes() {
        operacoes.put(ContaComum.class, new ContaComumOperacoesFactory());
        operacoes.put(ContaPremium.class, new ContaPremiumOperacoesFactory());
    }

    static OperacoesFactory obterOperacoesFactory(ContaBancoBeta conta) {
        return operacoes.getOrDefault(conta.getClass(), new ContaComumOperacoesFactory());
    }

}
