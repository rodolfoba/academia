package com.github.rodolfoba.academia;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.github.rodolfoba.academia.ControladorDeCanal;

public class ControladorDeCanalTest {

    Set<Integer> canais = new TreeSet<>(Arrays.asList(1, 2)); 
    ControladorDeCanal controlador;
    
    @Test
    public void canalInicial() {
        controlador = new ControladorDeCanal(canais);
        assertEquals(1, (int) controlador.getCanal());
    }
    
    @Test
    public void avancoDeCanal() {
        controlador = new ControladorDeCanal(canais);
        controlador.avancar();
        assertEquals(2, (int) controlador.getCanal());
    }
    
    @Test
    public void avancoNoUltimoCanal() {
        controlador = new ControladorDeCanal(canais);
        controlador.selecionar(2);
        controlador.avancar();
        assertEquals(1, (int) controlador.getCanal());
    }
    
    @Test
    public void retrocessoDeCanal() {
        controlador = new ControladorDeCanal(canais);
        controlador.selecionar(2);
        controlador.retroceder();
        assertEquals(1, (int) controlador.getCanal());
    }
    
    @Test
    public void retrocessoNoPrimeiroCanal() {
        controlador = new ControladorDeCanal(canais);
        controlador.retroceder();
        assertEquals(2, (int) controlador.getCanal());
    }
    
    @Test
    public void selecaoDeCanal() {
        controlador = new ControladorDeCanal(canais);
        controlador.selecionar(2);
        assertEquals(2, (int) controlador.getCanal());
    }
    
    @Test
    public void selecaoDeCanalInexistente() {
        controlador = new ControladorDeCanal(canais);
        controlador.selecionar(3);
        assertEquals(1, (int) controlador.getCanal());
    }
}
