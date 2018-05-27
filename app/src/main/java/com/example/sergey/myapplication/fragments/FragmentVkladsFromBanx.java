package com.example.sergey.myapplication.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.DataBase.DataBaseHelper;
import com.example.sergey.myapplication.MainActivity;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.adapters.ResAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentVkladsFromBanx extends Fragment {
    private ShimmerRecyclerView recyclerView;

    private Context context;
    private ResAdapter adapter;
    private List<DBCard> main_array;
    private DatabaseReference ref;
    private String firstChild;
    private String secondChild;

    public void setFirstChild(String firstChild) {
        this.firstChild = firstChild;
    }

    public void setSecondChild(String secondChild) {
        this.secondChild = secondChild;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    String bankName;

    public void setMain_array(List<DBCard> main_array) {
        this.main_array = main_array;
    }
    public ShimmerRecyclerView getRecyclerView() {
        return recyclerView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vklads, container, false);
        main_array = new ArrayList<>();
        context = getContext();

        ref = FirebaseDatabase.getInstance().getReference();
        ref.child(firstChild).child(secondChild).addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<DBCard>> t = new GenericTypeIndicator<List<DBCard>>(){};
                main_array = dataSnapshot.getValue(t);
                setAdapter();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //       LoadingThread thread = new LoadingThread(main_array, recyclerView, compatrator, new PercentsCreditsComparator(), adapter, getContext(), "all_vklads", "vklads", "vklads");
        //      thread.execute();
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setAdapter () {
        List<String> list = new ArrayList<>();
        list.add(bankName);
        if (secondChild.equals("vklads")){
            recyclerView.setAdapter(new ResAdapter(context, MainActivity.filterByBank(main_array, list), DataBaseHelper.TABLE_VKLADS, "vklads"));
        } else {
            recyclerView.setAdapter(new ResAdapter(context, MainActivity.filterByBank(main_array, list), DataBaseHelper.TABLE_VKLADS, "credits"));
        }

    }

}
