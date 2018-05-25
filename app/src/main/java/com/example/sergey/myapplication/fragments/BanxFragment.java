package com.example.sergey.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.BankCard;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.adapters.ResBankAdapter;
import com.example.sergey.myapplication.adapters.ResMyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sergey on 25.02.2018.
 */

public class BanxFragment extends Fragment {
    ShimmerRecyclerView recyclerView;
    List<BankCard> main_array;
    DatabaseReference reference;

    public void setTransaction(FragmentTransaction transaction) {
        this.transaction = transaction;
    }

    FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_credits, container, false);
        recyclerView = view.findViewById(R.id.my_kredit);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getMainArray();
        return view;
    }
    public void getMainArray(){
        main_array = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("all_banks").child("banks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recyclerView.showShimmerAdapter();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<List<BankCard>> t = new GenericTypeIndicator<List<BankCard>>(){};
                main_array = dataSnapshot.getValue(t);
                updateUI();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateUI() {
        ResBankAdapter adapter = new ResBankAdapter(main_array, transaction, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.hideShimmerAdapter();
    }
}
