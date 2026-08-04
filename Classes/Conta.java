package SistemaBancario.Classes;

import SistemaBancario.Servico.Formatador;
import SistemaBancario.Servico.Movimentacao;

import java.util.ArrayList;

public class Conta {
    private int numeroConta;
    private double saldo;
    private Cliente cliente;
    private ArrayList<Movimentacao> movimentacoes;

    public Conta(int numeroConta, double saldo, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.movimentacoes = new ArrayList<>();
    }

    public void adicionarMovimentacao(Movimentacao movimentacao) {
        this.movimentacoes.add(movimentacao);
    }

    public ArrayList<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    @Override
    public String toString() {
        return "=============================\n" +
                "Conta Bancária" + "\nNúmero: " + numeroConta  + "\nSaldo: " + Formatador.formatarMoeda(saldo) +
                "\n=============================\n";
    }
}
