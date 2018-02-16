package com.example.sergey.myapplication.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.GlobalFunctions;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.adapters.ResAdapter;
import com.example.sergey.myapplication.comparaters.PercentsCreditsComparator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sergey on 10.02.2018.
 */

public class FragmentCredits extends Fragment {
    ShimmerRecyclerView recyclerView;
    ResAdapter adapter;
    List<DBCard> main_array;
    PercentsCreditsComparator comparator;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credits, container, false);

        recyclerView = view.findViewById(R.id.this_res_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getMainArray();

        return view;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter != null) {
            adapter.saveStates(outState);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (adapter!= null) {
            adapter.restoreStates(savedInstanceState);
        }
    }
    public void getMainArray(){
        main_array = new ArrayList<>();
        DatabaseReference myBase = FirebaseDatabase.getInstance().getReference();



        myBase.child("all_kredits").child("kredits").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GlobalFunctions.getData(dataSnapshot, recyclerView, main_array);
                updateUI();


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MyLog", "Failed to read value.", error.toException());
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateUI(){
        comparator = new PercentsCreditsComparator();
        Collections.sort(main_array, comparator);
        adapter = new ResAdapter(getContext(), main_array);
        recyclerView.setAdapter(adapter);
        recyclerView.hideShimmerAdapter();
    }
}
