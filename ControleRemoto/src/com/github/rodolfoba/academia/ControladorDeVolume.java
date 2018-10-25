package com.github.rodolfoba.academia;

public class ControladorDeVolume {

    private final int minimo;
    private final int maximo;
    private int volume;

    public ControladorDeVolume(int minimo, int maximo, int volume) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.volume = volume;
    }

    public void aumentar() {
        if (volume < maximo) {
            volume++;
        }
    }
    
    public void diminuir() {
        if (volume > minimo) {
            volume--;
        }
    }
    
    public int getVolume() {
        return volume;
    }
    
}
