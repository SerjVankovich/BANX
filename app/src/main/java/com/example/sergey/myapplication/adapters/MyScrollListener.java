package com.example.sergey.myapplication.adapters;

import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.GlobalFunctions;
import com.google.firebase.database.DataSnapshot;

import java.util.List;

/**
 * Created by sergey on 18.02.2018.
 */

public class MyScrollListener extends ShimmerRecyclerView.OnScrollListener {
    List<DBCard> list;
    List<DBCard> showArray;
    ShimmerRecyclerView recyclerView;
    ResAdapter adapter;
    int beginSlice;
    int endSlice;
    boolean isLoading;

    public MyScrollListener(ShimmerRecyclerView recyclerView, List<DBCard> list, ResAdapter adapter, List<DBCard> showArray) {
        this.list = list;
        this.showArray = showArray;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.beginSlice = 15;
        this.endSlice = 30;
        this.isLoading = true;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int visibleTreashold = 5;
        int totalItemCount = manager.getItemCount();
        int lastVisibleItem = manager.findLastVisibleItemPosition();

        if (isLoading && totalItemCount <= (lastVisibleItem + visibleTreashold)) {
            try {
                GlobalFunctions.loadMore(list, showArray, adapter, beginSlice, endSlice);
            } catch (IndexOutOfBoundsException e) {
                isLoading = false;
            } finally {
                this.beginSlice += 15;
                this.endSlice += 15;
            }

        }

    }

}
