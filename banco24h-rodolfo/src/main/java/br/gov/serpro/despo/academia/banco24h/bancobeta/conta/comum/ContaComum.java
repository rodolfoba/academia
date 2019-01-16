package br.gov.serpro.despo.academia.banco24h.bancobeta.conta.comum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.LimiteDeOperacoesPorDiaExcedido;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.ContaBancoBeta;

public class ContaComum extends ContaBancoBeta {

    private final static int LIMITE_DIARIO = 2;
    private final Map<LocalDate, Integer> operacoesPorDia = new HashMap<>();
    
    public ContaComum(IdentificadorDeConta identificador) {
        super(identificador);
    }

    @Override
    protected BigDecimal getLimiteNegativo() {
        return BigDecimal.valueOf(200);
    }
    
    void validarLimiteDiario(LocalDate data) throws LimiteDeOperacoesPorDiaExcedido {
        if (LIMITE_DIARIO == operacoesPorDia.getOrDefault(data, 0)) {
            throw new LimiteDeOperacoesPorDiaExcedido("Limite de operações por dia excedido");
        }
    }
    
    void incrementaLimiteDiario(LocalDate data) {
        operacoesPorDia.put(data, operacoesPorDia.getOrDefault(data, 0) + 1);
    }
    
}
