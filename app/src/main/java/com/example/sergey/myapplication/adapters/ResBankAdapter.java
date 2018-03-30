package com.example.sergey.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergey.myapplication.DataBase.BankCard;
import com.example.sergey.myapplication.GlobalFunctions;
import com.example.sergey.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sergey on 25.02.2018.
 */

public class ResBankAdapter extends RecyclerView.Adapter<ResBankAdapter.ViewHolder> {
    private List<BankCard> main_array;

    public ResBankAdapter(List<BankCard> main_array) {
        this.main_array = main_array;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.creditsNum.setText(Integer.toString(main_array.get(position).credits));
        holder.vkladsNum.setText(Integer.toString(main_array.get(position).vklads));
        findImage(holder.bankName, holder.icon, main_array, position, 120, 120);

    }

    @Override
    public int getItemCount() {
        return main_array.size();
    }
    public void findImage(TextView bankName, ImageView imageView, List<BankCard> list, int position, int width, int height){
        String bank = list.get(position).name;
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

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView bankName;
        TextView vkladsNum;
        TextView creditsNum;
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            bankName = itemView.findViewById(R.id.BankName);
            vkladsNum = itemView.findViewById(R.id.bank_fragment_vklads);
            creditsNum = itemView.findViewById(R.id.bank_fragment_credits);
            icon = itemView.findViewById(R.id.bankIcon);
        }
    }
}