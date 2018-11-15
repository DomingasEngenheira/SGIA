package com.example.domingas.teste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.domingas.teste.cadastro.CadastroGeral;

public class Principal extends AppCompatActivity {
    Button btnSim, btnNao;
    String get_nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Intent intent = getIntent();
        get_nome = intent.getStringExtra("notify");
        Toast.makeText(getApplicationContext(), get_nome, Toast.LENGTH_SHORT).show();

        btnSim = (Button) findViewById(R.id.btnSim);
        btnNao = (Button) findViewById(R.id.btnNao);

        btnSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, CadastroGeral.class);
                startActivity(i);
                finish();

            }

        });

        btnNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, Actividades.class);
                startActivity(i);
                finish();
            }
        });
    }
}
