package com.example.sergey.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergey.myapplication.DataBase.BankCard;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.R;
import com.example.sergey.myapplication.fragments.MyCredits;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by sergey on 27.03.2018.
 */

public class ResDialogAdapter extends RecyclerView.Adapter<ResDialogAdapter.MyHolder> {
    String[] main_array;
    List<CheckBox> checkBoxes;

    public List<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public ResDialogAdapter(String[] main_array) {
        checkBoxes = new ArrayList<>();
        this.main_array = main_array;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_item, parent, false);
        MyHolder holder = new MyHolder(view);
        checkBoxes.add(holder.checkBox);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        findImage(holder.checkBox, holder.imageView,  position, main_array, 60, 60);

    }

    @Override
    public int getItemCount() {
        return main_array.length;
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
    public void findImage(CheckBox bankName,ImageView imageView, int position, String[] list, int width, int height){
        String bank = list[position];
        switch (bank){
            case "alfabank":
                Picasso.get().load(R.drawable.alfabank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Альфабанк");
                break;
            case "atb":
                Picasso.get().load(R.drawable.atb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("АТБ");
                break;
            case "baikalinvestbank":
                Picasso.get().load(R.drawable.baikalinvestbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Байкал Инвест Банк");
                break;
            case "bbrbank":
                Picasso.get().load(R.drawable.bbrbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("ББР Банк");
                break;
            case "binbank":
                Picasso.get().load(R.drawable.binbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Бинбанк");
                break;
            case "dalnevostochny":
                Picasso.get().load(R.drawable.dalnevostochny).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Дальневосточный");
                break;
            case "gazprombank":
                Picasso.get().load(R.drawable.gazprombank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Газпромбанк");
                break;
            case "homecreditbank":
                Picasso.get().load(R.drawable.homecreditbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Хоум Кредит");
                break;
            case "mosoblbank":
                Picasso.get().load(R.drawable.mosoblbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Мособлбанк");
                break;
            case "mts-bank":
                Picasso.get().load(R.drawable.mts_bank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("МТС-Банк");
                break;
            case "otkritie":
                Picasso.get().load(R.drawable.otkritie).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("ФК Открытие");
                break;
            case "pochtabank":
                Picasso.get().load(R.drawable.pochtabank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Почтабанк");
                break;
            case "primorye":
                Picasso.get().load(R.drawable.primorye).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Приморье");
                break;
            case "primsotsbank":
                Picasso.get().load(R.drawable.primsotsbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Примсоцбанк");
                break;
            case "promsvyazbank":
                Picasso.get().load(R.drawable.promsvyazbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Промсвязьбанк");
                break;
            case "ptkb":
                Picasso.get().load(R.drawable.ptkb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Примтеркомбанк");
                break;
            case "rgsbank":
                Picasso.get().load(R.drawable.rgsbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Росгосстрах Банк");
                break;
            case "rosbank":
                Picasso.get().load(R.drawable.rosbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Росбанк");
                break;
            case "roscap":
                Picasso.get().load(R.drawable.roscap).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Россиийский Капитал");
                break;
            case "rsb":
                Picasso.get().load(R.drawable.rsb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Русский Стандарт");
                break;
            case "rshb":
                Picasso.get().load(R.drawable.rshb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Россельхозбанк");
                break;
            case "rusfinancebank":
                Picasso.get().load(R.drawable.rusfinancebank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Русфинанс Банк");
                break;
            case "sberbank":
                Picasso.get().load(R.drawable.sberbank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Сбербанк");
                break;
            case "skb-bank":
                Picasso.get().load(R.drawable.skb_bank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("СКБ-Банк");
                break;
            case "sovcombank":
                Picasso.get().load(R.drawable.sovcombank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Совкомбанк");
                break;
            case "sviaz-bank":
                Picasso.get().load(R.drawable.sviaz_bank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Связьбанк");
                break;
            case "tinkoff":
                Picasso.get().load(R.drawable.tinkoff).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Тинькофф");
                break;
            case "ussury":
                Picasso.get().load(R.drawable.ussury).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк Уссури");
                break;
            case "v-express-bank":
                Picasso.get().load(R.drawable.v_express_bank).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Восточный экспресс банк");
                break;
            case "vtb":
                Picasso.get().load(R.drawable.vtb).resize(width, height).centerCrop().centerCrop().into(imageView);
                bankName.setText("Банк ВТБ");
                break;
        }
    }
}
