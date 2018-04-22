package com.example.sergey.myapplication.fragments;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;

import java.util.List;

/**
 * Created by sergey on 29.03.2018.
 */

public interface Getter {
    public abstract List<DBCard> getMainArray();
    public abstract ShimmerRecyclerView getRecyclerView();
}
