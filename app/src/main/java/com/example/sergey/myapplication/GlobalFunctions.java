package com.example.sergey.myapplication;

import android.util.Log;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.adapters.ResAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sergey on 16.02.2018.
 */

public class GlobalFunctions {
    public static void getData(DataSnapshot dataSnapshot, ShimmerRecyclerView recyclerView,
                               List<DBCard> main_array){
        recyclerView.showShimmerAdapter();
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        GenericTypeIndicator<List<Object>> t = new GenericTypeIndicator<List<Object>>(){};
        List<Object> value = dataSnapshot.getValue(t);

        List<String> titles = new ArrayList<>();
        List<String> sroks = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();
        List<Double> percents = new ArrayList<>();
        List<String> banks = new ArrayList<>();

        assert value != null;
        for (Object obj:
                value) {
            boolean titleHas = false;
            boolean sumHas = false;
            boolean srokHas = false;
            boolean perHas = false;
            boolean bankHas = false;

            String title = "";
            int sum = 0;
            String srok = "";
            double percent = 0;
            String bank = "";

            HashMap<String, String > hashMap = (HashMap<String, String>) obj;
            for (Map.Entry<String, String> entry:
                    hashMap.entrySet()) {

                String key = entry.getKey();


                switch (key){
                    case "title":
                        title = entry.getValue();
                        Log.d("MyLog", title);
                        titleHas = true;
                        break;
                    case "suminrub":
                        sum = (int)(double)Double.parseDouble(entry.getValue());
                        Log.d("MLOG", "" + sum);
                        sumHas = true;
                        break;
                    case "srokinrub":
                        srok = entry.getValue();
                        srokHas = true;
                        break;
                    case "perinrub":
                        percent = Double.parseDouble(entry.getValue());
                        perHas = true;
                        break;
                    case "bank":
                        bank = entry.getValue();
                        bankHas = true;
                        break;

                }




            }
            if(titleHas && sumHas && srokHas && perHas && bankHas) {
                titles.add(title);
                sroks.add(srok);
                sums.add(sum);
                percents.add(percent);
                banks.add(bank);
            }
        }
        for (int i = 0; i < titles.size(); i++) {
            try{
                DBCard card = new DBCard(titles.get(i), percents.get(i), sums.get(i), sroks.get(i), banks.get(i));
                main_array.add(card);
            } catch (IndexOutOfBoundsException e) {
                DBCard card = new DBCard(titles.get(i), 0, 0, null, null);
            }

        }
    }
    public static void loadMore(List<DBCard> main_array, List<DBCard> showArray, ResAdapter adapter, int beginSlice, int endSlice){

        for (int i = beginSlice; i < endSlice; i++) {
            showArray.add(main_array.get(i));
            adapter.notifyItemInserted(i);

        }
        adapter.notifyDataSetChanged();


    }
}
