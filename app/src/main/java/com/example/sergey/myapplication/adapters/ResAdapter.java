package com.example.sergey.myapplication.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.DataBase.DataBaseHelper;
import com.example.sergey.myapplication.GlobalFunctions;
import com.example.sergey.myapplication.MainActivity;
import com.example.sergey.myapplication.R;

import com.example.sergey.myapplication.SiteActivity;
import com.example.sergey.myapplication.comparaters.PercentVkladsCompatrator;
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
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

import info.hoang8f.widget.FButton;


public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ViewHolder> {
    List<DBCard> main_array;
    Context context;
    String table;
    private final ViewBinderHelper vhelper;
    String whatUsl;

    public ResAdapter(Context context, List<DBCard> main_array, String table, String whatUsl) {
        this.context = context;
        this.main_array = main_array;
        this.table = table;
        vhelper = new ViewBinderHelper();
        this.whatUsl = whatUsl;



    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout root;
        SwipeRevealLayout cardView;
        TextView title;
        TextView percents;
        TextView srok;
        TextView sum;
        Button add;
        ImageView icon;
        TextView sumPok;
        TextView srokPok;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            title = itemView.findViewById(R.id.Title);
            percents = itemView.findViewById(R.id.percents);
            srok = itemView.findViewById(R.id.srok);
            sum = itemView.findViewById(R.id.sum);
            cardView = itemView.findViewById(R.id.card_view);
            add = itemView.findViewById(R.id.button_add);
            icon = itemView.findViewById(R.id.imageView);
            sumPok = itemView.findViewById(R.id.textView8);
            srokPok = itemView.findViewById(R.id.textView9);


        }
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        final ViewHolder vh = new ViewHolder(view);

        vh.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = vh.getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view_dialog = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog, parent, false);

                TextView operation = view_dialog.findViewById(R.id.vklad);
                TextView perText = view_dialog.findViewById(R.id.num_percents);

                ImageView icon = view_dialog.findViewById(R.id.icon);
                Button cancell = view_dialog.findViewById(R.id.cancel_action);
                Button add = view_dialog.findViewById(R.id.add);

                final EditText sum = view_dialog.findViewById(R.id.sum);
                final EditText srok = view_dialog.findViewById(R.id.srok);
                if (whatUsl.equals("vklads")){
                    sum.setHint("Сумма (мин.: " + GlobalFunctions.formatToComfort(String.valueOf(main_array.get(position).suminrub)) + ")");
                    srok.setHint("Срок (мин.: " + GlobalFunctions.formatToComfort(String.valueOf(main_array.get(position).srokinrub)) + ")");
                } else {
                    sum.setHint("Сумма (макс.: " + GlobalFunctions.formatToComfort(String.valueOf(main_array.get(position).suminrub)) + ")");
                    srok.setHint("Срок (макс.: " + GlobalFunctions.formatToComfort(String.valueOf(main_array.get(position).srokinrub)) + ")");
                }


                final String vkladName = main_array.get(position).title;
                final String bankName = main_array.get(position).bank;
                final Double percents = main_array.get(position).perinrub;
                operation.setText(vkladName);
                perText.setText(percents.toString() + "%");
                GlobalFunctions.findImage(icon, position, main_array, 100, 100);

                builder.setView(view_dialog);
                final AlertDialog dialog = builder.create();

                cancell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            int summa =  Integer.parseInt(sum.getText().toString());
                            int srokInt = Integer.parseInt(srok.getText().toString());
                            if (whatUsl.equals("vklads")){
                                if (summa >= main_array.get(position).suminrub){
                                    if (srokInt >= main_array.get(position).srokinrub){
                                        writeToDB(vkladName, percents, summa, srokInt, bankName, dialog);
                                    } else{
                                        Toast.makeText(context, "Извините, указан срок, меньше минимального", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(context, "Извините, указана сумма, меньше максимальной", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (summa <= main_array.get(position).suminrub){
                                    if (srokInt <= main_array.get(position).srokinrub){
                                        writeToDB(vkladName, percents, summa, srokInt, bankName, dialog);
                                    } else{
                                        Toast.makeText(context, "Извините, указан срок, больше минимального", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(context, "Извините, указана сумма, больше максимальной", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, "Данные неверны", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
        });
        vh.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = vh.getAdapterPosition();
                Intent intent = new Intent(context, SiteActivity.class);
                intent.putExtra("link", main_array.get(position).link);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(main_array.get(position).title);
        holder.percents.setText(Double.toString(main_array.get(position).perinrub) + "%");
        holder.srok.setText(GlobalFunctions.formatToComfort(Integer.toString(main_array.get(position).srokinrub)) + " " + getText(main_array.get(position).srokinrub, "дни"));
        holder.sum.setText(GlobalFunctions.formatToComfort(Integer.toString(main_array.get(position).suminrub)) + " " + getText(main_array.get(position).suminrub, "рубли"));
        if (!whatUsl.equals("vklads")){
            holder.srokPok.setText("Срок (макс.):");
            holder.sumPok.setText("Сумма (макс.):");
        }
        GlobalFunctions.findImage(holder.icon, position, main_array, 120, 120);

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
    public String getText(int num, String text) {
        int number = num % 10;
        if (text == "дни"){
            if (number == 1) {
                return "день";
            } else if (number <= 9 && number >= 5 || number == 0 ){
                return "дней";
            } else if (number <= 4 && number >= 2 ) {
                return "дня";
            }
        } else if (text == "рубли") {
            if (number == 1) {
                return "рубль";
            } else if (number <= 9 && number >= 5 || number == 0 ){
                return "рублей";
            } else if (number <= 4 && number >= 2 ) {
                return "рубля";
            }
        }
        return text;
    }
    public void writeToDB(String vkladName, Double percents, int summa, int srokInt, String bankName, AlertDialog dialog){
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.KEY_TITLE, vkladName);
        contentValues.put(DataBaseHelper.KEY_PERCENTS, percents);
        contentValues.put(DataBaseHelper.KEY_SUM_IN_RUB, summa);
        contentValues.put(DataBaseHelper.KEY_SROK_IN_RUB, srokInt);
        contentValues.put(DataBaseHelper.KEY_BANK, bankName);

        db.insert(table, null, contentValues);
        db.close();
        dialog.dismiss();
    }

}
