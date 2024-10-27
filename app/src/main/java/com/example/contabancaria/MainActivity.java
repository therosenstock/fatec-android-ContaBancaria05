package com.example.contabancaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contabancaria.model.ContaBancaria;
import com.example.contabancaria.model.ContaEspecial;
import com.example.contabancaria.model.ContaPoupanca;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ContaBancaria> contas = new ArrayList<>();;

    private EditText editCliente, editNumConta, editValor, editDiaRendimento, editLimite;
    private RadioGroup radioGroup;
    private RadioButton radioPoupanca, radioEspecial;
    private ContaBancaria conta;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editCliente = findViewById(R.id.editCliente);
        editNumConta = findViewById(R.id.editNumConta);
        editValor = findViewById(R.id.editValor);
        editLimite = findViewById(R.id.editLimite);
        editDiaRendimento = findViewById(R.id.editDiaRendimento);
        radioGroup = findViewById(R.id.radioGroup);
        radioPoupanca = findViewById(R.id.radioPoupanca);
        radioEspecial = findViewById(R.id.radioEspecial);
        radioPoupanca.setChecked(true);
        radioEspecial.setChecked(false);


        Button btnSacar = findViewById(R.id.btnSacar);
        Button btnDepositar = findViewById(R.id.btnDepositar);
        Button btnMostrarDados = findViewById(R.id.btnMostrarDados);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnSacar.setOnClickListener(v -> sacarValor());
        btnDepositar.setOnClickListener(v -> depositarValor());
        btnMostrarDados.setOnClickListener(v -> mostrarDados());
        btnCadastrar.setOnClickListener(v -> cadastrar());

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioPoupanca) {
                editDiaRendimento.setVisibility(View.VISIBLE);
                editLimite.setVisibility(View.GONE);
            } else if (checkedId == R.id.radioEspecial) {
                editLimite.setVisibility(View.VISIBLE);
                editDiaRendimento.setVisibility(View.GONE);
            }
        });
    }

    private void cadastrar() {

            String nome = String.valueOf(editCliente.getText());
            int numConta = Integer.parseInt(String.valueOf(editNumConta.getText()));
            double valor = Double.parseDouble(String.valueOf(editValor.getText()));
            if (radioEspecial.isChecked()) {
                double limite = Double.parseDouble(String.valueOf(editLimite.getText()));
                ContaEspecial e = new ContaEspecial(nome, numConta, valor, limite);
                contas.add(e);
            } else {
                int diaRendimento = Integer.parseInt(String.valueOf(editDiaRendimento.getText()));
                ContaPoupanca p = new ContaPoupanca(nome, numConta, valor, diaRendimento);
                contas.add(p);
            }
            Toast.makeText(this, "Conta cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
        limpar();
    }

    private ContaBancaria buscarConta(int numeroConta) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }
    private void limpar(){
        editNumConta.setText("");
        editValor.setText("");
        editCliente.setText("");
        editLimite.setText("");
        editDiaRendimento.setText("");
    }
    private void sacarValor() {
        try {
            int numeroConta = Integer.parseInt(editNumConta.getText().toString());
            float valor = Float.parseFloat(editValor.getText().toString());

            ContaBancaria conta = buscarConta(numeroConta);
            if (conta != null && conta.sacar(valor)) {
                Toast.makeText(this, "Saque realizado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Saldo insuficiente ou conta não encontrada.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao realizar saque.", Toast.LENGTH_SHORT).show();
        }

    }

    private void depositarValor() {
        try {
            int numeroConta = Integer.parseInt(editNumConta.getText().toString());
            float valor = Float.parseFloat(editValor.getText().toString());

            ContaBancaria conta = buscarConta(numeroConta);
            if (conta != null) {
                conta.depositar(valor);
                Toast.makeText(this, "Depósito realizado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Conta não encontrada.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao realizar depósito.", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarDados() {
        if (conta != null) {
            textResultado.setText(conta.toString());
        } else {
            textResultado.setText("Nenhuma conta registrada.");
        }
    }

    public void criarConta(View view) {
        String cliente = editCliente.getText().toString();
        int numConta = Integer.parseInt(editNumConta.getText().toString());

        int tipoSelecionado = radioGroup.getCheckedRadioButtonId();
        if (tipoSelecionado == R.id.radioPoupanca) {
            conta = new ContaPoupanca(cliente, numConta, 0, 5); // Exemplo de dia de rendimento
        } else if (tipoSelecionado == R.id.radioEspecial) {
            conta = new ContaEspecial(cliente, numConta, 0, 1000); // Exemplo de limite
        }
    }
}