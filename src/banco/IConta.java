package banco;

public interface IConta {
	void creditar(double valor);
	void debitar(double valor) throws SaldoInsuficienteException;
	void imprimirExtrato();
}
