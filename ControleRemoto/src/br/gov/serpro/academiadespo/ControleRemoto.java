package br.gov.serpro.academiadespo;

public class ControleRemoto {

    public void ligarOuDesligar(DispositivoComAcionamentoControlavel dispositivo) {
        dispositivo.ligarOuDesligar();
    }
    
    public void aumentarVolume(DispositivoComVolumeControlavel dispositivo) {
        dispositivo.aumentarVolume();
    }
    
    public void diminuirVolume(DispositivoComVolumeControlavel dispositivo) {
        dispositivo.diminuirVolume();
    }
    
    public void avancarCanal(DispositivoComCanalControlavel dispositivo) {
        dispositivo.avancarCanal();
    }
    
    public void retrocederCanal(DispositivoComCanalControlavel dispositivo) {
        dispositivo.retrocederCanal();
    }
    
    public void selecionarCanal(DispositivoComCanalControlavel dispositivo, int numero) {
        dispositivo.selecionarCanal(numero);
    }
    
}
