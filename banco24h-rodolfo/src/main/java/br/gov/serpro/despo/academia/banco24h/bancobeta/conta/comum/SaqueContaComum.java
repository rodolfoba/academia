package br.gov.serpro.despo.academia.banco24h.bancobeta.conta.comum;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.serpro.despo.academia.banco24h.banco.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.ContaBancoBeta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.operacao.Saque;

public class SaqueContaComum extends Saque {

    public SaqueContaComum(LocalDate data, BigDecimal valor) {
        super(data, valor);
    }

    /**
     * TODO Avaliar uso de visitor
     */
    @Override
    public Void executar(ContaBancoBeta conta) throws OperacaoBancariaException {
        ContaComum contaComum = (ContaComum) conta;
        contaComum.validarLimiteDiario(this.getData());
        contaComum.movimentar(this);
        contaComum.incrementaLimiteDiario(this.getData());
        return null;
    }

}
