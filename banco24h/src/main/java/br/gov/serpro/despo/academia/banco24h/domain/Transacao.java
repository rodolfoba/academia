package br.gov.serpro.despo.academia.banco24h.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {
	
	private BigDecimal valor;
	private LocalDate data;
	private String descricao;
	
	public Transacao(BigDecimal valor, LocalDate data, String descricao){
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
