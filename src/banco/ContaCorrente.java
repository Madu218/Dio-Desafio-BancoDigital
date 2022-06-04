package banco;
public class ContaCorrente extends Conta {

	public ContaCorrente(Cliente cliente, String numero) {
		super(cliente, numero);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}
	
}