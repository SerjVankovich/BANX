package com.example.sergey.myapplication.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.DataBase.DataBaseHelper;
import com.example.sergey.myapplication.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;



public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ViewHolder> {
    List<DBCard> main_array;
    Context context;
    private final ViewBinderHelper vhelper;

    public ResAdapter(Context context, List<DBCard> main_array) {
        this.context = context;
        this.main_array = main_array;
        vhelper = new ViewBinderHelper();


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SwipeRevealLayout cardView;
        TextView title;
        TextView percents;
        TextView srok;
        TextView sum;
        Button delete;
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Title);
            percents = itemView.findViewById(R.id.percents);
            srok = itemView.findViewById(R.id.srok);
            sum = itemView.findViewById(R.id.sum);
            cardView = itemView.findViewById(R.id.card_view);
            delete = itemView.findViewById(R.id.button_add);
            icon = itemView.findViewById(R.id.imageView);


        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        ViewHolder vh = new ViewHolder(view);
        final int position = vh.getAdapterPosition();
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(main_array.get(position).title);
        holder.percents.setText(Double.toString(main_array.get(position).percents) + "%");
        holder.srok.setText(main_array.get(position).srok);
        holder.sum.setText(Integer.toString(main_array.get(position).sum));
        String bank = main_array.get(position).bank;
        switch (bank){
            case "alfabank":
                holder.icon.setImageResource(R.drawable.alfabank);
                break;
            case "atb":
                holder.icon.setImageResource(R.drawable.atb);
                break;
            case "baikalinvestbank":
                holder.icon.setImageResource(R.drawable.baikalinvestbank);
                break;
            case "bbrbank":
                holder.icon.setImageResource(R.drawable.bbrbank);
                break;
            case "binbank":
                holder.icon.setImageResource(R.drawable.binbank);
                break;
            case "dalnevostochny":
                holder.icon.setImageResource(R.drawable.dalnevostochny);
                break;
            case "gazprombank":
                holder.icon.setImageResource(R.drawable.gazprombank);
                break;
            case "homecreditbank":
                holder.icon.setImageResource(R.drawable.homecreditbank);
                break;
            case "mosoblbank":
                holder.icon.setImageResource(R.drawable.mosoblbank);
                break;
            case "mts-bank":
                holder.icon.setImageResource(R.drawable.mts_bank);
                break;
            case "otkritie":
                holder.icon.setImageResource(R.drawable.otkritie);
                break;
            case "pochtabank":
                holder.icon.setImageResource(R.drawable.pochtabank);
                break;
            case "primorye":
                holder.icon.setImageResource(R.drawable.primorye);
                break;
            case "primsotsbank":
                holder.icon.setImageResource(R.drawable.primsotsbank);
                break;
            case "promsvyazbank":
                holder.icon.setImageResource(R.drawable.promsvyazbank);
                break;
            case "ptkb":
                holder.icon.setImageResource(R.drawable.ptkb);
                break;
            case "rgsbank":
                holder.icon.setImageResource(R.drawable.rgsbank);
                break;
            case "rosbank":
                holder.icon.setImageResource(R.drawable.rosbank);
                break;
            case "roscap":
                holder.icon.setImageResource(R.drawable.roscap);
                break;
            case "rsb":
                holder.icon.setImageResource(R.drawable.rsb);
                break;
            case "rshb":
                holder.icon.setImageResource(R.drawable.rshb);
                break;
            case "rusfinancebank":
                holder.icon.setImageResource(R.drawable.rusfinancebank);
                break;
            case "sberbank":
                holder.icon.setImageResource(R.drawable.sberbank);
                break;
            case "skb-bank":
                holder.icon.setImageResource(R.drawable.skb_bank);
                break;
            case "sovcombank":
                holder.icon.setImageResource(R.drawable.sovcombank);
                break;
            case "sviaz-bank":
                holder.icon.setImageResource(R.drawable.sviaz_bank);
                break;
            case "tinkoff":
                holder.icon.setImageResource(R.drawable.tinkoff);
                break;
            case "ussury":
                holder.icon.setImageResource(R.drawable.ussury);
                break;
            case "v-express-bank":
                holder.icon.setImageResource(R.drawable.v_express_bank);
                break;
            case "vtb":
                holder.icon.setImageResource(R.drawable.vtb);
                break;
        }

        vhelper.bind(holder.cardView, main_array.get(position).toString());
        vhelper.setOpenOnlyOne(true);


    }



    @Override
    public int getItemCount() {
        return main_array.size();
    }

    public void saveStates(Bundle outState){
        vhelper.saveStates(outState);
    }
    public void restoreStates(Bundle inState) {
        vhelper.restoreStates(inState);
    }
    public void deleteCardView(int position){
        main_array.remove(position);
        notifyItemRemoved(position);
    }
}
