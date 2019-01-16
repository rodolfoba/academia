package br.gov.serpro.despo.academia.banco24h.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

public class TransacaoTest {

    @Test
    public void deveCriarCorretamente() {
        BigDecimal valor = BigDecimal.ZERO;
        LocalDate data = LocalDate.now();
        String descricao = "Descrição";
        
        Transacao transacao = new Transacao(valor, data, descricao);
        
        assertEquals(valor, transacao.getValor());
        assertEquals(data, transacao.getData());
        assertEquals(descricao, transacao.getDescricao());
    }

}
