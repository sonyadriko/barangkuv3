package com.example.xdreamer.barangkuv3.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xdreamer.barangkuv3.DetailProduk;
import com.example.xdreamer.barangkuv3.Interface.ItemClickListener;
import com.example.xdreamer.barangkuv3.R;
import com.example.xdreamer.barangkuv3.ViewHolder.ProdukViewHolder;
import com.example.xdreamer.barangkuv3.model.DataBarang;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View v;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("data_barang");
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirestoreRecyclerAdapter<DataBarang, ProdukViewHolder> adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.produkFragHome);
        layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        setUpRecycler();
        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = (MenuInflater) menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();

    }

    private void setUpRecycler() {
        /* collectionReference.whereGreaterThanOrEqualTo("hargabarang", "1")
                .orderBy("hargabarang", Query.Direction.ASCENDING)
                .limit(3)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            DataBarang dataBarang = documentSnapshot.toObject(DataBarang.class);
                            dataBarang.setNamabarang(documentSnapshot.getId());

                            String nama = dataBarang.getNamabarang();
                            int harga = dataBarang.getHargabarang();


                        }

                    }
                }); */
        Query query = db.collection("data_barang")
                .orderBy("hargabarang", Query.Direction.ASCENDING).limit(5);
        FirestoreRecyclerOptions<DataBarang> options = new FirestoreRecyclerOptions.Builder<DataBarang>()
                .setQuery(query, DataBarang.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<DataBarang, ProdukViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ProdukViewHolder holder, int position, @NonNull final DataBarang model) {
                Picasso.get().load(model.getImagebarang()).fit()
                        .error(R.drawable.load)
                        .placeholder(R.drawable.load)
                        .into(holder.imagebarang);
                holder.namabarang.setText(model.getNamabarang());
                holder.hargabarang.setText("Rp. " + String.valueOf(model.getHargabarang()));
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent barangs = new Intent(getActivity(), DetailProduk.class);
                        barangs.putExtra("namabarang", model.getNamabarang());
                        barangs.putExtra("imagebarang", model.getImagebarang());
                        barangs.putExtra("deskbarang", model.getDeskbarang());
                        barangs.putExtra("hargabarang", model.getHargabarang());
                        startActivity(barangs);
                    }

                });

            }


            @NonNull
            @Override
            public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_item, parent, false);
                return new ProdukViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
