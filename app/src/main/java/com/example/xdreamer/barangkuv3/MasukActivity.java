package com.example.xdreamer.barangkuv3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MasukActivity extends AppCompatActivity {

    private EditText email, pass;
    private TextView toDaft, lupaPass;
    private Button masuk;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        email = findViewById(R.id.etEmailMasuk);
        pass = findViewById(R.id.etPassMasuk);
        masuk = findViewById(R.id.buttonMasuk);
        auth = FirebaseAuth.getInstance();
        toDaft = findViewById(R.id.toDaftar);
        lupaPass = findViewById(R.id.lupaPass);


        getSupportActionBar().setTitle("Masuk");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasukActivity.this, ResetPassword.class));
            }
        });

        toDaft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasukActivity.this, DaftarActivity.class));
            }
        });

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString().trim();
                String passwordText = pass.getText().toString().trim();

                if (TextUtils.isEmpty(emailText)) {
                    email.setError("Email tidak boleh kosong");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(passwordText)) {
                    pass.setError("Password tidak boleh kosong");
                    pass.requestFocus();
                } else {
                    auth.signInWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener(MasukActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = auth.getCurrentUser();
                                        Intent i = new Intent(MasukActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(MasukActivity.this, "login gagal", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
