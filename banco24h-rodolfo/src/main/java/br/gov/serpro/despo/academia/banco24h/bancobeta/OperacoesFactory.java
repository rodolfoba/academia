package br.gov.serpro.despo.academia.banco24h.bancobeta;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.despo.academia.banco24h.bancobeta.operacao.Saque;

public interface OperacoesFactory {

    Saque criarSaque(LocalDate data, BigDecimal valor);

}
