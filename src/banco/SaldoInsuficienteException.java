package banco;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException() {
        super("Seu saldo é insuficiente para realizar a operação.");
    }
}
