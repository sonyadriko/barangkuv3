package com.example.xdreamer.barangkuv3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.xdreamer.barangkuv3.DetailProduk;
import com.example.xdreamer.barangkuv3.Interface.ItemClickListener;
import com.example.xdreamer.barangkuv3.R;
import com.example.xdreamer.barangkuv3.ViewHolder.CategoryViewHolder;
import com.example.xdreamer.barangkuv3.ViewHolder.ProdukViewHolder;
import com.example.xdreamer.barangkuv3.model.Category;
import com.example.xdreamer.barangkuv3.model.DataBarang;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment {

    View v;
    RecyclerView listCat;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
    FirestoreRecyclerAdapter<Category, CategoryViewHolder> adapter2;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseDatabase database;
    DatabaseReference categories;


    public InboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_inbox, container, false);
        listCat = v.findViewById(R.id.listCategory);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCat.setLayoutManager(layoutManager);

        loadCatFirestore();
        return v;
    }

    private void loadCatFirestore() {
        Query query = firebaseFirestore.collection("category")
                .orderBy("nama", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Category> options = new FirestoreRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class)
                .build();

        adapter2 = new FirestoreRecyclerAdapter<Category, CategoryViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull final Category model) {
                Picasso.get().load(model.getImage()).fit().into(holder.imageCat);
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(), model.getNama(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
            @NonNull
            @Override

            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category, viewGroup, false);
                return new CategoryViewHolder(view);
            }


        };
        listCat.setAdapter(adapter2);
    }


  /*  private void loadCat() {
        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.layout_category,
                CategoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, final Category model, int position) {
                viewHolder.nameCat.setText(model.getName());
                Picasso.get().load(model.getImage()).fit()
                        .into(viewHolder.imageCat);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(), model.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
               viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent startgame = new Intent(getActivity(), StartActivity.class);
                        Common.categoryId = adapter.getRef(position).getKey();
                        startActivity(startgame);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        listCat.setAdapter(adapter);
    }

    */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Category");
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter2 !=null){
            adapter2.stopListening();
        }
    }
}
