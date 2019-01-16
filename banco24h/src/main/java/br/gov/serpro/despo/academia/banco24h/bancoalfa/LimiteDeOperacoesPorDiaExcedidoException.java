package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import br.gov.serpro.despo.academia.banco24h.exceptions.OperacaoBancariaException;

public class LimiteDeOperacoesPorDiaExcedidoException extends OperacaoBancariaException {

    private static final long serialVersionUID = 1L;

    public LimiteDeOperacoesPorDiaExcedidoException(String mensagem) {
        super(mensagem);
    }

}
