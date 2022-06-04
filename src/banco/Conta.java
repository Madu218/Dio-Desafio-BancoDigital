package banco;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
	private static final int AGENCIA_PADRAO = 1234;
	
	private int agencia;
	private String numero;
	private double saldo;
	private Cliente cliente;
	private List<String> extrato;

	public Conta(Cliente cliente, String numero) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.cliente = cliente;
		this.numero = numero;
		this.saldo = 0.0;
		this.extrato = new ArrayList<>();
		this.extrato.add("Saldo inicial: R$ 0,00");
	}

	@Override
	public void debitar(double valor) throws SaldoInsuficienteException {
		if (this.saldo<valor) throw new SaldoInsuficienteException();
		saldo -= valor;
		this.extrato.add("              -R$ " + String.format("%.2f", valor));
	}

	@Override
	public void creditar(double valor) {
		saldo += valor;
		this.extrato.add("              +R$ " + String.format("%.2f", valor));
	}

	public int getAgencia() {
		return agencia;
	}

	public String getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println("Número: " + this.numero.substring(0, 8) + "-" + this.numero.substring(8));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
		System.out.println("...........................");
		for (String string : extrato) {
			System.out.println(string);
		}
	}
}
