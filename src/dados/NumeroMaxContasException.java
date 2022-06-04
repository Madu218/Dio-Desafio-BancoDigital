package dados;

public class NumeroMaxContasException extends Exception {
    public NumeroMaxContasException() {
        super("Você já possui o número máximo de contas, por favor entrar em contato com o banco.");
    }
}
