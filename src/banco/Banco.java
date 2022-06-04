package banco;

import java.util.Random;

import dados.ArmazenaContas;
import dados.ContaNaoEncontradaException;
import dados.NumeroMaxContasException;

public class Banco {
	private ArmazenaContas contas;
	Random gerador = new Random();

	public Banco(ArmazenaContas rep) {
		this.contas = rep;
	}

	public String gerarNumeroConta() {
		String numero = "";
		do {
			for (int i = 0; i < 9; i++) {
				numero+=gerador.nextInt(9);
			}
		} while (contas.existe(numero));
		return numero;
	}

	public void cadastrar(Cliente cliente, Conta conta) throws NumeroMaxContasException {
		this.contas.inserir(cliente, conta);
	}

	public void sacar(Cliente cliente, String numero, double valor) throws ContaNaoEncontradaException, SaldoInsuficienteException {
		Conta c = contas.procurar(cliente, numero);
		c.debitar(valor);
	}

	public void depositar(Cliente cliente, String numero, double valor) throws ContaNaoEncontradaException {
		Conta c = contas.procurar(cliente, numero);
		c.creditar(valor);
	}

	public void transferir(Cliente cliente, String nEnvia, String nRecebe, double valor) throws ContaNaoEncontradaException, SaldoInsuficienteException {
		Conta c = contas.procurar(cliente, nEnvia);
		Conta c2 = contas.procurar(cliente, nRecebe);
		c.debitar(valor);
		c2.creditar(valor);
	}

	public void consultarSaldo(Cliente cliente, String numero) throws ContaNaoEncontradaException {
		Conta c = contas.procurar(cliente, numero);
		System.out.println("Seu saldo atual é de R$ " + String.format("%.2f", c.getSaldo()));
	}

	public void consultarExtrato(Cliente cliente, String numero) throws ContaNaoEncontradaException {
		Conta c = contas.procurar(cliente, numero);
		c.imprimirExtrato();
	}

	public void cancelarCadastro(Cliente cliente) throws ContaNaoEncontradaException {
		this.contas.removerCliente(cliente);
	}

	public void renderJuros(Cliente cliente, String numero) throws ContaNaoEncontradaException {
		Conta c = contas.procurar(cliente, numero);
		((ContaPoupanca) c).renderJuros();
	}
}