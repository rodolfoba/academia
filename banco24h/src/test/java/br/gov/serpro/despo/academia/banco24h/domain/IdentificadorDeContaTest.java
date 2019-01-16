package br.gov.serpro.despo.academia.banco24h.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IdentificadorDeContaTest {

    @Test
    public void equalsAndHashCodeIguais() {
        long valor = 1;
        IdentificadorDeContaFake primeiro = new IdentificadorDeContaFake(valor);
        IdentificadorDeContaFake segundo = new IdentificadorDeContaFake(valor);
        
        assertEquals(primeiro.hashCode(), segundo.hashCode());
        assertTrue(primeiro.equals(segundo));
        assertTrue(segundo.equals(primeiro));
    }
    
    @Test
    public void equalsAndHashCodeDiferentes() {
        IdentificadorDeContaFake primeiro = new IdentificadorDeContaFake(1);
        IdentificadorDeContaFake segundo = new IdentificadorDeContaFake(2);
        
        assertNotEquals(primeiro.hashCode(), segundo.hashCode());
        assertFalse(primeiro.equals(segundo));
        assertFalse(segundo.equals(primeiro));
    }
    
}
