package com.example.sergey.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("all_banks").child("banks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recyclerView.showShimmerAdapter();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<List<Object>> t = new GenericTypeIndicator<List<Object>>(){};
                List<Object> value = dataSnapshot.getValue(t);
                List<String> titles = new ArrayList<>();
                List<Integer> vkladsNum = new ArrayList<>();
                List<Integer> creditsNum = new ArrayList<>();

                assert value != null;
                for (Object obj:
                        value) {
                    boolean bankHas = false;
                    boolean vkadsHas = false;
                    boolean creditsHas = false;
                    String bank = "";
                    int vklads = 0;
                    int credits = 0;
                    Log.d("Hi", "Hello from method");

                    HashMap<String, String > hashMap = (HashMap<String, String>) obj;
                    for (Map.Entry<String, String> entry:
                            hashMap.entrySet()) {
                        String key = entry.getKey();
                        Log.d("Hello", "Hello from cycle");
                        switch (key){
                            case "bank":
                                bank = entry.getValue();
                                Log.d("MLOG", entry.getValue());
                                bankHas = true;
                                break;
                            case "len_cred":
                                vklads = Integer.parseInt(entry.getValue());
                                Log.d("MLOG", entry.getValue());
                                vkadsHas = true;
                                break;
                            case "len_vklads":
                                credits = Integer.parseInt(entry.getValue());
                                Log.d("MLOG", entry.getValue());
                                creditsHas = true;
                                break;

                        }

                    }
                    if (bankHas && vkadsHas && creditsHas){
                        titles.add(bank);
                        vkladsNum.add(vklads);
                        creditsNum.add(credits);
                    }


                }
                for (int i = 0; i < titles.size(); i++) {
                    BankCard card = new BankCard(titles.get(i), creditsNum.get(i), vkladsNum.get(i));
                    main_array.add(card);
                }
                Log.d("MyArray", "" + main_array.size());
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateUI() {
        ResBankAdapter adapter = new ResBankAdapter(main_array);
        recyclerView.setAdapter(adapter);
        recyclerView.hideShimmerAdapter();
    }
}
