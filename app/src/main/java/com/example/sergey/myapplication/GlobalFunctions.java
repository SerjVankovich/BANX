package com.example.sergey.myapplication;

import android.util.Log;
import android.widget.ImageView;

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
    public static void findImage(ImageView imageView, int position, List<DBCard> main_array){
        String bank = main_array.get(position).bank;
        switch (bank){
            case "alfabank":
                imageView.setImageResource(R.drawable.alfabank);
                break;
            case "atb":
                imageView.setImageResource(R.drawable.atb);
                break;
            case "baikalinvestbank":
                imageView.setImageResource(R.drawable.baikalinvestbank);
                break;
            case "bbrbank":
                imageView.setImageResource(R.drawable.bbrbank);
                break;
            case "binbank":
                imageView.setImageResource(R.drawable.binbank);
                break;
            case "dalnevostochny":
                imageView.setImageResource(R.drawable.dalnevostochny);
                break;
            case "gazprombank":
                imageView.setImageResource(R.drawable.gazprombank);
                break;
            case "homecreditbank":
                imageView.setImageResource(R.drawable.homecreditbank);
                break;
            case "mosoblbank":
                imageView.setImageResource(R.drawable.mosoblbank);
                break;
            case "mts-bank":
                imageView.setImageResource(R.drawable.mts_bank);
                break;
            case "otkritie":
                imageView.setImageResource(R.drawable.otkritie);
                break;
            case "pochtabank":
                imageView.setImageResource(R.drawable.pochtabank);
                break;
            case "primorye":
                imageView.setImageResource(R.drawable.primorye);
                break;
            case "primsotsbank":
                imageView.setImageResource(R.drawable.primsotsbank);
                break;
            case "promsvyazbank":
                imageView.setImageResource(R.drawable.promsvyazbank);
                break;
            case "ptkb":
                imageView.setImageResource(R.drawable.ptkb);
                break;
            case "rgsbank":
                imageView.setImageResource(R.drawable.rgsbank);
                break;
            case "rosbank":
                imageView.setImageResource(R.drawable.rosbank);
                break;
            case "roscap":
                imageView.setImageResource(R.drawable.roscap);
                break;
            case "rsb":
                imageView.setImageResource(R.drawable.rsb);
                break;
            case "rshb":
                imageView.setImageResource(R.drawable.rshb);
                break;
            case "rusfinancebank":
                imageView.setImageResource(R.drawable.rusfinancebank);
                break;
            case "sberbank":
                imageView.setImageResource(R.drawable.sberbank);
                break;
            case "skb-bank":
                imageView.setImageResource(R.drawable.skb_bank);
                break;
            case "sovcombank":
                imageView.setImageResource(R.drawable.sovcombank);
                break;
            case "sviaz-bank":
                imageView.setImageResource(R.drawable.sviaz_bank);
                break;
            case "tinkoff":
                imageView.setImageResource(R.drawable.tinkoff);
                break;
            case "ussury":
                imageView.setImageResource(R.drawable.ussury);
                break;
            case "v-express-bank":
                imageView.setImageResource(R.drawable.v_express_bank);
                break;
            case "vtb":
                imageView.setImageResource(R.drawable.vtb);
                break;
        }
    }
}
