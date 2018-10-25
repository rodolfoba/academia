package br.gov.serpro.academiadespo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class ControleRemotoTest {

    Collection<Integer> canais = Arrays.asList(1, 2, 3, 4, 5);
    Televisao televisao;
    ControleRemoto controle;

    @Before
    public void before() {
        controle = new ControleRemoto();
    }

    @Test
    public void ligar() {
        televisao = new Televisao(canais);
        controle.ligarOuDesligar(televisao);
        assertTrue(televisao.isLigada());
    }

    @Test
    public void desligar() {
        televisao = new Televisao(canais, true);
        controle.ligarOuDesligar(televisao);
        assertTrue(televisao.isDesligada());
    }

    @Test
    public void aumentoDeVolume() {
        televisao = new Televisao(canais, true);
        int volume = televisao.volume();
        controle.aumentarVolume(televisao);
        assertEquals(volume + 1, televisao.volume());
    }

    @Test
    public void diminuicaoDeVolume() {
        televisao = new Televisao(canais, true, 1);
        int volume = televisao.volume();
        controle.diminuirVolume(televisao);
        assertEquals(volume - 1, televisao.volume());
    }
    
    @Test
    public void avancoDeCanal() {
        televisao = new Televisao(canais, true);
        controle.avancarCanal(televisao);
        assertEquals(2, televisao.canal());
    }
    
    @Test
    public void retrocessoDeCanal() {
        televisao = new Televisao(canais, true);
        controle.retrocederCanal(televisao);
        assertEquals(5, televisao.canal());
    }
    
    @Test
    public void selecaoDeCanal() {
        televisao = new Televisao(canais, true);
        controle.selecionarCanal(televisao, 3);
        assertEquals(3, televisao.canal());
    }

}
