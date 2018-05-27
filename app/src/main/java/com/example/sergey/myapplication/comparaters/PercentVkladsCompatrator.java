package com.example.sergey.myapplication.comparaters;

import com.example.sergey.myapplication.DataBase.DBCard;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Created by sergey on 07.02.2018.
 */

public class PercentVkladsCompatrator implements Comparator<DBCard> {
    @Override
    public int compare(DBCard o1, DBCard o2) {
        double per1 = o1.getPerinrub();
        double per2 = o2.getPerinrub();

        return Double.compare(per2, per1);

    }
}
