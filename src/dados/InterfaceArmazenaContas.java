package dados;

import banco.Cliente;
import banco.Conta;

public interface InterfaceArmazenaContas {
    
    void inserir(Cliente cliente, Conta conta) throws NumeroMaxContasException;
    void removerCliente(Cliente cliente) throws ContaNaoEncontradaException;
    Conta procurar(Cliente cliente, String numero) throws ContaNaoEncontradaException;
    boolean existe(String numeroConta);

}
