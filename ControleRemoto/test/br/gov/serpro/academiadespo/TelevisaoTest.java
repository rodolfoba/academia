package br.gov.serpro.academiadespo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class TelevisaoTest {

    Collection<Integer> canais = Arrays.asList(1, 2, 3, 4, 5);
    Televisao televisao;
    
    @Test
    public void novoTelevisor() {
        televisao = new Televisao(canais);
        assertTrue(televisao.isDesligada());
        assertFalse(televisao.isLigada());
        assertEquals(0, televisao.volume());
    }

    @Test
    public void ligarTelevisor() {
        televisao = new Televisao(canais);
        televisao.ligarOuDesligar();
        assertTrue(televisao.isLigada());
        assertFalse(televisao.isDesligada());
    }
    
    @Test
    public void desligarTelevisor() {
        televisao = new Televisao(canais, true);
        televisao.ligarOuDesligar();
        assertTrue(televisao.isDesligada());
        assertFalse(televisao.isLigada());
    }
    
    @Test
    public void aumentoDeVolume() {
        televisao = new Televisao(canais, true);
        int volumeInicial = televisao.volume();
        televisao.aumentarVolume();
        assertTrue(televisao.volume() > volumeInicial);
    }
    
    @Test
    public void diminuicaoDeVolume() {
        televisao = new Televisao(canais, true, 1);
        int volumeInicial = televisao.volume();
        televisao.diminuirVolume();
        assertTrue(televisao.volume() < volumeInicial);
    }
    
    @Test
    public void avancoDeCanal() {
        televisao = new Televisao(canais, true);
        televisao.avancarCanal();
        assertEquals(2, televisao.canal());
    }
    
    @Test
    public void retrocessoDeCanal() {
        televisao = new Televisao(canais, true);
        televisao.retrocederCanal();
        assertEquals(5, televisao.canal());
    }
    
    @Test
    public void selecaoDeCanal() {
        televisao = new Televisao(canais, true);
        televisao.selecionarCanal(3);
        assertEquals(3, televisao.canal());
    }
    
}
