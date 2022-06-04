package ui;

import banco.Banco;
import banco.Cliente;
import banco.Conta;
import banco.ContaCorrente;
import banco.ContaPoupanca;
import banco.SaldoInsuficienteException;
import dados.ArmazenaContas;
import dados.ContaNaoEncontradaException;
import dados.NumeroMaxContasException;

public class Main {

	public static void main(String[] args) {
		try {
			ArmazenaContas repositorio = new ArmazenaContas();
			Banco banco = new Banco(repositorio);

			Cliente venilton = new Cliente("Venilton Costa dos Santos", "214.243.243-23");
			Cliente maria = new Cliente("Maria Santana de Menezes", "334.243.243-23");

			String n, n2;
			n = banco.gerarNumeroConta();
			n2 = banco.gerarNumeroConta();
			
			Conta cc = new ContaCorrente(venilton, n);
			Conta poupanca = new ContaPoupanca(venilton, n2);
			Conta cp = new ContaPoupanca(maria, banco.gerarNumeroConta());

			banco.cadastrar(venilton, cc);
			banco.cadastrar(venilton, poupanca);
			banco.cadastrar(maria, cp);

			banco.depositar(maria, cp.getNumero(), 32);
			banco.depositar(venilton, n, 58.90);
			banco.depositar(venilton, n2, 75.50);

			banco.consultarSaldo(venilton, n);

			banco.sacar(venilton, n2, 23.68);
			banco.sacar(venilton, n, 3.50);

			banco.consultarSaldo(venilton, n);

			banco.transferir(venilton, n2, n, 12.80);
 
			banco.renderJuros(maria, cp.getNumero());

			System.out.println();
			banco.consultarExtrato(venilton, n2);
			System.out.println();
			banco.consultarExtrato(venilton, n);
			System.out.println();
			banco.consultarExtrato(maria, cp.getNumero());

		} catch (NumeroMaxContasException e) {
			System.out.println(e.getMessage());
		} catch (ContaNaoEncontradaException e) {
			System.out.println(e.getMessage());
		} catch (SaldoInsuficienteException e) {
			System.out.println(e.getMessage());
		} catch (ClassCastException e) {
			System.out.println("A conta solicitada não pode render juros.");
		}
	}
}
