package com.example.xdreamer.barangkuv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xdreamer.barangkuv3.model.DataBarang;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailProduk extends AppCompatActivity {

    private ImageView imageView;
    private TextView namaView, stokView, hargaView, deskView;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference dbRef;
    private CollectionReference collref;
    private DataBarang model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        namaView = findViewById(R.id.namaDetailProduk);
        stokView = findViewById(R.id.stokDetailProduk);
        hargaView = findViewById(R.id.hargaDetailProduk);
        deskView = findViewById(R.id.deskDetailroduk);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collref = firebaseFirestore.collection("data_barang");


        /*collref.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";

                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
                            DataBarang dataBarang = queryDocumentSnapshot.toObject(DataBarang.class);

                            String nama = dataBarang.getNamabarang();
                            String desk = dataBarang.getDeskbarang();
                            int harga = dataBarang.getHargabarang();
                            Picasso.get()
                                    .load(dataBarang.getImagebarang()).fit().into(imageView);
                        }

                    }
                });
                */

       /* private void setSlider(ArrayList<String> ){
            msliderLayout = findViewById(R.id.slider);

            for (int i = 0; i < model.getImagebarang() ; i++){
                TextSliderView sliderView = new TextSliderView(this);

                sliderView.image()
                        .description()
                        .setRequestOption()
                        .setProgressBarVisible(true)
                        .setOnSliderClickListener(this);

            }
        }
*/


        Intent p = getIntent();
        int gambar = p.getIntExtra("imagebarang", 0);
        String nama = p.getStringExtra("namabarang");
        int harga = p.getIntExtra("hargabarang", 6);
        String desk = p.getStringExtra("deskbarang");


        imageView.setImageResource((gambar));
        namaView.setText(nama);
        hargaView.setText("Rp. " + String.valueOf(harga));
        deskView.setText(desk);


    }
}
