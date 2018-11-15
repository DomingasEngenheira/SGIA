package com.example.domingas.teste.cadastro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.domingas.teste.Login.Conexao;
import com.example.domingas.teste.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroUsuario extends AppCompatActivity {
    private EditText editEmail, editSenha;
    private Button btnRegistrar, btnVoltar;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

                inicializarComponente();
                eventotoClicks();

            }

            private void eventotoClicks() {
                btnVoltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                btnRegistrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = editEmail.getText().toString().trim();
                        String senha = editSenha.getText().toString().trim();
                        criarUser(email,senha);
                    }
                });

            }

            @Override
            protected void onStart() {
                super.onStart();
                auth = Conexao.getFirebaseAuth();
            }



            private void criarUser(String email,String senha) {
                auth.createUserWithEmailAndPassword(email,senha)
                        .addOnCompleteListener(CadastroUsuario.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    alert("Usuário cadastrado com Sucesso");
                                    Intent i = new Intent(getApplicationContext(), CadastroMembro.class);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    alert("Erro de Cadastro");
                                }
                            }
                        });
            }


            private void alert (String msg){
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            }
            private void inicializarComponente() {
                editEmail = (EditText) findViewById(R.id.Email);
                editSenha = (EditText) findViewById(R.id.Senha);
                btnRegistrar = (Button) findViewById(R.id.btnCadastro);
                btnVoltar = (Button) findViewById(R.id.btnVoltar);

            }



        }

