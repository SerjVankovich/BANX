package com.example.sergey.myapplication.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.List;

/**
 * Created by sergey on 16.02.2018.
 */

public class MyCredits extends Fragment {
    ShimmerRecyclerView recyclerView;
    ResAdapter adapter;
    List<DBCard> my_cerdits;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credits, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new ResAdapter(getContext(), my_cerdits);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
// public List<DBCard> getMy_cerdits(){
//     DataBaseHelper helper = new DataBaseHelper(getContext());
//     SQLiteDatabase db = helper.getReadableDatabase();
//     Cursor cursor = db.query()
// }
}
