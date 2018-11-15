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

import com.example.domingas.teste.Modelo.Igreja;
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

public class CadastroIgreja extends AppCompatActivity {

    EditText edtnome, edtlocalidade;
    ListView listV_dadosI;

    //private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Igreja> listIgreja = new ArrayList<Igreja>();
    private ArrayAdapter<Igreja> arrayAdapterIgreja;

    Igreja igrejaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_igreja);

        edtnome = (EditText) findViewById(R.id.editNomeCongregação);
        edtlocalidade = (EditText) findViewById(R.id.editLocalidade);
        listV_dadosI = (ListView) findViewById(R.id.listV_dadosIgre);

        inicializarFirebase();
        //eventtoDatabase();


/*
        listV_dadosI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                igrejaSelecionada = (Igreja) parent.getItemAtPosition(position);
                edtnome.setText(igrejaSelecionada.getNome());
                edtlocalidade.setText(igrejaSelecionada.getLocalidade());

            }

        });*/

    }



    private void alert (String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }
/*
    private void eventtoDatabase() {

        databaseReference.child("Igreja").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listIgreja.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Igreja igreja = objSnapshot.getValue(Igreja.class);
                    listIgreja.add(igreja);
                }
                arrayAdapterIgreja = new ArrayAdapter<Igreja>(CadastroIgreja.this, android.R.layout.simple_list_item_1, listIgreja);
                listV_dadosI.setAdapter(arrayAdapterIgreja);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(CadastroIgreja.this);
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
            Igreja igreja = new Igreja();
            igreja.setUId(UUID.randomUUID().toString());

            //Toast.makeText(getApplicationContext(), edtgrupo.getText().toString(), Toast.LENGTH_SHORT).show();

            igreja.setNome(edtnome.getText().toString());
            igreja.setLocalidade(edtlocalidade.getText().toString());
            databaseReference.child("Igreja").child(igreja.getUId()).setValue(igreja);

            // Toast.makeText(getApplicationContext(), edtcargo.getText().toString(), Toast.LENGTH_SHORT).show();
           alert("Usuário cadastrado com Sucesso");
            limparcampos();
        }else if(id == R.id.menu_actualizar){
            Igreja igreja = new Igreja();
            igreja.setUId(igrejaSelecionada.getUId());
            igreja.setNome(edtnome.getText().toString().trim());
            igreja.setLocalidade(edtlocalidade.getText().toString().trim());
            //coro.setTipocoro(edtgrupo.getText().toString().trim());
            databaseReference.child("Igreja").child(igreja.getUId()).setValue(igreja);
            limparcampos();
        }else if(id == R.id.menu_delete){
            Igreja igreja = new Igreja();
            igreja.setUId(igrejaSelecionada.getUId());
            databaseReference.child("Igreja").child(igreja.getUId()).removeValue();
            limparcampos();

        }

        return true;
    }

    private void limparcampos() {
        edtlocalidade.setText("");
        edtnome.setText("");
       // edtgrupo.setText("");

    }

}
