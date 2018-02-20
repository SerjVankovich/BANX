package com.example.sergey.myapplication.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.GlobalFunctions;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.adapters.MyScrollListener;
import com.example.sergey.myapplication.adapters.ResAdapter;
import com.example.sergey.myapplication.comparaters.PercentVkladsCompatrator;
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
 * Created by sergey on 11.01.2018.
 */

public class FragmentVklads extends Fragment {
    ShimmerRecyclerView recyclerView;
    Context context;
    ResAdapter adapter;
    List<DBCard> main_array;
    Toolbar toolbar;
    PercentVkladsCompatrator compatrator;
    int beginSlice = 0;
    int endSlice = 15;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vklads, container, false);

        toolbar = container.findViewById(R.id.toolbar);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getMain_array();

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
    public void getMain_array (){
        main_array = new ArrayList<>();
        final DatabaseReference myBase = FirebaseDatabase.getInstance().getReference();




        myBase.child("all_vklads").child("vklads").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GlobalFunctions.getData(dataSnapshot, recyclerView, main_array);
                updateUI(dataSnapshot);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MyLog", "Failed to read value.", error.toException());
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateUI(DataSnapshot snapshot){
        compatrator = new PercentVkladsCompatrator();
        Collections.sort(main_array, compatrator);
        List<DBCard> showArray = new ArrayList<>();

        adapter = new ResAdapter(getContext(), showArray);
        GlobalFunctions.loadMore(main_array, showArray, adapter, 0, 15);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new MyScrollListener(recyclerView, main_array, adapter, showArray));
        recyclerView.hideShimmerAdapter();
    }

}