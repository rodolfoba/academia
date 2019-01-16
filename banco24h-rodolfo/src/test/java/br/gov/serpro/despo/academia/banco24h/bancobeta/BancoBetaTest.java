package br.gov.serpro.despo.academia.banco24h.bancobeta;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.gov.serpro.despo.academia.banco24h.banco.ContaNaoExistenteException;
import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.banco.IdentificadorDeContaFake;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.ContaBancoBeta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.IdentificadorDeContaBancoBeta;
import br.gov.serpro.despo.academia.banco24h.bancobeta.conta.comum.ContaComum;

public class BancoBetaTest {

    BancoBeta banco;
    IdentificadorDeContaBancoBeta identificador;
    ContaBancoBeta conta;
    BigDecimal valor;
    
    @Test
    public void saque() throws Exception {
        // given
        identificador = new IdentificadorDeContaBancoBeta();
        conta = umaContaComum(identificador);
        banco = umBanco(conta);
        
        // when
        banco.sacar(identificador, new BigDecimal("100"));
        
        // then
        assertEquals(new BigDecimal("-101.500"), conta.getSaldo());
        assertEquals(1, conta.getMovimentacoes().size());
    }
    
    @Test
    public void saqueComIdentificadorDeContaNulo() throws Exception {
        // given
        identificador = null;
        banco = umBancoComConta();
        
        // when
        assertThrows(IllegalArgumentException.class, () -> banco.sacar(identificador, BigDecimal.ONE));
    }
    
    @Test
    public void saqueComIdentificadorDeContaInvalido() throws Exception {
        // given
        IdentificadorDeConta identificador = new IdentificadorDeContaFake();
        banco = umBancoComConta();
        
        // when
        assertThrows(IllegalArgumentException.class, () -> banco.sacar(identificador, BigDecimal.ONE));
    }
    
    @Test
    public void saqueComIdentificadorDeContaNaoExistente() throws Exception {
        // given
        identificador = new IdentificadorDeContaBancoBeta();
        banco = umBancoComConta();
        
        // when then
        assertThrows(ContaNaoExistenteException.class, () -> banco.sacar(identificador, BigDecimal.ONE));
    }
    
    private BancoBeta umBancoComConta() {
        return new BancoBeta(asList(umaContaComum(new IdentificadorDeContaBancoBeta()))); 
    }
    
    private BancoBeta umBanco(ContaBancoBeta conta) {
        return new BancoBeta(asList(conta));
    }
    
    private ContaComum umaContaComum(IdentificadorDeContaBancoBeta identificador) {
        return new ContaComum(identificador);
    }
    
}
