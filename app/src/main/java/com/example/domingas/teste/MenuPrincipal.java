package com.example.domingas.teste;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.domingas.teste.cadastro.CadastroMembro;

public class MenuPrincipal extends AppCompatActivity {
    ImageView imgEvento;
    ImageView imgMinisterio;
    ImageView imgedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        imgEvento = (ImageView) findViewById(R.id.imgAgenda);
        imgMinisterio = (ImageView) findViewById(R.id.imgMinisterio);
        imgedit = (ImageView) findViewById(R.id.imgEdit);

        imgEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPrincipal.this, Eventos.class);
                startActivity(i);
                finish();

            }
        });

        imgMinisterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(MenuPrincipal.this, Actividades.class);
                startActivity(m);
                finish();
            }
        });

        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new  Intent(MenuPrincipal.this, CadastroMembro.class);
                startActivity(i);
                finish();
            }
        });
    }



}
