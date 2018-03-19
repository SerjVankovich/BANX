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
        holder.bankName.setText(main_array.get(position).name);
        holder.creditsNum.setText(Integer.toString(main_array.get(position).credits));
        holder.vkladsNum.setText(Integer.toString(main_array.get(position).vklads));
        findImage(holder.icon, main_array, position);

    }

    @Override
    public int getItemCount() {
        return main_array.size();
    }
    public void findImage(ImageView imageView, List<BankCard> list, int position){
        String bank = list.get(position).name;
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
