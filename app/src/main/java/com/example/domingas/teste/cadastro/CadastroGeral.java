package com.example.domingas.teste.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.domingas.teste.CadastroDireccao;
import com.example.domingas.teste.R;

public class CadastroGeral extends AppCompatActivity {
    Button btnMembro, btnCoro, btnDireccao, btnIgreja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_geral);
        btnMembro = (Button) findViewById(R.id.btnMembro);
        btnCoro = (Button) findViewById(R.id.btnCoro);
        btnDireccao = (Button) findViewById(R.id.btnDireccao);
        btnIgreja = (Button) findViewById(R.id.btnFiliais);

        btnMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CadastroGeral.this, CadastroMembro.class);
                startActivity(i);
            }
        });

        btnCoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(CadastroGeral.this, CadastroCoro.class);
                startActivity(a);
            }
        });



        /*btnDireccao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(CadastroGeral.this, CadastroCoro.class);
            }
        });*/

        btnIgreja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CadastroGeral.this, CadastroIgreja.class);
                startActivity(i);
                finish();
            }
        });

        }



}

