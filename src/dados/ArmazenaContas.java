package dados;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import banco.Cliente;
import banco.Conta;

public class ArmazenaContas implements InterfaceArmazenaContas {
    private Map<Cliente, Conta[]> contas;

    public ArmazenaContas() {
        this.contas = new HashMap<Cliente, Conta[]>();
    }

    @Override
    public void inserir(Cliente cliente, Conta conta) throws NumeroMaxContasException {
        if (!this.contas.containsKey(cliente)) this.inserirCliente(cliente);
        this.inserirConta(conta, cliente);
    }

    private void inserirCliente(Cliente cliente) {
        this.contas.put(cliente, null);
    }

    private void inserirConta(Conta conta, Cliente cliente) throws NumeroMaxContasException {
        Conta[] c = this.contas.get(cliente);
        if (c!=null ) {
            int count = 0;
            for (int i = 0; i < c.length; i++) {
                if (c[i] == null) c[i] = conta;
                else count++;
            } 
            if (count==c.length) throw new NumeroMaxContasException();
        }
        else {
            this.contas.put(cliente, new Conta[2]);
            Conta[] cc = this.contas.get(cliente);
            cc[0] = conta;
        }
    }

    @Override
    public void removerCliente(Cliente cliente) throws ContaNaoEncontradaException {
        if (!this.contas.containsKey(cliente)) throw new ContaNaoEncontradaException();
        this.contas.remove(cliente);
    }
    
    @Override
    public Conta procurar(Cliente cliente, String numero) throws ContaNaoEncontradaException { 
        Conta[] contas = this.contas.get(cliente);
        Conta conta = null;
        for (Conta conta2 : contas) {
            if (conta2!=null && conta2.getNumero().equals(numero)) return conta = conta2;
        }
        if (conta==null) throw new ContaNaoEncontradaException();
        return conta;
    }

    @Override
    public boolean existe(String numeroConta) {
        Collection<Conta[]> c = contas.values();
        for (Conta[] contas : c) {
            for (Conta cc : contas) {
                if (cc.getNumero().equals(numeroConta)) return true;
            }
        }
        return false;
    }
}
