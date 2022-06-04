package dados;

public class ContaNaoEncontradaException extends Exception {
    public ContaNaoEncontradaException() {
        super("Não foi possível encontrar a conta no cadastro do cliente.");
    }
}
