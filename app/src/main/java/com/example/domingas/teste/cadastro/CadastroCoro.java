package com.example.domingas.teste.cadastro;

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


import com.example.domingas.teste.Modelo.Coro;
import com.example.domingas.teste.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CadastroCoro extends AppCompatActivity {
    EditText edtcargo, edtnumero, edtgrupo;
    ListView listV_dadosC;

    //private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Coro> listCoro = new ArrayList<Coro>();
    private ArrayAdapter<Coro> arrayAdapterCoro;

    Coro coroSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_coro);

        edtcargo = (EditText) findViewById(R.id.editCargo);
        edtnumero = (EditText) findViewById(R.id.editNumero);
        edtgrupo = (EditText) findViewById(R.id.editTipoCoro);
        listV_dadosC = (ListView) findViewById(R.id.listV_dadosC);

        inicializarFirebase();
        eventtoDatabase();



        listV_dadosC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                coroSelecionada = (Coro) parent.getItemAtPosition(position);
                edtcargo.setText(coroSelecionada.getCargo());
                edtgrupo.setText(coroSelecionada.getTipocoro());
                edtnumero.setText(coroSelecionada.getNumero());
                   }

        });

    }



    private void alert (String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    private void eventtoDatabase() {

        databaseReference.child("Coro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listCoro.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Coro coro = objSnapshot.getValue(Coro.class);
                    listCoro.add(coro);
                }
                arrayAdapterCoro = new ArrayAdapter<Coro>(CadastroCoro.this, android.R.layout.simple_list_item_1, listCoro);
                listV_dadosC.setAdapter(arrayAdapterCoro);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(CadastroCoro.this);
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
            Coro coro = new Coro();
            coro.setUid(UUID.randomUUID().toString());

           //Toast.makeText(getApplicationContext(), edtgrupo.getText().toString(), Toast.LENGTH_SHORT).show();

            coro.setCargo(edtcargo.getText().toString());
            coro.setNumero(edtnumero.getText().toString());
            coro.setTipocoro(edtgrupo.getText().toString());
            databaseReference.child("Coro").child(coro.getUid()).setValue(coro);
            Toast.makeText(getApplicationContext(), edtcargo.getText().toString(), Toast.LENGTH_SHORT).show();
            alert("Usuário cadastrado com Sucesso" + edtgrupo.getText().toString());
            limparcampos();
        }else if(id == R.id.menu_actualizar){
            Coro coro = new Coro();
            coro.setUid(coroSelecionada.getUid());
            coro.setCargo(edtcargo.getText().toString().trim());
            coro.setNumero(edtnumero.getText().toString().trim());
            coro.setTipocoro(edtgrupo.getText().toString().trim());
            databaseReference.child("Coro").child(coro.getUid()).setValue(coro);
            limparcampos();
        }else if(id == R.id.menu_delete){
            Coro coro = new Coro();
            coro.setUid(coroSelecionada.getUid());
            databaseReference.child("Coro").child(coro.getUid()).removeValue();
            limparcampos();

        }

        return true;
    }

    private void limparcampos() {
        edtgrupo.setText("");
        edtnumero.setText("");
        edtgrupo.setText("");

    }
}