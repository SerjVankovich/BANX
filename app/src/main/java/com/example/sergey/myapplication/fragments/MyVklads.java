package com.example.sergey.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergey.myapplication.DataBase.DataBaseHelper;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.adapters.ResMyAdapter;

/**
 * Created by sergey on 16.03.2018.
 */

public class MyVklads extends Fragment {
    RecyclerView recyclerView;
    ResMyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_credits, container, false);

        recyclerView = view.findViewById(R.id.my_kredit);
        adapter = new ResMyAdapter(getContext(), DataBaseHelper.TABLE_VKLADS);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        if (adapter.getItemCount() != 0){
            return view;
        } else {
            return inflater.inflate(R.layout.empty_fragment_vklads, container, false);
        }


    }
}
