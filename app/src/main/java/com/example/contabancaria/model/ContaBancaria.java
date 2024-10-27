package com.example.contabancaria.model;

public class ContaBancaria {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    protected String cliente;
    protected int numConta;
    protected double saldo;

    public ContaBancaria() {

    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ContaBancaria(String cliente, int numConta, double saldoInicial) {
        this.cliente = cliente;
        this.numConta = numConta;
        this.saldo = saldoInicial;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente + "\nNúmero da Conta: " + numConta + "\nSaldo: R$" + saldo;
    }
}
