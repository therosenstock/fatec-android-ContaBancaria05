package com.example.contabancaria.model;

public class ContaPoupanca extends ContaBancaria {
    private int diaDeRendimento;

    public int getDiaDeRendimento() {
        return diaDeRendimento;
    }

    public void setDiaDeRendimento(int diaDeRendimento) {
        this.diaDeRendimento = diaDeRendimento;
    }

    public ContaPoupanca(String cliente, int numConta, double saldoInicial, int diaDeRendimento) {
        super(cliente, numConta, saldoInicial);
        this.diaDeRendimento = diaDeRendimento;
    }

    public void calcularNovoSaldo(double taxaRendimento) {
        saldo += saldo * (taxaRendimento / 100);
    }
}

