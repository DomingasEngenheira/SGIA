package com.example.domingas.teste;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText email;
    private Button registrar;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        email = (EditText) findViewById(R.id.editTextN);
        registrar = (Button) findViewById(R.id.buttonRegistrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTokenToServer();
            }


        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));
    }

    private void sendTokenToServer() {
        String email2 = email.getText().toString().trim();
        if (TextUtils.isEmpty(email2)) {
            Toast.makeText(this, "Entra com o seu email", Toast.LENGTH_LONG).show();
        } else {
            if (SharedPrefManager.getInstance(this).getDeviceToken() != null) {
                //StringRequest stringRequest = new StringRequest(DownloadManager.Request.Method,);
            } else {
                Toast.makeText(this, "Token not ", Toast.LENGTH_LONG).show();

            }
        }
    }
}


