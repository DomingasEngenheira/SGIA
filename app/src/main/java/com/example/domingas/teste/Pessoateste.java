package com.example.domingas.teste;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.domingas.teste.Modelo.Pessoa;
import com.example.domingas.teste.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;



public class Pessoateste extends AppCompatActivity {
    EditText edtNome, edtEmail, edtSenha;
    ListView listV_dados;

    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Pessoa> listPessoa = new ArrayList<Pessoa>();
    private ArrayAdapter<Pessoa> arrayAdapterPessoa;

    Pessoa pessoaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoateste);
       // edtEmail = (EditText) findViewById(R.id.editTextNome);
        edtNome = (EditText) findViewById(R.id.editTextNome);
        edtSenha = (EditText) findViewById(R.id.editText2Senha);
        listV_dados = (ListView) findViewById(R.id.testenovo);

        inicializarFirebase();
        eventtoDatabase();


        listV_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pessoaSelecionada = (Pessoa)parent.getItemAtPosition(position);
                edtNome.setText(pessoaSelecionada.getNome());
                edtSenha.setText(pessoaSelecionada.getSenha());
                //edtEmail.setText(pessoaSelecionada.getEmail());
            }

        });

    }



    private void eventtoDatabase() {
        databaseReference.child("Pessoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPessoa.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Pessoa p = objSnapshot.getValue(Pessoa.class);
                    listPessoa.add(p);
                }
                arrayAdapterPessoa = new ArrayAdapter<Pessoa>(Pessoateste.this,android.R.layout.simple_list_item_1,listPessoa);
                listV_dados.setAdapter(arrayAdapterPessoa);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(Pessoateste.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_novo) {
            Pessoa p = new Pessoa();
            p.setUid(UUID.randomUUID().toString());
            p.setNome(edtNome.getText().toString());
            p.setSenha(edtSenha.getText().toString());
            databaseReference.child("Pessoa").child(p.getUid()).setValue(p);

            limparcampos();
        }else if(id == R.id.menu_actualizar){
            Pessoa p = new Pessoa();
            p.setUid(pessoaSelecionada.getUid());
            p.setNome(edtNome.getText().toString().trim());
            p.setSenha(edtSenha.getText().toString().trim());
            databaseReference.child("Pessoa").child(p.getUid()).setValue(p);
            limparcampos();
        }else if(id == R.id.menu_delete){
            Pessoa p = new Pessoa();
            p.setUid(pessoaSelecionada.getUid());
            databaseReference.child("Pessoa").child(p.getUid()).removeValue();
            limparcampos();
        }

        return true;
    }

    private void limparcampos() {
        edtEmail.setText("");
        edtNome.setText("");
        edtSenha.setText("");

    }
}
