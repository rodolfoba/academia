package br.gov.serpro.academiadespo;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

public class ControladorDeCanal {

    private final LinkedList<Integer> canais;
    private Integer canal;
    
    public ControladorDeCanal(Set<Integer> canaisDisponiveis) {
        canais = new LinkedList<>(new TreeSet<>(canaisDisponiveis));
        canal = canais.getFirst();
    }
    
    public void avancar() {
        ListIterator<Integer> iterator = canais.listIterator(canais.indexOf(canal));
        iterator.next();
        if (iterator.hasNext()) {
            canal = iterator.next();
        } else {
            canal = canais.getFirst();
        }
    }
    
    public void retroceder() {
        ListIterator<Integer> iterator = canais.listIterator(canais.indexOf(canal));
        if (iterator.hasPrevious()) {
            canal = iterator.previous();
        } else {
            canal = canais.getLast();
        }
    }
    
    public void selecionar(int numero) {
        if (canais.contains(numero)) {
            canal = canais.get(canais.indexOf(numero));
        }
    }
    
    public Integer getCanal() {
        return canal;
    }
    
}
