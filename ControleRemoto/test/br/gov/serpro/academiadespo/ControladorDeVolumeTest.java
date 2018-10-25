package br.gov.serpro.academiadespo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ControladorDeVolumeTest {

    final int minimo = 0;
    final int maximo = 10;
    ControladorDeVolume controlador;
    
    @Test
    public void volumeInicial() {
        controlador = new ControladorDeVolume(minimo, maximo, 0);
        assertEquals(0, controlador.getVolume());
    }
    
    @Test
    public void aumentoDeVolume() {
        controlador = new ControladorDeVolume(minimo, maximo, 0);
        controlador.aumentar();
        assertEquals(1, controlador.getVolume());
    }
    
    @Test
    public void aumentoDeVolumeJaNoMaximo() {
        controlador = new ControladorDeVolume(minimo, maximo, maximo);
        controlador.aumentar();
        assertEquals(maximo, controlador.getVolume());
    }
    
    @Test
    public void diminuicaoDeVolume() {
        controlador = new ControladorDeVolume(minimo, maximo, 1);
        controlador.diminuir();
        assertEquals(0, controlador.getVolume());
    }
    
    @Test
    public void diminuicaoDeVolumeJaNoMinimo() {
        controlador = new ControladorDeVolume(minimo, maximo, minimo);
        controlador.diminuir();
        assertEquals(0, controlador.getVolume());
    }
}
