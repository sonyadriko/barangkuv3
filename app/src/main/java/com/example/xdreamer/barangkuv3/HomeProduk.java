package com.example.xdreamer.barangkuv3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.FirebaseStorage;

public class HomeProduk extends AppCompatActivity {

    private ImageView imageView;
    private TextView harga, judul;
    private FirebaseStorage firebaseStorage;
    private DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_produk);

        harga = findViewById(R.id.hargaHomePro);
        judul = findViewById(R.id.namaHomePro);
    }
}
