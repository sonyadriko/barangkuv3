package com.example.xdreamer.barangkuv3.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xdreamer.barangkuv3.Interface.ItemClickListener;
import com.example.xdreamer.barangkuv3.R;
import com.google.firebase.firestore.DocumentSnapshot;

public class ProdukViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView namabarang, hargabarang;
    public ImageView imagebarang;
    private ItemClickListener itemClickListener;
    private AdapterView.OnItemClickListener listener;

    public ProdukViewHolder(@NonNull final View itemView) {
        super(itemView);

        namabarang = itemView.findViewById(R.id.judulView);
        hargabarang = itemView.findViewById(R.id.hargaView);
        imagebarang = itemView.findViewById(R.id.imageView);

        itemView.setOnClickListener(this);
        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();

            }
        }); */

    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
