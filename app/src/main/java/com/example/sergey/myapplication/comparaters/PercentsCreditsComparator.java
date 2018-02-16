package com.example.sergey.myapplication.comparaters;

import com.example.sergey.myapplication.DataBase.DBCard;

import java.util.Comparator;

/**
 * Created by sergey on 10.02.2018.
 */

public class PercentsCreditsComparator implements Comparator<DBCard>{
    @Override
    public int compare(DBCard o1, DBCard o2) {
        double per1 = o1.percents;
        double per2 = o2.percents;

        return Double.compare(per1, per2);
    }
}
