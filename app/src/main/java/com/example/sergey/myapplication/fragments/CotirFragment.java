package com.example.sergey.myapplication.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.Cotirovka;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.adapters.ResCotAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Objects;

/**
 * Created by sergey on 28.03.2018.
 */

public class CotirFragment extends Fragment {
    List<Cotirovka> main_array;
    ShimmerRecyclerView recyclerView;
    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cotir_layout, container, false);
        recyclerView = view.findViewById(R.id.cotirs);
        recyclerView.showShimmerAdapter();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("all_cotir").child("cotirovki");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Cotirovka>> t = new GenericTypeIndicator<List<Cotirovka>>(){};
                main_array= dataSnapshot.getValue(t);
                for (int i = 0; i < main_array.size(); i++) {
                    if (Objects.equals(main_array.get(i).getCharCode(), "USD")) {
                        Cotirovka promcard = main_array.remove(i);
                        main_array.add(0, promcard);
                    }
                    if (Objects.equals(main_array.get(i).getCharCode(), "EUR")){
                        Cotirovka promcard = main_array.remove(i);
                        main_array.add(1, promcard);
                    }
                }
                recyclerView.setAdapter(new ResCotAdapter(main_array));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
