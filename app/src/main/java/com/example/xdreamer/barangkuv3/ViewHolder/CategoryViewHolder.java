package com.example.xdreamer.barangkuv3.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xdreamer.barangkuv3.Interface.ItemClickListener;
import com.example.xdreamer.barangkuv3.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nameCat;
    public ImageView imageCat;

    private ItemClickListener itemClickListener;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        nameCat = itemView.findViewById(R.id.nameCategory);
        imageCat = itemView.findViewById(R.id.imageCategory);

        imageCat.bringToFront();

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
