package com.example.xdreamer.barangkuv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xdreamer.barangkuv3.model.DataAkun;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DaftarActivity extends AppCompatActivity {

    private TextView nama_user, email_user, nohp_user, pass_user;
    private Button daftar;
    private FirebaseAuth auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference dataakunRef = db.collection("data_akun");
    private DocumentReference dataRef = db.document("data_akun/ex");
    String namas, emails, passs, nohps;
    String akses[] = {"penjual, pembeli"};
    private Spinner spin;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        getSupportActionBar().setTitle("Daftar");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama_user = findViewById(R.id.etNamaDaftar);
        email_user = findViewById(R.id.etEmailDaftar);
        pass_user = findViewById(R.id.etPassDaftar);
        nohp_user = findViewById(R.id.etNomorDaftar);
        daftar = findViewById(R.id.buttonDaftar);
        auth = FirebaseAuth.getInstance();

        // String[] akses = getResources().getStringArray(R.array.akses_daftar);

        // ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(DaftarActivity.this, R.id.spinDaftar, akses);

        /* ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter
                .createFromResource(this, R.array.akses_daftar, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinAdapter); */

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    final String email = email_user.getText().toString().trim();
                    final String password = pass_user.getText().toString().trim();
                    final String nama = nama_user.getText().toString();
                    final String nohp = nohp_user.getText().toString();

                   /* if (user_nama.isEmpty() || user_email.isEmpty() || user_pass.isEmpty() || user_nohp.isEmpty()) {
                        Toast.makeText(DaftarActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                    } else { */
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (passs.length() < 8) {
                                Toast.makeText(DaftarActivity.this, "password error", Toast.LENGTH_SHORT).show();
                            } else {
                                if (task.isSuccessful()) {
                                    sendUserData();
                                    //  SaveDaftar.read(getApplicationContext(), "session", "true");
                                    Toast.makeText(DaftarActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                                    auth.signOut();
                                    finish();
                                    startActivity(new Intent(DaftarActivity.this, MasukActivity.class));
                                } else {
                                    Toast.makeText(DaftarActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    });
                }
            }
        });
    }

    private Boolean validate() {
        Boolean result = false;

        namas = nama_user.getText().toString();
        passs = pass_user.getText().toString();
        emails = email_user.getText().toString();
        nohps = nohp_user.getText().toString();

        if (namas.isEmpty() || passs.isEmpty() || emails.isEmpty() || nohps.isEmpty() || passs.length() < 8) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }


    private void sendUserData() {
        String nama = nama_user.getText().toString();
        String email = email_user.getText().toString();
        String nohp = nohp_user.getText().toString();
        String password = pass_user.getText().toString();

        DataAkun datas = new DataAkun(nama, email, nohp, password);
        dataakunRef.add(datas);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getSelectedUser(View v) {
        DataAkun dataAS = (DataAkun) spin.getSelectedItem();
    }

}
