package com.example.sergey.myapplication.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.BankCard;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.DataBase.DataBaseHelper;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.adapters.ResAdapter;
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
 * Created by sergey on 16.02.2018.
 */

public class MyCredits extends Fragment {
    ShimmerRecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_credits, container, false);

        recyclerView = view.findViewById(R.id.my_kredit);
        ResMyAdapter adapter = new ResMyAdapter(getContext(), DataBaseHelper.TABLE_CREDITS);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (adapter.getItemCount() == 0) {
            return inflater.inflate(R.layout.empty_fragment_credits, container, false);
        }

        return view;
    }




    // public List<DBCard> getMy_cerdits(){
//     DataBaseHelper helper = new DataBaseHelper(getContext());
//     SQLiteDatabase db = helper.getReadableDatabase();
//     Cursor cursor = db.query()
// }
}
