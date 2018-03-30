package com.example.sergey.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergey.myapplication.DataBase.Cotirovka;
import com.example.sergey.myapplication.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sergey on 28.03.2018.
 */

public class ResCotAdapter extends RecyclerView.Adapter<ResCotAdapter.Holder> {
    List<Cotirovka> main_array;

    public ResCotAdapter(List<Cotirovka> main_array) {
        this.main_array = main_array;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cotir, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(main_array.get(position).getName());
        holder.kol.setText("за " + String.valueOf(main_array.get(position).getNominal()) + " ед.");
        holder.valute.setText(main_array.get(position).getCharCode());
        holder.rub.setText(String.valueOf(main_array.get(position).getValue()) + " руб.");
        double raznost = main_array.get(position).getValue() - main_array.get(position).getPrivious();
        if (raznost >= 0) {
            holder.razn.setBackgroundResource(R.drawable.shape_text_green);
            holder.razn.setText(String.valueOf(raznost).substring(0, 4));
        } else {
            holder.razn.setBackgroundResource(R.drawable.shape_text);
            holder.razn.setText(String.valueOf(raznost).substring(0, 4));
        }

    }

    @Override
    public int getItemCount() {
        return main_array.size();
    }

    static class Holder extends RecyclerView.ViewHolder{
        TextView name;
        TextView valute;
        TextView rub;
        TextView kol;
        TextView razn;

        public Holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            valute = itemView.findViewById(R.id.valute);
            rub = itemView.findViewById(R.id.rub);
            kol = itemView.findViewById(R.id.kol);
            razn = itemView.findViewById(R.id.raznost);
        }
    }
}
