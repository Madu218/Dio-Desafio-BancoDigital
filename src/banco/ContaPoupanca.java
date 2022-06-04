package banco;
public class ContaPoupanca extends Conta {
	private static final double TAXA = 0.2;

	public ContaPoupanca(Cliente cliente, String numero) {
		super(cliente, numero);
	}

	public void renderJuros() {
		double juros = this.getSaldo()*TAXA;
		this.creditar(juros);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Poupança ===");
		super.imprimirInfosComuns();
	}
}