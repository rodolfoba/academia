package br.gov.serpro.despo.academia.banco24h.domain;

public class IdentificadorDeContaFake extends IdentificadorDeConta {
	
	private long numeroConta;
	
	public IdentificadorDeContaFake(long numeroConta) {
		this.numeroConta = numeroConta;
	}

	@Override
	public boolean isValid(){
		return numeroConta > 0;
	}

	@Override
	protected boolean ehIgual(IdentificadorDeConta outroIdentificador) {
		if (getClass() != outroIdentificador.getClass()){
			return false;
		}
		IdentificadorDeContaFake other = (IdentificadorDeContaFake) outroIdentificador;
		if (numeroConta != other.numeroConta){
			return false;
		}
		return true;
	}

	@Override
	protected int calcularHashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + (int) (numeroConta ^ (numeroConta >>> 32));
		return result;
	}

	public long getNumeroConta(){
		return numeroConta;
	}

}
