package br.gov.serpro.despo.academia.banco24h.bancoalfa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import br.gov.serpro.despo.academia.banco24h.domain.Conta;
import br.gov.serpro.despo.academia.banco24h.exceptions.IdentificadorDeContaInvalidoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;

public abstract class ContaBancoAlfa extends Conta {

    private Map<LocalDate, Integer> operacoesPorDia = new HashMap<>();
    
    public ContaBancoAlfa(IdentificadorDeContaBancoAlfa identificadorDeConta, BigDecimal saldo)
            throws IdentificadorDeContaInvalidoException, SaldoInsuficienteException {
        
        super(identificadorDeConta, saldo);
    }
    
    @Override
    public IdentificadorDeContaBancoAlfa getIdentificadorDeConta() {
        return (IdentificadorDeContaBancoAlfa) super.getIdentificadorDeConta();
    }
    
    protected abstract int getLimiteDeOperacoesPorDia();
    
    public void registraOperacaoRealizada(LocalDate dia) {
        operacoesPorDia.put(dia, operacoesPorDia.getOrDefault(dia, 0) + 1);
    }
    
    public boolean isLimiteDeOperacoesPorDiaAlcancado(LocalDate dia) {
        return getLimiteDeOperacoesPorDia() != 0 && getLimiteDeOperacoesPorDia() <= operacoesPorDia.getOrDefault(dia, 0);
    }

}
