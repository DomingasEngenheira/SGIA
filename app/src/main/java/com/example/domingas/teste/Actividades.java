package com.example.domingas.teste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Actividades extends AppCompatActivity {
    ImageView btnMulher;
    TextView home;
    TextView agenda;
    TextView dizimos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        btnMulher = (ImageView) findViewById(R.id.mulher);
        home = (TextView) findViewById(R.id.home);
        agenda = (TextView) findViewById(R.id.evento);
        dizimos = (TextView) findViewById(R.id.txtDizimo);

        btnMulher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Actividades.this, PrincipalMulher.class);
                startActivity(i);

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Actividades.this, MenuPrincipal.class);
                startActivity(i);

            }
             });

        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Actividades.this, Eventos.class);
                startActivity(i);

            }
        });

        dizimos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent di = new Intent(Actividades.this, Dizimos.class);
                startActivity(di);
            }
        });
    }
}
