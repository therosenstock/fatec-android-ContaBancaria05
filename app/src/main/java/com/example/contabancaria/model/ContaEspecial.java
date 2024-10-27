package com.example.contabancaria.model;

public class ContaEspecial extends ContaBancaria {
    private double limite;

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public ContaEspecial(String cliente, int numConta, double saldoInicial, double limite) {
        super(cliente, numConta, saldoInicial);
        this.limite = limite;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo + limite) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}

