package br.gov.serpro.despo.academia.banco24h.domain;

public abstract class IdentificadorDeConta {	
	
	public abstract boolean isValid();
	protected abstract boolean ehIgual(IdentificadorDeConta outroIdentificador);
	protected abstract int calcularHashCode();
	
	@Override
	public int hashCode() {
		return calcularHashCode();
	}
	
	@Override
	public boolean equals(Object otherObject) {
		return ehIgual((IdentificadorDeConta)otherObject);
	}
}
