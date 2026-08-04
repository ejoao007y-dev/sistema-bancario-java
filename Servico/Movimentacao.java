package SistemaBancario.Servico;

import SistemaBancario.Classes.Conta;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Movimentacao {
    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;
    private double valor;
    private double saldoAtual;

    public Movimentacao(TipoMovimentacao tipo, double valor, double saldoAtual) {
        this.tipo = tipo;
        this.valor = valor;
        this.saldoAtual = saldoAtual;
        this.dataHora = LocalDateTime.now();
    }

    public Movimentacao(LocalDateTime dataHora, TipoMovimentacao tipo, double valor, double saldoAtual) {
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.valor = valor;
        this.saldoAtual = saldoAtual;
    }

    @Override
    public String toString() {
        return "==================================\n" +
                "\nData: " + Formatador.formatarData(dataHora) + "\nTipo: " + tipo + "\nValor: " + Formatador.formatarMoeda(valor) + "\nSaldo: " + Formatador.formatarMoeda(saldoAtual) + "\nSaldo após movimentação: " + Formatador.formatarMoeda(saldoAtual) +
                "\n==================================\n";
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }
}
