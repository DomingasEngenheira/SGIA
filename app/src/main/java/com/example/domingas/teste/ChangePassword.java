package com.example.domingas.teste;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.domingas.teste.Login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class ChangePassword extends AppCompatActivity {
    private Toolbar mtoolbar;
    private Button ResetPasswordSendEmail;
    private EditText ResetEmailInput;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        auth = FirebaseAuth.getInstance();



        ResetPasswordSendEmail = (Button) findViewById(R.id.troca);
        ResetEmailInput = (EditText) findViewById(R.id.novaPassa);

        ResetPasswordSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = ResetEmailInput.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(ChangePassword.this, "escre um email valido", Toast.LENGTH_SHORT).show();
                } else {
                    auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePassword.this, "Please visualize o seu email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChangePassword.this, Login.class));
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(ChangePassword.this, "Erro", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

}