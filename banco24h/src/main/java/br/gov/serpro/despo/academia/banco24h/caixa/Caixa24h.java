package br.gov.serpro.despo.academia.banco24h.caixa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.serpro.despo.academia.banco24h.domain.IdentificadorDeConta;
import br.gov.serpro.despo.academia.banco24h.domain.Transacao;
import br.gov.serpro.despo.academia.banco24h.exceptions.BancoNaoEncontradoException;
import br.gov.serpro.despo.academia.banco24h.exceptions.OperacaoBancariaException;
import br.gov.serpro.despo.academia.banco24h.exceptions.SaldoInsuficienteException;
import br.gov.serpro.despo.academia.banco24h.interfaces.BancoOperavel;

public class Caixa24h {

    private final Map<String, BancoOperavel> mapaNumerosBancosDisponiveis = new HashMap<>();
    
	public Caixa24h(Collection<BancoOperavel> bancosDisponiveis) throws BancoNaoEncontradoException {
    	if (bancosDisponiveis == null || bancosDisponiveis.isEmpty()) {
			throw new BancoNaoEncontradoException("Não é possível criar um Caixa Eletrônico sem bancos");
		}
        for (BancoOperavel banco : bancosDisponiveis) {
            this.mapaNumerosBancosDisponiveis.put(banco.getIdentificacao(), banco);
        }
    }

    public void sacar(String identificadorDeBanco, IdentificadorDeConta identificadorDeConta, BigDecimal valor) 
    		throws SaldoInsuficienteException, BancoNaoEncontradoException, OperacaoBancariaException {
        obterBanco(identificadorDeBanco).sacar(identificadorDeConta, valor);
    }

    public void depositar(String identificadorDeBanco, IdentificadorDeConta identificadorDeConta, BigDecimal valor) 
    		throws BancoNaoEncontradoException, OperacaoBancariaException{
        obterBanco(identificadorDeBanco).depositar(identificadorDeConta,valor);
    }

    public void transferir(String identificadorDeBanco, IdentificadorDeConta origem, IdentificadorDeConta destino, BigDecimal valor)
    		throws SaldoInsuficienteException, BancoNaoEncontradoException, OperacaoBancariaException{
        obterBanco(identificadorDeBanco).transferir(origem, destino, valor);
    }

    public BigDecimal consultarSaldo(String identificadorDeBanco, IdentificadorDeConta identificadorDeConta)
    		throws BancoNaoEncontradoException, OperacaoBancariaException{
        return obterBanco(identificadorDeBanco).consultarSaldo(identificadorDeConta);
    }

    public List<Transacao> consultarExtrato(String identificadorDeBanco, IdentificadorDeConta identificadorDeConta, LocalDate dataInicial, LocalDate dataFinal)
    		throws BancoNaoEncontradoException, OperacaoBancariaException{
        return obterBanco(identificadorDeBanco).consultarExtrato(identificadorDeConta, dataInicial, dataFinal);
    }

    private BancoOperavel obterBanco(String identificadorDeBanco) throws BancoNaoEncontradoException {
        if (!mapaNumerosBancosDisponiveis.containsKey(identificadorDeBanco)) {
            throw new BancoNaoEncontradoException("Banco com identificador '" + identificadorDeBanco + "' indisponível");
        }

        return mapaNumerosBancosDisponiveis.get(identificadorDeBanco);
    }

}
