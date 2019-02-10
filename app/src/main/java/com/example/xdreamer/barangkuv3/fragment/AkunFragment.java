package com.example.xdreamer.barangkuv3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xdreamer.barangkuv3.DaftarActivity;
import com.example.xdreamer.barangkuv3.MasukActivity;
import com.example.xdreamer.barangkuv3.R;
import com.example.xdreamer.barangkuv3.SaveDaftar;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment implements View.OnClickListener {

    Boolean session;

    View v;
    Button masuk, daftar, logout;
    static int REQUEST_CODE = 100;

    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_akun, container, false);
        masuk = v.findViewById(R.id.butBefMasuk);
        daftar = v.findViewById(R.id.butBefDaftar);
       // logout = v.findViewById(R.id.buttonlogout);

        logout.setOnClickListener(this);
        masuk.setOnClickListener(this);
        daftar.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.butBefDaftar) {
            startActivity(new Intent(getActivity(), DaftarActivity.class));
        }
        if (v.getId() == R.id.butBefMasuk) {
            startActivity(new Intent(getActivity(), MasukActivity.class));
        }
       // if (v.getId() == R.id.buttonlogout) {
        //    FirebaseAuth.getInstance().signOut();
         //   SaveDaftar.read(getActivity(), "session", "false");
       // }

    }
}
