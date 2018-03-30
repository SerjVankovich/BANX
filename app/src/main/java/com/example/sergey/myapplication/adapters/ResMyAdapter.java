package com.example.sergey.myapplication.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
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
import com.example.sergey.myapplication.GlobalFunctions;
import com.example.sergey.myapplication.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 24.02.2018.
 */

public class ResMyAdapter extends RecyclerView.Adapter<ResMyAdapter.ViewHolder> {
    List<DBCard> main_array;
    Context context;
    String table;
    ViewBinderHelper helper;

    public ResMyAdapter(Context context, String table) {
        this.context = context;
        this.table = table;
        this.main_array = getMainArray();
        this.helper = new ViewBinderHelper();

    }

    private List<DBCard> getMainArray() {
        List<DBCard> list = new ArrayList<>();

        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.query(table, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            int titleId = cursor.getColumnIndex(DataBaseHelper.KEY_TITLE);
            int perId = cursor.getColumnIndex(DataBaseHelper.KEY_PERCENTS);
            int sumId = cursor.getColumnIndex(DataBaseHelper.KEY_SUM_IN_RUB);
            int srokId = cursor.getColumnIndex(DataBaseHelper.KEY_SROK_IN_RUB);
            int bankId = cursor.getColumnIndex(DataBaseHelper.KEY_BANK);
            int absoluteID = cursor.getColumnIndex(DataBaseHelper.KEY_ID);
            do {
                DBCard card = new DBCard(cursor.getString(titleId), cursor.getDouble(perId), cursor.getInt(sumId), cursor.getInt(srokId), cursor.getString(bankId), null);
                card.absoluteID = cursor.getInt(absoluteID);
                list.add(card);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_view, parent, false);
        final ViewHolder vh = new ViewHolder(view);

        vh.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = vh.getAdapterPosition();
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view_dialog = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_sure, parent, false);

                Button yes = view_dialog.findViewById(R.id.yes);
                Button cancel = view_dialog.findViewById(R.id.cancel);
                builder.setView(view_dialog);

                final AlertDialog dialog = builder.create();

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteCardView(position);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(main_array.get(position).title);
        holder.percents.setText(Double.toString(main_array.get(position).perinrub) + "%");
        holder.srok.setText(Integer.toString(main_array.get(position).srokinrub));
        holder.sum.setText(Integer.toString(main_array.get(position).suminrub));
        GlobalFunctions.findImage(holder.icon, position, main_array, 120, 120);
        helper.bind(holder.cardView, main_array.get(position).toString());
        helper.setOpenOnlyOne(true);

    }

    @Override
    public int getItemCount() {
        return main_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SwipeRevealLayout cardView;
        TextView title;
        TextView percents;
        TextView srok;
        TextView sum;
        Button del;
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Title);
            percents = itemView.findViewById(R.id.percents);
            srok = itemView.findViewById(R.id.srok);
            sum = itemView.findViewById(R.id.sum);
            cardView = itemView.findViewById(R.id.card_view);
            del = itemView.findViewById(R.id.del);
            icon = itemView.findViewById(R.id.imageView);
        }
    }
    public void deleteCardView(int position){
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(table, DataBaseHelper.KEY_ID  + "='" + Integer.toString(main_array.get(position).absoluteID) + "'", null);
        main_array.remove(position);
        notifyItemRemoved(position);
    }
}
