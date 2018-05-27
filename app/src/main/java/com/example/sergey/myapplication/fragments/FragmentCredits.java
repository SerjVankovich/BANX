package com.example.sergey.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.DataBase.DataBaseHelper;
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
import java.util.List;

/**
 * Created by sergey on 10.02.2018.
 */

public class FragmentCredits extends Fragment implements Getter {
    private ShimmerRecyclerView recyclerView;
    private ResAdapter adapter;
    private List<DBCard> main_array;
    private PercentsCreditsComparator comparator;
    private DatabaseReference ref;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credits, container, false);
        main_array = new ArrayList<>();

        recyclerView = view.findViewById(R.id.this_res_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("all_kredits").child("kredits").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<DBCard>> t = new GenericTypeIndicator<List<DBCard>>(){};
                main_array = dataSnapshot.getValue(t);
      //          new LoadingThread(main_array, new PercentVkladsCompatrator(), comparator, "kredits", getContext()).execute();
                recyclerView.setAdapter(new ResAdapter(getContext(), main_array, DataBaseHelper.TABLE_CREDITS, "credits"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


 //       new LoadingThread(main_array, recyclerView,new PercentVkladsCompatrator(), comparator, adapter, getContext(), "all_kredits", "kredits", "kredits").execute();

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

    @Override
    public List<DBCard> getMainArray() {
        return main_array;
    }

    @Override
    public ShimmerRecyclerView getRecyclerView() {
        return recyclerView;
    }
}
