package br.gov.serpro.academiadespo;

import java.util.Collection;
import java.util.TreeSet;

public class Televisao implements DispositivoComAcionamentoControlavel, DispositivoComVolumeControlavel,
        DispositivoComCanalControlavel {

    private static final int VOLUME_MIN = 0;
    private static final int VOLUME_MAX = 50;

    private boolean ligada;
    private final ControladorDeVolume controladorDeVolume;
    private final ControladorDeCanal controladorDeCanal;

    public Televisao(Collection<Integer> canais) {
        this(canais, false);
    }

    public Televisao(Collection<Integer> canais, boolean isLigada) {
        this(canais, isLigada, 0);
    }

    public Televisao(Collection<Integer> canais, boolean isLigada, int volume) {
        this.ligada = isLigada;
        this.controladorDeVolume = new ControladorDeVolume(VOLUME_MIN, VOLUME_MAX, volume);
        this.controladorDeCanal = new ControladorDeCanal(new TreeSet<>(canais));
    }

    @Override
    public void ligarOuDesligar() {
        ligada = !ligada;
    }

    @Override
    public void aumentarVolume() {
        controladorDeVolume.aumentar();
    }

    @Override
    public void diminuirVolume() {
        controladorDeVolume.diminuir();
    }

    public int volume() {
        return controladorDeVolume.getVolume();
    }

    public boolean isLigada() {
        return ligada;
    }

    public boolean isDesligada() {
        return !ligada;
    }

    @Override
    public void avancarCanal() {
        controladorDeCanal.avancar();

    }

    @Override
    public void retrocederCanal() {
        controladorDeCanal.retroceder();

    }

    @Override
    public void selecionarCanal(int numero) {
        controladorDeCanal.selecionar(numero);
    }
    
    public int canal() {
        return controladorDeCanal.getCanal();
    }

}
