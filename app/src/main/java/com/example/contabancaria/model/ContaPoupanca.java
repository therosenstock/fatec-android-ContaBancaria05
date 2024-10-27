package com.example.contabancaria.model;

public class ContaPoupanca extends ContaBancaria {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    private int diaDeRendimento;

    public int getDiaDeRendimento() {
        return diaDeRendimento;
    }

    public void setDiaDeRendimento(int diaDeRendimento) {
        this.diaDeRendimento = diaDeRendimento;
    }

    public void calcularNovoSaldo(double taxaRendimento) {
        saldo += saldo * (taxaRendimento / 100);
    }
}

