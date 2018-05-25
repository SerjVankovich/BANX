package com.example.sergey.myapplication;

import android.util.Log;
import android.widget.ImageView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.adapters.ResAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sergey on 16.02.2018.
 */

public class GlobalFunctions {
    public static void getData(List<DBCard> main_array, DataSnapshot dataSnapshot, ShimmerRecyclerView recyclerView
                               ){

        recyclerView.showShimmerAdapter();
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        GenericTypeIndicator<List<Object>> t = new GenericTypeIndicator<List<Object>>(){};
        List<Object> value = dataSnapshot.getValue(t);

        List<String> titles = new ArrayList<>();
        List<Integer> sroks = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();
        List<Double> percents = new ArrayList<>();
        List<String> banks = new ArrayList<>();
        List<String> links = new ArrayList<>();

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
            int srok = 0;
            double percent = 0;
            String bank = "";
            String link = "";

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
                        srok = (int)(double)Double.parseDouble(entry.getValue());
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
                    case "link":
                        link = entry.getValue();


                }




            }
            if(titleHas && sumHas && srokHas && perHas && bankHas) {
                titles.add(title);
                sroks.add(srok);
                sums.add(sum);
                percents.add(percent);
                banks.add(bank);
                links.add(link);
            }
        }
        for (int i = 0; i < titles.size(); i++) {
            try{
                DBCard card = new DBCard(titles.get(i), percents.get(i), sums.get(i), sroks.get(i), banks.get(i), links.get(i));
                main_array.add(card);
            } catch (IndexOutOfBoundsException e) {
                DBCard card = new DBCard(titles.get(i), 0, 0, 0, null, null);
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
    public static void findImage(ImageView imageView, int position, List<DBCard> main_array, int width, int height){
        String bank = main_array.get(position).bank;
        switch (bank){
            case "alfabank":
                Picasso.get().load(R.drawable.alfabank).resize(width, height).centerCrop().centerCrop().into(imageView);
                break;
            case "atb":
                Picasso.get().load(R.drawable.atb).resize(width, height).centerCrop().into(imageView);
                break;
            case "baikalinvestbank":
                Picasso.get().load(R.drawable.baikalinvestbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "bbrbank":
                Picasso.get().load(R.drawable.bbrbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "binbank":
                Picasso.get().load(R.drawable.binbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "dalnevostochny":
                Picasso.get().load(R.drawable.dalnevostochny).resize(width, height).centerCrop().into(imageView);
                break;
            case "gazprombank":
                Picasso.get().load(R.drawable.gazprombank).resize(width, height).centerCrop().into(imageView);
                break;
            case "homecreditbank":
                Picasso.get().load(R.drawable.homecreditbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "mosoblbank":
                Picasso.get().load(R.drawable.mosoblbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "mts-bank":
                Picasso.get().load(R.drawable.mts_bank).resize(width, height).centerCrop().into(imageView);
                break;
            case "otkritie":
                Picasso.get().load(R.drawable.otkritie).resize(width, height).centerCrop().into(imageView);
                break;
            case "pochtabank":
                Picasso.get().load(R.drawable.pochtabank).resize(width, height).centerCrop().into(imageView);
                break;
            case "primorye":
                Picasso.get().load(R.drawable.primorye).resize(width, height).centerCrop().into(imageView);
                break;
            case "primsotsbank":
                Picasso.get().load(R.drawable.primsotsbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "promsvyazbank":
                Picasso.get().load(R.drawable.promsvyazbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "ptkb":
                Picasso.get().load(R.drawable.ptkb).resize(width, height).centerCrop().into(imageView);
                break;
            case "rgsbank":
                Picasso.get().load(R.drawable.rgsbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "rosbank":
                Picasso.get().load(R.drawable.rosbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "roscap":
                Picasso.get().load(R.drawable.roscap).resize(width, height).centerCrop().into(imageView);
                break;
            case "rsb":
                Picasso.get().load(R.drawable.rsb).resize(width, height).centerCrop().into(imageView);
                break;
            case "rshb":
                Picasso.get().load(R.drawable.rshb).resize(width, height).centerCrop().into(imageView);
                break;
            case "rusfinancebank":
                Picasso.get().load(R.drawable.rusfinancebank).resize(width, height).centerCrop().into(imageView);
                break;
            case "sberbank":
                Picasso.get().load(R.drawable.sberbank).resize(width, height).centerCrop().into(imageView);
                break;
            case "skb-bank":
                Picasso.get().load(R.drawable.skb_bank).resize(width, height).centerCrop().into(imageView);
                break;
            case "sovcombank":
                Picasso.get().load(R.drawable.sovcombank).resize(width, height).centerCrop().into(imageView);
                break;
            case "sviaz-bank":
                Picasso.get().load(R.drawable.sviaz_bank).resize(width, height).centerCrop().into(imageView);
                break;
            case "tinkoff":
                Picasso.get().load(R.drawable.tinkoff).resize(width, height).centerCrop().into(imageView);
                break;
            case "ussury":
                Picasso.get().load(R.drawable.ussury).resize(width, height).centerCrop().into(imageView);
                break;
            case "v-express-bank":
                Picasso.get().load(R.drawable.v_express_bank).resize(width, height).centerCrop().into(imageView);
                break;
            case "vtb":
                Picasso.get().load(R.drawable.vtb).resize(width, height).centerCrop().into(imageView);
                break;
        }
    }
    public static String formatToComfort(String number) {
        List<String> numList = new ArrayList<>();
        StringBuffer buffer = new StringBuffer(number);
        buffer.reverse();
        String afterRev = buffer.toString();
        String newStr = "";
        for (int i = 0; i < afterRev.length(); i++) {
            if (i % 3 == 0) {
                numList.add(" ");
                numList.add(String.valueOf(afterRev.charAt(i)));


            } else {
                numList.add(String.valueOf(afterRev.charAt(i)));
            }
        }
        Collections.reverse(numList);
        for (int i = 0; i < numList.size(); i++) {
            newStr += numList.get(i);
        }

        return newStr;
    }
}
