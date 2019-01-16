package br.gov.serpro.despo.academia.banco24h.bancobeta;

import br.gov.serpro.despo.academia.banco24h.banco.OperacaoBancariaException;

public class LimiteDeOperacoesPorDiaExcedido extends OperacaoBancariaException {

    private static final long serialVersionUID = 1L;

    public LimiteDeOperacoesPorDiaExcedido(String mensagem){
        super(mensagem);
    }

}
